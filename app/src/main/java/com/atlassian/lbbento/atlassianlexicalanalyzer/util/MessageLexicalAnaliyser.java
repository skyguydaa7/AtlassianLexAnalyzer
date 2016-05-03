package com.atlassian.lbbento.atlassianlexicalanalyzer.util;

import com.atlassian.lbbento.atlassianlexicalanalyzer.home.LinkModel;
import com.atlassian.lbbento.atlassianlexicalanalyzer.home.ParsedMessageModel;

import java.util.StringTokenizer;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by lbbento on 02/05/16.
 *
 * Class with methods to make the message parsing and returning as an object
 */

public class MessageLexicalAnaliyser {



    /**
     * parseMessage - Get the observable - RX
     *
     * @param message - message to be parsed
     * @return Observable<ParsedMessageModel>
     */
    public static Observable<ParsedMessageModel> parseMessage(final String message) {

        Observable<ParsedMessageModel> myObservable = Observable.create(
                new Observable.OnSubscribe<ParsedMessageModel>() {
                    @Override
                    public void call(Subscriber<? super ParsedMessageModel> messageReturn) {

                        try {

                            if (!message.isEmpty()) {
                                ParsedMessageModel parsedMessage = parser(message);


                                for (LinkModel link : parsedMessage.getLinks()) {
                                    try {
                                        link.setTitle(NetworkUtil.getTitle(link.getUrl()));
                                    }catch (Exception e) {
                                        e.printStackTrace();
                                        break; //Just ignore and log if there is an error getting the page title
                                    }

                                }


                                messageReturn.onNext(parsedMessage);

                            }

                        }catch (Exception e) {
                            e.printStackTrace();
                            messageReturn.onError(e);
                        }


                        messageReturn.onCompleted();
                    }
                }
        );
        return myObservable;
    }


    /**
     *
     * @param message - Message to be parsed
     * @return ParsedMessageModel object containing mentions, emoticons and urls(the page title will be set afterwards).
     */
    public static ParsedMessageModel parser(String message) {
        //TODO


        ParsedMessageModel parsedMessage = new ParsedMessageModel();
        //Analyzing each token because it wasn't clear if it has to be a space between each token or it could be all together without breaking chars.. Doing it
        //this way it works for all cases, even tough it's slower.


        StringTokenizer stringTokenizer = new StringTokenizer(message, "@() ", true);
        String fallback = "";
        String currentToken;

        while (stringTokenizer.hasMoreTokens() || !fallback.isEmpty()) {

            if (!fallback.isEmpty()) {
                currentToken = fallback;
                fallback = "";
            }else
                currentToken = stringTokenizer.nextToken();

            if (currentToken.equals("@")) {
                if (stringTokenizer.hasMoreTokens()) {
                    String possibleMention = stringTokenizer.nextToken();
                    if (isValidMention(possibleMention))
                        parsedMessage.getMentions().add(possibleMention);
                }
            }else if (currentToken.equals("(")) {
                    if (stringTokenizer.hasMoreTokens()) {
                        String possibleEmoticon = stringTokenizer.nextToken();

                        if (isValidEmoticon(possibleEmoticon) && stringTokenizer.hasMoreTokens()) {
                            //Even if it's a valid word, it has to have a )
                            String possibleCloseStatement = stringTokenizer.nextToken();
                            if (possibleCloseStatement.equals(")"))
                                parsedMessage.getEmoticons().add(possibleEmoticon);
                            else
                                fallback = possibleCloseStatement;
                        }else
                            fallback = possibleEmoticon;

                    }

            }else if (currentToken.contains("http")) {
                String possibleUrl = currentToken.substring(currentToken.indexOf("http"), currentToken.length());
                if (isValidUrl(possibleUrl))
                    parsedMessage.getLinks().add(new LinkModel(possibleUrl));
            }

        }

        return parsedMessage;
    }


    /**
     * Check whether it's a valid mention
     * @param mention
     * @return boolean valid or not
     */
    private static boolean isValidMention(String mention) {
        if (mention == null)
            return false;
        if (mention.isEmpty())
            return false;
        if (mention.trim().length() <= 0)
            return false;

        return true;
    }

    /**
     * Check whether it's a valid emoticon
     * @param emoticon
     * @return boolean valid or not
     */
    private static boolean isValidEmoticon(String emoticon) {
        if (emoticon == null)
            return false;
        if (emoticon.isEmpty())
            return false;
        if (emoticon.trim().length() <= 0)
            return false;
        if (emoticon.length() > 15)
            return false;

        return true;
    }

    /**
     * Check whether it's a valid url
     * @param url
     * @return boolean valid or not
     */
    private static boolean isValidUrl(String url) {
        if (url == null)
            return false;
        if (url.isEmpty())
            return false;
        if (url.trim().length() <= 0)
            return false;
        if (!url.contains("http"))
            return false;

        return true;
    }

}
