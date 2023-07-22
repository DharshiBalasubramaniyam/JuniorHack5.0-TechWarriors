import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws Exception {

        System.out.println("\n----Welcome to the library management system UOK!----\n");

        Library library = new Library();

        library.mainMenu();
        int option = library.validateChoice(1, 7);

        try {
            while (option!=7) {
                switch(option) {
                    case 1:
                        library.addNewBook();
                        break;
                    case 2:
                        library.updateBook();
                        break;
                    case 3:
                        library.removeBook();
                        break;
                    case 4:
                        library.lendBook();
                        break;
                    case 5:
                        library.returnBook();
                        break;
                    case 6:
                        library.listAllBooks();
                        break;
                }

                library.mainMenu();
                option = library.validateChoice(1, 7);
            }
        }
        catch (Exception e) {
            System.out.println("Something went wrong. Please try again later.");
        }



    }
}