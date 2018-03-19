package hillelProject.jira;

import java.text.SimpleDateFormat;
import java.util.Date;

public interface JiraVars {
    static final String baseURL = "http://jira.hillel.it:8080/";
    static final String username = "autorob";
    static final String password = "forautotests";

    static final String newIssueSummary = "AutoTest " + new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
    static final String attachmentFileLocation = "/Users/villiburduza/Desktop/";
    static final String attachmentFileName = "picture.jpg";
    static final String userEmail = "v.burdiuzha@gmail.com";
    static final String userName = "Villi";
    static final String userNick = "VilliTest";
    static final String userPass = "123456";

}