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

public class Controller {

    public TextField codeText;
    public TextArea textAreaInfo;

    public void getByCode(ActionEvent actionEvent) throws UnirestException {
        String code = codeText.getText();
        HttpResponse<JsonNode> response = Unirest.get("https://restcountries-v1.p.mashape.com/alpha/" + code)
                .header("X-Mashape-Key", "F0NC8jv2KumshbvwF4mmMKEvBvBup1UyBMWjsnkrbTeV7elenc")
                .header("Accept", "application/json")
                .asJson();

        JSONObject myObj = response.getBody().getObject();
        String msg = myObj.getString("name");
        textAreaInfo.setText(msg);


    }
}
