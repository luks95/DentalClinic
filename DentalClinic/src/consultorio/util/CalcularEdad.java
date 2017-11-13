package consultorio.util;

import java.time.LocalDate;

import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class CalcularEdad {
	public static int calcularEdad(Date date) {
		int r;
		LocalDate hoy = LocalDate.now();
		LocalDate nacimiento = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		r = (int) ChronoUnit.YEARS.between(nacimiento, hoy);
		return r;
	}
}
