package zti.lab04;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import zti.db.Database;
import zti.model.Person;

import java.util.List;

@Path("/jdbc/person")
@Produces(MediaType.APPLICATION_JSON)
public class JDBCResource {

    @GET
    public List<Person> getAll() {
        return new Database().getAllPersons();
    }

    @GET
    @Path("{id}")
    public Person getById(@PathParam("id") int id) {
        return new Database().getPersonById(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String add(Person person) {
        new Database().addPerson(person);
        return "add record";
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String update(Person person) {
        new Database().updatePerson(person);
        return "update record";
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String delete(@PathParam("id") int id) {
        new Database().deletePerson(id);
        return "delete record";
    }
}
