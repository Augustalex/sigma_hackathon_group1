package translateSlang;

import com.google.gson.Gson;
import com.sun.xml.internal.bind.v2.runtime.Coordinator;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import locator.*;
import org.apache.http.entity.ContentType;

/**
 * Created by August on 2016-11-27.
 */
public class IndexController implements Initializable{

    @FXML
    private TextField translateInput;

    @FXML
    private TextField submitInput;

    @FXML
    private Button translate;

    @FXML
    private Button submit;

    @FXML
    private Label translateOutput;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        submit.setOnAction(e -> {
            post(translateInput.getText(), submitInput.getText());
        });

        translate.setOnAction(e -> {
            ObjectProperty<String> result = get(translateInput.getText());
            result.addListener((observable, oldValue, newValue) -> Platform.runLater(() -> translateOutput.setText(new Gson().fromJson(newValue, Word.class).getWord())));
        });
    }

    private ObjectProperty<String> get(String word){
        ObjectProperty<String> result = new SimpleObjectProperty<>();

        new Thread(() -> {
            try {
                System.out.println("GETTING");
                String res = (Request.Get("http://192.168.43.160:8096/v1/slang/translate?word="+word)
                        .execute().returnContent().toString());

                System.out.println(res);

                result.set(res);


            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        return result;
    }

    private void post(String slang, String officialWord){
        new Thread(() -> {
            try {
                System.out.println("POSTING");
                String res = Request.Post("http://192.168.43.160:8096/v1/slang/translate?word=" + officialWord)
                        .bodyString("\""+ slang + "\"", ContentType.TEXT_PLAIN)
                        .execute().returnContent().toString();

                System.out.println(res);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}
