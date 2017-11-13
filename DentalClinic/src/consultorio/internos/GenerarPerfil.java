package consultorio.internos;

import java.util.ArrayList;
import java.util.List;

import consultorio.daos.FuncionalidadDao;
import consultorio.daos.PerfilDao;
import consultorio.modelo.Funcionalidad;
import consultorio.modelo.Perfil;
import consultorio.modelo.Perfil_Funcionalidad;

public class GenerarPerfil {

	private static Perfil perfil;
	private static Perfil perfilGeneral;
	private static Perfil perfilPropietario;
	private static Perfil perfilSecretaria;
	private static Perfil perfilBasico;
	private static List<Perfil> lista = new ArrayList<Perfil>();
	private static List<Perfil_Funcionalidad> perfilesFunciones = new ArrayList<>();

	public static void generar() {

		cargarDatos();
		for (int i = 0; i < lista.size(); i++) {
			PerfilDao dao = new PerfilDao();
			perfil = lista.get(i);
			perfil.setId(perfil.getId());
			;
			perfil.setPerfilNombre(perfil.getPerfilNombre());
			perfil.setPerfilDetalle(perfil.getPerfilDetalle());
			try {
				dao.insertarOModificar(perfil);
				dao.ejecutar();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		lista.clear();

	}

	private static void cargarDatos() {

		perfilGeneral = new Perfil();
		perfilGeneral.setId(1);
		perfilGeneral.setPerfilNombre("General");
		lista.add(perfilGeneral);

		cargarDetallePropietario();
		perfilPropietario = new Perfil();
		perfilPropietario.setId(2);
		perfilPropietario.setPerfilNombre("Propietario");
		for (int i = 0; i < perfilesFunciones.size(); i++) {
			perfilesFunciones.get(i).setPerfil(perfilPropietario);
		}
		perfilPropietario.setPerfilDetalle(perfilesFunciones);
		lista.add(perfilPropietario);

		perfilSecretaria = new Perfil();
		perfilSecretaria.setId(3);
		perfilSecretaria.setPerfilNombre("Secretaría");
		lista.add(perfilSecretaria);

		perfilBasico = new Perfil();
		perfilBasico.setId(4);
		perfilBasico.setPerfilNombre("Basico");
		lista.add(perfilBasico);

	}

	private static void cargarDetallePropietario() {
		Funcionalidad funcio = new Funcionalidad();
		List<Funcionalidad> listaFuncion = new ArrayList<>();
		FuncionalidadDao fuDao = new FuncionalidadDao();
		listaFuncion = fuDao.recuperarTodo();
		funcio = listaFuncion.get(0);
///////////   AGREGANDO FUNCIONES A LOS PERFILES
		
		Perfil_Funcionalidad detalles = new Perfil_Funcionalidad();
		detalles.setFuncionalidad(funcio);
		detalles.setAgregar(true);
		detalles.setEliminar(false);
		detalles.setReporteBasico(true);
		detalles.setModificar(true);
		detalles.setReporteComplejo(true);
		perfilesFunciones.add(detalles);
///////////   AGREGANDO FUNCIONES A LOS PERFILES	
		
		detalles = new Perfil_Funcionalidad();
		funcio = listaFuncion.get(1);
		detalles.setFuncionalidad(funcio);
		detalles.setAgregar(true);
		detalles.setEliminar(false);
		detalles.setReporteBasico(true);
		detalles.setModificar(true);
		detalles.setReporteComplejo(true);
		perfilesFunciones.add(detalles);
	}

}
