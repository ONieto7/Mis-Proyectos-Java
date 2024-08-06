package Controlador;

import java.util.Optional;

import Modelo.Producto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

// TODO: Auto-generated Javadoc
/**
 * The Class ControlStock.
 */
public class ControlStock {

    /** The btn agregar. */
    @FXML
    private Button btnAgregar;

    /** The btn modificar. */
    @FXML
    private Button btnModificar;
    
    /** The btn buscar. */
    @FXML
    private Button btnBuscar;

    /** The txt id. */
    @FXML
    private TextField txtId;
       
    /** The txt nombre. */
    @FXML
    private TextField txtNombre;
      
    /** The txt stock. */
    @FXML
    private TextField txtStock;

    /** The tv stock. */
    @FXML
    private TableView<Producto> tvStock;
    
    /**
     * Gets the tv stock.
     *
     * @return the tv stock
     */
    public TableView<Producto> getTvStock() {
        return tvStock;
    }

    /** The col id. */
    @FXML
    private TableColumn<Producto, String> col_Id;
    
    /**
     * Gets the col id.
     *
     * @return the col id
     */
    public TableColumn<Producto, String> getCol_Id() {
        return col_Id;
    }

    /** The col nombre. */
    @FXML
    private TableColumn<Producto, String> col_Nombre;
    
    /**
     * Gets the col nombre.
     *
     * @return the col nombre
     */
    public TableColumn<Producto, String> getCol_Nombre() {
        return col_Nombre;
    }

    /** The col stock. */
    @FXML
    private TableColumn<Producto, Integer> col_Stock;
    
    /**
     * Gets the col stock.
     *
     * @return the col stock
     */
    public TableColumn<Producto, Integer> getCol_Stock() {
        return col_Stock;
    }

    /** The lista productos. */
    private ObservableList<Producto> listaProductos;

    /**
     * Initialize.
     */
    @FXML
    private void initialize() {
        listaProductos = FXCollections.observableArrayList();

        col_Id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_Nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        col_Stock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        
        limitarLongitudCampo(txtId, 10); 
        limitarLongitudCampo(txtNombre, 16);
        limitarLongitudCampo(txtStock, 5);

        tvStock.setItems(listaProductos); 
        
    }
    
    /**
     * Agregar stock.
     */
    @FXML
    private void agregarStock() {
        String id = txtId.getText();
        String nombre = txtNombre.getText();
        String stockStr = txtStock.getText();

        if (id.isEmpty() || nombre.isEmpty() || stockStr.isEmpty()) {
            mostrarAlerta("Error", "Todos los campos deben estar llenos");
            return;
        }

        int stock;
        try {
            stock = Integer.parseInt(stockStr);
        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "El campo stock debe ser un número");
            return;
        }

        for (Producto producto : listaProductos) {
            if (producto.getId().equals(id)) {
                mostrarAlerta("Error", "El identificador ya existe");
                return;
            }
        }

        Producto nuevoProducto = new Producto(nombre, id, stock);
        listaProductos.add(nuevoProducto);
        limpiarCampos();
    }

	/**
	 * On mouse clicked tabla.
	 *
	 * @param event the event
	 */
	@FXML
    private void onMouseClickedTabla(MouseEvent event) {
    	if (event.getClickCount() ==2) {
        Producto productoSeleccionado = tvStock.getSelectionModel().getSelectedItem();
        if (productoSeleccionado != null) {
            txtId.setText(productoSeleccionado.getId());
            txtNombre.setText(productoSeleccionado.getNombre());
            txtStock.setText(String.valueOf(productoSeleccionado.getStock()));
            txtId.setDisable(true);
        }
      }
    }

    /**
     * Modif stock.
     */
    @FXML
    private void ModifStock() {
        Producto productoSeleccionado = tvStock.getSelectionModel().getSelectedItem();
        if (productoSeleccionado == null) {
            mostrarAlerta("Error", "No hay ningún producto seleccionado");
            return;
        }

        String nombre = txtNombre.getText();
        String stockStr = txtStock.getText();

        if (nombre.isEmpty() || stockStr.isEmpty()) {
            mostrarAlerta("Error", "Los campos nombre y stock deben estar llenos");
            return;
        }

        int stock;
        try {
            stock = Integer.parseInt(stockStr);
        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "El campo stock debe ser un número");
            return;
        }

        productoSeleccionado.setNombre(nombre);
        productoSeleccionado.setStock(stock);
        tvStock.refresh();
        limpiarCampos();
        txtId.setDisable(false);
    }
    
    /**
     * Limitar longitud campo.
     *
     * @param campoTexto the campo texto
     * @param maxLength the max length
     */
    //Si la longitud excede el maximo, volver al valor anterior
    private void limitarLongitudCampo(TextField campoTexto, int maxLength) {
        campoTexto.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > maxLength) {
                campoTexto.setText(oldValue);
            }
        });
    }

    
    /**
     * Buscar tabla.
     */
    public void buscarTabla() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Buscar Producto");
        dialog.setHeaderText("Introduce el nombre del producto:");
        dialog.setContentText("Nombre:");
        
        Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
        stage.setResizable(false);

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(nombre -> {
            
            for (Producto producto : listaProductos) {
                if (producto.getNombre().equals(nombre)) {
                    
                    tvStock.getSelectionModel().select(producto);              
                    txtId.setText(producto.getId());
                    txtNombre.setText(producto.getNombre());
                    txtStock.setText(String.valueOf(producto.getStock()));
                    txtId.setDisable(true); 
                    
                    stage.close();
                    return; 
                }
            } 
            mostrarAlerta("Producto no encontrado", "No se encontró un producto con el nombre: " + nombre);
        });
    }

    /**
     * Limpiar campos.
     */
    //Deja en blanco los campos de txt
    private void limpiarCampos() {
        txtId.clear();
        txtNombre.clear();
        txtStock.clear();
        txtId.setDisable(false);
    }
    
    /**
     * Mostrar alerta.
     *
     * @param titulo the titulo
     * @param mensaje the mensaje
     */
    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    
}
