package consultorio.internos;

import java.util.Date;

import java.util.List;

import consultorio.daos.PerfilDao;
import consultorio.daos.UsuarioDao;
import consultorio.modelo.Perfil;
import consultorio.modelo.Usuario;

public class GenerarUserAdmin {

	private static Usuario usuario;
	private static Date date;
	private static Perfil perfil;
	private static List<Perfil> listaPerfil;

	public static void generar() {
		UsuarioDao dao = new UsuarioDao();
		cargarDatos();
		try {
			dao.insertarOModificar(usuario);
			dao.ejecutar();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void cargarDatos() {
		usuario = new Usuario();
		date = new Date();
		PerfilDao daoPerf = new PerfilDao();
		listaPerfil = daoPerf.recuperarTodo();
		perfil = listaPerfil.get(3);

		usuario.setCodigoUsuario(1);
		usuario.setNombre("Admin");
		usuario.setApellido("Vacio");
		usuario.setPassworld("leafprogramingx");
		usuario.setFechaRegistro(date);
		usuario.setPerfil(perfil);
	}
}
