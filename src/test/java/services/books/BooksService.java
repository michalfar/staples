package services.books;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static net.serenitybdd.rest.RestRequests.given;

public class BooksService {

    RestAssured request;

    public BooksService() {
        request = new RestAssured();
        request.baseURI = "http://fakerestapi.azurewebsites.net/api/";
    }

    public void isApplicationWorking() {
        request.get("/Books").then().statusCode(200);
    }

    public Response getBookById(int id) {
        Response response = request.get("/Books/" + id).then().statusCode(200).extract().response();
        return response;
    }

    public Response postBook() {
        String payload = "{\n" +
                "  \"ID\": \"3\",\n" +
                "  \"Title\": \"Book 3\",\n" +
                "  \"Description\": \"Description\",\n" +
                "  \"PageCount\": \"100\",\n" +
                "  \"Excerpt\": \"Excerpt\",\n" +
                "  \"PublishDate\": \"2020-03-07\"\n" +
                "}";

        Response response = given()
                .contentType(ContentType.JSON)
                .body(payload)
                .post("/Books")
                .then()
                .extract()
                .response();
        return response;
    }
}