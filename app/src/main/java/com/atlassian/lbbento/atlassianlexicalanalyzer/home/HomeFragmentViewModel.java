package com.atlassian.lbbento.atlassianlexicalanalyzer.home;

import android.content.Context;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.atlassian.lbbento.atlassianlexicalanalyzer.util.MessageLexicalAnaliyser;
import com.atlassian.lbbento.atlassianlexicalanalyzer.util.ViewUtil;
import com.atlassian.lbbento.atlassianlexicalanalyzer.view.DefaultViewModel;
import com.google.gson.Gson;

import org.parceler.Parcel;
import org.parceler.Transient;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by lbbento on 2/05/16.
 * Bind the viewControllers and manage the HomeFragment Content
 */
@Parcel
public class HomeFragmentViewModel extends DefaultViewModel {

    @Transient
    Subscriber<ParsedMessageModel> parserSubscriber;

    //Edit - Watchers
    public ObservableField<String> message =
            new ObservableField<>();

    public ObservableField<String> messageOutput =
            new ObservableField<>();

    //Actions
    public void parseMessageClick(View view) {

        state.set(ViewUtil.VIEW_STATE_LOADING);

        //subscriber
        subscribe();

        Observable<ParsedMessageModel> observer =  MessageLexicalAnaliyser.parseMessage(message.get());
        observer
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(parserSubscriber);

    };


    public HomeFragmentViewModel(){}
    /**
     * HomeFragmentViewModel constructor
     * @param mainActivityContext - Context to MainActivity
     */
    public HomeFragmentViewModel(@NonNull final Context mainActivityContext) {
        super();
        this.mainActivityContext = mainActivityContext;

    }




    private void subscribe() {
        //Instantiate subscriber
        parserSubscriber = new Subscriber<ParsedMessageModel>() {
            @Override
            public void onNext(ParsedMessageModel parsedMessage) {
                //Generate json
                Gson gson = new Gson();
                String json = gson.toJson(parsedMessage);

                messageOutput.set(json);
            }

            @Override
            public void onCompleted() {
                state.set(ViewUtil.VIEW_STATE_LOADED);
            }

            @Override
            public void onError(Throwable e) {
                state.set(ViewUtil.VIEW_STATE_ERROR);
            }
        };
    }




}
