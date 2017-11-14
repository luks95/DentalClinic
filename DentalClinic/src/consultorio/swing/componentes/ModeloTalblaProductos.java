package consultorio.swing.componentes;

import java.util.List; 
import javax.swing.table.AbstractTableModel;
import consultorio.modelo.Productos;

public class ModeloTalblaProductos extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	private String columnas[] = { "Id", "Nombre", "Caracteristicas", "Costo Unitario", "Porcentaje"};
	private Object datos[][] = new Object[0][0];

	public void setLista(List<Productos> lista) {
		datos = new Object[lista.size()][5];
		for (int i = 0; i < lista.size(); i++) {
			datos[i][0] = lista.get(i).getId();
			datos[i][1] = lista.get(i).getNombre();
			datos[i][2] = lista.get(i).getCaracteris();
			datos[i][3] = lista.get(i).getPrecioCosto();
			datos[i][4] = lista.get(i).getPorcentaje();
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
	public Object getValueAt(int row, int col) {
		return datos[row][col];
	}

	@Override
	public String getColumnName(int i) {
		return columnas[i];
	}

	@Override
	public boolean isCellEditable(int row, int col) {
		return false;
	}

}
