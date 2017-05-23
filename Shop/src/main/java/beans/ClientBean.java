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
    private static final int NAME_MAX_LENGTH = 16;

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

    public void updateClientInfo() {
        if (clientInfo.isLoggedIn()) {
            clientInfo.init(get(clientInfo.getId()));
        }
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
        checkName(name);
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

    private void edit(int id, String name, String defaultAddress, Boolean isAdmin) {
        ClientEntity entity = get(id);

        if (entity == null) {
            return;
        }
        ClientEntity duplicate;
        if ((duplicate = getByLogin(name)) != null && duplicate.getId() != id) {
            throw new EJBException("Клиент с таким именем уже существует: " + name);
        }
        if (entity.getAdmin() && !isAdmin) {
            throw new EJBException("Понижение прав администратора невозможно.");
        }
        checkName(name);

        entity.setName(name);
        entity.setDefaultAddress(defaultAddress);
        entity.setAdmin(isAdmin);
    }

    @NeedAdmin
    public void editClient(int id, String name, String defaultAddress, boolean isAdmin) {
        edit(id, name, defaultAddress, isAdmin);
    }

    @NeedAuthorization
    public void editClientInfo(String name, String defaultAddress) {
        edit(clientInfo.getId(), name, defaultAddress, get(clientInfo.getId()).getAdmin());
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

    private void checkName(String name) {
        if (name.length() > NAME_MAX_LENGTH) {
            throw new EJBException("Имя должно быть не длиннее 16 символов.");
        }
    }
}
