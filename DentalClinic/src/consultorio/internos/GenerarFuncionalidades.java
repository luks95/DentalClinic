package consultorio.internos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import consultorio.daos.FuncionalidadDao;
import consultorio.modelo.Funcionalidad;

public class GenerarFuncionalidades {
	private static Funcionalidad funcionalidadUsuarioGeneral;
	private static Funcionalidad funcionalidadCita;
	private static List<Funcionalidad> listaDeFunciones = new ArrayList<Funcionalidad>();
	private static Funcionalidad funcion;
	private static Date date;

	public static void generalFuncionalidades() {

		cargarLista();

		for (int i = 0; i < listaDeFunciones.size(); i++) {
			FuncionalidadDao dao = new FuncionalidadDao();
			funcion = listaDeFunciones.get(i);
			funcion.setCodigoFunciones(funcion.getCodigoFunciones());
			funcion.setNombreFucion(funcion.getNombreFucion());
			funcion.setFechaReg(funcion.getFechaReg());

			try {
				dao.insertarOModificar(funcion);
				dao.ejecutar();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		listaDeFunciones.clear();
	}

	private static void cargarLista() {
		date = new Date();
		funcionalidadUsuarioGeneral = new Funcionalidad();
		funcionalidadUsuarioGeneral.setCodigoFunciones(1);
		funcionalidadUsuarioGeneral.setNombreFucion("GestionarUsuario");
		funcionalidadUsuarioGeneral.setFechaReg(date);
		listaDeFunciones.add(funcionalidadUsuarioGeneral);
		
		date = new Date();
		funcionalidadCita = new Funcionalidad();
		funcionalidadCita.setCodigoFunciones(2);
		funcionalidadCita.setNombreFucion("Cita");
		funcionalidadCita.setFechaReg(date);
		listaDeFunciones.add(funcionalidadCita);
		
		
	}

}
