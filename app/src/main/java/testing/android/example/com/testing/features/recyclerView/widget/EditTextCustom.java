package testing.android.example.com.testing.features.recyclerView.widget;

import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;

public class EditTextCustom extends AppCompatEditText {

    private final String NOWORD = "-1";
    private String newWord = "This is a random word";

    public EditTextCustom(Context context) {
        super(context);
    }

    public EditTextCustom(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EditTextCustom(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setCorrectNewWord() {
        newWord = NOWORD;

        if (!this.getText().toString().matches("")) {
            newWord = this.getText().toString();
            if (newWord.equals(" ")) {
                newWord = "This is a random word";
            }
        }
        setNewWord(newWord);
    }

    public String getNewWord() {
        return newWord;
    }

    public void setNewWord(String newWord) {
        this.newWord = newWord;
    }

    public void expandEditTextAnimation() {
        Animation scaleAnimation = new ScaleAnimation(0, 1, 1, 1, Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0);
        scaleAnimation.setDuration(500);
        scaleAnimation.setFillAfter(true);
        this.startAnimation(scaleAnimation);

    }

    public void hideEditTextAnimation() {
        Animation scaleAnimation = new ScaleAnimation(1, 0, 1, 1, Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0);
        scaleAnimation.setDuration(500);
        scaleAnimation.setFillAfter(true);
        this.startAnimation(scaleAnimation);
    }
}
