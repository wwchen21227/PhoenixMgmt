package sg.edu.nus.iss.phoenix.schedule.entity;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author aswathyl
 */
public class ProgramSlot implements Cloneable, Serializable {

    private static final long serialVersionUID = -5500218812568593553L;
   
    /** 
     * Persistent Instance variables. This data is directly 
     * mapped to the columns of database table.
     */
    private String programName;
    private Time duration;
    private Date dateOfProgram;
    private Time startTime;
    private String programSlotId;
    private String weeklyScheduleId;
    private String presenter;
    private String producer;
    
    /** 
     * Constructors. 
     * The first one takes no arguments and provides the most simple
     * way to create object instance. The another one takes one
     * argument, which is the primary key of the corresponding table.
     */

    public ProgramSlot () {

    }
    
    public ProgramSlot (String programSlotIdIn) {
        this.programSlotId = programSlotIdIn;
    }

    public ProgramSlot (String weeklyScheduleIdIn, Date dateOfProgramIn, 
            Time startTimeIn) {
        this.dateOfProgram = dateOfProgramIn;
        this.startTime = startTimeIn;
        this.weeklyScheduleId = weeklyScheduleIdIn;
    }

    public ProgramSlot (String programSlotIdIn, String programNameIn, Time durationIn, 
            Date dateOfProgramIn, Time startTimeIn, String weeklyScheduleIdIn,
            String presenterIn, String producerIn) {
        this.programSlotId = programSlotIdIn;
        this.programName = programNameIn;
        this.duration = durationIn;
        this.dateOfProgram = dateOfProgramIn;
        this.startTime = startTimeIn;
        this.weeklyScheduleId = weeklyScheduleIdIn;
        this.presenter = presenterIn;
        this.producer = producerIn;
    }
    
    /** 
     * Get- and Set-methods for persistent variables. The default
     * behaviour does not make any checks against malformed data,
     * so these might require some manual additions.
     * @return 
     */
    
    public String getProgramName() {
          return this.programName;
    }
    public void setProgramName(String programNameIn) {
          this.programName = programNameIn;
    }

    public Time getDuration() {
          return this.duration;
    }
    public void setDuration(Time durationIn) {
          this.duration = durationIn;
    }
    
    public Date getDateOfProgram() {
          return this.dateOfProgram;
    }
    public void setDateOfProgram(Date dateOfProgramIn) {
          this.dateOfProgram = dateOfProgramIn;
    }
    
    public Time getStartTime() {
          return this.startTime;
    }
    public void setStartTime(Time startTimeIn) {
          this.startTime = startTimeIn;
    }
    
   public String getWeeklyScheduleId() {
          return this.weeklyScheduleId;
    }
    public void setweeklyScheduleId(String weeklyScheduleIdIn) {
          this.weeklyScheduleId = weeklyScheduleIdIn;
    }
    
    public String getPresenter() {
          return this.presenter;
    }
    public void setPresenter(String presenterIn) {
          this.presenter = presenterIn;
    }
    
    public String getProducer() {
          return this.producer;
    }
    public void setProducer(String producerIn) {
          this.producer = producerIn;
    }
    
    public String getProgramSlotId() {
        return this.programSlotId;
    }
    public void setProgramSlotId(String programSlotIdIn) {
          this.programSlotId = programSlotIdIn;
    }
    /** 
     * setAll allows to set all persistent variables in one method call.
     * This is useful, when all data is available and it is needed to 
     * set the initial state of this object. Note that this method will
     * directly modify instance variables, without going trough the 
     * individual set-methods.
     * @param programSlotIdIn
     * @param programNameIn
     * @param durationIn
     * @param dateOfProgramIn
     * @param startTimeIn
     * @param weeklyScheduleIdIn
     * @param presenterIn
     * @param producerIn
     */

    public void setAll(String programSlotIdIn, String programNameIn, Time durationIn, 
            Date dateOfProgramIn, Time startTimeIn, String weeklyScheduleIdIn,
            String presenterIn, String producerIn) {
        this.programSlotId = programSlotIdIn;
        this.programName = programNameIn;
        this.duration = durationIn;
        this.dateOfProgram = dateOfProgramIn;
        this.startTime = startTimeIn;
        this.weeklyScheduleId = weeklyScheduleIdIn;
        this.presenter = presenterIn;
        this.producer = producerIn;
    }    
    
        /** 
     * hasEqualMapping-method will compare two ProgramSlot instances
     * and return true if they contain same values in all persistent instance 
     * variables. If hasEqualMapping returns true, it does not mean the objects
     * are the same instance. However it does mean that in that moment, they 
     * are mapped to the same row in database.
     * @param valueObject
     * @return 
     */
    public boolean hasEqualMapping(ProgramSlot valueObject) {
           if (this.programSlotId == null) {
                    if (valueObject.getProgramSlotId() != null)
                           return(false);
          } else if (!this.programSlotId.equals(valueObject.getProgramSlotId())) {
                    return(false);
          }
          if (this.programName == null) {
                    if (valueObject.getProgramName() != null)
                           return(false);
          } else if (!this.programName.equals(valueObject.getProgramName())) {
                    return(false);
          }
          if (this.duration == null) {
                    if (valueObject.getDuration()!= null)
                           return(false);
          } else if (!this.duration.equals(valueObject.getDuration())) {
                    return(false);
          }
          if (this.dateOfProgram == null) {
                    if (valueObject.getDateOfProgram()!= null)
                           return(false);
          } else if (!this.dateOfProgram.equals(valueObject.getDateOfProgram())) {
                    return(false);
          }
          if (this.startTime == null) {
                    if (valueObject.getStartTime()!= null)
                           return(false);
          } else if (!this.startTime.equals(valueObject.getStartTime())) {
                    return(false);
          }
          if (this.weeklyScheduleId == null) {
                    if (valueObject.getWeeklyScheduleId()!= null)
                           return(false);
          } else if (!this.weeklyScheduleId.equals(valueObject.getWeeklyScheduleId())) {
                    return(false);
          }
          if (this.presenter == null) {
                    if (valueObject.getPresenter()!= null)
                           return(false);
          } else if (!this.presenter.equals(valueObject.getPresenter())) {
                    return(false);
          }
          if (this.producer == null) {
                    if (valueObject.getProducer()!= null)
                           return(false);
          } else if (!this.producer.equals(valueObject.getProducer())) {
                    return(false);
          }     

          return true;
    }
    
     /**
     * toString will return String object representing the state of this 
     * valueObject. This is useful during application development, and 
     * possibly when application is writing object states in text log.
     */
        @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        out.append("\nProgramSlot class, mapping to table program-slot\n");
        out.append("Persistent attributes: \n"); 
        out.append("programSlotId = ").append(this.programSlotId).append("\n"); 
        out.append("program-name = ").append(this.programName).append("\n"); 
        out.append("duration = ").append(this.duration).append("\n"); 
        out.append("dateOfProgram = ").append(this.dateOfProgram).append("\n"); 
        out.append("startTime = ").append(this.startTime).append("\n"); 
        out.append("weeklyScheduleId = ").append(this.weeklyScheduleId).append("\n"); 
        out.append("presenter = ").append(this.presenter).append("\n"); 
        out.append("producer = ").append(this.producer).append("\n"); 
        return out.toString();
    }
    
    /**
     * Clone will return identical deep copy of this valueObject.
     * Note, that this method is different than the clone() which
     * is defined in java.lang.Object. Here, the returned cloned object
     * will also have all its attributes cloned.
     * @return 
     * @throws java.lang.CloneNotSupportedException 
     */
        @Override
    public Object clone() throws CloneNotSupportedException {
        ProgramSlot cloned = new ProgramSlot();
        if (this.programSlotId != null)
             cloned.setProgramSlotId(this.programSlotId); 
        if (this.programName != null)
             cloned.setProgramName(this.programName); 
        if (this.duration != null)
             cloned.setDuration(this.duration); 
        if (this.dateOfProgram != null)
             cloned.setDateOfProgram(this.dateOfProgram); 
        if (this.startTime != null)
             cloned.setStartTime(this.startTime); 
        if (this.weeklyScheduleId != null)
             cloned.setweeklyScheduleId(this.weeklyScheduleId); 
        if (this.presenter != null)
             cloned.setPresenter(this.presenter); 
        if (this.producer != null)
             cloned.setProducer(this.producer); 
        
        return cloned;
    }
    
}
