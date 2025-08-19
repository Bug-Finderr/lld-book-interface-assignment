import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class DatasetReader {
    private DatasetReader() {
    }

    public static List<Book> readDataset(String csvPath) throws IOException {
        List<String> lines = Files.readAllLines(Path.of(csvPath));
        List<Book> books = new ArrayList<>();
        if (lines.size() <= 1)
            return books;

        for (int i = 1; i < lines.size(); ++i) {
            String line = lines.get(i).trim();
            if (line.isEmpty())
                continue;

            List<String> cols = parseCsvLine(line);
            if (!isValid(cols))
                continue;

            try {
                Book book = createBook(cols);
                books.add(book);
            } catch (Exception e) {
                System.err.println("Error parsing line " + (i + 1) + ": " + e.getMessage());
            }
        }
        return books;
    }

    private static boolean isValid(List<String> cols) {
        if (cols == null || cols.size() < 7)
            return false;
        for (int idx = 0; idx < cols.size(); ++idx) {
            String val = cols.get(idx);
            if (val == null || val.isBlank())
                return false;
        }
        return true;
    }

    private static Book createBook(List<String> cols) {
        String name = cols.get(0).trim();
        String author = cols.get(1).trim();
        float userRating = Float.parseFloat(cols.get(2).trim());
        int reviews = Integer.parseInt(cols.get(3).trim());
        float price = Float.parseFloat(cols.get(4).trim());
        String genre = cols.get(6).trim();

        return new Book(name, author, userRating, reviews, price, genre);
    }

    private static List<String> parseCsvLine(String line) {
        List<String> cols = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        boolean inQuotes = false;

        for (char c : line.toCharArray()) {
            if (c == '"') {
                inQuotes = !inQuotes;
            } else if (c == ',' && !inQuotes) {
                cols.add(sb.toString().trim());
                sb.setLength(0);
            } else {
                sb.append(c);
            }
        }

        cols.add(sb.toString().trim());
        return cols;
    }
}