package testing.android.example.com.testing.features.recyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.util.ArrayList;
import java.util.List;

import testing.android.example.com.testing.MainActivity;
import testing.android.example.com.testing.R;
import testing.android.example.com.testing.features.recyclerView.widget.EditTextCustom;

public class RecyclerViewActivity extends AppCompatActivity {

    private static String WORDS_RESTORE_KEY = "WORDS_RESTORE_KEY";

    private List<String> wordList = new ArrayList<>();

    private RecyclerView recyclerView;
    private WordListAdapter adapter;
    private InputMethodManager keyboard;

    private SharedPreferences myPreferences;
    private String sharedPref = "com.example.testing.sharedprefs";

    private final int LISTSIZE = 31;
    private final String NOWORD = "-1";

    private boolean pressed = false;
    private String newWord = "This is a random word";

    private FloatingActionButton addWordButton;
    private FloatingActionButton restart_floating_button;
    private AppCompatTextView currentTime;
    private EditTextCustom addNewWordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_activity);
        init(savedInstanceState);
        checkSharedPreferences();
    }

    private void init(Bundle savedInstanceState) {
        bindViews();
        setListeners();
        createList(savedInstanceState);
        createRecyclerView();
        setCurrentTime();
    }

    private void bindViews() {
        currentTime = findViewById(R.id.current_time);
        addWordButton = findViewById(R.id.add_floating_button);
        addNewWordEditText = findViewById(R.id.new_word_edit_text);
        restart_floating_button = findViewById(R.id.restart_floating_button);
        keyboard = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
    }

    private void setListeners() {
        addWordButton.setOnClickListener(__ -> addNewWordPressed());
        restart_floating_button.setOnClickListener(__ -> restartList());
    }

    private void createList(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            wordList = savedInstanceState.getStringArrayList(WORDS_RESTORE_KEY);
        } else {
            for (int i = 0; i < LISTSIZE; i++) {
                wordList.add("Word " + i);
            }
        }
    }

    private void refreshList() {
        for (int i = 0; i < LISTSIZE; i++) {
            wordList.add("Word " + i);
        }
    }

    private void addNewWordPressed() {
        if (!pressed) {
            setNewWordViews(true);
            pressed = true;
        } else {
            addNewWordEditText.setCorrectNewWord();
            if (addNewWordEditText.getNewWord().equals(NOWORD)) {
                setNewWordViews(false);
                closeKeyboard();

                pressed = false;
            } else {
                addNewWordToList(addNewWordEditText.getNewWord());
                pressed = false;
            }
        }
    }

    private void setNewWordViews(Boolean pressed) {
        if (pressed) {
            addNewWordEditText.expandEditTextAnimation();
            addNewWordEditText.setVisibility(View.VISIBLE);
            addWordButton.setSelected(true);
            showKeyboard();
        } else {
            addNewWordEditText.hideEditTextAnimation();
            addNewWordEditText.setVisibility(View.INVISIBLE);
            addWordButton.setSelected(false);
        }
    }

    private void addNewWordToList(String editTextNewWord) {
        int wordListSize = wordList.size();

        wordList.add(addNewWordEditText.getNewWord());
        setNewWordViews(false);
        recyclerView.getAdapter().notifyItemInserted(wordListSize);
        recyclerView.smoothScrollToPosition(wordListSize);
        resetEditText();
        closeKeyboard();
    }

    private void resetEditText() {
        addNewWordEditText.setText("");
    }

    public void showKeyboard() {
        keyboard.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
        addNewWordEditText.requestFocus();
    }

    public void closeKeyboard() {
        View view = this.getCurrentFocus();
        keyboard.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    private void createRecyclerView() {
        recyclerView = findViewById(R.id.recyclerview);
        adapter = new WordListAdapter(this, wordList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setCurrentTime() {
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        currentTime.setText(message);
    }

    private void restartList() {
        SharedPreferences.Editor preferencesEditor = myPreferences.edit();
        preferencesEditor.clear();
        preferencesEditor.apply();
        wordList.clear();
        refreshList();
        createRecyclerView();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        ArrayList<String> newWordList = new ArrayList<>(wordList);

        outState.putStringArrayList(WORDS_RESTORE_KEY, newWordList);
    }

    //      SHARED PREFERENCES TEST
    private void checkSharedPreferences() {
        myPreferences = getSharedPreferences(sharedPref, MODE_PRIVATE);
        if (!myPreferences.getAll().isEmpty()) {
            for (int i = 0; i < myPreferences.getAll().size(); i++) {
                wordList.add(myPreferences.getString("Word" + (i + LISTSIZE), ""));
            }
        }
    }

    @Override
    protected void onDestroy() {
        if (wordList.size() > LISTSIZE) {
            SharedPreferences.Editor preferencesEditor = myPreferences.edit();
            for (int i = LISTSIZE; i < wordList.size(); i++) {
                preferencesEditor.putString("Word" + String.valueOf(i), wordList.get(i));
                preferencesEditor.apply();
            }
        }
        super.onDestroy();
    }

}

