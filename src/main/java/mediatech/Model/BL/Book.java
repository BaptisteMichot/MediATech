package mediatech.Model.BL;

import java.util.Date;

public class Book extends MediaObject {

    private String isbn;
    private String author;
    private String publisher;
    private int pageCount;

    public Book(int id, String title, boolean available, String state, Date publicationDate, String isbn, String author, String publisher, int pageCount) {
        super(id, title, available, state, publicationDate);

        this.isbn = isbn;
        this.author = author;
        this.publisher = publisher;
        this.pageCount = pageCount;
    }

    public Book() {}

    public String getIsbn() {
        return isbn;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }   

    public String getPublisher() {
        return publisher;
    }    
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }   
     
    public int getPageCount() {
        return pageCount;
    }
    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }
    
}
