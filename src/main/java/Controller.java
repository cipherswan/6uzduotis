import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import org.json.JSONArray;
import org.json.JSONObject;

import java.beans.IntrospectionException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller {

    public TextField codeField;
    public TextArea textAreaInfo;
    public TextField nameField;
    public TextField capitalField;
    public Label nameLabel;
    public Label capCityLabel;
    public Label regionLabel;
    public Label popLabel;
    public Label codeLabel;
    public TextField numberField;
    public TextArea textAreaNumbers;
    public Label numberLabel;
    public TextArea areaCurrency;
    public TextField from;
    public TextField amount;
    public TextField to;
    public TextArea quoteArea;
    public Label labelAuthor;
    public Label labelCategory;
    public Button changeCSS;
    public VBox box;
    public Hyperlink helpLink;


    public void getCountry(ActionEvent actionEvent) throws UnirestException {
        String code = codeField.getText();
       // String countryName = nameField.getText();

            HttpResponse<JsonNode> responseCode = Unirest.get("https://restcountries-v1.p.mashape.com/alpha/" + code)
                    .header("X-Mashape-Key", "F0NC8jv2KumshbvwF4mmMKEvBvBup1UyBMWjsnkrbTeV7elenc")
                    .header("Accept", "application/json")
                    .asJson();

            JSONObject myObj = responseCode.getBody().getObject();
            String msgName = myObj.getString("name");
            String msgCode = myObj.getString("alpha2Code");
            String msgCity = myObj.getString("capital");
            String msgRegion = myObj.getString("region");
            Integer msgPop = myObj.getInt("population");

            nameLabel.setText(msgName);
            codeLabel.setText(msgCode);
            capCityLabel.setText(msgCity);
            regionLabel.setText(msgRegion);
            popLabel.setText(msgPop.toString());
    }

    public void getNumber(ActionEvent actionEvent) throws UnirestException {
        String gNumber = numberField.getText();

        HttpResponse<JsonNode> responseNumber = Unirest.get("https://numbersapi.p.mashape.com/"+gNumber+"/math?fragment=true&json=true")
                .header("X-Mashape-Key", "F0NC8jv2KumshbvwF4mmMKEvBvBup1UyBMWjsnkrbTeV7elenc")
                .header("Accept", "text/plain")
                .asJson();

        JSONObject myObj = responseNumber.getBody().getObject();
        Integer number = myObj.getInt("number");
        String text = myObj.getString("text");

        numberLabel.setText(number.toString());
        textAreaNumbers.setText(text);
    }

    public void getQuote(ActionEvent actionEvent) throws UnirestException {

        HttpResponse<JsonNode> responseQuote = Unirest.get("https://andruxnet-random-famous-quotes.p.mashape.com/?cat=famous&count=1")
                .header("X-Mashape-Key", "F0NC8jv2KumshbvwF4mmMKEvBvBup1UyBMWjsnkrbTeV7elenc")
                .header("Accept", "application/json")
                .asJson();

        JSONObject myObj = responseQuote.getBody().getObject();
        String author = myObj.getString("author");
        String category = myObj.getString("category");
        String quote = myObj.getString("quote");

        labelAuthor.setText(author);
        labelCategory.setText(category);
        quoteArea.setText(quote);
    }

    public void changeTheme(ActionEvent actionEvent) {
        String style = getClass().getResource("theme01.css").toExternalForm();
        String style2 = getClass().getResource("theme02.css").toExternalForm();
        box.getStylesheets().remove(style);

        if (box.getStylesheets().contains(style2))
        {
            box.getStylesheets().remove(style2);
            box.getStylesheets().addAll(style);
        }
        else
        {
            box.getStylesheets().remove(style);
            box.getStylesheets().addAll(style2);
        }
    }

    public void getHelp(ActionEvent actionEvent) {
        String helpMessage = "To get an information about a country, type in the code of the desired country and press OK." +
                System.lineSeparator() + System.lineSeparator() +
                "To get information about your desired number, enter the number and press the button labeled 'Get information'" +
                System.lineSeparator() + System.lineSeparator() +
                "To get a random quote, just press the button labeled 'Get random quote'";

        new Alert(Alert.AlertType.INFORMATION, helpMessage).showAndWait();
    }
}
