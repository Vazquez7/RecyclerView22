package es.agilcentros.pmdm.recyclerview22;

public class User {

    private String name;
    private String gender;
    private String street;
    private String location;
    private String email;
    private String phone;
    private int pictureRes;


    public User(String name, String gender, String street, String location, String email, String phone, int pictureRes) {
        this.name = name;
        this.gender = gender;
        this.street = street;
        this.location = location;
        this.email = email;
        this.phone = phone;
        this.pictureRes = pictureRes;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getStreet() {
        return street;
    }

    public String getLocation() {
        return location;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public int getPictureRes() {
        return pictureRes;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
