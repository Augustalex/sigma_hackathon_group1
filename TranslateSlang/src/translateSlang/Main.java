package translateSlang;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by August on 2016-11-27.
 */
public class Main extends Application{

    @Override
    public void start(Stage primaryStage)  {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/translateSlang/IndexView.fxml"));
            Pane pane = loader.load();
            primaryStage.setScene(new Scene(pane, 1200, 1000));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        launch(args);
    }
}
