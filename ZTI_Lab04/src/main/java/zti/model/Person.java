package zti.model;

public class Person {

    private Integer id;
    private String fname;
    private String lname;
    private String email;
    private String city;
    private String tel;
    private String type;

    public Person() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getFname() { return fname; }
    public void setFname(String fname) { this.fname = fname; }

    public String getLname() { return lname; }
    public void setLname(String lname) { this.lname = lname; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getTel() { return tel; }
    public void setTel(String tel) { this.tel = tel; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
}
