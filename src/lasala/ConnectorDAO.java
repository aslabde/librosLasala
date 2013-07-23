

package lasala;

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
       
           // sesion.close(); 
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
