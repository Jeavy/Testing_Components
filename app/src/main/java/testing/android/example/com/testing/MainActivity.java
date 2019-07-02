package testing.android.example.com.testing;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;

import java.text.SimpleDateFormat;
import java.util.Date;

import testing.android.example.com.testing.banner.Banner;
import testing.android.example.com.testing.banner.BannerActivity;
import testing.android.example.com.testing.recyclerView.RecyclerViewActivity;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.testing.MESSAGE";

    View layout;
    AppCompatButton recyclerButton;
    AppCompatButton bannerButton;
    Banner banner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        bindViews();
        listeners();
    }

    private void bindViews() {
        recyclerButton = findViewById(R.id.recycler_button);
        bannerButton = findViewById(R.id.banner_buton);
    }

    private void listeners() {
        recyclerButton.setOnClickListener(__ -> goToRecyclerTest());
        bannerButton.setOnClickListener(__ -> goToBannerTest());
    }

    private void goToRecyclerTest() {
        Intent intent = new Intent(this, RecyclerViewActivity.class);
        String currentTime = getTime();
        intent.putExtra(EXTRA_MESSAGE, currentTime);
        startActivity(intent);
    }

    private void goToBannerTest() {
        Intent intent = new Intent(this, BannerActivity.class);
        String currentTime = getTime();
        intent.putExtra(EXTRA_MESSAGE, currentTime);
        startActivity(intent);
    }

    private String getTime() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        return(formatter.format(date));
    }
}
