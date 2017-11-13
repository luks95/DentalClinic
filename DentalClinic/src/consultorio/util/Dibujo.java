package consultorio.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Dibujo {

	FileInputStream entrada;
	FileOutputStream salida;
	File archivo;

	public byte[] abreImagen(File archivo) {
		byte[] byteImg = new byte[1024 * 100];
		try {
			entrada = new FileInputStream(archivo);
			entrada.read(byteImg);
		} catch (Exception e) {

		}

		return byteImg;
	}
}
