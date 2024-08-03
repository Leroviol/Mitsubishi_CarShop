package users;

public class User{
    private String firstName, secondName, phoneNumber;
    private String login;
    private String password;
    private UserType userType;
    public enum UserType{
        ADMINISTRATOR,
        MANAGER,
        CUSTOMER
    }

    public User(String firstName, String secondName,
                String login, String password,
                String phoneNumber, UserType userType) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.login = login;
        this.password = password;
        this.userType = userType;
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public UserType getUserType() {
        return userType;
    }
}
