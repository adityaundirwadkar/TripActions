package com.tripactions.articles.data.response;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class ArticleResponse {

    private Response response;
    private String status;
    private String copyright;

    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public Map<String, Object> getAdditionalProperties() {
        return additionalProperties;
    }

    public void setAdditionalProperties(Map<String, Object> additionalProperties) {
        this.additionalProperties = additionalProperties;
    }
}
