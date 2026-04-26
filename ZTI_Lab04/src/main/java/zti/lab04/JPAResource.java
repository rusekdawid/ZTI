package zti.lab04;

import jakarta.persistence.*;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import zti.model2.Person;

import java.util.List;

@Path("/jpa/person")
@Produces(MediaType.APPLICATION_JSON)
public class JPAResource {

    private static final EntityManagerFactory emf =
        Persistence.createEntityManagerFactory("PU_Postgresql");

    @GET
    public List<Person> getAll() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createNamedQuery("findAll", Person.class).getResultList();
        } finally {
            em.close();
        }
    }

    @GET
    @Path("{id}")
    public Person getById(@PathParam("id") int id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Person.class, id);
        } finally {
            em.close();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String add(Person person) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(person);
            em.flush();
            tx.commit();
            return "add record";
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            return "error: " + e.getMessage();
        } finally {
            em.close();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String update(Person person) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(person);
            em.flush();
            tx.commit();
            return "update record";
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            return "error: " + e.getMessage();
        } finally {
            em.close();
        }
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String delete(@PathParam("id") int id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Person p = em.find(Person.class, id);
            if (p != null) {
                em.remove(p);
                em.flush();
            }
            tx.commit();
            return "delete record";
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            return "error: " + e.getMessage();
        } finally {
            em.close();
        }
    }
}
