package testing.android.example.com.testing.recyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import testing.android.example.com.testing.MainActivity;
import testing.android.example.com.testing.R;

public class RecyclerViewActivity extends AppCompatActivity {

    private final List<String> wordList = new ArrayList<>();

    private RecyclerView recyclerView;
    private WordListAdapter adapter;

    private FloatingActionButton addWordButton;
    private AppCompatTextView currentTime;

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

    private void bindViews(){
        currentTime = findViewById(R.id.recycler_current_time);
        addWordButton = findViewById(R.id.add_button);
    }

    private void setListeners(){
        addWordButton.setOnClickListener(__ -> addNewWord());
    }

    private void addNewWord() {
        int wordListSize = wordList.size();
        wordList.add("Added Word " + wordListSize);
        recyclerView.getAdapter().notifyItemInserted(wordListSize);
        recyclerView.smoothScrollToPosition(wordListSize);
    }

    private void createList(){
        for (int i = 0; i < 31; i++) {
            wordList.add("Word " + i);
        }
    }

    private void setCurrentTime(){
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        currentTime.setText(message);
    }

    private void createRecyclerView(){
        recyclerView = findViewById(R.id.recyclerview);
        adapter = new WordListAdapter(this, wordList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
