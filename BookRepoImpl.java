import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class BookRepoImpl implements BookRepo {
    public long getBooksIncludingDuplicatesCountByAuthor(List<Book> books, String author) {
        if (author == null || author.isBlank())
            return 0;

        String authorTrimmed = author.trim();

        return books.stream()
                .filter(book -> authorTrimmed.equalsIgnoreCase(book.getAuthor()))
                .count();
    }

    public long getBooksCountByAuthor(List<Book> books, String author) {
        if (author == null || author.isBlank())
            return 0;

        String authorTrimmed = author.trim();

        return books.stream()
                .filter(book -> authorTrimmed.equalsIgnoreCase(book.getAuthor()))
                .map(Book::getName)
                .filter(name -> name != null && !name.isBlank())
                .map(String::trim)
                .distinct()
                .count();
    }

    public List<String> getAllAuthors(List<Book> books) {
        return books.stream()
                .map(Book::getAuthor)
                .filter(author -> author != null && !author.isBlank())
                .map(String::trim)
                .distinct()
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public List<String> getBooksByAuthor(List<Book> books, String author) {
        if (author == null || author.isBlank())
            return List.of();

        String authorTrimmed = author.trim();

        return books.stream()
                .filter(book -> authorTrimmed.equalsIgnoreCase(book.getAuthor()))
                .map(Book::getName)
                .filter(name -> name != null && !name.isBlank())
                .map(String::trim)
                .distinct()
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public List<Book> getBooksByRating(List<Book> books, float rating) {
        if (rating < 0)
            return List.of();

        Set<String> seenTitles = new HashSet<>();

        return books.stream()
                .filter(book -> book.getUserRating() == rating)
                .filter(book -> seenTitles.add(book.getName().trim()))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public Map<String, Float> getBookAndPricesByAuthor(List<Book> books, String author) {
        if (author == null || author.isBlank())
            return Map.of();

        String authorTrimmed = author.trim();

        return books.stream()
                .filter(book -> authorTrimmed.equalsIgnoreCase(book.getAuthor()))
                .collect(Collectors.toMap(Book::getName, Book::getPrice, (existing, replacement) -> existing));
    }
}
