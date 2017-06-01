/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VISTA;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


/**
 *
 * @author jorge
 */
public class Inicio extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Login/FXMLLogin.fxml"));

        Scene scene = new Scene(root);

        stage.setTitle("Valmercat");
        stage.getIcons().add(new Image("images/logoMin1.png"));
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
