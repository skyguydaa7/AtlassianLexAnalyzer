package com.atlassian.lbbento.atlassianlexicalanalyzer.home;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lbbento on 2/05/16.
 *
 * Class that represents the parsed message
 */

public class ParsedMessageModel implements Serializable{

    private List<String> mentions;
    private List<String> emoticons;
    private List<LinkModel> links;


    public ParsedMessageModel() {
        this.mentions = new ArrayList<>();
        this.emoticons = new ArrayList<>();
        this.links = new ArrayList<>();
    }

    public List<String> getMentions() {
        return mentions;
    }

    public void setMentions(List<String> mentions) {
        this.mentions = mentions;
    }

    public List<String> getEmoticons() {
        return emoticons;
    }

    public void setEmoticons(List<String> emoticons) {
        this.emoticons = emoticons;
    }

    public List<LinkModel> getLinks() {
        return links;
    }

    public void setLinks(List<LinkModel> links) {
        this.links = links;
    }
}
