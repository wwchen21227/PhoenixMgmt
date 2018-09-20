package sg.edu.nus.iss.phoenix.schedule.entity;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author kooc
 */
public class WeeklySchedule implements Cloneable, Serializable {

    private static final long serialVersionUID = 1L;
   
    /** 
     * Persistent Instance variables. This data is directly 
     * mapped to the columns of database table.
     */
    private Date startDate;
    private String assignedBy;
    
    /** 
     * Constructors. 
     * The first one takes no arguments and provides the most simple
     * way to create object instance. The another one takes one
     * argument, which is the primary key of the corresponding table.
     */

    public WeeklySchedule () {

    }

    public WeeklySchedule (Date startDateIn) {

        this.startDate = startDateIn;
    }

    public WeeklySchedule (Date startDateIn,
            String assignedByIn) {
        this.startDate = startDateIn;
        this.assignedBy = assignedByIn;
    }
    
    /** 
     * Get- and Set-methods for persistent variables. The default
     * behaviour does not make any checks against malformed data,
     * so these might require some manual additions.
     * @return 
     */
    
    public Date getStartDate() {
          return this.startDate;
    }
    public void setStartDate(Date startDateIn) {
          this.startDate = startDateIn;
    }

    public String getAssignedBy() {
          return this.assignedBy;
    }
    public void setAssignedBy(String assignedByIn) {
          this.assignedBy = assignedByIn;
    }
    
    /** 
     * setAll allows to set all persistent variables in one method call.
     * This is useful, when all data is available and it is needed to 
     * set the initial state of this object. Note that this method will
     * directly modify instance variables, without going trough the 
     * individual set-methods.
     * @param startDateIn
     * @param assignedByIn
     */

    public void setAll(Date startDateIn,
          String assignedByIn) {
          this.startDate = startDateIn;
          this.assignedBy = assignedByIn;
    }    
    
        /** 
     * hasEqualMapping-method will compare two RadioProgram instances
     * and return true if they contain same values in all persistent instance 
     * variables. If hasEqualMapping returns true, it does not mean the objects
     * are the same instance. However it does mean that in that moment, they 
     * are mapped to the same row in database.
     * @param valueObject
     * @return 
     */
    public boolean hasEqualMapping(WeeklySchedule valueObject) {

          if (this.startDate == null) {
                    if (valueObject.getStartDate()!= null)
                           return(false);
          } else if (!this.startDate.equals(valueObject.getStartDate())) {
                    return(false);
          }
          if (this.assignedBy == null) {
                    if (valueObject.getAssignedBy()!= null)
                           return(false);
          } else if (!this.assignedBy.equals(valueObject.getAssignedBy())) {
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
        out.append("\nWeeklySchedule class, mapping to table weekly-schedule\n");
        out.append("Persistent attributes: \n"); 
        out.append("startDate = ").append(this.startDate).append("\n"); 
        out.append("assignedBy = ").append(this.assignedBy).append("\n"); 
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
        WeeklySchedule cloned = new WeeklySchedule();

        if (this.startDate != null)
             cloned.setStartDate(this.startDate); 
        if (this.assignedBy != null)
             cloned.setAssignedBy(this.assignedBy); 
        return cloned;
    }
    
}
