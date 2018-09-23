package sg.edu.nus.iss.phoenix.user.restful;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import sg.edu.nus.iss.phoenix.authenticate.entity.User;
import sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException;
import sg.edu.nus.iss.phoenix.user.service.ProducerService;

/**
 *
 * @author sourcepirate
 */

@Path("producer")
public class ProducerRESTService {
    @Context
    private UriInfo context;
    
    private ProducerService producerService;
    
    public ProducerRESTService(){
        producerService = new ProducerService();
    }
    
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getAllUsers() throws NotFoundException, SQLException {
        return producerService.getAll();
    }
    
    /**
     *
     * @param id
     * @return
     * @throws NotFoundException
     * @throws SQLException
     */
    @GET
    @Path("/id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public User getUser(@PathParam("id") String id) throws NotFoundException, SQLException {
        return producerService.getProducer(id);
    }
    
    @GET
    @Path("/search")
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> searchUser(@QueryParam("q") String prefix) throws NotFoundException, SQLException {
       return producerService.searchProducer(prefix);
    }
}
