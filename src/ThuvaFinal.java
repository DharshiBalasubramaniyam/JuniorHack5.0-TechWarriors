import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class ThuvaFinal{
    public static void removeBook() throws Exception {
        int isbnNum;
        String confirm;
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the ISBN: ");
        isbnNum = scan.nextInt();
        System.out.println();
        System.out.println();
        System.out.println("Are you sure you want to remove this book?(Y/N)");
        confirm = scan.nextLine();
        if (confirm.equalsIgnoreCase(",y")) {
            Connection conn = DbConnection.getConnection();
            String query = "delete from book where isbn = " + isbnNum;

            Statement st = conn.createStatement();
            int rows = st.executeUpdate(query);

            System.out.println("Number of rows affected: " + rows);
            conn.close();
        }
    }

    public static void listAllBooks() throws Exception {
        String query = "select * from book";
        Connection conn = DbConnection.getConnection();

        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(query);

        while(rs.next()){
            System.out.println(rs.getInt(2)+"-"+rs.getString(3)+"-"+rs.getString(4)+"-"+rs.getString(5));
        }
        st.close();
    }
}

