

package lasala;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ebalsa@uoc.edu
 */
public class Libro implements Serializable{

    private String LS=System.getProperty("line.separator");
    
   private long id; 
   private  String titulo;
   private  String autor;
   private  String editorial;  
   private  String isbn;
   private double netoCompra;
   private double pvp;
   private List<BookStatus> histStatus = new ArrayList<BookStatus>();
   private Distribuidora distribuidora;
   private EnumeratedStatus Status;
   
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
       this.Status=EnumeratedStatus.AVAILABLE;
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
        return this.distribuidora;
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

 
   public void setAvailable(){
       
        this.getHistStatus().add(new BookStatus(new Fecha(), new Status(EnumeratedStatus.AVAILABLE)));
        this.setStatus(EnumeratedStatus.AVAILABLE);
   }

   public void setSold(){
        this.getHistStatus().add(new BookStatus(new Fecha(), new Status(EnumeratedStatus.SOLD_OUT)));
        this.setStatus(EnumeratedStatus.SOLD_OUT);
   }
   
   public void setReturned(){
        this.getHistStatus().add(new BookStatus(new Fecha(), new Status(EnumeratedStatus.RETURNED)));
        this.setStatus(EnumeratedStatus.RETURNED);
   }

   public String toString(){
       StringBuffer sb = new StringBuffer();
       
       for (BookStatus b: this.getHistStatus()){
            
           sb.append(b.toString());
           sb.append(getLS());
       }
       
       return this.getTitulo() + " " + this.getAutor() + getLS() + sb.toString();
   }

    /**
     * @return the LS
     */
    public String getLS() {
        return LS;
    }

    /**
     * @param LS the LS to set
     */
    public void setLS(String LS) {
        this.LS = LS;
    }

    /**
     * @param histStatus the histStatus to set
     */
   

    /**
     * @return the Status
     */
    public EnumeratedStatus getStatus() {
        return Status;
    }

    /**
     * @param Status the Status to set
     */
    private void setStatus(EnumeratedStatus Status) {
        this.Status = Status;
    }

    /**
     * @param histStatus the histStatus to set
     */
    public void setHistStatus(List<BookStatus> histStatus) {
        this.histStatus = histStatus;
    }

}
