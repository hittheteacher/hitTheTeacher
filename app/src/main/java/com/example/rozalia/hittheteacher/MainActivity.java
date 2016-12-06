package com.example.rozalia.hittheteacher;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

import static android.R.id.list;
import static android.R.id.switch_widget;

public class MainActivity extends Activity {

    protected void setStatusBarTranslucent(boolean makeTranslucent) {
        if (makeTranslucent) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        } else {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    private int score=0;
    private int lives=3;

    private ObjectAnimator anim1 = new ObjectAnimator();
    private ObjectAnimator anim2 = new ObjectAnimator();
    private ObjectAnimator anim3 = new ObjectAnimator();
    private ObjectAnimator anim4 = new ObjectAnimator();

    private final AnimatorSet set1 = new AnimatorSet();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        //super.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);

        //setStatusBarTranslucent(true);

        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                        | View.SYSTEM_UI_FLAG_IMMERSIVE);

        final LinearLayout ll1 = (LinearLayout) findViewById(R.id.LL1);
        final LinearLayout ll2 = (LinearLayout) findViewById(R.id.LL2);
        final LinearLayout ll3 = (LinearLayout) findViewById(R.id.LL3);
        final LinearLayout ll4 = (LinearLayout) findViewById(R.id.LL4);

        final ImageButton ib11 = (ImageButton) findViewById(R.id.IB11);
        final ImageButton ib12 = (ImageButton) findViewById(R.id.IB12);
        final ImageButton ib13 = (ImageButton) findViewById(R.id.IB13);
        final ImageButton ib21 = (ImageButton) findViewById(R.id.IB21);
        final ImageButton ib22 = (ImageButton) findViewById(R.id.IB22);
        final ImageButton ib23 = (ImageButton) findViewById(R.id.IB23);
        final ImageButton ib31 = (ImageButton) findViewById(R.id.IB31);
        final ImageButton ib32 = (ImageButton) findViewById(R.id.IB32);
        final ImageButton ib33 = (ImageButton) findViewById(R.id.IB33);
        final ImageButton ib41 = (ImageButton) findViewById(R.id.IB41);
        final ImageButton ib42 = (ImageButton) findViewById(R.id.IB42);
        final ImageButton ib43 = (ImageButton) findViewById(R.id.IB43);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        anim1 = ObjectAnimator.ofFloat(ll1, "y", -height/4, height);
        anim2 = ObjectAnimator.ofFloat(ll2, "y",  -height/4, height);
        anim3 = ObjectAnimator.ofFloat(ll3, "y",  -height/4, height);
        anim4 = ObjectAnimator.ofFloat(ll4, "y",  -height/4, height);

        configureAnimation(anim1, ib11, ib12, ib13);
        configureAnimation(anim2, ib21, ib22, ib23);
        configureAnimation(anim3, ib31, ib32, ib33);
        configureAnimation(anim4, ib41, ib42, ib43);

        /*anim1.setRepeatCount(Animation.INFINITE);

        //anim2.setStartDelay(250);
        anim2.setRepeatCount(Animation.INFINITE);

        //anim3.setStartDelay(500);
        anim3.setRepeatCount(Animation.INFINITE);

        //anim4.setStartDelay(750);
        anim4.setRepeatCount(Animation.INFINITE);*/

        setSpeed(1500);

        /*stimt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Load animation
                anim1.start();
                anim2.start();
                anim3.start();
            }
        });*/
        set1.playTogether(anim1, anim2, anim3, anim4);
        //set1.setDuration(1000);
        set1.setInterpolator(new LinearInterpolator());
        set1.start();

        /*anim1.start();
        anim2.start();
        anim3.start();
        anim4.start();*/

        setInvisible(ib11);
        setInvisible(ib12);
        setInvisible(ib13);
        setInvisible(ib21);
        setInvisible(ib22);
        setInvisible(ib23);
        setInvisible(ib31);
        setInvisible(ib32);
        setInvisible(ib33);
        setInvisible(ib41);
        setInvisible(ib42);
        setInvisible(ib43);
    }
    /*public void aniMate(Button b){
        ObjectAnimator animY = ObjectAnimator.ofFloat(b, "translationY", -1000f, 0f);
        animY.setDuration(1000);
        animY.setInterpolator(new BounceInterpolator());
        animY.setRepeatCount(0);
        animY.start();
    }*/

    public void setInvisible(final ImageButton ib){
        /*ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ib.setVisibility(View.INVISIBLE);
                Log.d("CLICKED", "You clicked to a button! :))");
            }
        });*/
        ib.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(ib.getVisibility() == View.VISIBLE){
                    ib.setVisibility(View.INVISIBLE);
                    score += 1;
                    TextView scoreTV = (TextView) findViewById(R.id.TVscore);
                    scoreTV.setText(String.valueOf(score));
                    Log.d("CLICKED", "You clicked a button! :)) "+score);
                    return true;
                }
                return false;
            }
        });
    }

    public void setSpeed(long value){
        set1.setDuration(value);

        anim1.setStartDelay(0);
        anim2.setStartDelay(set1.getDuration()/4);
        anim3.setStartDelay(set1.getDuration()/2);
        anim4.setStartDelay(set1.getDuration()*3/4);
    }

    public void configureAnimation(ObjectAnimator anim, final ImageButton ib1, final ImageButton ib2, final ImageButton ib3){

        anim.setRepeatCount(Animation.INFINITE);

        anim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                ib1.setVisibility(View.INVISIBLE);
                ib2.setVisibility(View.INVISIBLE);
                ib3.setVisibility(View.INVISIBLE);

                Random rand = new Random();
                int  n = rand.nextInt(3) + 1;
                if(n==1){
                    ib1.setVisibility(View.VISIBLE);
                } else if(n==2){
                    ib2.setVisibility(View.VISIBLE);
                } else {
                    ib3.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                Random rand = new Random();
                int  n = rand.nextInt(3) + 1;
                if(ib1.getVisibility() == View.VISIBLE || ib2.getVisibility() == View.VISIBLE || ib3.getVisibility() == View.VISIBLE){
                    Log.d("Death","Meghaltal!");
                    lives -= 1;
                    if(lives == 0){
                        ImageView ivLives1 = (ImageView) findViewById(R.id.IVsziv1);
                        ivLives1.setVisibility(View.INVISIBLE);

                        AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
                        builder1.setMessage("Your score is: "+String.valueOf(score));
                        builder1.setCancelable(false);

                        builder1.setPositiveButton(
                                "OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                        finish();
                                        System.exit(0);
                                    }
                                });

                        /*builder1.setNegativeButton(
                                "No",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });*/

                        AlertDialog yourScoreDialog = builder1.create();
                        yourScoreDialog.show();
                    } else if(lives == 2){
                        ImageView ivLives3 = (ImageView) findViewById(R.id.IVsziv3);
                        ivLives3.setVisibility(View.INVISIBLE);
                    } else if(lives == 1){
                        ImageView ivLives2 = (ImageView) findViewById(R.id.IVsziv2);
                        ivLives2.setVisibility(View.INVISIBLE);
                    }
                }
                ib1.setVisibility(View.INVISIBLE);
                ib2.setVisibility(View.INVISIBLE);
                ib3.setVisibility(View.INVISIBLE);
                if(n==1){
                    ib1.setVisibility(View.VISIBLE);
                } else if(n==2){
                    ib2.setVisibility(View.VISIBLE);
                } else {
                    ib3.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}
