package com.atlassian.lbbento.atlassianlexicalanalyzer.main;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.atlassian.lbbento.atlassianlexicalanalyzer.R;
import com.atlassian.lbbento.atlassianlexicalanalyzer.home.HomeFragment;

/**
 * MainActivity - Holds the content
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Set MainContent
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.main_content_frame, HomeFragment.newInstance(), HomeFragment.class.getName());
        ft.commit();

    }
}
