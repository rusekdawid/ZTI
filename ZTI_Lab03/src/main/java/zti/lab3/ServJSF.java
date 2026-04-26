package zti.lab3;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import zti.model.Person;
import zti.model.PersonRepository;

import java.io.Serializable;
import java.util.List;

@Named("servJPABean")
@SessionScoped
public class ServJSF implements Serializable {

    private Person editPerson;
    private Person viewPerson;
    private Person addPerson = new Person();
    private String searchQuery = "";

    @Inject
    PersonRepository personRepository;

    public List<Person> getPeople() {
        return personRepository.getAllPersons();
    }

    public List<Person> getSearchResults() {
        if (searchQuery == null || searchQuery.trim().isEmpty()) {
            return personRepository.getAllPersons();
        }
        return personRepository.searchPersons(searchQuery.trim());
    }

    public String delPerson(Integer id) {
        personRepository.deletePerson(id);
        return "allRecPost";
    }

    public String selPerson(Person entity) {
        viewPerson = copy(entity);
        return "viewRecPost";
    }

    public String updPerson(Person entity) {
        editPerson = copy(entity);
        return "updRecPost";
    }

    public String updatePerson() {
        personRepository.updatePerson(editPerson);
        editPerson = new Person();
        return "allRecPost";
    }

    public String savePerson() {
        personRepository.savePerson(addPerson);
        addPerson = new Person();
        return "allRecPost";
    }

    public String search() {
        return "searchRecPost";
    }

    private Person copy(Person entity) {
        Person p = new Person();
        p.setId(entity.getId());
        p.setFname(entity.getFname());
        p.setLname(entity.getLname());
        p.setCity(entity.getCity());
        p.setEmail(entity.getEmail());
        p.setTel(entity.getTel());
        return p;
    }

    public Person getEditPerson() { return editPerson; }
    public Person getAddPerson() { return addPerson; }
    public Person getViewPerson() { return viewPerson; }
    public String getSearchQuery() { return searchQuery; }
    public void setSearchQuery(String searchQuery) { this.searchQuery = searchQuery; }
}
