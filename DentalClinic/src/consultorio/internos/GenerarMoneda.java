package consultorio.internos;

import java.util.ArrayList;
import java.util.List;

import consultorio.daos.MonedaDao;
import consultorio.modelo.Moneda;

public class GenerarMoneda {
	private static Moneda moneda;
	private static Moneda dolar;
	private static Moneda real;
	private static Moneda peso;

	private static List<Moneda> lista = new ArrayList<Moneda>();

	public static void generarMonedas() {
		datosCargados();
		for (int i = 0; i < lista.size(); i++) {
			MonedaDao dao = new MonedaDao();
			moneda = lista.get(i);
			moneda.setCodigoMoneda(moneda.getCodigoMoneda());
			moneda.setNombre(moneda.getNombre());
			moneda.setPais(moneda.getPais());

			try {
				dao.insertarOModificar(moneda);
				dao.ejecutar();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		lista.clear();

	}

	public static void datosCargados() {
		dolar = new Moneda();
		dolar.setCodigoMoneda(1);
		dolar.setNombre("Dolar");
		dolar.setPais("Americano");
		lista.add(dolar);

		real = new Moneda();
		real.setCodigoMoneda(2);
		real.setNombre("Real");
		real.setPais("Brasil");
		lista.add(real);

		peso = new Moneda();
		peso.setCodigoMoneda(3);
		peso.setNombre("Peso");
		peso.setPais("Argentino");
		lista.add(peso);

	}

}
