package BusinessLayer;

import java.io.Serializable;

/**
 * The type User.
 */
public class User implements Serializable {
    private String username;
    private String password;
    private String type;
    private int id;

    /**
     * Well formed.
     *
     * @invariant username.length() <= 10
     */
    public void wellFormed(){
        assert(username.length()<=10);
    }

    /**
     * Instantiates a new User.
     *
     * @param username the username
     * @param password the password
     * @param type     the type
     * @param id       the id
     */
    public User(String username, String password, String type,int id) {

        this.username = username;
        this.password = password;
        this.type = type;
        this.id=id;
        wellFormed();
    }

    /**
     * Gets username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets username.
     *
     * @param username the username
     */
    public void setUsername(String username) {
        this.username = username;
        wellFormed();
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Sets type.
     *
     * @param type the type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }
}
