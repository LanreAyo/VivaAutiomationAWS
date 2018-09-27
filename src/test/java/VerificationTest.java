
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Random;


public class VerificationTest {
    Random randomNumber = new Random();

    public String unregisteredUser = "7788778877"+randomNumber.nextInt(100)+randomNumber.nextInt(200);
    public String newUser = "7788778977"+randomNumber.nextInt(100)+randomNumber.nextInt(200);
    public String testcountry = "gb";


    @Test
    public void GetRoot(){

        RestAssured.baseURI = "https://auzp14e5v2.execute-api.us-east-1.amazonaws.com/";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.request(Method.GET, "/staging");
        String responseBody = response.getBody().asString();
        System.out.println("Response Body is =>  " + responseBody);
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode,403);

    }

    @Test
    public void GetHealth(){
        // Specify the base URL to the RESTful web service
        RestAssured.baseURI = "https://auzp14e5v2.execute-api.us-east-1.amazonaws.com/staging";
        // Get the RequestSpecification of the request sent to the server.
        RequestSpecification httpRequest = RestAssured.given();
        //Make a request to the server by specifying the method Type and the method URL.
        Response response = httpRequest.request(Method.GET, "/health");
        // Retrieve the body of the Response
        String responseBody = response.getBody().asString();
        System.out.println("Response Body is =>  " + responseBody);
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode,200);
        // First get the JsonPath object instance from the Response interface
        JsonPath jsonPathEvaluator = response.jsonPath();
        Assert.assertEquals(jsonPathEvaluator.get("data.ping").toString(),"pong");

    }

    @Test
    public void GetJwtEncode(){

        RestAssured.baseURI = "https://auzp14e5v2.execute-api.us-east-1.amazonaws.com/staging/jwt";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.request(Method.GET, "/encode?userId="+unregisteredUser+"&country="+testcountry+"&status=pending");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode,200);
        String responseBody = response.getBody().asString();
        System.out.println("Response Body is =>  " + responseBody);

    }

    @Test
    public void GetJwtDecode(){
        //encode jwt
        RestAssured.baseURI = "https://auzp14e5v2.execute-api.us-east-1.amazonaws.com/staging/jwt";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.request(Method.GET, "/encode?userId="+unregisteredUser+"&country="+testcountry+"&status=pending");
        JsonPath jsonPathEvaluator = response.jsonPath();
        String token = jsonPathEvaluator.get("data.token").toString();
        //decode jwt
        RestAssured.baseURI = "https://auzp14e5v2.execute-api.us-east-1.amazonaws.com/staging/jwt";
        RequestSpecification httpRequest2 = RestAssured.given();
        Response response2 = httpRequest2.request(Method.GET, "/decode?q="+token);
        int statusCode = response2.getStatusCode();
        Assert.assertEquals(statusCode,200);
        String responseBody = response2.getBody().asString();
        System.out.println("Response Body is =>  " + responseBody);
        JsonPath jsonPathEvaluator2 = response2.jsonPath();
        String user =jsonPathEvaluator2.get("data.userId").toString();
        Assert.assertEquals(user,unregisteredUser);

    }

    @Test
    public void GetUserStatus_CountryMissing(){
        //Get - encode JWT
        RestAssured.baseURI = "https://auzp14e5v2.execute-api.us-east-1.amazonaws.com/staging/jwt";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.request(Method.GET, "/encode?userId="+unregisteredUser);
        JsonPath jsonPathEvaluator = response.jsonPath();
        String token = jsonPathEvaluator.get("data.token").toString();
        //Get - user status
        RestAssured.baseURI = "https://auzp14e5v2.execute-api.us-east-1.amazonaws.com/staging/user/status";
        RequestSpecification httpRequest2 = RestAssured.given();
        Response response2 = httpRequest2.request(Method.GET, "/get?q="+token);

        int statusCode = response2.getStatusCode();
        Assert.assertEquals(statusCode,400);
        System.out.println("Status message " + response2.body().asString());
        JsonPath jsonPathEvaluator2 = response2.jsonPath();
        String data =jsonPathEvaluator2.get("data").toString();
        Assert.assertEquals(data,"Missing the following field in the JWT: country");

    }

    @Test
    public void GetUserStatusNotFound(){
        //Get - encode JWT
        RestAssured.baseURI = "https://auzp14e5v2.execute-api.us-east-1.amazonaws.com/staging/jwt";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.request(Method.GET, "/encode?userId="+unregisteredUser+"&country="+testcountry);
        JsonPath jsonPathEvaluator = response.jsonPath();
        String token = jsonPathEvaluator.get("data.token").toString();
        //Get - user status
        RestAssured.baseURI = "https://auzp14e5v2.execute-api.us-east-1.amazonaws.com/staging/user/status";
        RequestSpecification httpRequest2 = RestAssured.given();
        Response response2 = httpRequest2.request(Method.GET, "/get?q="+token);

        int statusCode = response2.getStatusCode();
        Assert.assertEquals(statusCode,200);
        System.out.println("Status message " + response2.body().asString());
        JsonPath jsonPathEvaluator2 = response2.jsonPath();
        String Status =jsonPathEvaluator2.get("data.status").toString();
        Assert.assertEquals(Status,"not_found");

    }

    @Test
    public void GetUserStatusPending(){
        String status = "pending";
        //Get - encode JWT
        RestAssured.baseURI = "https://auzp14e5v2.execute-api.us-east-1.amazonaws.com/staging/jwt";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.request(Method.GET, "/encode?userId="+unregisteredUser+"&country="+testcountry+"&status="+status);
        JsonPath jsonPathEvaluator = response.jsonPath();
        String token = jsonPathEvaluator.get("data.token").toString();
        //Start sets user status to pending
        RestAssured.baseURI = "https://auzp14e5v2.execute-api.us-east-1.amazonaws.com/staging/start/";
        RequestSpecification httpRequest2 = RestAssured.given();
        Response response2 = httpRequest2.request(Method.GET, "/?q="+token);

        int statusCode = response2.getStatusCode();
        Assert.assertEquals(statusCode,200);
        System.out.println("Status message " + response2.body().asString());
        JsonPath jsonPathEvaluator2 = response2.jsonPath();
        String Status =jsonPathEvaluator2.get("data.status").toString();
        Assert.assertEquals(Status,status);

    }

    @Test
    public void SetUserStatus(){
        String status = "verified";
        //Get - encode JWT
        RestAssured.baseURI = "https://auzp14e5v2.execute-api.us-east-1.amazonaws.com/staging/jwt";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.request(Method.GET, "/encode?userId="+unregisteredUser+"&country="+testcountry+"&status="+status);
        JsonPath jsonPathEvaluator = response.jsonPath();
        String token = jsonPathEvaluator.get("data.token").toString();
        //Set - user status to verified
        RestAssured.baseURI = "https://auzp14e5v2.execute-api.us-east-1.amazonaws.com/staging/user/status/set/";
        RequestSpecification httpRequest2 = RestAssured.given();
        Response response2 = httpRequest2.request(Method.GET, "/?q="+token+"&status="+status);

        int statusCode = response2.getStatusCode();
        Assert.assertEquals(statusCode,200);
        System.out.println("Status message " + response2.body().asString());
        JsonPath jsonPathEvaluator2 = response2.jsonPath();
        String Status =jsonPathEvaluator2.get("data.status").toString();
        Assert.assertEquals(Status,status);

    }
    @Test
    public void SetUserInvalid_MissingStatus(){
        //Get - encode JWT
        RestAssured.baseURI = "https://auzp14e5v2.execute-api.us-east-1.amazonaws.com/staging/jwt";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.request(Method.GET, "/encode?userId="+newUser+"&country="+testcountry);
        JsonPath jsonPathEvaluator = response.jsonPath();
        String token = jsonPathEvaluator.get("data.token").toString();
        //Set - user status
        RestAssured.baseURI = "https://auzp14e5v2.execute-api.us-east-1.amazonaws.com/staging/user/status/set/";
        RequestSpecification httpRequest2 = RestAssured.given();
        Response response2 = httpRequest2.request(Method.GET, "/?q="+token);

        int statusCode = response2.getStatusCode();
        Assert.assertEquals(statusCode,400);
        JsonPath jsonPathEvaluator2 = response2.jsonPath();
        String data =jsonPathEvaluator2.get("data").toString();
        System.out.println("Response:" + data);
        Assert.assertEquals(data,"Missing the following field in the JWT: status");

    }
    @Test
    public void SetUserInvalid_MissingToken(){
        //Set - user status
        RestAssured.baseURI = "https://auzp14e5v2.execute-api.us-east-1.amazonaws.com/staging/user/status/set/";
        RequestSpecification httpRequest2 = RestAssured.given();
        Response response2 = httpRequest2.request(Method.GET, "");

        int statusCode = response2.getStatusCode();
        Assert.assertEquals(statusCode,400);
        JsonPath jsonPathEvaluator2 = response2.jsonPath();
        String data =jsonPathEvaluator2.get("data").toString();
        System.out.println("Response:" + data);
        Assert.assertEquals(data,"We need a JWT token for this request. It should be in the QS or the body");

    }

    @Test
    public void SetUserInvalid_token(){
        //Get - encode JWT
        RestAssured.baseURI = "https://auzp14e5v2.execute-api.us-east-1.amazonaws.com/staging/jwt";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.request(Method.GET, "/encode?userId="+newUser+"&country="+testcountry);
        JsonPath jsonPathEvaluator = response.jsonPath();
        String token = jsonPathEvaluator.get("data.token").toString();
        //Set - user status
        RestAssured.baseURI = "https://auzp14e5v2.execute-api.us-east-1.amazonaws.com/staging/user/status/set/";
        RequestSpecification httpRequest2 = RestAssured.given();
        Response response2 = httpRequest2.request(Method.GET, "/?q="+token+"23&status=pending");

        int statusCode = response2.getStatusCode();
        Assert.assertEquals(statusCode,400);
        JsonPath jsonPathEvaluator2 = response2.jsonPath();
        String data =jsonPathEvaluator2.get("data").toString();
        System.out.println("Response: " + data);
        Assert.assertEquals(data,"invalid signature");

    }
}
