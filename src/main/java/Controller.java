import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.json.JSONArray;
import org.json.JSONObject;

import java.beans.IntrospectionException;

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


    }
}
