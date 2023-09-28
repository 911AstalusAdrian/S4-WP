package main.model;

import main.domain.User;

import java.sql.*;
import java.util.HashMap;

public class DatabaseManager {
    private Statement stmt = null;
    private Connection con = null;

    public DatabaseManager() {
        this.connect();
    }

    public void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/wplabdb", "root", "");
            stmt = con.createStatement();
        } catch (Exception e) {
            System.out.println("eroare la connect:" + e.getMessage());
            e.printStackTrace();
        }
    }

    public void searchByName(HashMap<Integer, User> users, String name) {
        if (name == null || name.length() <= 0) {
            return;
        }
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM usermanagement WHERE name LIKE '%" + name + "%';");
            while (rs.next()) {
                User user = new User(
                        rs.getInt("userID"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getInt("age"),
                        rs.getString("hometown")
                );
                users.put(user.getId(), user);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void searchByEmail(HashMap<Integer, User> users, String email) {
        if (email == null || email.length() <= 0) {
            return;
        }
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM usermanagement WHERE email LIKE '%" + email + "%';");
            while (rs.next()) {
                User user = new User(
                        rs.getInt("userID"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getInt("age"),
                        rs.getString("hometown")
                );
                users.put(user.getId(), user);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void searchByAge(HashMap<Integer, User> users, String ageAsStr) {
        if (ageAsStr == null || ageAsStr.length() <= 0 || Integer.parseInt(ageAsStr) <= 0) {
            return;
        }
        Integer age = Integer.parseInt(ageAsStr);
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM usermanagement WHERE age=" + age + ";");
            while (rs.next()) {
                User user = new User(
                        rs.getInt("userID"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getInt("age"),
                        rs.getString("hometown")
                );
                users.put(user.getId(), user);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void searchByHometown(HashMap<Integer, User> users, String hometown) {
        if (hometown == null || hometown.length() <= 0) {
            return;
        }
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM usermanagement WHERE hometown LIKE '%" + hometown + "%';");
            while (rs.next()) {
                User user = new User(
                        rs.getInt("userID"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getInt("age"),
                        rs.getString("hometown")
                );
                users.put(user.getId(), user);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean updateName(Integer id, String name) {
        if (name == null || name.length() <= 0) {
            return false;
        }
        try {
            PreparedStatement updateStmt = con.prepareStatement("UPDATE usermanagement SET name=? WHERE userID=?;");
            updateStmt.setString(1, name);
            updateStmt.setInt(2, id);
            return updateStmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateEmail(Integer id, String email) {
        if (email == null || email.length() <= 0) {
            return false;
        }
        try {
            PreparedStatement updateStmt = con.prepareStatement("UPDATE usermanagement SET email=? WHERE userID=?;");
            updateStmt.setString(1, email);
            updateStmt.setInt(2, id);
            return updateStmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updatePassword(Integer id, String password, String confirmPassword) {
        if (password == null || password.length() <= 0 || confirmPassword == null || confirmPassword.length() <= 0 || !password.equals(confirmPassword)) {
            return false;
        }
        try {
            PreparedStatement updateStmt = con.prepareStatement("UPDATE usermanagement SET password=? WHERE userID=?;");
            updateStmt.setString(1, password);
            updateStmt.setInt(2, id);
            return updateStmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateAge(Integer id, String ageAsStr) {
        if (ageAsStr == null || ageAsStr.length() <= 0 || Integer.parseInt(ageAsStr) <= 0) {
            return false;
        }
        Integer age = Integer.parseInt(ageAsStr);
        try {
            PreparedStatement updateStmt = con.prepareStatement("UPDATE usermanagement SET age=? WHERE userID=?;");
            updateStmt.setInt(1, age);
            updateStmt.setInt(2, id);
            return updateStmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateHometown(Integer id, String hometown) {
        if (hometown == null || hometown.length() <= 0) {
            return false;
        }
        try {
            PreparedStatement updateStmt = con.prepareStatement("UPDATE usermanagement SET hometown=? WHERE userID=?;");
            updateStmt.setString(1, hometown);
            updateStmt.setInt(2, id);
            return updateStmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean emailExists(String email) {
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM usermanagement WHERE email='" + email + "';");
            if (rs.next()) {
                return true;
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public User login(String email, String password) {
        User user = null;
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM usermanagement WHERE email='" + email + "' and password='" + password + "'");
            if (rs.next()) {
                user = new User(
                        rs.getInt("userID"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getInt("age"),
                        rs.getString("hometown")
                );
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public User signupUser(User user) {
        try {
            PreparedStatement signupStmt = con.prepareStatement("INSERT INTO usermanagement (email, name, password, age, hometown) VALUES (?, ?, ?, ?, ?)");
            signupStmt.setString(1, user.getEmail());
            signupStmt.setString(2, user.getName());
            signupStmt.setString(3, user.getPassword());
            signupStmt.setInt(4, user.getAge());
            signupStmt.setString(5, user.getHometown());
            int result = signupStmt.executeUpdate();
            if (result > 0) {
                user.setId(this.login(user.getEmail(), user.getPassword()).getId());
            } else {
                System.out.println("fail :(");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}