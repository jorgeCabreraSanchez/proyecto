/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author jorge
 */
public class Proyecto extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Inicio.fxml"));

        Scene scene = new Scene(root);

        stage.setTitle("Valmercat");
        stage.getIcons().add(new Image("proyecto/images/logoMin1.png"));
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the ººººººººcommand line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
