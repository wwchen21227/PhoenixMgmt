/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.schedule.restful;

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
import sg.edu.nus.iss.phoenix.schedule.entity.AnnualSchedule;
import sg.edu.nus.iss.phoenix.schedule.entity.WeeklySchedule;
import sg.edu.nus.iss.phoenix.schedule.service.ScheduleService;
import java.sql.Date;

/**
 * REST Web Service
 *
 * @author kooc
 */
@Path("schedule")
@RequestScoped
public class ScheduleRESTService {
    
    @Context
    private UriInfo context;
    
    private ScheduleService service;

    /**
     * Creates a new instance of ScheduleRESTService
     */
    public ScheduleRESTService() {
        service = new ScheduleService();
    }

    /**
     * Retrieves representation of an instance of resource
     * @return an instance of resource
     */

    @GET
    @Path("/allAnnualSchedule")
    @Produces(MediaType.APPLICATION_JSON)
    public Schedules getAllAnnualSchedule() {
        ArrayList<AnnualSchedule> aslist = service.findAllAS();
        Schedules assList = new Schedules();
        assList.setAsList(new ArrayList<AnnualSchedule>());
        
        for (int i = 0; i < aslist.size(); i++) {
            assList.getAsList().add(
                new AnnualSchedule(aslist.get(i).getYear(), 
                    aslist.get(i).getAssignedBy()));
        }

        return assList;
    }
    
    /**
     * PUT method for updating or creating an instance of resource
     * @param content representation for the resource
     */
    @POST
    @Path("/updateAnnualSchedule")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateAnnualSchedule(AnnualSchedule as) {
        service.processModifyAS(as);
    }
    
    /**
     * POST method for creating an instance of resource
     * @param content representation for the resource
     */
    @PUT
    @Path("/createAnnualSchedule")
    @Consumes(MediaType.APPLICATION_JSON)
    public void createAnnualSchedule(AnnualSchedule as) {
        service.processCreateAS(as);
    }
   
    /**
     * DELETE method for deleting an instance of resource
     * @param name name of the resource
     */
    @DELETE
    @Path("/deleteAnnualSchedule/{year}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void deleteAnnualSchedule(@PathParam("year") Integer year) {
        String year2;
        try { 
            year2 = URLDecoder.decode(year+"", "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace(); 
            return;
        }

        service.processDeleteAS(year);
    }
    
    @GET
    @Path("/allWeeklySchedule")
    @Produces(MediaType.APPLICATION_JSON)
    public Schedules getAllWeeklySchedule() {
        ArrayList<WeeklySchedule> wslist = service.findAllWS();
        Schedules wssList = new Schedules();
        wssList.setWsList(new ArrayList<WeeklySchedule>());
        
        for (int i = 0; i < wslist.size(); i++) {
            wssList.getWsList().add(
                new WeeklySchedule(wslist.get(i).getStartDate(),
                    wslist.get(i).getAssignedBy()));
        }

        return wssList;
    }
    
    /**
     * PUT method for updating or creating an instance of resource
     * @param content representation for the resource
     */
    @POST
    @Path("/updateWeeklySchedule")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateWeeklySchedule(WeeklySchedule ws) {
        service.processModifyWS(ws);
    }
    
    /**
     * POST method for creating an instance of resource
     * @param content representation for the resource
     */
    @PUT
    @Path("/createWeeklySchedule")
    @Consumes(MediaType.APPLICATION_JSON)
    public void createWeeklySchedule(WeeklySchedule ws) {
        service.processCreateWS(ws);
    }
   
    /**
     * DELETE method for deleting an instance of resource
     * @param name name of the resource
     */
    @DELETE
    @Path("/deleteWeeklySchedule/{startdate}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void deleteWeeklySchedule(@PathParam("wsstartdate") Date startDate) {
        String startDate2;
        try { 
            startDate2 = URLDecoder.decode(startDate+"", "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace(); 
            return;
        }

        service.processDeleteWS(startDate);
    }
}
