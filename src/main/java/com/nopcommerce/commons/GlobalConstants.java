package com.nopcommerce.commons;

import java.io.File;

public class GlobalConstants {
    public static final String PROJECT_PATH = System.getProperty("user.dir");

    public static final long LONG_TIMEOUT = 30;

    public static final String UPLOAD_PATH = PROJECT_PATH + File.separator +"uploadFiles" + File.separator;

    public static final String DATA_TEST_PATH =
            PROJECT_PATH + File.separator + "src" + File.separator
                    + "test" + File.separator + "resources" + File.separator
                    + "data" + File.separator;
    public static final String ENVIRONMENT_CONFIG_PATH = PROJECT_PATH + File.separator + "environmentConfig" + File.separator;

    public static final String JIRA_SITE_URL = "https://thanhngan02.atlassian.net/" ;
    public static final String JIRA_USERNAME = "thanhngan060295@gmail.com";
    public static final String JIRA_PROJECT_KEY = "RT02";
}
