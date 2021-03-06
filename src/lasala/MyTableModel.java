

package lasala;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import java.util.List;

public class MyTableModel  extends AbstractTableModel{

    ArrayList<Object> searchResults = new ArrayList();
    ArrayList<Object> names = new ArrayList();          //Names to the columns    
    
    public MyTableModel(ArrayList<Object> searchResults, ArrayList<Object> names) {
        this.searchResults = searchResults;
        this.names = names;    
    }

    public int getColumnCount() {
      return this.names.size();
    }

    public int getRowCount() {
        return (this.searchResults.size() / this.names.size()); // Total data / Columns
    }
    
    public String getColumnName(int columnIndex) {
     
         return this.names.toArray(new String[names.size()])[columnIndex]; //Titles
      
    }
    
    public Object getValueAt(int rowIndex, int columnIndex) {  //Data / Rows + Columns
        
        return this.searchResults.get(rowIndex * this.names.size() + columnIndex );
    }

     public boolean isCellEditable(int row, int col){ //No writable cells
        if (col == 7) return true;
         
         return false;
      }
     
     public Class<?> getColumnClass(int columnIndex){
         
         return searchResults.get(columnIndex).getClass();
         
         
     }
     
     public void setValueAt(Object value, int rowIndex, int columnIndex){
      
         this.searchResults.set(rowIndex*8 + 7, value);    
         fireTableCellUpdated(rowIndex, columnIndex);
     }
     
     
}