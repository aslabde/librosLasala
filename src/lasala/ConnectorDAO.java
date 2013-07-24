

package lasala;

import java.util.HashMap;
import org.hibernate.*; 
import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.criterion.Restrictions;


public class ConnectorDAO {

    private Session sesion;
    private Transaction tx;
    
   public  long saveLibro(Libro libro) throws HibernateException { 
   
        long id = 0;  

        try { 
        
          beginOperation(); 
            id = (Long) sesion.save(libro); 
            tx.commit(); 
        } catch (HibernateException he) { 
        
            handleException(he); 
            throw he; 
        } finally  { 
       
            sesion.close(); 
        }  

        return id; 
    }  

    public void updateLibro(Libro libro) throws HibernateException 
    { 
        try  { 
       
            beginOperation(); 
            sesion.update(libro); 
            tx.commit(); 
        } catch (HibernateException he) 
        { 
            handleException(he); 
            throw he; 
        } finally 
        { 
            sesion.close(); 
        } 
    }  

    public void deleteLibro(Libro libro) throws HibernateException 
    { 
        try 
        { 
            beginOperation(); 
            sesion.delete(libro); 
            tx.commit(); 
        } catch (HibernateException he) 
        { 
            handleException(he); 
            throw he; 
        } finally 
        { 
            sesion.close(); 
        } 
    }  

    public Libro getLibro(long idLibro) throws HibernateException 
    { 
        Libro libro = null;  
        try 
        { 
            beginOperation(); 
            libro = (Libro) sesion.get(Libro.class, idLibro); 
        } finally 
        { 
            sesion.close(); 
        }  

        return libro; 
    }  

    public List<Libro> getListLibros() throws HibernateException 
    { 
        List listLibros = null;  

        try 
        { 
            beginOperation(); 
            listLibros = sesion.createQuery("from Libro").list(); 
           
        } finally 
        { 
            sesion.close(); 
        }  

        return listLibros; 
    }  
    
     public List<Libro> getListLibros(String searchQuery) throws HibernateException 
    { 
        List<Libro>listLibros = null;  
        
        
        try 
        { 
            beginOperation(); 
            Criteria cr =sesion.createCriteria(Libro.class);
            cr.add(Restrictions.ilike("titulo","%"+searchQuery+"%" ));
            listLibros=cr.list();
                    
        } finally 
        { 
             sesion.close(); 
        }  

        return listLibros; 
    }  
     
     public  long saveDistribuidora(Distribuidora distribuidora) throws HibernateException { 
   
        long id = 0;  

        try { 
        
          beginOperation(); 
            id = (Long) sesion.save(distribuidora); 
            tx.commit(); 
        } catch (HibernateException he) { 
        
            handleException(he); 
            throw he; 
        } finally  { 
       
            sesion.close(); 
        }  

        return id; 
    }  
     
     
     
     
     
     
    public HashMap getDistributorMap () throws HibernateException {
        HashMap distribuidoras = new HashMap();
        List<Distribuidora> returnedDistributorsList = null;
        
         try 
        { 
            beginOperation(); 
            returnedDistributorsList = sesion.createQuery("from Distribuidora").list(); 
           
        } finally 
        { 
            sesion.close(); 
        }  
         
         for (Distribuidora d: returnedDistributorsList){
             distribuidoras.put(d.getId(), d.getName());
         }
        
        return distribuidoras;
    }
     
    public Distribuidora getDistributor(long idDistribuidora) throws HibernateException {
        
         Distribuidora distribuidora = null;  
        try 
        { 
            beginOperation(); 
            distribuidora = (Distribuidora) sesion.get(Distribuidora.class, idDistribuidora); 
        } finally 
        { 
            sesion.close(); 
        }  

        return distribuidora; 
        
    }
    
    public void updateDistributorBooksList(Libro book, Distribuidora distributor){
        
        try{
            beginOperation(); 
            distributor.addBooktoDistributor(book);
            sesion.update(distributor);
        }finally{
            sesion.close();
        }
     
    }
    

    private void beginOperation() throws HibernateException 
    { 
        sesion = HibernateUtil.getSessionFactory().openSession();
        tx = sesion.beginTransaction(); 
    }  

    private void handleException(HibernateException he) throws HibernateException 
    { 
        JOptionPane.showMessageDialog(null, "Error al guardar los datos");
        tx.rollback(); 
        throw new HibernateException("Ocurrió un error en la capa de acceso a datos", he); 
    } 


}
