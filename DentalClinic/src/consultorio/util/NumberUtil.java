package consultorio.util;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class NumberUtil {
	private static NumberFormat nf = NumberFormat.getInstance(Locale.GERMAN);

	public static String getNumeroFormateado(Double d) {
		return nf.format(d);
	}

	public static String getNumeroFormateado(Integer i) {
		return nf.format(i);
	}

	public static Double getValorDouble(String s){
		try {
			return nf.parse(s).doubleValue();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			return null; 
		}
	}
	public static int getValorInt(String s){
		try {
			return nf.parse(s).intValue();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			return (Integer) null; 
		}
	}

}
