package testing.android.example.com.testing.recyclerView;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import testing.android.example.com.testing.MainActivity;
import testing.android.example.com.testing.R;

public class RecyclerViewActivity extends AppCompatActivity {

    private final List<String> wordList = new ArrayList<>();

    private RecyclerView recyclerView;
    private WordListAdapter adapter;

    private boolean pressed = false;
    private final String NOWORD = "-1";
    private String newWord = "This is a random word";

    private FloatingActionButton addWordButton;
    private AppCompatTextView currentTime;
    private AppCompatEditText addNewWordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_activity);
        createList();
        bindViews();
        setListeners();
        setCurrentTime();
        createRecyclerView();
    }

    private void bindViews() {
        currentTime = findViewById(R.id.recycler_current_time);
        addWordButton = findViewById(R.id.add_floating_button);
        addNewWordEditText = findViewById(R.id.new_word_edit_text);
    }

    private void setListeners() {
        addWordButton.setOnClickListener(__ -> addNewWordPressed());
    }

    private void addNewWordPressed() {
        if (!pressed) {
            setNewWordViews(true);
            pressed = true;
        } else {
            setCorrectNewWord();
            if (getNewWord().equals(NOWORD)) {
                setNewWordViews(false);
                pressed = false;
            } else {
                addNewWordToList(getNewWord());
                pressed = false;
            }
        }
    }

    private void addNewWordToList(String editTextNewWord) {
        int wordListSize = wordList.size();

        wordList.add(getNewWord());
        setNewWordViews(false);
        recyclerView.getAdapter().notifyItemInserted(wordListSize);
        recyclerView.smoothScrollToPosition(wordListSize);
        resetEditText();
    }

    private void resetEditText() {
        addNewWordEditText.setText("");
    }

    private void setCorrectNewWord() {
        newWord = NOWORD;

        if (!addNewWordEditText.getText().toString().matches("")) {
            newWord = addNewWordEditText.getText().toString();
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


    private void createList() {
        for (int i = 0; i < 31; i++) {
            wordList.add("Word " + i);
        }
    }

    private void setCurrentTime() {
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        currentTime.setText(message);
    }

    private void createRecyclerView() {
        recyclerView = findViewById(R.id.recyclerview);
        adapter = new WordListAdapter(this, wordList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setNewWordViews(Boolean pressed) {
        if (pressed) {
            addNewWordEditText.setVisibility(View.VISIBLE);
            addWordButton.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.commons_light_blue)));
        } else {
            addNewWordEditText.setVisibility(View.INVISIBLE);
            addWordButton.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.commons_blue_dark)));
        }
    }

    @Override
    public void onBackPressed() {
        if (addNewWordEditText.getVisibility() == View.VISIBLE) {
            setNewWordViews(false);
            pressed = false;
        } else {
            super.onBackPressed();
        }
    }
}
