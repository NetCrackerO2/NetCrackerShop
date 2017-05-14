package beans;

import clientInfo.*;
import models.ClientEntity;

import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.interceptor.Interceptors;

@Named
@Stateless
@Interceptors({AuthorizationInterceptor.class, AdminInterceptor.class})
public class ClientBean extends GenericBean<ClientEntity> {

    @Inject
    ClientInfo clientInfo;

    @Override
    protected Class<ClientEntity> getEntityClass() {
        return ClientEntity.class;
    }

    public ClientEntity getByLogin(String login) {
        try {
            return em.createQuery("SELECT e from ClientEntity e where e.name=:token", getEntityClass())
                     .setParameter("token", login).getSingleResult();
            // TODO: нормальная обработка исключения
        } catch (Exception e) {
            return null;
        }
    }

    public boolean login(String login) {
        if (clientInfo.isLoggedIn()) {
            return true;
        }

        ClientEntity client = getByLogin(login);
        if (client == null) {
            return false;
        }

        clientInfo.init(client);
        return true;
    }

    public ClientInfo getClientInfo() {
        return clientInfo;
    }

    private ClientEntity add(String name, String defaultAddress, Boolean isAdmin) {
        if (getByLogin(name) != null) {
            throw new EJBException("Клиент с таким именем уже существует: " + name);
        }
        ClientEntity client = new ClientEntity();

        if (name.equals("")) {
            throw new EJBException("Недопустимое пустое имя клиента.");
        }
        client.setName(name);

        if (defaultAddress.equals("")) {
            throw new EJBException("Недопустимый пустой адрес.");
        }
        client.setDefaultAddress(defaultAddress);

        client.setAdmin(isAdmin);

        return persist(client);
    }

    @NeedAdmin
    public ClientEntity addClient(String name, String defaultAddress, Boolean isAdmin) {
        return add(name, defaultAddress, isAdmin);
    }

    public ClientEntity registerClient(String name, String defaultAddress) {
        return add(name, defaultAddress, false);
    }

    private void edit(int id, String name, String defaultAddress) {
        ClientEntity entity = get(id);

        if (entity == null) {
            return;
        }

        entity.setName(name);
        entity.setDefaultAddress(defaultAddress);
    }

    @NeedAdmin
    public void editClient(int id, String name, String defaultAddress) {
        edit(id, name, defaultAddress);
    }

    @NeedAuthorization
    public void editClientInfo(String name, String defaultAddress) {
        edit(clientInfo.getId(), name, defaultAddress);
    }

    @Override
    @NeedAdmin
    public void remove(int id) {
        ClientEntity client = get(id);

        if (client == null || !canRemove(client)) {
            throw new EJBException("Этот пользователь не может быть удалён.");
        }

        super.remove(id);
    }

    @Override
    @NeedAdmin
    public boolean canRemove(ClientEntity entity) {
        boolean flag = true;

        //flag = flag && (clientInfo.getId() != entity.getId());
        flag = flag && (!entity.getAdmin());

        return flag;
    }
}
