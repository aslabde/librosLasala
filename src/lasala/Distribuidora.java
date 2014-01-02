

package lasala;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/**
 *
 * @author ebalsa@uoc.edu
 */
public class Distribuidora implements Serializable {
  
    private long id;
    private String name;
    private Set<Libro> books = new HashSet<Libro>();
            
    public Distribuidora(){
        
    }        

     public Distribuidora(String name){
         this.name=name;
      
     }   
          
     
     public void addBook(Libro book){
         this.getBooks().add(book);
     }

    /**
     * @return the id
     */
    public long getId() {
        return this.id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the books
     */
    public Set<Libro> getBooks() {
        return books;
    }

    /**
     * @param books the books to set
     */
    public void setBooks(Set<Libro> books) {
        this.books = books;
    }
     
     

}
