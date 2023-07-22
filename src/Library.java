import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

class InvalidInputException extends Exception{
    public InvalidInputException(String message) {
        super(message);
    }
}

public class Library {

    static Scanner scan = new Scanner(System.in);

    public int validateChoice(int start, int end) {

        int choice = -1;
        boolean isValid = false;

        while (!isValid) {
            try {
                choice = scan.nextInt();
                if (choice < start || choice > end) {
                    throw new InvalidInputException("Enter a valid choice between " + start + " and " + end + ": ");
                }
                isValid = true;
            } catch (InvalidInputException e) {
                System.out.print(e.getMessage());
            } catch (InputMismatchException e) {
                System.out.print("Enter an integer: ");
            } finally {
                scan.nextLine();
            }
        }

        return choice;

    }

    public boolean confirm() {
        System.out.print("\nConfirm (y/n) : ");
        String input = scan.next();

        while (!input.equalsIgnoreCase("y") && !input.equalsIgnoreCase("n")) {
            System.out.print("Enter a valid choice: ");
            input = scan.next();
        }
        return input.equalsIgnoreCase("y");
    }

    public void mainMenu() {

        System.out.println("\n1. Add a new book\n" +
                "2. Update an existing book\n" +
                "3. Remove an existing book\n" +
                "4. Lend a book\n" +
                "5. Return a book\n" +
                "6. List all available books\n" +
                "7. Exit");

        System.out.print("Please select an option: ");
    }

    public void addNewBook() throws SQLException, ClassNotFoundException {

        Connection connection = DBConnection.getConnection();
        Statement statement = connection.createStatement();

        System.out.println("\n----Add a new book----");


        System.out.print("Enter the ISBN: ");
        String isbn;

        while (true) {
            isbn = scan.next();
            while(isbn.length()!=13) {
                System.out.print("ISBN should have 13 characters\nEnter a valid ISBN: ");
                isbn = scan.next();
            }

            String query1 = "select * from book where isbn ='" + isbn + "'";
//            System.out.println(query1);
            ResultSet result = statement.executeQuery(query1);
            if (!result.next()) {
                break;
            } else {
                System.out.print("This ISBN already exists.\nEnter a valid ISBN: ");
            }
        }

        scan.nextLine();
        System.out.print("Enter the Title: ");
        String title = scan.nextLine();

        System.out.print("Enter the Category: ");
        String category = scan.next();
        scan.nextLine();

        System.out.print("Enter the Author Name: ");
        String authorName = scan.nextLine();


        String query2 = "insert into book values (" + isbn + ", '" + title + "', '" + category + "', '" + authorName + "', 'Available')";
//        System.out.println(query2);

        int rows = statement.executeUpdate(query2);
        if (rows > 0) {
            System.out.println("\nNew Book added successfully.\n");
        } else {
            System.out.println("error");
        }

    }


    public void lendBook() throws SQLException {
        System.out.println("\n----Lend a Book----");
        System.out.print("Enter the ISBN: ");
        String isbn = scan.next();

        Connection connection = DBConnection.getConnection();
        Statement statement = connection.createStatement();

        String query1 = "select * from book where isbn = " + isbn;

        ResultSet result = statement.executeQuery(query1);

        if (result.next()) {
            String query2 = "update book set isAvailable = 'Not Available' where isbn = '" + isbn + "'";
            int rows = statement.executeUpdate(query2);
            if (rows > 0) {
                System.out.println("You have lend the book successfully!\n");
            } else {
                System.out.println("error");
            }
        } else {
            System.out.println("Sorry, There is no book in the library with the given isbn.");
        }

        connection.close();
        statement.close();
    }


    public void returnBook() throws SQLException {
        System.out.println("\n----Return a Book----");
        System.out.print("Enter the ISBN: ");
        String isbn = scan.next();

        Connection connection = DBConnection.getConnection();
        Statement statement = connection.createStatement();

        String query1 = "select * from book where isbn = " + isbn;

        ResultSet result = statement.executeQuery(query1);

        if (result.next()) {
            String query2 = "update book set isAvailable = 'Available' where isbn = '" + isbn + "'";
            int rows = statement.executeUpdate(query2);
            if (rows > 0) {
                System.out.println("You have return the book successfully!\n");
            } else {
                System.out.println("error");
            }
        } else {
            System.out.println("Sorry, There is no book in the library with the given isbn.");
        }

        connection.close();
        statement.close();

    }


    public void removeBook() throws Exception {

        System.out.println("\n----Remove books from library----\n");
        String isbnNum;

        System.out.print("Enter the ISBN: ");
        isbnNum = scan.next();

        Connection connection = DBConnection.getConnection();
        String query1 = "select * from Book where isbn= '" + isbnNum + "'";
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(query1);

        if (result.next()) {
            System.out.println("\nAre you sure you want to remove this book?(Y/N)");
            if (confirm()) {
                Connection conn = DBConnection.getConnection();
                String query2 = "delete from book where isbn = '" + isbnNum + "'";

                Statement st = conn.createStatement();
                int rows = st.executeUpdate(query2);

                System.out.println("Book removed successfully.");
                conn.close();
            }
        }
        else {
        System.out.println("Sorry, There is no book in the library with the given isbn.");
        }

    }

    public void listAllBooks() throws Exception {

        System.out.println("\n----Available Books in the library----\n");
        String query = "select * from book where isAvailable = 'Available'";
        Connection connection = DBConnection.getConnection();

        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(query);

        while (rs.next()) {
            System.out.println(rs.getString(1) + " - " + rs.getString(2) + " - " + rs.getString(3) + " - " + rs.getString(4));
        }
        st.close();
        connection.close();
    }

    public void updateBook() throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n----Update a book----\n");
        System.out.print("Enter the ISBN: ");
        String userISBN = sc.next();

        Connection connection = DBConnection.getConnection();
        String query = "select * from Book where isbn= '" + userISBN + "'";
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(query);

        if (result.next()) {
            System.out.print("Enter the new Title: ");
            String title = scan.nextLine();

            System.out.print("Enter the new Category: ");
            String category = scan.next();
            scan.nextLine();

            System.out.print("Enter the new Author Name: ");
            String authorName = scan.nextLine();


            String query2 = "update book set title = '" + title + "', category = '" + category + "', author = '" + authorName + "' where isbn = '" + userISBN + "'";
//            System.out.println(query2);

            int rows = statement.executeUpdate(query2);
            if (rows > 0) {
                System.out.println("\nBook updated successfully.\n");
            } else {
                System.out.println("error");
            }
        } else {
            System.out.println("Sorry, There is no book in the library with the given isbn.");
        }


    }
}