package beans;


import clientInfo.AuthorizationInterceptor;
import clientInfo.ClientInfo;
import clientInfo.NeedAuthorization;
import models.ClientEntity;

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
                    .setParameter("token", login)
                    .getSingleResult();
            //TODO: нормальная обработка исключения
        } catch (Exception e) {
            return null;
        }
    }

    public boolean login(String login) {
        if (clientInfo.isLoggedIn())
            return true;

        ClientEntity client = getByLogin(login);
        if (client == null)
            return false;

        clientInfo.init(client);
        return true;
    }

    @NeedAuthorization
    public ClientInfo getClientInfo() {
        return clientInfo;
    }

    public void addClient(int id,String name,String defaultAddress){
        em.createNativeQuery("Insert into public.clients (id,name,default_address) VALUES(:id,:name,:defaultAddress)")
                .setParameter("id",id)
                .setParameter("name",name)
                .setParameter("defaultAddress",defaultAddress)
                .executeUpdate();
    }
}
