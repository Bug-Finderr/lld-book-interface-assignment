public class Book {
    private final String name;
    private final String author;
    private final float userRating;
    private final int reviews;
    private final float price;
    private final String genre;

    public Book(String name, String author, float userRating, int reviews, float price, String genre) {
        this.name = name;
        this.author = author;
        this.userRating = userRating;
        this.reviews = reviews;
        this.price = price;
        this.genre = genre;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public float getUserRating() {
        return userRating;
    }

    public int getReviews() {
        return reviews;
    }

    public float getPrice() {
        return price;
    }

    public String getGenre() {
        return genre;
    }

    public void printDetails() {
        System.out.println("Book Name: " + name);
        System.out.println("Author: " + author);
        System.out.println("User Rating: " + userRating);
        System.out.println("Reviews: " + reviews);
        System.out.println("Price: " + price);
        System.out.println("Genre: " + genre + "\n");
    }
}