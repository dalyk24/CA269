/**
 * Book.java - Assignment 01
 * @author Killian Daly
 */

enum BookMedium {
    PhysicalBook, EBook, AudioBook
}

enum BookGenre {
    Fiction, NonFiction
}

enum BookRating {
    Rating5, Rating4, Rating3, Rating2, Rating1
}

class Book {
    // fields are private
    private String author = "";
    private String title = "";
    private int pubDate = 0;
    private int readDate = 0;
    private BookMedium medium = null;
    private BookGenre genre = null;
    private BookRating rating = null;


    /**
     * Default constructor takes title, author, and genre
     */
    public Book(String title, String author, BookGenre genre) {
        setTitle(title);
        setAuthor(author);
        setGenre(genre);
    }

    /**
     * Additional constructor also takes published date
     */
    public Book(String title, String author, BookGenre genre, int pubDate) {
        setTitle(title);
        setAuthor(author);
        setGenre(genre);
        setPublishedDate(pubDate);
    }

    /**
     * Additional constructor also takes published date,
     * read date, read medium, and rating
     */
    public Book(String title, String author, BookGenre genre, int pubDate, int readDate, BookMedium medium, 
    BookRating rating) {
        setTitle(title);
        setAuthor(author);
        setGenre(genre);
        setPublishedDate(pubDate);
        setReadDate(readDate);
        setMedium(medium);
        setRating(rating);
    }

    // getters
    public String getAuthor() { // <-- getter
        return author;
    }

    public String getTitle() { // <-- getter
        return title;
    }

    public int getPublishedDate() { // <-- getter
        return pubDate;
    }

    public int getReadDate() { // <-- getter
        return readDate;
    }

    public BookMedium getMedium() { // <-- getter
        return medium;
    }

    public BookGenre getGenre() { // <-- getter
        return genre;
    }

    public BookRating getRating() { // <-- getter
        return rating;
    }

    public int getNumericRating(BookRating rating) {
        int rated = 0;

        switch(rating) {
            case Rating5:
              rated = 5;
              break;
            case Rating4:
              rated = 4;
              break;
            case Rating3:
              rated = 3;
              break;
            case Rating2:
              rated = 2;
              break;
            case Rating1:
              rated = 1;
              break;
          }

        return rated;
    }

    // setters
    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPublishedDate(int pubDate) {
        this.pubDate = pubDate;
    }

    public void setReadDate(int readDate) {
        this.readDate = readDate;
    }

    public void setMedium(BookMedium medium) {
        this.medium = medium;
    }

    public void setGenre(BookGenre genre) {
        this.genre = genre;
    }

    public void setRating(BookRating rating) {
        this.rating = rating;
    }

    /**
     * toString implementation
     * Depends on available information
     * For title, an author, and a genre - Title by Author
     * If publication date is present - Title by Author (Year of Publication)
     * If read date, read medium, and rating is present -
     * Title by Author (Year of Publication) - read in Year of Reading, rated rating/5
     */
    public String toString() {
        if(this.readDate != 0 && this.medium != null && this.rating != null) {
            return this.title + " by " + this.author + " (" + this.pubDate + ") - read in " + readDate +
            ", rated " + getNumericRating(rating) + "/5";
        }
        else if(this.pubDate != 0) {
            return this.title + " by " + this.author + " (" + this.pubDate + ")";
        }
        else if(this.author != "" && this.title != "" && this.genre != null) {
            return this.title + " by " + this.author;
        }
        else {
            return "Not enough information";
        }
    }

    public static void main(String[] args) {
        Book b1 = new Book("Children of Time", "Adrian Tchaikovsky", BookGenre.Fiction);
        System.out.println(b1);
        Book b2 = new Book("The Fifth Season", "N. K. Jemesin", BookGenre.Fiction, 2015);
        System.out.println(b2);
        Book b3 = new Book("Perdido Street Station", "China Mieville",
            BookGenre.Fiction, 2000, 2020, BookMedium.EBook, BookRating.Rating5);
        System.out.println(b3);

        System.out.println(b1.getTitle());
        System.out.println(b1.getAuthor());
        System.out.println(b1.getGenre());
        System.out.println(b2.getPublishedDate());
        System.out.println(b3.getReadDate());
        System.out.println(b3.getMedium());
        System.out.println(b3.getRating());
    }
}
