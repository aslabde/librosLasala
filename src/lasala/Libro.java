

package lasala;

import java.io.Serializable;

/**
 *
 * @author ebalsa@uoc.edu
 */
public class Libro implements Serializable{

   private long id; 
   private  String titulo;
   private  String autor;
   private  String editorial;  
   private  String isbn;
   private double netoCompra;
   private double pvp;
   //bookStatus
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
   
   

}
