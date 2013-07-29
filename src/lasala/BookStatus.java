

package lasala;

import java.io.Serializable;

/**
 *
 * @author ebalsa@uoc.edu
 */
public class BookStatus implements Serializable{

   private long id;
   private Fecha dateIni=null;
   private Fecha dateEnd=null;
   private Status status;
   
   public BookStatus(){
       
   }
   
    public BookStatus(Fecha dateIni, Status status){
        this.dateIni=dateIni;
        this.status=status;       
   }

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return the dateIni
     */
    public Fecha getDateIni() {
        return dateIni;
    }

    /**
     * @param dateIni the dateIni to set
     */
    public void setDateIni(Fecha dateIni) {
        this.dateIni = dateIni;
    }

    /**
     * @return the dateEnd
     */
    public Fecha getDateEnd() {
        return dateEnd;
    }

    /**
     * @param dateEnd the dateEnd to set
     */
    public void setDateEnd(Fecha dateEnd) {
        this.dateEnd = dateEnd;
    }

    /**
     * @return the status
     */
    public Status getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(Status status) {
        this.status = status;
    }
    
    public String toString(){
        
        return this.dateIni.toString() +" -> " + this.status.toString();
    }
    
}
