package com.atlassian.lbbento.atlassianlexicalanalyzer.home;

import java.io.Serializable;

/**
 * Created by lbbento on 2/05/16.
 * Class that represents a parsed Link.
 */

public class LinkModel implements Serializable{

    private String url;
    private String title;

    public LinkModel() {}

    public LinkModel(String url) {
        this.url = url;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
