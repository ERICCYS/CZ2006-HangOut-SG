package com.example.hangout_v0.welcome_page;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.hangout_v0.R;
import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;

public class IntroActivity extends AppIntro {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addSlide(com.example.hangout_v0.welcome_page.SampleSlide.newInstance(R.layout.intro_slide_1));
        addSlide(com.example.hangout_v0.welcome_page.SampleSlide.newInstance(R.layout.intro_slide_2));
        addSlide(com.example.hangout_v0.welcome_page.SampleSlide.newInstance(R.layout.intro_slide_3));
        addSlide(com.example.hangout_v0.welcome_page.SampleSlide.newInstance(R.layout.intro_slide_last));
        showSkipButton(true);
        setFadeAnimation();
    }

    public void onSkipPressed(Fragment currentFragment){
        super.onSkipPressed(currentFragment);
        finish();
    }

    public void onDonePressed(Fragment currentFragment){
        super.onDonePressed(currentFragment);
        finish();
    }

}
