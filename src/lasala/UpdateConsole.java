

package lasala;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.HibernateException;

/**
 *
 * @author ebalsa@uoc.edu
 */
public class UpdateConsole {
 
    static ConnectorDAO connectorDAO =new ConnectorDAO();
    static InsertConsole insConsole = new  InsertConsole();
    static SalesConsole browserConsole = new  SalesConsole();
    static BookDetail bookDetail= new BookDetail();
    static NewDistributorConsole newDistributorConsole = new NewDistributorConsole();
    
    public UpdateConsole(){
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
   
   public static  void buildNewDistributorConsole(){
    newDistributorConsole.clearUI(); 
    newDistributorConsole.setLocationRelativeTo(null);  
    newDistributorConsole.setVisible(true);
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
        
        System.out.println(bookToSave);
        
         connectorDAO.saveLibro(bookToSave);
         currentDistributorSelected.addBook(bookToSave);
         connectorDAO.updateDistributor(currentDistributorSelected); 
        
        JOptionPane.showMessageDialog(null, "datos guardados correctamente");
                      
     
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
   
   public void changeBookStatus(Long id, EnumeratedStatus newStatus){
       
       Libro bookToUpdate=connectorDAO.getLibro(id);
       switch(newStatus){
           case AVAILABLE: bookToUpdate.setAvailable();
                                                         break;
            
           case RETURNED: bookToUpdate.setReturned();
                                                         break;
          
           case SOLD_OUT: bookToUpdate.setSold();
                                                         break;
           } 
       
          connectorDAO.updateLibro(bookToUpdate);
   }
   
   
    public static void saveDistributor(String distName){
     
        Distribuidora newDistributor = new Distribuidora(distName);
        connectorDAO.saveDistributor(newDistributor);
         JOptionPane.showMessageDialog(null, "datos guardados correctamente");
     }
   
    public void runTestScript(){
        
        
        
    }
    
}
