package beans;


import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;


public abstract class GenericBean<T> {
    @PersistenceContext
    protected EntityManager em;

    protected abstract Class<T> getEntityClass();

    public abstract boolean canRemove(T entity);


    protected T persist(T entity) {
        em.persist(entity);
        em.flush();
        em.refresh(entity);
        return entity;
    }

    public List<T> getAll() {
        try {
            return em.createQuery("SELECT e from " + getEntityClass().getSimpleName() + " e", getEntityClass())
                     .getResultList();
        } catch (EntityNotFoundException e) {
            return null;
        }
    }

    public T get(int id) {
        try {
            return em.find(getEntityClass(), id);
        } catch (EntityNotFoundException e) {
            return null;
        }
    }


    protected void remove(int id) {
        T entity = get(id);

        if (entity != null) {
            em.remove(entity);
        }
    }

    public List<T> filterBy(List<String[]> parameters) {
        return filterBy(parameters, null);
    }

    public List<T> filterBy(List<String[]> parameters, List<String[]> minMaxParameters) {
        StringBuilder query = new StringBuilder().append("select e from ");
        query.append(getEntityClass().getCanonicalName());
        query.append(" e");

        int parametersCount;
        int minMaxParametersCount;

        if (parameters == null) {
            parametersCount = 0;
        } else if (!parameters.stream().allMatch(item -> item.length == 2)) {
            throw new IllegalArgumentException();
        } else {
            parametersCount = parameters.size();
        }

        if (minMaxParameters == null) {
            minMaxParametersCount = 0;
        } else if (!minMaxParameters.stream().allMatch(item -> item.length == 3)) {
            throw new IllegalArgumentException();
        } else {
            minMaxParametersCount = minMaxParameters.size();
        }

        if (parametersCount + minMaxParametersCount > 0) {
            query.append(" where");
        }

        //TODO: Это дохрена небезопасно, но кого это волнует?
        for (int i = 0; i < parametersCount; i++) {
            String[] parameter = parameters.get(i);
            parameter[1] = parameter[1].replace("\'", "");

            query.append(" lower(e.");
            query.append(parameter[0]);
            query.append(") like '%");
            query.append(parameter[1].toLowerCase());
            query.append("%'");

            if (i != parametersCount - 1) {
                query.append(" and");
            }
        }

        for (int i = 0; i < minMaxParametersCount; i++) {
            String[] minMaxParameter = minMaxParameters.get(i);
            minMaxParameter[1] = minMaxParameter[1].replace("\'", "");
            minMaxParameter[2] = minMaxParameter[2].replace("\'", "");

            if (parametersCount != 0 || i != 0) {
                query.append(" and");
            }
            query.append(" (e.");
            query.append(minMaxParameter[0]);
            query.append(" between '");
            query.append(minMaxParameter[1]);
            query.append("' and '");
            query.append(minMaxParameter[2]);
            query.append("')");
        }

        List<T> resultList = new ArrayList<>();
        try {
            resultList = em.createQuery(query.toString(), getEntityClass()).getResultList();
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }

        return resultList;
    }
}
