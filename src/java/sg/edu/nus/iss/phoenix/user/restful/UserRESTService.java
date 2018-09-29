/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.user.restful;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import sg.edu.nus.iss.phoenix.authenticate.entity.User;
import sg.edu.nus.iss.phoenix.user.service.UserService;

/**
 *
 * @author wengweichen
 */
/**
 * REST Web Service
 *
 */
@Path("user")
@RequestScoped
public class UserRESTService {
    @Context
    private UriInfo context;
    
    private UserService service;

    /**
     * Creates a new instance of RadioProgramRESTService
     */
    public UserRESTService() {
        service = new UserService();
    }

    /**
     * Retrieves representation of an instance of resource
     * @return an instance of resource
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public User getUser() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }
    
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Users getAllUsers() {
        ArrayList<User> userList = service.findAllUser();
        Users users = new Users();
        users.setUserList(new ArrayList<User>());
        
        for (int i = 0; i < userList.size(); i++) {
            users.getUserList().add(
                new User(userList.get(i).getId(), 
                    userList.get(i).getName(), 
                    userList.get(i).getRoles()));
        }

        return users;
    }
    
    /**
     * PUT method for updating or creating an instance of resource
     * @param user representation for the resource
     */
    @POST
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateUser(User user) {
        service.processModify(user);
    }
    
    /**
     * POST method for creating an instance of resource
     * @param user representation for the resource
     */
    @PUT
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    public void createUser(User user) {
        service.processCreate(user);
    }
    
    /**
     * DELETE method for deleting an instance of resource
     * @param id of the resource
     */
    @DELETE
    @Path("/delete/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void deleteUser(@PathParam("id") String id) {
        String decodedId;
        try { 
            decodedId = URLDecoder.decode(id, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace(); 
            return;
        }

        service.processDelete(decodedId);
    }
}
