
package testing.android.example.com.testing.features.recyclerView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import testing.android.example.com.testing.R;

public class WordListAdapter extends
        RecyclerView.Adapter<WordListAdapter.WordViewHolder> {

    private final List<String> mWordList;
    private final LayoutInflater mInflater;

    private int index = -1;

    public WordListAdapter(Context context, List<String> wordList) {
        mInflater = LayoutInflater.from(context);
        this.mWordList = wordList;
    }

    @Override
    public WordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(
                R.layout.wordlist_item, parent, false);
        return new WordViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(WordViewHolder holder, int position) {
        String currentPosition = mWordList.get(position);
        holder.wordItemView.setText(currentPosition);

        setRowLinearLayoutListener(holder, position);

        if (index == position) {
            paintItem(holder);
        }
        else {
            if (holder.row_linearLayout.isSelected())
            unpaintItem(holder);
        }
    }

    private void setRowLinearLayoutListener(WordViewHolder holder, int position) {
        holder.row_linearLayout.setOnClickListener(v -> {
            index = position;
            notifyDataSetChanged();
        });
    }

    public int getSelectedIndex(){
        return this.index;
    }

    private void paintItem(WordViewHolder holder) {
        holder.row_linearLayout.setSelected(true);
        holder.wordItemView.setSelected(true);
    }


    private void unpaintItem(WordViewHolder holder) {
        holder.row_linearLayout.setSelected(false);
        holder.wordItemView.setSelected(false);
    }

    @Override
    public int getItemCount() {
        return mWordList.size();
    }


    class WordViewHolder extends RecyclerView.ViewHolder {

        final TextView wordItemView;
        LinearLayout row_linearLayout;
        WordListAdapter mAdapter;


        public WordViewHolder(View itemView, WordListAdapter adapter) {
            super(itemView);

            row_linearLayout = itemView.findViewById(R.id.linear_layout);
            wordItemView = itemView.findViewById(R.id.word);

            this.mAdapter = adapter;
        }
    }
}

