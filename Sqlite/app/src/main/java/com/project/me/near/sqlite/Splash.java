package com.project.me.near.sqlite;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class Splash extends AppCompatActivity {

    Typeface typeface;

    TextView textView;

    ImageView imageView;

    Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //get the text
        //textView = (TextView) findViewById(R.id.textSplash);

        imageView = (ImageView) findViewById(R.id.imageViewSale);

        //define the type face for the font.
        //typeface = Typeface.createFromAsset(getAssets(), "fonts/SansationLight.ttf");

        //set the typeface.
        //textView.setTypeface(typeface);


        //load the animation and pass in the context of the activity to animate,
        //and the file holding the animation properties.
        animation = AnimationUtils.loadAnimation(this, R.anim.splash);

        //set the animation to a specific object on the ui.
        //textView.setAnimation(animation);

        imageView.setAnimation(animation);

        //finally set the animation listener.
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                //first kill the splash activity
                finish();

                //then launch the activity of your choice.
                startActivity(new Intent(getApplicationContext(), Prologue.class));
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

}
