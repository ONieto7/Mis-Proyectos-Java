package Vista;

import java.io.IOException;

import Controlador.ControlStock;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

// TODO: Auto-generated Javadoc
/**
 * The Class Aplicacion.
 */
public class Aplicacion extends Application {

    /**
     * Start.
     *
     * @param primaryStage the primary stage
     * @throws Exception the exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AppStock.fxml"));
            Parent root = loader.load();
                     
            Scene scene = new Scene(root); 
            scene.getStylesheets().add(getClass().getResource("/Estilos.css").toExternalForm());
            
            //Tamaño minimo de la ventana principal
            primaryStage.setMinWidth(700);
            primaryStage.setMinHeight(400);
            
            primaryStage.setScene(scene);
            primaryStage.setTitle("Control de Stock");
            primaryStage.show();
            
            ControlStock controller = loader.getController();
            
            // Vincular el ancho de la tabla con el ancho de la escena
            controller.getTvStock().prefWidthProperty().bind(primaryStage.widthProperty().subtract(300));
            // Vincular la altura de la tabla con la altura de la escena
            controller.getTvStock().prefHeightProperty().bind(primaryStage.heightProperty().subtract(150));                         
            
            // Vincular el ancho de las columnas con el ancho de la tabla
            controller.getCol_Id().prefWidthProperty().bind(controller.getTvStock().widthProperty().divide(3));
            controller.getCol_Nombre().prefWidthProperty().bind(controller.getTvStock().widthProperty().divide(3));
            controller.getCol_Stock().prefWidthProperty().bind(controller.getTvStock().widthProperty().divide(3));
            
         // Impedir que las columnas se redimensionables
            controller.getCol_Id().setResizable(false);
            controller.getCol_Nombre().setResizable(false);
            controller.getCol_Stock().setResizable(false);
              
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
