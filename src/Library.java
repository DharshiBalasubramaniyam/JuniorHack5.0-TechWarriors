import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;


public class Library {

    Scanner sc=new Scanner(System.in);
    Connection con;

       public void addNewBook() throws SQLException, ClassNotFoundException {

           con = DBconnecter.DBconnection();


           System.out.println("Add a new book");



           System.out.println("Enter the ISBN");
           long ISBN= sc.nextLong();


           System.out.println("Enter the Title");
           String title=sc.next();

           System.out.println("Enter the Category");
           String category=sc.next();

           System.out.println("Enter the Author Name");
           String authorName=sc.next();



           PreparedStatement ps = con.prepareStatement("insert into book values(?,?,?,?,?)");

           ps.setLong(2, ISBN);
           ps.setString(3, title);
           ps.setString(4, category);
           ps.setString(5, authorName);
           ps.setString(6,"available");

           int count = ps.executeUpdate();
           while (count!=0) {
               System.out.println("inserted successfully");
           }





       }
}
