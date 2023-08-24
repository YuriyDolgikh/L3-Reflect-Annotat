package biz.itehnika;

public class User {
    @Save private String firstName;
    @Save private String lastName;
    @Save private int age;
    private String password;

    public User() {
    }

    public User(String firstName, String lastName, int age, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", password='" + password + '\'' +
                '}';
    }
}
