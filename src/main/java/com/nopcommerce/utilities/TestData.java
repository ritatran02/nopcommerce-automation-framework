package com.nopcommerce.utilities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nopcommerce.commons.GlobalConstants;

import java.io.File;

public class TestData {

    @JsonProperty("Register")
    private RegisterData register;

    public RegisterData getRegister() {
        return register;
    }

    public static TestData getRegisterData() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(
                    DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
                    false
            );

            return mapper.readValue(
                    new File(GlobalConstants.DATA_TEST_PATH + "userData.json"),
                    TestData.class
            );
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @JsonProperty("Login")
    private LoginData login;

    public LoginData getLogin() {
        return login;
    }

    @JsonProperty("UserLogin")
    private LoginData userLogin;

    public LoginData getUserLogin() {
        return userLogin;
    }

    public static TestData getLoginData() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(
                    DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
                    false
            );

            return mapper.readValue(
                    new File(GlobalConstants.DATA_TEST_PATH + "userData.json"),
                    TestData.class
            );
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @JsonProperty("FailedLogin")
    private LoginData failedLogin;

    public LoginData getFailedLogin() {
        return failedLogin;
    }

    @JsonProperty("Search")
    private SearchData search;

    public SearchData getSearch() {
        return search;
    }

    public static TestData getSearchData() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(
                    DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
                    false
            );

            return mapper.readValue(
                    new File(GlobalConstants.DATA_TEST_PATH + "productData.json"),
                    TestData.class
            );
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @JsonProperty("Edit")
    private EditData SKU;

    public EditData getSKU() {
        return SKU;
    }

    public static TestData getEditData() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(
                    DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
                    false
            );

            return mapper.readValue(
                    new File(GlobalConstants.DATA_TEST_PATH + "productData.json"),
                    TestData.class
            );
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}