public class Book {
    private long   isbn;
    private String title;
    private String category;
    private String author;

    public long getIsbn() {
        return isbn;
    }

    public void setIsbn(long isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Book(long isbn, String title, String category, String author) {
        this.isbn = isbn;
        this.title = title;
        this.category = category;
        this.author = author;
    }


}