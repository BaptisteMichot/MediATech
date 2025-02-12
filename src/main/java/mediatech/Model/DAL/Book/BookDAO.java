package mediatech.Model.DAL.Book;

import mediatech.Model.BL.Book;
import mediatech.Model.DAL.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookDAO {
    private Connection connection;
    private PreparedStatement selectAvailableBooks;
    private PreparedStatement selectBookByTitle;

    public BookDAO(DBConnection dbConnection) {
        try {
            this.connection = dbConnection.getConnection();
            this.selectAvailableBooks = connection.prepareStatement("SELECT * FROM book WHERE available = true");
            this.selectBookByTitle = connection.prepareStatement("SELECT * FROM book WHERE title = ?");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    //@Override
    public ArrayList<Book> getAllAvailableBooks() {
        ArrayList<Book> listBooks = new ArrayList<Book>();
        try {
            ResultSet set = this.selectAvailableBooks.executeQuery();
            while (set.next()) {
                Book book = new Book(set.getInt(1), set.getString(2), set.getBoolean(3), set.getString(4), 
                    set.getDate(5), set.getString(6), set.getString(7), set.getString(8), set.getInt(9));
                listBooks.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listBooks;
    }


    //@Override
    public Book getBookByTitle(String title) {
        try {
            this.selectBookByTitle.setString(1, title);
            ResultSet set = this.selectBookByTitle.executeQuery();

            if (set.next()) {
                Book book = new Book(set.getInt(1), set.getString(2), set.getBoolean(3), set.getString(4), 
                    set.getDate(5), set.getString(6), set.getString(7), set.getString(8), set.getInt(9));
                return book;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(title + " not found");
        return null;
    }


    //@Override
    // public boolean create(Book book) {
    //     try {
    //         String query = "INSERT INTO book (title, author, isbn, page_count) VALUES (?, ?, ?, ?)";
    //         PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
    //         stmt.setString(1, book.getTitle());
    //         stmt.setString(2, book.getAuthor());
    //         stmt.setString(3, book.getIsbn());
    //         stmt.setInt(4, book.getPageCount());

    //         int affectedRows = stmt.executeUpdate();
    //         if (affectedRows > 0) {
    //             ResultSet generatedKeys = stmt.getGeneratedKeys();
    //             if (generatedKeys.next()) {
    //                 book.setId(generatedKeys.getInt(1));
    //             }
    //             return true;
    //         }
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //     }
    //     return false;
    // }

    
    //@Override
    // public boolean delete(int id) {
    //     try {
    //         String query = "DELETE FROM book WHERE id = ?";
    //         PreparedStatement stmt = connection.prepareStatement(query);
    //         stmt.setInt(1, id);

    //         return stmt.executeUpdate() > 0;
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //     }
    //     return false;
    // }
}
