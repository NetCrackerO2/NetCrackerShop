package beans;


import clientInfo.ClientInfo;
import models.ClientEntity;

import javax.inject.Inject;
import javax.inject.Named;


@Named
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

    public void login(String login) {
        if (clientInfo.isLoggedIn())
            return;

        clientInfo.setId(getByLogin(login).getId());
    }
}
