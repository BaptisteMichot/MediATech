package mediatech.Model.BL;

public class User {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String role;

    public User(String lastName, String firstName, String email, String password, String role) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.password = password;
        this.role = role;
    }
    
    public String getLastName() { 
        return lastName; 
    }
    public void setLastName(String lastName) { 
        this.lastName = lastName; 
    }

    public String getFirstName() {
        return firstName; 
    }
    public void setFirstName(String firstName) { 
        this.firstName = firstName; 
    }

    public String getEmail() { 
        return email; 
    }
    public void setEmail(String email) { 
        this.email = email; 
    }

    public String getPassword() { 
        return password; 
    }
    public void setPassword(String password) { 
        this.password = password; 
    }

    public String getRole() { 
        return role; 
    }
    public void setRole(String role) { 
        this.role = role; 
    }



    public boolean isValidName(String name) {
        String regex = "^[A-Za-zÀ-ÖØ-öø-ÿ]{2,50}([ '-][A-Za-zÀ-ÖØ-öø-ÿ]{2,50})*$"; //only letters, 2 to 50 characters, allow apostrophes, spaces and hyphens between words
        return name != null && name.matches(regex);
    }
    
    public boolean isValidEmail(String email) {
        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"; //email format
        return email != null && email.matches(regex);
    }

    public boolean isValidPassword(String password) {
        String regex = "^(?=.*[A-Za-z])(?=.*\\d).{5,}$"; //at least one letter, one number and 5 characters
        return password != null && password.matches(regex);
    }
}
