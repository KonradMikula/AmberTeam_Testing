import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class ApiTest {


    @Test
    public void getCertificate() {


        String getId = "http://localhost:9999/api/rest/v1/certificate?id=";

        System.out.println(get(getId + 5).getBody());

        get(getId + 5).then()
                .assertThat()
                .statusCode(200)
                .body("id", equalTo(5))
                .body("organization", equalTo("ELLA"));

    }

    @Test
    public void getCertificateByPathP() {


        String getId = "http://localhost:9999/api/rest/v1/certificate/";

        System.out.println(get(getId + 5).getBody());

        get(getId + 5).then()
                .assertThat()
                .statusCode(200)
                .body("id", equalTo(5))
                .body("organization", equalTo("ELLA"));

    }

    @Test
    public void postCertificate() {

        String postApi = "http://localhost:9999/api/rest/v1/certificate";
        String getId = "http://localhost:9999/api/rest/v1/certificate?id=";


        JSONObject request = new JSONObject();
        request.put("id", 9);
        request.put("organization", "TestO");
        request.put("name", "TestN");
        request.put("period", "Test5");
        request.put("trade", "TestT");


        given()
                .header("Content-Type", "application/json")
                .body(request.toString())
                .when()
                .post(postApi)
                .then().statusCode(201);


        get(getId + 9).then()
                .assertThat()
                .statusCode(200)
                .body("id", equalTo(9))
                .body("organization", equalTo("TestO"))
                .body("period", equalTo("Test5"));


    }

    @Test
    public void putCertificate() {

        String postApi = "http://localhost:9999/api/rest/v1/certificate/";

        JSONObject request = new JSONObject();
        request.put("id", 3);
        request.put("organization", "put0");
        request.put("name", "putN");
        request.put("period", "put5");
        request.put("trade", "putT");

        given()
                .header("Content-Type", "application/json")
                .body(request.toString())
                .when()
                .put(postApi + 3)
                .then()
                .statusCode(200);

        get(postApi + 3).then()
                .assertThat()
                .statusCode(200)
                .body("id", equalTo(3))
                .body("organization", equalTo("put0"))
                .body("period", equalTo("put5"));

    }

    @Test
    public void deleteCertificate() {

        String postApi = "http://localhost:9999/api/rest/v1/certificate/";

        given()
                .header("Content-Type", "application/json")
                .when().delete(postApi + 2).then().assertThat().statusCode(200);

    }



}
