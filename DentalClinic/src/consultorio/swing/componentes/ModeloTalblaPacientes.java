package consultorio.swing.componentes;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import consultorio.modelo.Paciente;
import consultorio.util.FormatoDeFechas;

public class ModeloTalblaPacientes extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	private String columnas[] = { "Id", "Nombre", "Apellido", "Doc", "Fecha Nacimiento", "Teléfono" };
	private Object datos[][] = new Object[0][0];

	public void setLista(List<Paciente> lista) {
		datos = new Object[lista.size()][6];
		for (int i = 0; i < lista.size(); i++) {
			datos[i][0] = lista.get(i).getCodigoCli();
			datos[i][1] = lista.get(i).getNombre();
			datos[i][2] = lista.get(i).getApellido();
			datos[i][3] = lista.get(i).getDoc();
			datos[i][4] = FormatoDeFechas.dateAString(lista.get(i).getFechaNac());
			datos[i][5] = lista.get(i).getTelefono();
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
