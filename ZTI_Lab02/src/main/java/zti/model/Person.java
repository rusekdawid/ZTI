package zti.model;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.component.UIParameter;
import jakarta.faces.event.ActionEvent;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@Named("personBean")
@SessionScoped
public class Person implements Serializable {

    private String lname;
    private String fname;
    private String city;
    private String email;
    private String tel;
    private String type = "student";
    private Integer id;

    public Person() {}

    public Person(String lname, String fname, String city, String email, String tel, String type, Integer id) {
        this.lname = lname;
        this.fname = fname;
        this.city = city;
        this.email = email;
        this.tel = tel;
        this.type = type;
        this.id = id;
    }

    public void setPerson(Person person) {
        this.setLname(person.getLname());
        this.setFname(person.getFname());
        this.setCity(person.getCity());
        this.setEmail(person.getEmail());
        this.setTel(person.getTel());
        this.setType(person.getType());
        this.setId(person.getId());
    }

    public Person getPerson() {
        return new Person(this.getLname(), this.getFname(), this.getCity(),
                this.getEmail(), this.getTel(), this.getType(), this.getId());
    }

    public List<String> getTypeList() {
        return Arrays.asList("student", "teacher", "admin", "guest");
    }

    public List<Person> getPeople() {
        Database baza = new Database();
        return baza.getPersonList();
    }

    public void savePerson(ActionEvent event) {
        Database baza = new Database();
        baza.createPerson(this.getPerson());
    }

    public void selectPerson(ActionEvent event) {
        Database baza = new Database();
        UIParameter component = (UIParameter) event.getComponent().findComponent("editId");
        String id = component.getValue().toString();
        this.setPerson(baza.readPerson(id));
    }

    public void updatePerson(ActionEvent event) {
        Database baza = new Database();
        baza.updatePerson(this.getPerson());
    }

    public void deletePerson(ActionEvent event) {
        Database baza = new Database();
        UIParameter component = (UIParameter) event.getComponent().findComponent("deleteId");
        String id = component.getValue().toString();
        baza.deletePerson(id);
    }

    public String getFname() { return fname; }
    public void setFname(String fname) { this.fname = fname; }
    public String getLname() { return lname; }
    public void setLname(String lname) { this.lname = lname; }
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getTel() { return tel; }
    public void setTel(String tel) { this.tel = tel; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
}
