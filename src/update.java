import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;




    public class Update {
        public void updateAnExistingBook(){
            Scanner sc= new Scanner(System.in);
            System.out.println("Update an existing book");
            System.out.println("enter the ISBN that need to update:");
            long userISBN=sc.nextLong();

            System.out.println("Update the book:");
            connnection=DBconnecter.DBconnection();
            String query="select * from Book where isbn="+userISBN;
            try{
                Statement st=connection.createStatement();
                ResultSet rs=st.executeQuery(query);

                while(rs.next()) {
                    System.out.println("Enter the new title:" + "(" + rs.getString(3));
                    String newTitle = sc.next();
                    System.out.println("Enter the new Category:" + "(" + rs.getString(4));
                    String newCategory = sc.next();
                    System.out.println("Enter the new Author:" + "(" + rs.getString(5));
                    String newAuthor = sc.next();


                    String updateQuery="update Book set title=?,category=?,author=? where isbn=?";
                    Statement ps=connection.createStatement();
                    ResultSet resultSet= ps.executeUpdate(updateQuery);
                    ps.setString(1,newTitle);
                    ps.setString(2,newCategory);
                    ps.setString(3,newAuthor);
                    ps.setLong(4,userISBN);

                }
            catch(Exception e) {
                    System.out.println(e);
                }
            }



        }
    }
}

}
