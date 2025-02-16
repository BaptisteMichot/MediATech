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
    private PreparedStatement selectBookTitleById;
    private PreparedStatement selectBookIdByTitle;
    private PreparedStatement updateBookAvailability;
    private PreparedStatement updateBookState;

    public BookDAO(DBConnection dbConnection) {
        try {
            this.connection = dbConnection.getConnection();
            this.selectAvailableBooks = connection.prepareStatement("SELECT * FROM book WHERE available = true");
            this.selectBookTitleById = connection.prepareStatement("SELECT title FROM book WHERE id = ?");
            this.selectBookIdByTitle = connection.prepareStatement("SELECT id FROM book WHERE title = ?");
            this.updateBookAvailability = connection.prepareStatement("UPDATE book SET available = ? WHERE id = ?");
            this.updateBookState = connection.prepareStatement("UPDATE book SET state = ? WHERE id = ?");
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


    public String getBookTitleById(int id) {
        String title = "";
        try {            
            this.selectBookTitleById.setInt(1, id);
            ResultSet set = this.selectBookTitleById.executeQuery();
            if (set.next()) {
                title = set.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return title;
    }

    public int getBookIdByTitle(String title) {
        int id = -1;
        try {            
            this.selectBookIdByTitle.setString(1, title);
            ResultSet set = this.selectBookIdByTitle.executeQuery();
            if (set.next()) {
                id = set.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public boolean updateBookAvailability(int id, boolean availability) {
        try {
            this.updateBookAvailability.setBoolean(1, availability);
            this.updateBookAvailability.setInt(2, id);
            this.updateBookAvailability.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean updateBookState(int id, String state) {
        try {
            this.updateBookState.setString(1, state);
            this.updateBookState.setInt(2, id);
            this.updateBookState.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    public boolean close() {
        boolean returnValue = true;

        if (this.connection != null) {
            try {
                this.connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
                returnValue = false;
            }
        }
        if (this.selectAvailableBooks != null) {
            try {
                this.selectAvailableBooks.close();
            } catch (SQLException e) {
                e.printStackTrace();
                returnValue = false;
            }
        }
        if (this.selectBookTitleById != null) {
            try {
                this.selectBookTitleById.close();
            } catch (SQLException e) {
                e.printStackTrace();
                returnValue = false;
            }
        }
        if (this.selectBookIdByTitle != null) {
            try {
                this.selectBookIdByTitle.close();
            } catch (SQLException e) {
                e.printStackTrace();
                returnValue = false;
            }
        }
        if (this.updateBookAvailability != null) {
            try {
                this.updateBookAvailability.close();
            } catch (SQLException e) {
                e.printStackTrace();
                returnValue = false;
            }
        }
        if (this.updateBookState != null) {
            try {
                this.updateBookState.close();
            } catch (SQLException e) {
                e.printStackTrace();
                returnValue = false;
            }
        }
        return returnValue;
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
