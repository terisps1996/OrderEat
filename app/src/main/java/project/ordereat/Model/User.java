package project.ordereat.Model;

/**
 * Created by Shep on 10/18/2017.
 */

public class User {
    private String Name;
    private String Password;
    private String phone;
    private String Type;

    public User() {
    }

    public User(String name, String password, String type) {
        Name = name;
        Password = password;
        Type = type;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getPhone() { return phone; }

    public void setPhone(String phone) { this.phone = phone; }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        this.Type = type;
    }
}
