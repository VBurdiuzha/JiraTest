package hillelProject.api;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.testng.Assert;
import org.testng.annotations.Test;

import hillelProject.api.requests.Requests;


public class Tests {
    String baseURL = "http://soft.it-hillel.com.ua:3000/api/users";
    String userId = "";

    private void findUserID(String data) {
        Matcher m = Pattern.compile("\"id\":\"(\\d+)").matcher(data);
        if (m.find())
            userId = m.group(1);
    }

    private void checkContentType(String headers) {
        Assert.assertTrue(headers.contains("Content-Type: application/json"));
    }

    @Test(description = "Second requirement - getting user list")
    void getUsers() throws IOException {
        String[] responseData = Requests.sendGet(baseURL);
        Assert.assertTrue(responseData[1].contains("[{\"id\":\""));
        findUserID(responseData[1]);
        checkContentType(responseData[0]);
    }

    @Test(description = "Third requirement - saving users")
    void saveUser(String data) throws IOException {
        String[] responseData = Requests.sendPut(baseURL + userId, '{' + data + '}');
        Assert.assertTrue(Requests.getUserInfo(baseURL, userId).contains(data));
        checkContentType(responseData[0]);
    }

}