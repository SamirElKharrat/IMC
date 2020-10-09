package dad.imc;

import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class IMC extends Application {

	private TextField pesotxt, alturatxt;
	private Label masacorporal, indice;

	private IntegerProperty peso = new SimpleIntegerProperty(0);
	private IntegerProperty altura = new SimpleIntegerProperty(0);
	private IntegerProperty IMC = new SimpleIntegerProperty(0);
	

	@Override
	public void start(Stage primaryStage) throws Exception {


		pesotxt = new TextField("0");
		pesotxt.setPrefColumnCount(4);
		
		HBox pesoBox = new HBox(5, new Label("Peso:"), pesotxt, new Label("kg"));
		pesoBox.setAlignment(Pos.BASELINE_CENTER);
		

		alturatxt = new TextField("0");
		alturatxt.setPrefColumnCount(4);
		
		HBox alturaBox = new HBox(5, new Label("Altura:"), alturatxt, new Label("cm"));
		alturaBox.setAlignment(Pos.BASELINE_CENTER);
		
        indice = new Label("(peso * altura^ 2)");
        
        Label textoindice = new Label();
        textoindice.setText("IMC:");
		
		HBox indiceBox = new HBox(5, textoindice, indice);
		indiceBox.setAlignment(Pos.BASELINE_CENTER);
		
		
		
		masacorporal = new Label("Bajo Peso | Normal | Sobrepeso | Obeso");
		
		
		
		// creamos un panel con disposición vertical
		VBox root = new VBox();
		root.setSpacing(5);
		root.setAlignment(Pos.CENTER);
		root.getChildren().addAll(pesoBox, alturaBox, indiceBox, masacorporal);

		// creamos la escena
		Scene escena = new Scene(root, 320, 200);

		// configuramos la ventana
		primaryStage.setScene(escena);
		primaryStage.setTitle("IMC");
		primaryStage.show();
	}
	
	public void onCambiarAction(ActionEvent e) {
		
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}

}
