package sg.edu.nus.iss.phoenix.schedule.entity;

import java.io.Serializable;

/**
 *
 * @author kooc
 */
public class AnnualSchedule implements Cloneable, Serializable {

    private static final long serialVersionUID = 1L;
   
    /** 
     * Persistent Instance variables. This data is directly 
     * mapped to the columns of database table.
     */
    private Integer year;
    private String assingedBy;
    
    /** 
     * Constructors. 
     * The first one takes no arguments and provides the most simple
     * way to create object instance. The another one takes one
     * argument, which is the primary key of the corresponding table.
     */

    public AnnualSchedule () {

    }

    public AnnualSchedule (Integer yearIn) {

        this.year = yearIn;
    }

    public AnnualSchedule (Integer yearIn,
            String assingedByIn) {
        this.year = yearIn;
        this.assingedBy = assingedByIn;
    }
    
    /** 
     * Get- and Set-methods for persistent variables. The default
     * behaviour does not make any checks against malformed data,
     * so these might require some manual additions.
     * @return 
     */
    
    public Integer getYear() {
          return this.year;
    }
    public void setYear(Integer yearIn) {
          this.year = yearIn;
    }

    public String getAssignedBy() {
          return this.assingedBy;
    }
    public void setAssignedBy(String assingedByIn) {
          this.assingedBy = assingedByIn;
    }
    
    /** 
     * setAll allows to set all persistent variables in one method call.
     * This is useful, when all data is available and it is needed to 
     * set the initial state of this object. Note that this method will
     * directly modify instance variables, without going trough the 
     * individual set-methods.
     * @param yearIn
     * @param assingedByIn
     */

    public void setAll(Integer yearIn,
          String assingedByIn) {
          this.year = yearIn;
          this.assingedBy = assingedByIn;
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
    public boolean hasEqualMapping(AnnualSchedule valueObject) {

          if (this.year == null) {
                    if (valueObject.getYear() != null)
                           return(false);
          } else if (!this.year.equals(valueObject.getYear())) {
                    return(false);
          }
          if (this.assingedBy == null) {
                    if (valueObject.getAssignedBy()!= null)
                           return(false);
          } else if (!this.assingedBy.equals(valueObject.getAssignedBy())) {
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
        out.append("\nAnnualSchedule class, mapping to table annual-schedule\n");
        out.append("Persistent attributes: \n"); 
        out.append("year = ").append(this.year).append("\n"); 
        out.append("assingedBy = ").append(this.assingedBy).append("\n"); 
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
        AnnualSchedule cloned = new AnnualSchedule();

        if (this.year != null)
             cloned.setYear(this.year); 
        if (this.assingedBy != null)
             cloned.setAssignedBy(this.assingedBy); 
        return cloned;
    }
    
}
