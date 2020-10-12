package dad.imc;

import javafx.application.Application;
import javafx.beans.binding.Binding;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

public class IMC extends Application {

	private TextField pesotxt, alturatxt;
	private Label masacorporal, indice;

	private DoubleProperty peso = new SimpleDoubleProperty(0);
	private StringProperty pesotxtProperty = new SimpleStringProperty();
	private DoubleProperty altura = new SimpleDoubleProperty(0);
	private StringProperty alturatxtProperty = new SimpleStringProperty();
	private DoubleProperty IMC = new SimpleDoubleProperty(0);
	private StringProperty imcProperty = new SimpleStringProperty();

	public void IndicadorMasaCorporal() {

		if (IMC.get() <= 0) {
			indice.setText("(peso * altura^ 2)");
			masacorporal.setText("Bajo Peso | Normal | Sobrepeso | Obeso");
			

		} else {
			indice.setText("IMC: " + IMC.get());
			if (IMC.get() < 18.5)
				masacorporal.setText("Bajo Peso");
			else if (IMC.get() >= 18.5 && IMC.get() < 25)
				masacorporal.setText("Normal");
			else if (IMC.get() >= 25 && IMC.get() < 30)
				masacorporal.setText("Sobrepeso");
			else
				masacorporal.setText("Obeso");
		}

	}

	public void calculoIMC() {
		if ((peso.get() == 0d) || (altura.get() == 0d))
			IMC.set(0d);
		else {
			Double resultado = (peso.get() / (altura.get() * altura.get()) * 1000d);
			IMC.set(Math.round(resultado * 10.0));
		}
	}

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

		pesotxtProperty.bindBidirectional(pesotxt.textProperty());
		Bindings.bindBidirectional(pesotxtProperty, peso, new NumberStringConverter());
		peso.addListener((o, ov, nv) -> calculoIMC());

		alturatxtProperty.bindBidirectional(alturatxt.textProperty());
		Bindings.bindBidirectional(alturatxtProperty, altura, new NumberStringConverter());
		altura.addListener((o, ov, nv) -> calculoIMC());

		imcProperty.bindBidirectional(textoindice.textProperty());
		Bindings.bindBidirectional(imcProperty, IMC, new NumberStringConverter());
		IMC.addListener((o, ov, nv) -> IndicadorMasaCorporal());

		// creamos un panel con disposición vertical
		VBox root = new VBox();
		root.setSpacing(5);
		root.setAlignment(Pos.CENTER);
		root.getChildren().addAll(pesoBox, alturaBox, indiceBox, indice, masacorporal);

		// creamos la escena
		Scene escena = new Scene(root, 320, 200);

		// configuramos la ventana
		primaryStage.setScene(escena);
		primaryStage.setTitle("IMC");
		primaryStage.show();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}

}
