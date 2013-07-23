

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
    
   public static void saveBook(ArrayList<String> userInput) throws HibernateException{
    
       String tituloPar = userInput.get(0);
       String autorPar = userInput.get(1);
       String editorialPar = userInput.get(2);
       String isbnPar = userInput.get(3);
       String netoParS = userInput.get(4);
       String pvpParS = userInput.get(5);
       Double netoPar = null;
       Double pvpPar = null;
       
       if(netoParS != null){
            netoPar = Double.parseDouble(netoParS);
       }
       
      if(pvpParS != null){
            pvpPar = Double.parseDouble(pvpParS);
       }
       
       Libro bookToSave = new Libro(tituloPar, autorPar, editorialPar, isbnPar, netoPar, pvpPar );
       
      try{
           connectorDAO.saveLibro(bookToSave);
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

            }
     }                
           return parsedResults;
       
   }
   
 
}
