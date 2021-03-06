

package lasala;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import org.hibernate.HibernateException;

/**
 *
 * @author ebalsa@uoc.edu
 */
public class InterfaceController {
 
    static ConnectorDAO connectorDAO =new ConnectorDAO();
    static InsertConsole insConsole = new  InsertConsole();
    static BrowserConsole browserConsole = new  BrowserConsole();
    static BookDetail bookDetail= new BookDetail();
    static BookChangeDetail bookChangeDetail= new BookChangeDetail();
    static BookSaleDetail bookSaleDetail= new BookSaleDetail();
    static NewDistributorConsole newDistributorConsole = new NewDistributorConsole();
    static SalesConsole salesConsole = new SalesConsole();
    static ReturnConsole returnConsole = new ReturnConsole();
    
    public InterfaceController(){
         MainConsole mc = new MainConsole(); 
               
        mc.setLocationRelativeTo(null);       
        mc.setVisible(true);
        
               
    }
    
 
   public static  void buildInsertConsole(){
     LinkedHashMap distNames = connectorDAO.getDistributorMap();
     
     insConsole.initValues(distNames);
    insConsole.clearFields();
    insConsole.setLocationRelativeTo(null);  
    insConsole.setVisible(true);
    }
   
   public static  void buildBrowserConsole(){
     browserConsole.clearUI();
    browserConsole.setLocationRelativeTo(null);  
    browserConsole.setVisible(true);
    }
   
   public static  void buildSalesConsole(){
     salesConsole.clearUI();
    salesConsole.setLocationRelativeTo(null);  
    salesConsole.setVisible(true);
    }
   
   public static  void buildNewDistributorConsole(){
    newDistributorConsole.clearUI(); 
    newDistributorConsole.setLocationRelativeTo(null);  
    newDistributorConsole.setVisible(true);
    }
   
   public static  void buildReturnConsole() throws JRException{
     LinkedHashMap distNames = connectorDAO.getDistributorMap();
     returnConsole = new ReturnConsole();
     
    returnConsole.initValues(distNames);
    returnConsole.setLocationRelativeTo(null);  
    returnConsole.setVisible(true);
    }
   
   
   public static void buildBookDetail(long id){
       ArrayList<String> parsedBook = new ArrayList<String>();
       
       Libro returnedBook= connectorDAO.getLibro(id);
     
       parsedBook.add(returnedBook.getTitulo());
       parsedBook.add(returnedBook.getAutor());
       parsedBook.add(returnedBook.getEditorial());
       parsedBook.add(returnedBook.getIsbn());
       parsedBook.add(String.valueOf(returnedBook.getNetoCompra()));
       parsedBook.add(String.valueOf(returnedBook.getPvp()));
       parsedBook.add(String.valueOf(returnedBook.getId()));
       parsedBook.add(returnedBook.getStatus().toString());
        
       bookDetail.setFields(parsedBook);
       bookDetail.setLocationRelativeTo(null);  
       bookDetail.setVisible(true);
   }
   
     public static void buildBookChangeDetail(long id, BookDetail bd){
       ArrayList<String> parsedBook = new ArrayList<String>();
       
       Libro returnedBook= connectorDAO.getLibro(id);
     
       parsedBook.add(returnedBook.getTitulo());
       parsedBook.add(returnedBook.getAutor());
       parsedBook.add(returnedBook.getEditorial());
       parsedBook.add(returnedBook.getIsbn());
       parsedBook.add(String.valueOf(returnedBook.getNetoCompra()));
       parsedBook.add(String.valueOf(returnedBook.getPvp()));
       parsedBook.add(returnedBook.getStatus().toString());
       
       bookChangeDetail = new BookChangeDetail(id, bd);
       bookChangeDetail.setFields(parsedBook);
       bookChangeDetail.setLocationRelativeTo(null);  
       bookChangeDetail.setVisible(true);
   }
   
   public static void buildBookSaleDetail(long id){
       ArrayList<String> parsedBook = new ArrayList<String>();
       
       Libro returnedBook= connectorDAO.getLibro(id);
     
       parsedBook.add(returnedBook.getTitulo());
       parsedBook.add(returnedBook.getAutor());
       parsedBook.add(returnedBook.getEditorial());
       parsedBook.add(returnedBook.getIsbn());
       parsedBook.add(String.valueOf(returnedBook.getPvp()));
       parsedBook.add(String.valueOf(returnedBook.getId()));
             
       bookSaleDetail.setFields(parsedBook);
       bookSaleDetail.setLocationRelativeTo(null);  
       bookSaleDetail.setVisible(true);
   }
    
   public static void saveBook(ArrayList<String> userInput) throws HibernateException{
    
       String tituloPar = userInput.get(0);
       String autorPar = userInput.get(1);
       String editorialPar = userInput.get(2);
       String isbnPar = userInput.get(3);
       String netoParS = userInput.get(4);
       String pvpParS = userInput.get(5);
       Double netoPar = null;
       Double pvpPar = null;
       long distribuidoraId=0;
       String distribuidoraParS = userInput.get(6); //Distributor id
       
       if(netoParS != null){
            netoPar = Double.parseDouble(netoParS);
       }
       
      if(pvpParS != null){
            pvpPar = Double.parseDouble(pvpParS);
       }
     
    
        distribuidoraId = Long.parseLong(distribuidoraParS); //Parse dist ID
        Distribuidora currentDistributorSelected=connectorDAO.getDistributor(distribuidoraId);//Get Distribuidora
                            
                                 
        
        Libro bookToSave = new Libro(tituloPar, autorPar, editorialPar, isbnPar, netoPar, pvpPar,
                                                     currentDistributorSelected);
          
        bookToSave.setAvailable();
        
       //System.out.println(bookToSave);
        
         connectorDAO.saveLibro(bookToSave);
         currentDistributorSelected.addBook(bookToSave);
         connectorDAO.updateDistributor(currentDistributorSelected); 
        
        JOptionPane.showMessageDialog(null, "datos guardados correctamente");
                      
     
   }
  
   
   public static void updateBookDetails ( ArrayList<String> InputResults ) throws HibernateException {
       String tituloPar = InputResults.get(0);
       String autorPar = InputResults.get(1);
       String editorialPar = InputResults.get(2);
       String isbnPar = InputResults.get(3);
       Double netoPar = Double.parseDouble(InputResults.get(4));
       Double pvpPar = Double.parseDouble(InputResults.get(5));
       long bookId = Long.parseLong(InputResults.get(6));
       
       Libro bookToUpdate=connectorDAO.getLibro(bookId); 
       
       bookToUpdate.setTitulo(tituloPar);
       bookToUpdate.setAutor(autorPar);
       bookToUpdate.setEditorial(editorialPar);
       bookToUpdate.setIsbn(isbnPar);
       bookToUpdate.setNetoCompra(netoPar); 
       bookToUpdate.setPvp(pvpPar);
       
       String s = InputResults.get(7);
       
       switch(s){
            case "AVAILABLE":  bookToUpdate.setAvailable();
                                   break;
            case "RETURNED":   bookToUpdate.setReturned();
                                   break;
            case "SOLD_OUT":   bookToUpdate.setSold();
                                   break;    
                
        }
      
       
        connectorDAO.updateLibro(bookToUpdate);      
   }
   
   public static ArrayList getListBooks(String sQuery){
     List<Libro> results= new ArrayList<>();
     ArrayList parsedResults =new ArrayList<>();
     
     results=connectorDAO.getListLibros(sQuery);
      
     if(!results.isEmpty()){
            for(Libro l: results){
                parsedResults.add(l.getTitulo());
                parsedResults.add(l.getAutor());
                parsedResults.add(l.getEditorial());
                parsedResults.add(l.getIsbn());
                parsedResults.add(String.valueOf(l.getNetoCompra()));
                parsedResults.add(String.valueOf(l.getPvp()));
                parsedResults.add(String.valueOf(l.getId()));

            }
     }                
           return parsedResults;
       
   }
   
   public static ArrayList getAvailableListBooks(String sQuery){
     List<Libro> results= new ArrayList<>();
     ArrayList parsedResults =new ArrayList<>();
     
     results=connectorDAO.getListAvailableLibros(sQuery);
      
     if(!results.isEmpty()){
            for(Libro l: results){
                parsedResults.add(l.getTitulo());
                parsedResults.add(l.getAutor());
                parsedResults.add(l.getEditorial());
                parsedResults.add(l.getIsbn());
                parsedResults.add(String.valueOf(l.getNetoCompra()));
                parsedResults.add(String.valueOf(l.getPvp()));
                parsedResults.add(String.valueOf(l.getId()));

            }
     }                
           return parsedResults;
       
   }
   public static Libro getBookById(long id){
       Libro book = connectorDAO.getLibro(id);
       
       return book;
       
   }
   
      
   public static ArrayList getBooksByDistributor(String sQuery){
     List<Libro> results= new ArrayList<>();
     ArrayList parsedResults =new ArrayList<>();
     
          
     results=connectorDAO.getBooksByDistributor(sQuery);
     
     if(results !=null){
            for(Libro l: results){
                parsedResults.add(l.getTitulo());
                parsedResults.add(l.getAutor());
                parsedResults.add(l.getEditorial());
                parsedResults.add(l.getIsbn());
                parsedResults.add(String.valueOf(l.getNetoCompra()));
                parsedResults.add(String.valueOf(l.getPvp()));
                parsedResults.add(String.valueOf(l.getId()));
                parsedResults.add(new Boolean(false));
               
            }
     }                
           return parsedResults;
       
   }
   public static ArrayList getSaleListBooks(String sQuery){
     List<Libro> results= new ArrayList<>();
     ArrayList parsedResults =new ArrayList<>();
     
     results=connectorDAO.getListAvailableLibros(sQuery);
      
     if(!results.isEmpty()){
            for(Libro l: results){
                parsedResults.add(l.getTitulo());
                parsedResults.add(l.getAutor());
                parsedResults.add(l.getEditorial());
                parsedResults.add(String.valueOf(l.getPvp()));
                parsedResults.add(String.valueOf(l.getId()));

            }
     }                
           return parsedResults;
       
   }
   
   public static void changeBookStatus(Long id, EnumeratedStatus newStatus){
       
       Libro bookToUpdate=connectorDAO.getLibro(id);
       switch(newStatus){
           case AVAILABLE: bookToUpdate.setAvailable();
                                                         break;
            
           case RETURNED: bookToUpdate.setReturned();
                                                         break;
          
           case SOLD_OUT: bookToUpdate.setSold();
                                    JOptionPane.showMessageDialog(null, "libro vendido!!");
                                                         break;
           } 
       
          connectorDAO.updateLibro(bookToUpdate);
   }
   
   public static void newBackUp() throws IOException{
       String partial_path = new String("C:\\Users\\Cris\\Dropbox\\copiaDB\\");
       StringBuilder sb = new StringBuilder();
       DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
       Date date = new Date();
       
       sb.append(partial_path).append(dateFormat.format(date));
       
       String path =sb.toString();
       
       
        try{    
       String user = "lasala";
       String password ="Cris_samU";
       String dbase = "lasala";
       Process p;
       ProcessBuilder pb;
       
       pb = new ProcessBuilder( "cmd.exe",  "/c", "pg_dump.exe","-v","-n","public", "-f", path, "-U", user, dbase); 
       pb.environment().put("PGPASSWORD", password);
       pb.redirectError();
       pb.directory(new File("C:/PostgreSQL/9.2/bin"));
       p = pb.start();
       
       JOptionPane.showMessageDialog(null, "datos guardados correctamente");
       
        }catch(IOException e){
             JOptionPane.showMessageDialog(null, "Error al guardar los datos");
       
             throw new IOException("Ocurrió un error en la copia de seguridad", e); 
         }
          finally 
        { 
        
        }              
   }
   
    public static void saveDistributor(String distName){
     
        Distribuidora newDistributor = new Distribuidora(distName);
        connectorDAO.saveDistributor(newDistributor);
         JOptionPane.showMessageDialog(null, "datos guardados correctamente");
     }
   
    public void runTestScript(){
        
        
        
    }
    
}
