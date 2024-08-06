package Modelo;

// TODO: Auto-generated Javadoc
/**
 * The Class Producto.
 */
public class Producto {

	/** The nombre. */
	private String nombre;
	
	/** The id. */
	private String id;
	
	/** The stock. */
	private int stock;
	
	/**
	 * Instantiates a new producto.
	 *
	 * @param nombre the nombre
	 * @param id the id
	 * @param stock the stock
	 */
	public Producto(String nombre, String id, int stock) {
		super();
		this.nombre = nombre;
		this.id = id;
		this.stock = stock;
	}

	/**
	 * Gets the nombre.
	 *
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Sets the nombre.
	 *
	 * @param nombre the new nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Gets the stock.
	 *
	 * @return the stock
	 */
	public int getStock() {
		return stock;
	}

	/**
	 * Sets the stock.
	 *
	 * @param stock the new stock
	 */
	public void setStock(int stock) {
		this.stock = stock;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "Producto [nombre=" + nombre + ", id=" + id + ", stock=" + stock + "]";
	}
	
}
