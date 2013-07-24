

package lasala;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author ebalsa@uoc.edu
 */
public class Distribuidora implements Serializable {
  
    private long id;
    private String name;
    private ArrayList<Libro> books = new ArrayList<Libro>();
            
    public Distribuidora(){
        
    }        

     public Distribuidora(long id, String name,  ArrayList<Libro> books){
         this.id=id;
         this.name=name;
         this.books=books;
     }   
     
     public void addBooktoDistributor(Libro book){
         this.getBooks().add(book);
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
    public ArrayList<Libro> getBooks() {
        return books;
    }

    /**
     * @param books the books to set
     */
    public void setBooks(ArrayList<Libro> books) {
        this.books = books;
    }
     
     

}
