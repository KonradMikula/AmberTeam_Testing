import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ApiTest {

    @Test
    public void getCertificate() throws UnirestException {

        String getId5 = "http://localhost:9999/api/rest/v1/certificate?id=5";

        JsonNode body = Unirest.get(getId5).asJson().getBody();

        assertTrue(body.toString().equals("{\"period\":\"5 LAT\",\"trade\":\"KOSMETYKA\",\"organization\":\"ELLA\",\"name\":\"DEPILACJA CUKROWA PASTĄ CUKROWĄ ELLA PROFESSIONAL\",\"id\":5}"));

    }

    @Test
    public void postCertificate() throws UnirestException {

        String postApi = "http://localhost:9999/api/rest/v1/certificate";
        String getId9 = "http://localhost:9999/api/rest/v1/certificate?id=9";

        JSONObject obj=new JSONObject();
        obj.put("id",new Integer(9));
        obj.put("organization","Testorganization");
        obj.put("name","TestName");
        obj.put("period","TestPeriod");
        obj.put("trade","Testtrade");



        Unirest.post(postApi)
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .body(obj)
                .asJson();


        JsonNode body = Unirest.get(getId9).asJson().getBody();

        assertTrue(body.toString().equals("{\"period\":\"TestPeriod\",\"trade\":\"Testtrade\",\"organization\":\"Testorganization\",\"name\":\"TestName\",\"id\":9}"));


    }



}
