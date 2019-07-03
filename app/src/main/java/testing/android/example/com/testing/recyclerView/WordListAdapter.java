
package testing.android.example.com.testing.recyclerView;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
    private int lastIndex = -1;


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
       // Log.e("test3", "Holder to String: " + holder.toString());
       // Log.e("test3", "Holder Id: " + String.valueOf(holder.getItemId()));

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
//            if(index != -1) {
//                unpaintItem(holder, position);
//            }
            // Log.e("test1", lastIndex + " " + index + " " + position);
            lastIndex = index;
            index = position;
            notifyDataSetChanged();
        });
    }

    private void paintItem(WordViewHolder holder) {
//        holder.row_linearLayout.setSelected(true);
        //  Log.e("test2", "Holder to String: " + holder.toString());
        // Log.e("test2", "Holder Id: " + String.valueOf(holder.getItemId()));

        holder.row_linearLayout.setSelected(true);
        holder.wordItemView.setSelected(true);
//            holder.row_linearLayout.setBackgroundColor(Color.parseColor("#001DAB"));
//            holder.wordItemView.setTextColor(Color.parseColor("#FFFFFF"));
    }


    private void unpaintItem(WordViewHolder holder) {
//        holder.row_linearLayout.setSelected(false);
//        holder.row_linearLayout.setBackgroundColor(Color.parseColor("#FFFFFF"));
//        holder.wordItemView.setTextColor(Color.parseColor("#000000"));
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

