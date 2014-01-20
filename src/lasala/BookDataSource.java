

package lasala;

import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 *
 * @author ebalsa@uoc.edu
 */
public class BookDataSource implements JRDataSource{

    private List<Libro> bookList = new ArrayList<Libro>();
    private int index = -1;

    public Object getFieldValue(JRField jrf) throws JRException
    {
        Object valor = null;

        if ("titulo".equals(jrf.getName()))
        {
            valor = bookList.get(index).getTitulo();
        }
        else if ("editorial".equals(jrf.getName()))
        {
            valor = bookList.get(index).getEditorial();
        }
        else if ("netoCompra".equals(jrf.getName()))
        {
            valor = bookList.get(index).getNetoCompra();
        }
       
        return valor;
    }

    public boolean next() throws JRException
    {
        return ++index < bookList.size();
    }

    public void addBook(Libro book)
    {
        this.bookList.add(book);
    }
}
