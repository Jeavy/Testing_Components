package testing.android.example.com.testing.features.banner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;

import testing.android.example.com.testing.MainActivity;
import testing.android.example.com.testing.R;

public class BannerActivity extends AppCompatActivity {

    AppCompatButton testbutton;
    AppCompatTextView currentTime;
    Banner banner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.banner_activity);
        startBanner();
        bindViews();
        setCurrentTime();
        listeners();
    }

    private void startBanner() {
        banner = findViewById(R.id.banner);
    }

    private void bindViews() {
        currentTime = findViewById(R.id.current_time);
        testbutton = findViewById(R.id.testbutton);
    }

    private void setCurrentTime() {
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        currentTime.setText(message);
    }

    private void listeners() {
        testbutton.setOnClickListener(__ -> startAnimation());
    }

    private void startAnimation() {
        banner.showBanner();
    }
}
