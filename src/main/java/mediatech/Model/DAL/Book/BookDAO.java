package mediatech.Model.DAL.Book;

import mediatech.Model.BL.Book;
import mediatech.Model.DAL.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class BookDAO implements IBookDAO {
    private Connection connection;
    private PreparedStatement selectAvailableBooks;
    private PreparedStatement selectBookTitleById;
    private PreparedStatement selectBookIdByTitle;
    private PreparedStatement updateBookAvailability;
    private PreparedStatement updateBookState;
    private PreparedStatement insertBook;
    private PreparedStatement deleteBook;
    private PreparedStatement createTable;

    public BookDAO(DBConnection dbConnection) {
        try {
            this.connection = dbConnection.getConnection();
            this.createTable = connection.prepareStatement("CREATE TABLE IF NOT EXISTS book(id SERIAL PRIMARY KEY, title VARCHAR(200) NOT NULL, "
                + "available BOOLEAN NOT NULL, state VARCHAR(50) NOT NULL, publicationdate DATE NOT NULL, isbn VARCHAR(20) UNIQUE NOT NULL, "
                + "author VARCHAR(100) NOT NULL, publisher VARCHAR(100) NOT NULL, pagecount INT NOT NULL);");
            try {
                this.createTable.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            this.selectAvailableBooks = connection.prepareStatement("SELECT * FROM book WHERE available = true");
            this.selectBookTitleById = connection.prepareStatement("SELECT title FROM book WHERE id = ?");
            this.selectBookIdByTitle = connection.prepareStatement("SELECT id FROM book WHERE title = ?");
            this.updateBookAvailability = connection.prepareStatement("UPDATE book SET available = ? WHERE id = ?");
            this.updateBookState = connection.prepareStatement("UPDATE book SET state = ? WHERE id = ?");
            this.insertBook = connection.prepareStatement("INSERT INTO book (title, available, state, publicationdate, isbn, " +
                "author, publisher, pagecount) VALUES (?, ?, ?, ?, ?, ?, ?, ? )");
            this.deleteBook = connection.prepareStatement("DELETE FROM book WHERE title = ?");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
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


    @Override
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


    @Override
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


    @Override
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


    @Override
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


    @Override
    public boolean insertBook(String title, String state, Date publicationDate, String isbn, 
        String author, String publisher, int pageCount) {

        try {
            this.insertBook.setString(1, title);
            this.insertBook.setBoolean(2, true);
            this.insertBook.setString(3, state);
            this.insertBook.setDate(4, new java.sql.Date(publicationDate.getTime()));
            this.insertBook.setString(5, isbn);
            this.insertBook.setString(6, author);
            this.insertBook.setString(7, publisher);
            this.insertBook.setInt(8, pageCount);
            this.insertBook.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    @Override
    public boolean deleteBook(String title) {
        try {
            this.deleteBook.setString(1, title);
            this.deleteBook.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    @Override
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
        if (this.insertBook != null) {
            try {
                this.insertBook.close();
            } catch (SQLException e) {
                e.printStackTrace();
                returnValue = false;
            }
        }
        if (this.deleteBook != null) {
            try {
                this.deleteBook.close();
            } catch (SQLException e) {
                e.printStackTrace();
                returnValue = false;
            }
        }
        return returnValue;
    }
}
