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
import javax.ws.rs.QueryParam;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;

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
     * GET method for searching an instance of resource
     * @param year year for the resource
     */
    @GET
    @Path("/getAnnualScheduleByYear/{year}")
    @Produces(MediaType.APPLICATION_JSON)
    public AnnualSchedule getAnnualScheduleByYear(@PathParam("year") Integer year) {
        String year2;
        try { 
            year2 = URLDecoder.decode(year+"", "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace(); 
            return null;
        }
        
        AnnualSchedule aslist = service.findAS(year);
        return aslist;
    }
    
    /**
     * POST method for updating or creating an instance of resource
     * @param as representation for the resource
     */
    @POST
    @Path("/updateAnnualSchedule")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateAnnualSchedule(AnnualSchedule as) {
        service.processModifyAS(as);
    }
    
    /**
     * PUT method for creating an instance of resource
     * @param as representation for the resource
     */
    @PUT
    @Path("/createAnnualSchedule")
    @Consumes(MediaType.APPLICATION_JSON)
    public void createAnnualSchedule(AnnualSchedule as) {
        service.processCreateAS(as);
    }
   
    /**
     * DELETE method for deleting an instance of resource
     * @param year of the resource
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
     * GET method for searching an instance of resource
     * @param startDate for the resource
     */
    
    @GET
    @Path("/getWeeklyScheduleByDate/{startdate}")
    @Produces(MediaType.APPLICATION_JSON)
    public WeeklySchedule getWeeklyScheduleByDate(@PathParam("wsstartdate") Date startDate) {
        
        String startDate2;
        try { 
            startDate2 = URLDecoder.decode(startDate+"", "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace(); 
            return null;
        }
        
        WeeklySchedule wslist = service.findWSbyDate(startDate);
        return wslist;
    }
    
    /**
     * PUT method for updating or creating an instance of resource
     * @param ws representation for the resource
     */
    @POST
    @Path("/updateWeeklySchedule")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateWeeklySchedule(WeeklySchedule ws) {
        service.processModifyWS(ws);
    }
    
    /**
     * POST method for creating an instance of resource
     * @param ws representation for the resource
     */
    @PUT
    @Path("/createWeeklySchedule")
    @Consumes(MediaType.APPLICATION_JSON)
    public void createWeeklySchedule(WeeklySchedule ws) {
        service.processCreateWS(ws);
    }
   
    /**
     * DELETE method for deleting an instance of resource
     * @param startDate of the resource
     */
    @DELETE
    @Path("/deleteWeeklySchedule/{startdate}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void deleteWeeklySchedule(@PathParam("startdate") Date startDate) {
        String startDate2;
        try { 
            startDate2 = URLDecoder.decode(startDate+"", "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace(); 
            return;
        }

        service.processDeleteWS(startDate);
    }
    
    @GET
    @Path("/allProgramSlot")
    @Produces(MediaType.APPLICATION_JSON)
    public Schedules getAllProgramSlot() {
        ArrayList<ProgramSlot> pslist = service.findAllPS();
        Schedules pssList = new Schedules();
        pssList.setPsList(new ArrayList<ProgramSlot>());
        
        for (int i = 0; i < pslist.size(); i++) {
            pssList.getPsList().add(
                new ProgramSlot(pslist.get(i).getProgramSlotId(),
                    pslist.get(i).getProgramName(),
                    pslist.get(i).getDuration(),
                    pslist.get(i).getDateOfProgram(),
                    pslist.get(i).getStartTime(),
                    pslist.get(i).getWeeklyScheduleId(),
                    pslist.get(i).getPresenter(),
                    pslist.get(i).getProducer()
                )
            );
        }

        return pssList;
    }
    
      /**
     * GET method for searching an instance of resource
     * @param dateOfProgram for the resource
     */
    
    @GET
    @Path("/getProgramSlotByDate/{dateOfProgram}")
    @Produces(MediaType.APPLICATION_JSON)
    public ProgramSlot getProgramSlotByDate(@PathParam("dateOfProgram") Date dateOfProgram) {
        String pgmid2;
        try { 
            pgmid2 = URLDecoder.decode(dateOfProgram+"", "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace(); 
            return null;
        }
        
        ProgramSlot pslist = service.findPSByDate(dateOfProgram);
        return pslist;
    }
    /**
     * PUT method for updating or creating an instance of resource
     * @param ps representation for the resource
     */
    @POST
    @Path("/updateProgramSlot")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateProgramSlot(ProgramSlot ps) {
        service.processModifyPS(ps);
    }
    
    /**
     * POST method for creating an instance of resource
     * @param ps representation for the resource
     */
    @PUT
    @Path("/createProgramSlot")
    @Consumes(MediaType.APPLICATION_JSON)
    public void createProgramSlot(ProgramSlot ps) {
        service.processCreatePS(ps);
    }
   
    /**
     * DELETE method for deleting an instance of resource
     * @param programSlotId of the resource
     */
    @DELETE
    @Path("/deleteProgramSlot/{programSlotId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void deleteProgramSlot(@PathParam("programSlotId") String programSlotId) {
        String pgmid2;
        try { 
            pgmid2 = URLDecoder.decode(programSlotId+"", "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace(); 
            return;
        }

        service.processDeletePS(programSlotId);
    }
    
     /**
     * Check NewTime method for create new programSlot
     * @param newTime of the resource
     * checkNewTimeOverLap(@QueryParam("newTime") String newTime)
     */
  
    
    @GET
    @Path("/checkNewTimeOverLap")
    @Consumes(MediaType.APPLICATION_JSON)
    public String checkNewTimeOverLap() {
          
        String isOverLap =  service.checkOverLap();
        return isOverLap;
    }  
}
