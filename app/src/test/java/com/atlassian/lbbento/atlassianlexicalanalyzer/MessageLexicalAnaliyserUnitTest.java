package com.atlassian.lbbento.atlassianlexicalanalyzer;

import com.atlassian.lbbento.atlassianlexicalanalyzer.home.ParsedMessageModel;
import com.atlassian.lbbento.atlassianlexicalanalyzer.util.MessageLexicalAnaliyser;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class MessageLexicalAnaliyserUnitTest {

    @Test
    public void parsingIsCorrect() throws Exception {

        //usecase1
        String testMessage = "@bob @john (success) such a cool feature; https://twitter.com/jdorfman/status/430511497475670016";

        ParsedMessageModel parsedMessageModel = MessageLexicalAnaliyser.parser(testMessage);

        assertEquals(parsedMessageModel.getEmoticons().size(), 1);
        assertEquals(parsedMessageModel.getMentions().size(), 2);
        assertEquals(parsedMessageModel.getLinks().size(), 1);


        //usecase2 - without spaces and ( as normal message text
        testMessage = "@bob@john(success)such a cool (feature;https://twitter.com/jdorfman/status/430511497475670016";

        parsedMessageModel = MessageLexicalAnaliyser.parser(testMessage);

        assertEquals(parsedMessageModel.getEmoticons().size(), 1);
        assertEquals(parsedMessageModel.getMentions().size(), 2);
        assertEquals(parsedMessageModel.getLinks().size(), 1);

    }
}