

package lasala;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.HibernateException;

/**
 *
 * @author ebalsa@uoc.edu
 */
public class InterfaceController {
 
    static ConnectorDAO connectorDAO =new ConnectorDAO();
    static InsertConsole insConsole = new  InsertConsole();
    static BrowserConsole browserConsole = new  BrowserConsole();
    static BookDetail bookDetail= new BookDetail() ;
    
    public InterfaceController(){
         MainConsole mc = new MainConsole(); 
               
        mc.setLocationRelativeTo(null);       
        mc.setVisible(true);
    }
    
 
   public static  void buildInsertConsole(){
     
    insConsole.clearFields();
    insConsole.setLocationRelativeTo(null);  
    insConsole.setVisible(true);
    }
   
   public static  void buildBrowserConsole(){
     browserConsole.clearUI();
    browserConsole.setLocationRelativeTo(null);  
    browserConsole.setVisible(true);
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
     
      
      try{
        distribuidoraId = Long.parseLong(distribuidoraParS); //Parse dist ID
        Distribuidora currentDistributorSelected=connectorDAO.getDistributor(distribuidoraId);//Get Distribuidora
       
        Libro bookToSave = new Libro(tituloPar, autorPar, editorialPar, isbnPar, netoPar, pvpPar,
                                                     currentDistributorSelected);
              
      
         connectorDAO.saveLibro(bookToSave);
         connectorDAO.updateDistributorBooksList( bookToSave, currentDistributorSelected);
         JOptionPane.showMessageDialog(null, "datos guardados correctamente");
                      
      }
       catch (HibernateException he) { 
         
            throw he; 
            
        }
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
   
 
}
