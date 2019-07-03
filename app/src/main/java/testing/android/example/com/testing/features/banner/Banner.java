package testing.android.example.com.testing.features.banner;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

import testing.android.example.com.testing.R;

public class Banner extends ConstraintLayout {

    private ConstraintLayout bannerConstraint;
    private AppCompatImageView closeButton;
    private AppCompatImageView leftImage;
    private AppCompatTextView textView;

    final int BANNER_SPEED = 250;

    public Banner(@NonNull Context context) {
        super(context);
        init();
    }

    public Banner(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
        setObjectAttrs(attrs);
    }

    public Banner(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
        setObjectAttrs(attrs);
    }

    private void init() {
        inflateViews();
        bindViews();
        setListeners();
    }

    private void inflateViews() {
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutInflater.inflate(R.layout.banner, this, true);
    }

    private void setObjectAttrs(@NonNull AttributeSet attrs) {
        TypedArray attributes = getContext().obtainStyledAttributes(attrs, R.styleable.Banner);
        leftImage.setImageDrawable(attributes.getDrawable(R.styleable.Banner_Banner_left_image));
        String text = attributes.getString(R.styleable.Banner_Banner_text);
        textView.setText(text);
        attributes.recycle();
    }

    private void bindViews() {
        bannerConstraint = findViewById(R.id.banner_constraint);
        closeButton = findViewById(R.id.banner_right_button);
        textView = findViewById(R.id.banner_text);
        leftImage = findViewById(R.id.banner_left_image);
    }

    private void setListeners() {
        closeButton.setOnClickListener(__ -> hideBanner());
    }

    public void showBanner() {
        TranslateAnimation animation = new TranslateAnimation(0, 0, 0 - bannerConstraint.getHeight(), 0);
        animation.setDuration(BANNER_SPEED);
        animation.setFillAfter(true);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                bannerConstraint.setVisibility(VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        bannerConstraint.startAnimation(animation);
    }

    private void hideBanner() {
        TranslateAnimation animation = new TranslateAnimation(0, 0, 0, 0 - bannerConstraint.getHeight());
        animation.setDuration(BANNER_SPEED);
        animation.setFillAfter(true);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                bannerConstraint.setVisibility(GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        bannerConstraint.startAnimation(animation);
    }
}
