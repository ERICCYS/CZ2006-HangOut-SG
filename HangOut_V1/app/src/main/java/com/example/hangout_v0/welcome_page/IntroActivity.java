package com.example.hangout_v0.welcome_page;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.example.hangout_v0.R;
import com.github.paolorotolo.appintro.AppIntro;

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
