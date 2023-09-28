package main.domain;

public class User {
    private Integer id;
    private String name;
    private String email;
    private String password;
    private Integer age;
    private String hometown;

    public User(Integer id, String name, String email, String password, Integer age, String hometown) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.age = age;
        this.hometown = hometown;
    }

    public User(String name, String email, String password, Integer age, String hometown) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.age = age;
        this.hometown = hometown;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getHometown() {
        return hometown;
    }

    public void setHometown(String hometown) {
        this.hometown = hometown;
    }
}
