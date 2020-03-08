package stepDefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import services.books.BooksService;

import static org.junit.Assert.assertEquals;

public class Books {

    BooksService service = new BooksService();
    Response response = null;

    @Given("^Application is working$")
    public void applicationIsWorking() {
        service.isApplicationWorking();
    }


    @When("^User gets Book record Data by \"([^\"]*)\"$")
    public void userGetsBookRecordDataBy(int bookId) {
        response = service.getBookById(bookId);
    }

    @Then("^Books record is equal to \"([^\"]*)\" and \"([^\"]*)\"$")
    public void booksRecordIsEqualToAnd(String title, int pageCount) {
        JsonPath jsonPath = response.then().extract().jsonPath();
        String actualTitle = jsonPath.get("Title");
        int actualPageCount = jsonPath.getInt("PageCount");
        assertEquals(title, actualTitle);
        assertEquals(pageCount, actualPageCount);
    }

    @When("^User creates new Book Record$")
    public void userCreatesNewBookRecord() {
        response = service.postBook();
    }

    @Then("^Books record is created$")
    public void booksRecordIsCreated() {
        int statusCode = response.then().extract().statusCode();
        assertEquals(200, statusCode);
    }
}
