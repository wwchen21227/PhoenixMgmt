/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.user.service;

import java.sql.SQLException;
import java.util.ArrayList;
import sg.edu.nus.iss.phoenix.core.dao.DAOFactoryImpl;
import sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException;
import sg.edu.nus.iss.phoenix.authenticate.dao.UserDao;
import sg.edu.nus.iss.phoenix.authenticate.entity.User;

/**
 *
 * @author wengweichen
 */
public class UserService {
    DAOFactoryImpl factory;
    UserDao userDao;
    
    public UserService() {
        super();
     
        factory = new DAOFactoryImpl();
        userDao = factory.getUserDAO();
    }
    
    public ArrayList<User> searchPrograms(User user) {
        ArrayList<User> list = new ArrayList<User>();
        try {
            list = (ArrayList<User>) userDao.searchMatching(user);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }
  
    public User findUser(String name) {
        User currentUser = new User();
        currentUser.setName(name);
        try {
            currentUser = ((ArrayList<User>) userDao.searchMatching(currentUser)).get(0);
            return currentUser;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return currentUser;
    }
    
    public ArrayList<User> findAllUser() {
        ArrayList<User> currentList = new ArrayList<User>();
        try {
                currentList = (ArrayList<User>) userDao.loadAll();
        } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        }
        return currentList;
    }
    
    public void processCreate(User user) {
        try {
            userDao.create(user);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public void processModify(User user) {
        try {
                userDao.save(user);
        } catch (NotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        }
    }
    
    public void processDelete(String id) {
        try {
            User user = new User(id);
            userDao.delete(user);
        } catch (NotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
