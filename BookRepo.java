import java.util.List;
import java.util.Map;

public interface BookRepo {
    long getBooksIncludingDuplicatesCountByAuthor(List<Book> books, String author);

    long getBooksCountByAuthor(List<Book> books, String author);

    List<String> getAllAuthors(List<Book> books);

    List<String> getBooksByAuthor(List<Book> books, String author);

    List<Book> getBooksByRating(List<Book> books, float rating);

    Map<String, Float> getBookAndPricesByAuthor(List<Book> books, String author);
}
