package com.nopcommerce.jiraConfigs;

import com.nopcommerce.commons.BaseTest;
import com.nopcommerce.commons.EnvironmentConfig;
import com.nopcommerce.commons.GlobalConstants;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.nio.file.Files;

public class JiraListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {

        JiraCreateIssue jiraAnnotation = result.getMethod()
                .getConstructorOrMethod()
                .getMethod()
                .getAnnotation(JiraCreateIssue.class);

        if (jiraAnnotation != null && jiraAnnotation.isCreateIssue()) {

            WebDriver driver = BaseTest.getDriver();

            byte[] screenshot = null;
            if (driver != null) {
                screenshot = ((TakesScreenshot) driver)
                        .getScreenshotAs(OutputType.BYTES);
            }

            JiraServiceProvider jiraServiceProvider =
                    new JiraServiceProvider(
                            GlobalConstants.JIRA_SITE_URL,
                            GlobalConstants.JIRA_USERNAME,
                            EnvironmentConfig.JIRA_API_KEY,
                            GlobalConstants.JIRA_PROJECT_KEY
                    );

            String testName = result.getMethod()
                    .getConstructorOrMethod()
                    .getMethod()
                    .getName();

            String failureReason = result.getThrowable().getMessage();

            String testSteps = getFeatureSteps(testName);

            String issueDescription =
                    "AUTOMATION TEST FAILURE\n\n" +
                            "Test Case:\n" +
                            testName + "\n\n" +
                            "Test Steps:\n" +
                            testSteps + "\n\n" +
                            "Failure Reason:\n" +
                            failureReason + "\n\n" +
                            "Please check the attached screenshot.";

            String issueSummary =
                    testName + " - Failed in Automation Testing";

            jiraServiceProvider.createJiraIssue(
                    "Bug",
                    issueSummary,
                    issueDescription,
                    screenshot
            );
        }
    }

    private String getFeatureSteps(String testName) {
        try {
            File featuresDir = new File(GlobalConstants.PROJECT_PATH
                    + File.separator + "src" + File.separator + "test"
                    + File.separator + "resources" + File.separator + "features");

            if (!featuresDir.exists()) {
                return "No feature steps found.";
            }

            String tag = mapTestNameToTag(testName);

            File[] featureFiles = featuresDir.listFiles((dir, name) -> name.endsWith(".feature"));
            if (featureFiles == null) return "No feature files found.";

            for (File featureFile : featureFiles) {
                String content = Files.readString(featureFile.toPath());
                String[] lines = content.split("\n");

                boolean foundScenario = false;
                int stepCount = 0;
                StringBuilder steps = new StringBuilder();

                for (int i = 0; i < lines.length; i++) {
                    String line = lines[i].trim();

                    if (line.contains(tag)) {
                        foundScenario = true;
                        continue;
                    }

                    if (foundScenario) {
                        if (line.startsWith("Scenario:")) {
                            steps.append(line).append("\n");
                            stepCount = 0;
                            continue;
                        }
                        if (line.startsWith("Given") || line.startsWith("When")
                                || line.startsWith("Then") || line.startsWith("And")) {
                            stepCount++;
                            steps.append("  ").append(stepCount).append(". ").append(line).append("\n");
                        }
                        if ((line.startsWith("@") || line.startsWith("Scenario:"))
                                && steps.length() > 0 && !line.startsWith("Scenario:")) {
                            break;
                        }
                    }
                }

                if (steps.length() > 0) {
                    return steps.toString();
                }
            }

            return "No matching scenario found for: " + testName;

        } catch (Exception e) {
            return "Error reading feature steps: " + e.getMessage();
        }
    }

    private String mapTestNameToTag(String testName) {
        if (testName.contains("TC_02") || testName.contains("TC02")) return "@TC02";
        if (testName.contains("TC_03") || testName.contains("TC03")) return "@TC03";
        if (testName.contains("TC_06") || testName.contains("TC06")) return "@TC06";
        if (testName.contains("TC_07") || testName.contains("TC07")) return "@TC07";
        return "@" + testName;
    }

    @Override
    public void onTestSkipped(ITestResult result) {}

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {}

    @Override
    public void onStart(ITestContext context) {}

    @Override
    public void onFinish(ITestContext context) {}

    @Override
    public void onTestStart(ITestResult result) {}

    @Override
    public void onTestSuccess(ITestResult result) {}
}
