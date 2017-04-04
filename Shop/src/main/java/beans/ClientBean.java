package beans;


import clientInfo.ClientInfo;
import models.ClientEntity;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;


@Named
@Stateless
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
                    .setParameter("token", login)
                    .getSingleResult();
            //TODO: нормальная обработка исключения
        } catch (Exception e) {
            return null;
        }
    }

    public ClientInfo getUserInfo(int clientId) {
        ClientEntity client = get(clientId);
        if (client == null) {
            return null;
        }

        return new ClientInfo(client.getId());
    }

    public boolean login(String login) {
        if (clientInfo.isLoggedIn())
            return true;

        ClientEntity client = getByLogin(login);
        if (client == null)
            return false;

        clientInfo.setId(client.getId());
        return true;
    }
}
