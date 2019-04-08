package io.codelex.quiz.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.net.URL;
import java.util.List;

public class UrlList {
    private List<String> urlList;

    @JsonCreator
    public UrlList(@JsonProperty("urlList") List<String> urlList) {
        this.urlList = urlList;
    }

    public List<String> getUrlList() {
        return urlList;
    }

    public void setUrlList(List<String> urlList) {
        this.urlList = urlList;
    }
}
