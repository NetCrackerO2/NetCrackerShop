package beans;

import clientInfo.AuthorizationInterceptor;
import clientInfo.ClientInfo;
import clientInfo.NeedAuthorization;
import models.ClientEntity;

import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.interceptor.Interceptors;

@Named
@Stateless
@Interceptors(AuthorizationInterceptor.class)
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

    @NeedAuthorization
    public ClientInfo getClientInfo() {
        return clientInfo;
    }

    public ClientEntity addClient(String name, String defaultAddres) {
        if (getByLogin(name) != null) {
            throw new EJBException("Имя уже используется.");
        }
        ClientEntity client = new ClientEntity();

        if (name.equals("")) {
            throw new EJBException("Недопустимое имя клиента.");
        }
        client.setName(name);

        if (defaultAddres.equals("")) {
            throw new EJBException("Недопустимый адрес.");
        }
        client.setDefaultAddress(defaultAddres);

        return persist(client);
    }

    public void editClient(int id, String name, String defaultAddress) {
        ClientEntity entity = get(id);

        if (entity == null) {
            return;
        }

        entity.setName(name);
        entity.setDefaultAddress(defaultAddress);
    }

    @Override
    public boolean canRemove(ClientEntity entity) {
        // TODO Auto-generated method stub
        return clientInfo.getId() != entity.getId();
    }
}
