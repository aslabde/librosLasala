

package lasala;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ebalsa@uoc.edu
 */
public class Libro implements Serializable{

    final String LS=System.getProperty("line.separator");
    
   private long id; 
   private  String titulo;
   private  String autor;
   private  String editorial;  
   private  String isbn;
   private double netoCompra;
   private double pvp;
   private List<BookStatus> histStatus = new ArrayList<BookStatus>();
   private Distribuidora distribuidora;
   
   public Libro(){
      
   }

   public Libro (String titulo, String autor, String editorial, String isbn, double netoCompra, double pvp,
                         Distribuidora distribuidora){
       
       this.titulo=titulo;
       this.autor=autor;
       this.editorial=editorial;
       this.isbn=isbn;
       this.netoCompra=netoCompra;       
       this.pvp=pvp;
       this.distribuidora=distribuidora;
   }

    /**
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @return the autor
     */
    public String getAutor() {
        return autor;
    }

    /**
     * @param autor the autor to set
     */
    public void setAutor(String autor) {
        this.autor = autor;
    }

    /**
     * @return the editorial
     */
    public String getEditorial() {
        return editorial;
    }

    /**
     * @param editorial the editorial to set
     */
    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    /**
     * @return the isbn
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * @param isbn the isbn to set
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * @return the netoCompra
     */
    public double getNetoCompra() {
        return netoCompra;
    }

    /**
     * @param netoCompra the netoCompra to set
     */
    public void setNetoCompra(double netoCompra) {
        this.netoCompra = netoCompra;
    }

    /**
     * @return the pvp
     */
    public double getPvp() {
        return pvp;
    }

    /**
     * @param pvp the pvp to set
     */
    public void setPvp(double pvp) {
        this.pvp = pvp;
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
     * @return the distribuidora
     */
    public Distribuidora getDistribuidora() {
        return distribuidora;
    }

    /**
     * @param distribuidora the distribuidora to set
     */
    public void setDistribuidora(Distribuidora distribuidora) {
        this.distribuidora = distribuidora;
    }

    /**
     * @return the histStatus
     */
    public List<BookStatus> getHistStatus() {
        return histStatus;
    }

    /**
     * @param histStatus the histStatus to set
     */
    public void setHistStatus(List<BookStatus> histStatus) {
        this.histStatus = histStatus;
    }
   
   public void setAvailable(){
       
       this.histStatus.add(new BookStatus(new Fecha(), new Status(EnumeratedStatus.AVAILABLE)));
   }

   public void setSold(){
       this.histStatus.add(new BookStatus(new Fecha(), new Status(EnumeratedStatus.SOLD_OUT)));
   }
   
   public void setReturned(){
       this.histStatus.add(new BookStatus(new Fecha(), new Status(EnumeratedStatus.RETURNED)));
   }

   public String toString(){
       StringBuffer sb = new StringBuffer();
       
       for (BookStatus b: this.histStatus){
            
           sb.append(b.toString());
           sb.append(LS);
       }
       
       return this.titulo + " " + this.autor + LS + sb.toString();
   }

}
