package com.nopcommerce.utilities;
import com.fasterxml.jackson.annotation.JsonProperty;

public class EditData {
    @JsonProperty("SKU")
    private String SKU;

    public String getSKU() {
        return SKU;
    }
}
