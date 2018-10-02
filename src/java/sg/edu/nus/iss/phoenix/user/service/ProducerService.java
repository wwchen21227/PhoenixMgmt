/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.user.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sg.edu.nus.iss.phoenix.core.dao.DAOFactoryImpl;
import sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException;
import sg.edu.nus.iss.phoenix.authenticate.dao.PresentorDao;
import sg.edu.nus.iss.phoenix.authenticate.dao.ProducerDao;
import sg.edu.nus.iss.phoenix.authenticate.entity.User;
import sg.edu.nus.iss.phoenix.authenticate.entity.Role;

/**
 *
 * @author sourcepirate
 */
public class ProducerService {
    private DAOFactoryImpl factory;
    private ProducerDao producerDao;
    
    public ProducerService(){
        super();
        factory = new DAOFactoryImpl();
        producerDao = factory.getProducerDao();
    }
    
    public ProducerService(ProducerDao dao){
       producerDao = dao;
    }
    
    public User getProducer(String id) throws NotFoundException, SQLException{
        return producerDao.selectProducer(id);
    }
    
    public List<User> searchProducer(String prefix) throws NotFoundException, SQLException {
        return producerDao.searchMatching(prefix);
    }
    
    public List<User> getAll() throws NotFoundException, SQLException {
        return producerDao.getAll();
    }
}
