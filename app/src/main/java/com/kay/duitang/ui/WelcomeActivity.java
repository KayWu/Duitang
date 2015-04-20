package com.kay.duitang.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.kay.duitang.R;
import com.squareup.picasso.Picasso;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by mac on 15/4/20.
 */
public class WelcomeActivity extends Activity {
    @InjectView(R.id.welecome_image)
    ImageView welcomeImg;

    @InjectView(R.id.count_down)
    TextView countDown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_welcome);
        ButterKnife.inject(this);
        initView();
    }

    private void initView() {
        Picasso.with(this).load(R.drawable.welcome_img).into(welcomeImg);
        final Typeface font = Typeface.createFromAsset(getAssets(), "splash.ttf");
        countDown.setTypeface(font);
        CountDownTimer timer = new CountDownTimer(3200, 1000) {
            int num = 2;

            @Override
            public void onTick(long millisUntilFinished) {
                countDown.setText(String.valueOf(num));
                num--;
                Log.d("WelcomeActivity", num + "");
            }

            @Override
            public void onFinish() {

                Intent i = new Intent(WelcomeActivity.this, MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
                overridePendingTransition(0, 0);
                finish();

            }
        };

        timer.start();


    }


}
