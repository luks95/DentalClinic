package test;

import java.util.ArrayList;
import java.util.List;

import consultorio.daos.PerfilFuncionalidadDao;
import consultorio.daos.UsuarioDao;
import consultorio.internos.GenerarFuncionalidades;
import consultorio.internos.GenerarPerfil;
import consultorio.modelo.Perfil_Funcionalidad;
import consultorio.modelo.Usuario;
import consultorio.util.Factory;

public class Regenerar {

	public static void main(String[] args) {
		Pruebausuarios();

	}
	@SuppressWarnings("unused")
	private static void regenerarFuncionalidades(){
		Factory.setUp();
		GenerarFuncionalidades.generalFuncionalidades();
	}
	
	@SuppressWarnings("unused")
	private static void regenerarPerfiles_Funciones(){
		Factory.setUp();
		GenerarPerfil.generar();
	}
	
	private static void Pruebausuarios(){
		List<Usuario> usus = new ArrayList<>();
		
		//Usuario usu = new Usuario();
		UsuarioDao daoUsu = new UsuarioDao();
		usus = daoUsu.recuperarTodo();
		/////
		List<Perfil_Funcionalidad> pf = new ArrayList<>();
		PerfilFuncionalidadDao dao = new PerfilFuncionalidadDao();
		pf = dao.recuperar(usus.get(0).getPerfil().getId());
		////
		
		System.out.println(usus.get(0).getNombre() +" "+ usus.get(0).getPerfil().getPerfilNombre()+" "
				+ pf.get(0).getFuncionalidad().getNombreFucion() +" "+ pf.get(1).getFuncionalidad().getNombreFucion());
	}
	
}
