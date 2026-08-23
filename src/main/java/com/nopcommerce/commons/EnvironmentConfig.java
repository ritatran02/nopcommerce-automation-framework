package com.nopcommerce.commons;

public class EnvironmentConfig {
    public static final String JIRA_API_KEY = System.getenv("JIRA_KEY");

    public static void main(String[] args){
        System.out.println(JIRA_API_KEY);
    }
}
