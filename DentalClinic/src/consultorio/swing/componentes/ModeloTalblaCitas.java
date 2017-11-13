package consultorio.swing.componentes;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import consultorio.modelo.Cita;
import consultorio.util.FormatoDeFechas;

public class ModeloTalblaCitas extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	private String columnas[] = { "Id", "Paciente", "Fecha", "Hora", "Doctor", "Estado" };
	private Object datos[][] = new Object[0][0];

	public void setLista(List<Cita> lista) {
		datos = new Object[lista.size()][6];
		for (int i = 0; i < lista.size(); i++) {
			datos[i][0] = lista.get(i).getId();
			datos[i][1] = lista.get(i).getPaciente().getNombre() +" "+lista.get(i).getPaciente().getApellido();
			datos[i][2] = FormatoDeFechas.dateAString(lista.get(i).getFechaCita());
			datos[i][3] = FormatoDeFechas.hora(lista.get(i).getHora());
			datos[i][4] = lista.get(i).getDoctor().getNombre() +" "+ lista.get(i).getDoctor().getApellido();
			
			if(lista.get(i).getEstado() == 0){
				datos[i][5] = "Pendiente";
			}else if(lista.get(i).getEstado() == 1){
				datos[i][5] = "Cerrado";
			}else{
				datos[i][5] = "Cancelado";
			}
			
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
