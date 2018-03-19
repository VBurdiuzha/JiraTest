package hillelProject.listeners;

import java.util.HashSet;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import hillelProject.WebDriverTools;
import hillelProject.reporting.TestRail;

public class TestListener implements ITestListener {

    public void onStart(ITestContext context) {
    }

    @Override
    public void onFinish(ITestContext context) {
        String baseURL = "https://hillelrob.testrail.io/";
        String projectId = "1";
        String runPrefix = "Jira";
        String username = "rvalek@intersog.com";
        String password = "hillel";

        System.out.println("Reporting to " + baseURL);

        TestRail trReport = new TestRail(baseURL);
        trReport.setCreds(username, password);

        try {
            trReport.startRun(Integer.parseInt(projectId), runPrefix + " Robert Auto - " + WebDriverTools.timestamp());

            HashSet<ITestResult> allResults = new HashSet<>();
            allResults.addAll(context.getSkippedTests().getAllResults());
            allResults.addAll(context.getFailedTests().getAllResults());
            allResults.addAll(context.getPassedTests().getAllResults());

            for (ITestResult result : allResults) {
                String testDescription = result.getMethod().getDescription();
                try {
                    int caseId = Integer.parseInt(testDescription.substring(0, testDescription.indexOf(".")));
                    trReport.setResult(caseId, result.getStatus());
                } catch (IndexOutOfBoundsException | NumberFormatException e) {
                    System.out.println(testDescription + " - Case ID missing; not reporting to TestRail.");
                    e.printStackTrace();
                }
            }

            trReport.endRun();
            System.out.println("Sent reports successfully.");
        } catch (Exception e) {
            System.out.println("Failed to send report to TestRail.");
            e.printStackTrace();
        }

    }

    @Override
    public void onTestStart(ITestResult result) {
        result.getTestContext().getSkippedTests().removeResult(result.getMethod());
    }

    public void onTestSuccess(ITestResult result) {
    }

    public void onTestFailure(ITestResult result) {
    }

    public void onTestSkipped(ITestResult result) {
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }
}