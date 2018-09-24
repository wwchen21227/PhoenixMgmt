/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.authenticate.dao.impl;

import java.util.List;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import sg.edu.nus.iss.phoenix.authenticate.entity.*;
import sg.edu.nus.iss.phoenix.authenticate.dao.PresentorDao;
import sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException;

/**
 *
 * @author sourcepirate
 */
public class PresentorDaoImpl implements PresentorDao {

    private Connection connection;

    private static final String QUERY_ALL = "select * from user where role like '%presenter%'";
    private static final String QUERY_ID = "select * from user where id = ?";
    private static final String QUERY_SEARCH = "select * from user where role like '%presenter%' and name like ";

    public PresentorDaoImpl() {
        super();
        connection = openConnection();
    }

    @Override
    public User createValueObject() {
        return new User(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<User> getAll() throws SQLException {
       PreparedStatement stmt = this.connection.prepareStatement(QUERY_ALL);
       return this.listUsers(stmt);
    }

    @Override
    public User selectPresentor(String id) throws NotFoundException, SQLException {
        //To change body of generated methods, choose Tools | Templates.
        PreparedStatement stmt = this.connection.prepareStatement(QUERY_ID);
        stmt.setString(0, id);
        User user;
        try(ResultSet result = stmt.executeQuery()){
           result.next();
           user = this.getUserFromResult(result);
        } finally {
            stmt.close();
        }
        return user;
    }

    @Override
    public List<User> searchMatching(String prefix) throws NotFoundException, SQLException {
        //To change body of generated methods, choose Tools | Templates.
        
        String qs = QUERY_SEARCH + " '" + prefix + "%'";
        PreparedStatement stmt = this.connection.prepareStatement(qs);
        return this.listUsers(stmt);
    }

    private Connection openConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
        }

        try {
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/phoenix", "phoenix",
                    "password");
        } catch (SQLException e) {
        }
        return conn;
    }
    
    private List<Role> parseRoles(String roleString) {
        List<Role> roles= new ArrayList<Role>();
        String[] roleNames = roleString.trim().split(":");
        for(String roleName: roleNames) {
            roles.add(new Role(roleName));
        }
        return roles;
    }
    
    protected User getUserFromResult(ResultSet result) throws SQLException {
        User user = this.createValueObject();
        user.setId(result.getString("id"));
        user.setName(result.getString("name"));
        user.setPassword(result.getString("password"));
        ArrayList<Role> roles= (ArrayList<Role>) this.parseRoles(result.getString("role"));
        user.setRoles(roles);
        return user;
    }
    
    protected List<User> listUsers(PreparedStatement stmt) throws SQLException{
        List<User> users = new ArrayList<User>();
        try(ResultSet result = stmt.executeQuery()){
            while(result.next()) {
                User user = this.getUserFromResult(result);
                users.add(user);
            }
        } finally {
            stmt.close();
        }
        return users;
    } 
}
