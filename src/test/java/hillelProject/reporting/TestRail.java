package hillelProject.reporting;
import java.util.HashMap;

import org.json.simple.JSONObject;

public class TestRail {
    private final APIClient client;
    private Long runID;

    public TestRail(String baseURL) {

        client = new APIClient(baseURL);
    }

    public void setCreds(String username, String password) {
        client.setUser(username);
        client.setPassword(password);
    }

    public void startRun(int projectID, String runName) throws Exception {
        HashMap<String, String> data = new HashMap<>();
        data.put("name", runName);
        JSONObject r = (JSONObject) client.sendPost(("add_run/" + projectID), data);
        this.runID = (Long) r.get("id");
    }

    public void endRun() throws Exception {
        client.sendPost(String.format("close_run/%d", this.runID), new HashMap<>());
    }

    public void setResult(Integer caseID, Integer testNGResult) throws Exception {
        HashMap<String, Integer> data = new HashMap<>();
        data.put("status_id", convertResult(testNGResult));
        client.sendPost(String.format("add_result_for_case/%d/%d", this.runID, caseID), data);
    }

    private Integer convertResult(Integer testNGResult) {
        switch (testNGResult) {
            case 1:
                return 1; // Success
            case 2:
                return 5; // Failure
            case 3:
                return 2; // Skip/Blocked
            default:
                return 4; //Retest
        }
    }
}