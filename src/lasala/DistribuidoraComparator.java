

package lasala;

import java.util.Comparator;

/**
 *
 * @author ebalsa@uoc.edu
 */
public class DistribuidoraComparator implements Comparator<Distribuidora> {

    public int compare(Distribuidora d1, Distribuidora d2)
    {
        return d1.getName().compareTo(d2.getName());
    }
   

}
