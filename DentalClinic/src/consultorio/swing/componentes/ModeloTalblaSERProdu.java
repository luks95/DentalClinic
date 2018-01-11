package consultorio.swing.componentes;
import java.util.List;  
import javax.swing.table.AbstractTableModel;

import org.hibernate.hql.internal.ast.tree.BooleanLiteralNode;

import consultorio.modelo.Producto;
import consultorio.modelo.Servcios_Productos;

public class ModeloTalblaSERProdu extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	private String columnas[] = { "Id", "Nombre",  "Precio", "Posee"};
	private Object datos[][] = new Object[0][0];

	public void setListaAndItems(List<Producto> lista) {
		
		datos = new Object[lista.size()][4];
		for (int i = 0; i < lista.size(); i++) {
			////
			System.out.println(lista.get(i).getNombre()+" "+lista.get(i).getB());
			////
			datos[i][0] = lista.get(i).getId();
			datos[i][1] = lista.get(i).getNombre();
			datos[i][2] = lista.get(i).getPorcentaje()+lista.get(i).getPrecioCosto();
			datos[i][3] = lista.get(i).getB();
			
			
//			if(listaItem != null){
//				
//				for (int j = 0; j < listaItem.size(); j++) {
//					if(lista.get(i).getId() == listaItem.get(j).getId()){
//					
//						datos[i][3] = true;
//					}else{
//						datos[i][3] = false;
//						
//					}
//				}
//			}	
		}
			

	}


	
	@Override
	public int getColumnCount() {
		return columnas.length;
	}

	@Override
	public int getRowCount() {
		return datos.length;
	}
	
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		 datos[rowIndex][columnIndex]=aValue;
	}

	@Override
	public Object getValueAt(int row, int col) {		
		
		return datos[row][col];
	}

	@Override
	public String getColumnName(int i) {
		return columnas[i];
	}
	
	@Override
	public Class<?> getColumnClass(int column) { 
		
		return (getValueAt(0, column).getClass());
	}
	
	public boolean recuperarEstado(int i) {
		return (boolean) datos[i][3];
	}
	
	@Override
	public boolean isCellEditable(int row, int col) {
		 if (col == 3) {
	            return true;
	        } else {
	            return false;
	        }
	}

}
