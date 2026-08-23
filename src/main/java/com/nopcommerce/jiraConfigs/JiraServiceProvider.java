package com.nopcommerce.jiraConfigs;

import net.rcarz.jiraclient.*;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class JiraServiceProvider {

    private JiraClient Jira;
    private String projectKey;
    private String jiraUrl;

    private String username;
    private String password;

    public JiraServiceProvider(String JiraUrl, String username,
                               String password, String project) {

        this.jiraUrl = JiraUrl;

        BasicCredentials creds = new BasicCredentials(username, password);
        Jira = new JiraClient(JiraUrl, creds);

        this.projectKey = project;
        this.username = username;
        this.password = password;
    }

    public void createJiraIssue(String issueType,
                                String summary,
                                String description,
                                byte[] screenshot) {

        try {
            // =========================
            // 1. CREATE JIRA ISSUE
            // =========================
            Issue.FluentCreate fluentCreate =
                    Jira.createIssue(projectKey, issueType);

            fluentCreate.field(Field.SUMMARY, summary);
            fluentCreate.field(Field.DESCRIPTION, description);

            Issue newIssue = fluentCreate.execute();

            String issueKey = newIssue.getKey();

            System.out.println("********************************************");
            System.out.println("New issue created in Jira: " + issueKey);
            System.out.println("New issue URL: "
                    + jiraUrl + "/browse/" + issueKey);
            System.out.println("********************************************");


            // =========================
            // 2. UPLOAD SCREENSHOT
            // =========================
            if (screenshot != null) {
                attachScreenshot(issueKey, screenshot);
            }

        } catch (JiraException e) {
            e.printStackTrace();
        }
    }

    private void attachScreenshot(String issueKey, byte[] screenshot) {

        try {
            String boundary = "----JiraScreenshotBoundary";

            String fileName = "Failure_Screenshot.png";

            byte[] filePart = (
                    "--" + boundary + "\r\n" +
                            "Content-Disposition: form-data; name=\"file\"; filename=\""
                            + fileName + "\"\r\n" +
                            "Content-Type: image/png\r\n\r\n"
            ).getBytes(StandardCharsets.UTF_8);

            byte[] endPart = (
                    "\r\n--" + boundary + "--\r\n"
            ).getBytes(StandardCharsets.UTF_8);

            byte[] body = new byte[
                    filePart.length +
                            screenshot.length +
                            endPart.length
                    ];

            System.arraycopy(
                    filePart, 0,
                    body, 0,
                    filePart.length
            );

            System.arraycopy(
                    screenshot, 0,
                    body, filePart.length,
                    screenshot.length
            );

            System.arraycopy(
                    endPart, 0,
                    body,
                    filePart.length + screenshot.length,
                    endPart.length
            );

            String auth = username + ":" + password;

            String encodedAuth = Base64.getEncoder()
                    .encodeToString(
                            auth.getBytes(StandardCharsets.UTF_8)
                    );

            String attachmentUrl =
                    jiraUrl + "/rest/api/3/issue/"
                            + issueKey
                            + "/attachments";

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(attachmentUrl))
                    .header(
                            "Authorization",
                            "Basic " + encodedAuth
                    )
                    .header(
                            "Accept",
                            "application/json"
                    )
                    .header(
                            "X-Atlassian-Token",
                            "no-check"
                    )
                    .header(
                            "Content-Type",
                            "multipart/form-data; boundary=" + boundary
                    )
                    .POST(
                            HttpRequest.BodyPublishers.ofByteArray(body)
                    )
                    .build();

            HttpClient client = HttpClient.newHttpClient();

            HttpResponse<String> response =
                    client.send(
                            request,
                            HttpResponse.BodyHandlers.ofString()
                    );

            System.out.println(
                    "Jira attachment response: "
                            + response.statusCode()
            );

            if (response.statusCode() == 200) {
                System.out.println(
                        "Screenshot attached successfully to Jira."
                );
            } else {
                System.out.println(
                        "Failed to attach screenshot."
                );

                System.out.println(
                        response.body()
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}