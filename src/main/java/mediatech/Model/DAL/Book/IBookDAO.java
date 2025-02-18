package mediatech.Model.DAL.Book;

import java.util.ArrayList;
import java.util.Date;

import mediatech.Model.BL.Book;

public interface IBookDAO {

    public ArrayList<Book> getAllAvailableBooks();
    
    public String getBookTitleById(int id);
    
    public int getBookIdByTitle(String title);
    
    public boolean updateBookAvailability(int id, boolean availability);
    
    public boolean updateBookState(int id, String state);
    
    public boolean insertBook(String title, String state, Date publicationDate, String isbn, 
        String author, String publisher, int pageCount);
    
    public boolean deleteBook(String title);

    public boolean close();    
}
