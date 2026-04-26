package zti.model;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Named("databaseBean")
@RequestScoped
public class Database {

    private Person person;
    private Connection conn = null;
    private PreparedStatement prestmt = null;
    private Statement stmt = null;
    private List<Person> list = new ArrayList<>();

    public Database() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        String url = "jdbc:postgresql://pascal.fis.agh.edu.pl:5432/u2rusin";
        String username = "u2rusin";
        String password = "2rusin";
        try {
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Person> getPersonList() {
        try {
            stmt = conn.createStatement();
            ResultSet rset = stmt.executeQuery("SELECT * FROM public.person ORDER BY lname");
            while (rset.next()) {
                person = new Person();
                person.setFname(rset.getString("fname"));
                person.setLname(rset.getString("lname"));
                person.setEmail(rset.getString("email"));
                person.setTel(rset.getString("tel"));
                person.setCity(rset.getString("city"));
                person.setType(rset.getString("type"));
                person.setId(rset.getInt("id"));
                list.add(person);
            }
            rset.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            closeAll();
        }
        return list;
    }

    public void createPerson(Person person) {
        try {
            prestmt = conn.prepareStatement(
                "INSERT INTO public.person (fname, lname, email, city, tel, type) VALUES (?,?,?,?,?,?)");
            prestmt.setString(1, person.getFname());
            prestmt.setString(2, person.getLname());
            prestmt.setString(3, person.getEmail());
            prestmt.setString(4, person.getCity());
            prestmt.setString(5, person.getTel());
            prestmt.setString(6, person.getType());
            prestmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            closeAll();
        }
    }

    public Person readPerson(String id) {
        try {
            Integer ident = Integer.parseInt(id);
            prestmt = conn.prepareStatement("SELECT * FROM public.person WHERE id = ?");
            prestmt.setInt(1, ident);
            ResultSet rset = prestmt.executeQuery();
            person = new Person();
            while (rset.next()) {
                person.setFname(rset.getString("fname"));
                person.setLname(rset.getString("lname"));
                person.setEmail(rset.getString("email"));
                person.setTel(rset.getString("tel"));
                person.setCity(rset.getString("city"));
                person.setType(rset.getString("type"));
                person.setId(rset.getInt("id"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            closeAll();
        }
        return person;
    }

    public void updatePerson(Person person) {
        try {
            prestmt = conn.prepareStatement(
                "UPDATE public.person SET fname=?,lname=?,email=?,city=?,tel=?,type=? WHERE id=?");
            prestmt.setString(1, person.getFname());
            prestmt.setString(2, person.getLname());
            prestmt.setString(3, person.getEmail());
            prestmt.setString(4, person.getCity());
            prestmt.setString(5, person.getTel());
            prestmt.setString(6, person.getType());
            prestmt.setInt(7, person.getId());
            prestmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            closeAll();
        }
    }

    public void deletePerson(String id) {
        try {
            Integer ident = Integer.parseInt(id);
            prestmt = conn.prepareStatement("DELETE FROM public.person WHERE id=?");
            prestmt.setInt(1, ident);
            prestmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            closeAll();
        }
    }

    private void closeAll() {
        try {
            if (prestmt != null) prestmt.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
