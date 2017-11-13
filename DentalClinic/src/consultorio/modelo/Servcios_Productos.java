package consultorio.modelo;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Servcios_Productos
 *
 */
@Entity

public class Servcios_Productos implements Serializable {

	   
	@Id
	private int id;
	private static final long serialVersionUID = 1L;

	public Servcios_Productos() {
		super();
	}   
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}
   
}
