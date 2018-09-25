package sg.edu.nus.iss.phoenix.authenticate.dao;

import java.sql.SQLException;
import java.util.List;

import sg.edu.nus.iss.phoenix.authenticate.entity.User;
import sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException;

/**
 *
 * @author sourcepirate
 */
public interface ProducerDao {
    
    /**
     * method is used by any object to create a new producer value
     * instance.
     * @return
     */
    public abstract User createValueObject();
    
    /**
     * method is used by any object to list all producer instances
     * @return
     */
    public abstract List<User> getAll() throws SQLException;

    /**
     *
     * @param id
     * @return
     * @throws NotFoundException
     */
    public abstract User selectProducer(String id) throws NotFoundException, SQLException;
    
    /**
     *
     * @param prefix
     * @return
     * @throws NotFoundException
     */
    public abstract List<User> searchMatching(String prefix) throws NotFoundException, SQLException;
}
