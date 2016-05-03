package com.atlassian.lbbento.atlassianlexicalanalyzer.view;

import android.content.Context;
import android.databinding.ObservableInt;

import com.atlassian.lbbento.atlassianlexicalanalyzer.util.ViewUtil;

import org.parceler.Transient;

/**
 * Created by lbbento on 03/05/2016
 * Every ViewModel should extend this class
 */
public class DefaultViewModel {

    @Transient
    public Context mainActivityContext;

    //State
    public ObservableInt state =
            new ObservableInt(ViewUtil.VIEW_STATE_OPENED);


    /**
     * Default constructor - Set state as opened
     */
    public DefaultViewModel() {
        state.set(ViewUtil.VIEW_STATE_OPENED); //State opened
    }


}
