package com.tripactions.articles.data.response;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */

public class Headline {

    private String main;
    private String printHeadline;

    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getPrintHeadline() {
        return printHeadline;
    }

    public void setPrintHeadline(String printHeadline) {
        this.printHeadline = printHeadline;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
