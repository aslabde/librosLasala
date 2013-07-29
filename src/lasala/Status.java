

package lasala;

import java.io.Serializable;

/**
 *
 * @author ebalsa@uoc.edu
 */
public class Status implements Serializable{

   private long id;
   private EnumeratedStatus status;
    
    
   public Status (){

    }
   
   public Status (EnumeratedStatus status){
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
     * @return the status
     */
    public EnumeratedStatus getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(EnumeratedStatus status) {
        this.status = status;
    }

    public String toString(){
        
        return this.status.toString();
    }
    
}
