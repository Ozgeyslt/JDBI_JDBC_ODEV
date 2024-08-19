package web.test;

import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import web.test.Connection.JDBCConnecion;
import web.test.Connection.JDBIConnection;
import web.test.Personel.Personel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBI {
    public static void main(String[] args) {
        JDBIConnection jdbiConnecion = new JDBIConnection();
        try (Handle handle = jdbiConnecion.connect().open()) {

            //Create
            handle.execute("CREATE TABLE IF NOT EXISTS personel ("
                    + "id BIGINT PRIMARY KEY, "
                    + "firstName VARCHAR(255) NOT NULL, "
                    + "lastName VARCHAR(255) NOT NULL, "
                    + "salary INT NOT NULL)");
            System.out.println("'personel' tablosu oluşturuldu");

            Personel personel = new Personel(2L, "Mehmet", "Mehmetoglu", 250);


            // Insert
            handle.createUpdate("INSERT INTO personel (id, firstName, lastName, salary) VALUES (:id, :firstName, :lastName, :salary)")
                    .bind("id", personel.getId())
                    .bind("firstName", personel.getFirstName())
                    .bind("lastName", personel.getLastName())
                    .bind("salary", personel.getSalary())
                    .execute();
            System.out.println("Insert başarılı!");


            // Select
            handle.createQuery("SELECT * FROM personel")
                    .map((rs, ct) -> new Personel(
                            rs.getLong("id"),
                            rs.getString("firstName"),
                            rs.getString("lastName"),
                            rs.getInt("salary")))
                    .list()
                    .forEach(pers -> System.out.println(
                            "ID: " + pers.getId() +
                                    ", First Name: " + pers.getFirstName() +
                                    ", Last Name: " + pers.getLastName() +
                                    ", Salary: " + pers.getSalary()
                    ));


            // Update
            handle.createUpdate("UPDATE personel SET lastName = :lastName, salary = :salary WHERE id = :id")
                    .bind("lastName", "yilmaz")
                    .bind("salary", 300)
                    .bind("id", 2L)
                    .execute();
            System.out.println("Update başarılı!");

            handle.createQuery("SELECT * FROM personel")
                    .map((rs, ct) -> new Personel(
                            rs.getLong("id"),
                            rs.getString("firstName"),
                            rs.getString("lastName"),
                            rs.getInt("salary")))
                    .list()
                    .forEach(pers -> System.out.println(
                            "ID: " + pers.getId() +
                                    ", First Name: " + pers.getFirstName() +
                                    ", Last Name: " + pers.getLastName() +
                                    ", Salary: " + pers.getSalary()
                    ));


            //Delete
            handle.createUpdate("DELETE FROM personel WHERE firstName = :firstName")
                    .bind("firstName", "Mehmet")
                    .execute();
            System.out.println("Delete başarılı");

        } catch (Exception e) {
            System.out.println("Hata " + e.getMessage());
        }
    }
}
