package web.test;

import web.test.Connection.JDBCConnecion;
import web.test.Personel.Personel;

import java.sql.*;

public class JDBC {
    public static void main(String[] args) {
        JDBCConnecion jdbcConnecion = new JDBCConnecion();

        try (Connection conn = jdbcConnecion.connect()) {
            if (conn != null) {

                //Create
                String createTableSql = "CREATE TABLE IF NOT EXISTS public.personel (" +
                        "id BIGINT PRIMARY KEY, " +
                        "firstName VARCHAR(50) NOT NULL, " +
                        "lastName VARCHAR(50) NOT NULL, " +
                        "salary INT NOT NULL);";

                try (PreparedStatement preparedStatement = conn.prepareStatement(createTableSql)) {
                    preparedStatement.execute();
                    System.out.println("Table created successfully.");
                } catch (SQLException e) {
                    System.out.println("Hata: " + e.getMessage());
                }

                Personel personel = new Personel(2L, "Mehmet", "Mehmetoglu", 250);

                // Insert
                String insertSQL = "INSERT INTO public.personel (id, firstName, lastName, salary) VALUES (?, ?, ?, ?)";
                try (PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
                    pstmt.setLong(1, personel.getId());
                    pstmt.setString(2, personel.getFirstName()); // Changed to use personel object's first name
                    pstmt.setString(3, personel.getLastName());  // Changed to use personel object's last name
                    pstmt.setInt(4, personel.getSalary());       // Changed to use personel object's salary
                    int affectedRows = pstmt.executeUpdate();
                    if (affectedRows > 0) {
                        System.out.println("Insert başarılı!");
                    } else {
                        System.out.println("Insert başarısız");
                    }
                } catch (SQLException e) {
                    System.out.println("Hata:" + e.getMessage());
                }


                // Select
                String selectSQL = "SELECT * FROM public.personel";
                try (PreparedStatement pstmt = conn.prepareStatement(selectSQL)) {
                    ResultSet rs = pstmt.executeQuery();
                    while (rs.next()) {
                        long id = rs.getLong("id");
                        String firstName = rs.getString("firstName");
                        String lastName = rs.getString("lastName");
                        int salary = rs.getInt("salary");

                        System.out.println("ID: " + id + ", First Name: " + firstName
                                + ", Last Name: " + lastName + ", Salary: " + salary);
                    }
                } catch (SQLException e) {
                    System.out.println("Hata: " + e.getMessage());
                }


                // Delete
                String deleteSQL = "DELETE FROM public.personel WHERE firstName = ?";
                try (PreparedStatement pstmt = conn.prepareStatement(deleteSQL)) {
                    pstmt.setString(1, "Mehmet");
                    int affectedRows = pstmt.executeUpdate();
                    if (affectedRows > 0) {
                        System.out.println("Delete başarılı");
                    } else {
                        System.out.println("Delete başarısız");
                    }
                } catch (SQLException e) {
                    System.out.println("Hata: " + e.getMessage());
                }
            } else {
                System.out.println("Veritabanına baglanılamadı");
            }
        } catch (SQLException e) {
            System.out.println("Hata: " + e.getMessage());
        }




    }
}
