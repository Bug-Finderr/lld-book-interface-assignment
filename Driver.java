import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        String path = "data.csv";
        List<Book> books;

        try {
            books = DatasetReader.readDataset(path);
        } catch (Exception e) {
            System.err.println("Failed to read dataset: " + e.getMessage());
            return;
        }

        BookRepo utils = new BookRepoImpl();
        Scanner sc = new Scanner(System.in);

        printMenu();
        while (true) {
            System.out.print("\nEnter choice: ");
            String input = sc.nextLine().trim();
            switch (input) {
                case "1":
                    books.forEach(Book::printDetails);
                    break;
                case "2":
                    utils.getAllAuthors(books).forEach(System.out::println);
                    break;
                case "3":
                    System.out.print("Author name: ");
                    String author2 = sc.nextLine().trim();
                    System.out.println("Count (includes duplicates if any): "
                            + utils.getBooksIncludingDuplicatesCountByAuthor(books, author2));
                    System.out.println("Count (unique books only): " + utils.getBooksCountByAuthor(books, author2));
                    break;
                case "4":
                    System.out.print("Author name: ");
                    String author4 = sc.nextLine().trim();
                    utils.getBooksByAuthor(books, author4).forEach(System.out::println);
                    break;
                case "5":
                    System.out.print("Rating: ");
                    try {
                        float rating = Float.parseFloat(sc.nextLine().trim());
                        utils.getBooksByRating(books, rating).forEach(Book::printDetails);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid rating.");
                    }
                    break;
                case "6":
                    System.out.print("Author name: ");
                    String author6 = sc.nextLine().trim();
                    Map<String, Float> map = utils.getBookAndPricesByAuthor(books, author6);
                    map.forEach((name, price) -> System.out.println(name + " : " + price));
                    break;
                case "9":
                    printMenu();
                    break;
                case "0":
                    System.out.println("Exiting.");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("""
                \nMenu:
                1: print all books
                2: get all authors
                3: get books count by author
                4: get books by author
                5: get books by rating
                6: get book and prices by author

                9: print the above menu
                0: exit""");
    }
}