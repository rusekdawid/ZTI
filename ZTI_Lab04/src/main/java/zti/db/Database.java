package zti.db;

import zti.model.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {

    private Connection conn;
    private PreparedStatement prestmt;
    private Statement stmt;

    public Database() {
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Person> getAllPersons() {
        List<Person> list = new ArrayList<>();
        try {
            stmt = conn.createStatement();
            ResultSet rset = stmt.executeQuery("SELECT * FROM public.person ORDER BY lname");
            while (rset.next()) {
                Person p = new Person();
                p.setId(rset.getInt("id"));
                p.setFname(rset.getString("fname"));
                p.setLname(rset.getString("lname"));
                p.setEmail(rset.getString("email"));
                p.setCity(rset.getString("city"));
                p.setTel(rset.getString("tel"));
                p.setType(rset.getString("type"));
                list.add(p);
            }
            rset.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            closeAll();
        }
        return list;
    }

    public Person getPersonById(int id) {
        Person p = null;
        try {
            prestmt = conn.prepareStatement("SELECT * FROM public.person WHERE id = ?");
            prestmt.setInt(1, id);
            ResultSet rset = prestmt.executeQuery();
            if (rset.next()) {
                p = new Person();
                p.setId(rset.getInt("id"));
                p.setFname(rset.getString("fname"));
                p.setLname(rset.getString("lname"));
                p.setEmail(rset.getString("email"));
                p.setCity(rset.getString("city"));
                p.setTel(rset.getString("tel"));
                p.setType(rset.getString("type"));
            }
            rset.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            closeAll();
        }
        return p;
    }

    public void addPerson(Person person) {
        try {
            prestmt = conn.prepareStatement(
                "INSERT INTO public.person (fname, lname, email, city, tel, type) VALUES (?,?,?,?,?,?)");
            prestmt.setString(1, person.getFname());
            prestmt.setString(2, person.getLname());
            prestmt.setString(3, person.getEmail());
            prestmt.setString(4, person.getCity());
            prestmt.setString(5, person.getTel());
            prestmt.setString(6, person.getType() != null ? person.getType() : "guest");
            prestmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            closeAll();
        }
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
            prestmt.setString(6, person.getType() != null ? person.getType() : "guest");
            prestmt.setInt(7, person.getId());
            prestmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            closeAll();
        }
    }

    public void deletePerson(int id) {
        try {
            prestmt = conn.prepareStatement("DELETE FROM public.person WHERE id=?");
            prestmt.setInt(1, id);
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
