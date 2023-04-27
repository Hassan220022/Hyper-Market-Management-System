package Person;

import java.util.ArrayList;
import java.util.HashMap;

public class User {
    private static int id = 0;
    private String name;
    private String username;
    private String password;
    private int age;
    private boolean gender;
    protected static ArrayList<User> arraylistUser = new ArrayList<>();
    protected static HashMap<String, Integer> userMap = new HashMap<>();

    public User(String name, String username, String password, int age, boolean gender) {
        id++;
        this.name = name;
        this.username = username;
        this.password = password;
        this.age = age;
        this.gender = gender;
        arraylistUser.add(this);
        userMap.put(username, id);
    }

    public static int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getAge() {
        return age;
    }

    public boolean isGender() {
        return gender;
    }

    public static ArrayList<User> getArraylistUser() {
        return arraylistUser;
    }

    public static HashMap<String, Integer> getUserMap() {
        return userMap;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }
}
