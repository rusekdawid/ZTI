package zti.model;

import jakarta.ejb.Stateless;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;

@Stateless
public class PersonRepository implements Serializable {

    @PersistenceContext
    EntityManager entityManager;

    public List<Person> getAllPersons() {
        return entityManager.createQuery("select p from person p", Person.class).getResultList();
    }

    public List<Person> searchPersons(String query) {
        String q = "%" + query.toLowerCase() + "%";
        return entityManager.createQuery(
            "select p from person p where lower(p.lname) like :q or lower(p.email) like :q", Person.class)
            .setParameter("q", q)
            .getResultList();
    }

    public void savePerson(Person entity) {
        entityManager.persist(entity);
        entityManager.flush();
    }

    public void updatePerson(Person entity) {
        entityManager.merge(entity);
    }

    public void deletePerson(Integer id) {
        Person p = entityManager.find(Person.class, id);
        entityManager.remove(p);
        entityManager.flush();
    }

    public Person findPerson(Integer id) {
        return entityManager.find(Person.class, id);
    }
}
