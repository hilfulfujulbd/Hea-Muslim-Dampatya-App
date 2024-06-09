package com.shahrinsiddeka.heamoslimdampatya.MainAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shahrinsiddeka.heamoslimdampatya.R;

import java.util.ArrayList;

public class RecyclerAdapterView extends RecyclerView.Adapter<RecyclerAdapterView.RecyclerAdapterHolder> {

    private final ArrayList<ChapterModel> chapterList;
    private final ChapterClick chapterClick;

    public RecyclerAdapterView(ArrayList<ChapterModel> chapterList, ChapterClick click) {
        this.chapterList = chapterList;
        this.chapterClick = click;
    }


    @NonNull
    @Override
    public RecyclerAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecyclerAdapterHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.single_item_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapterHolder holder, int position) {
        holder.textView.setText(chapterList.get(position).getTitle());
        holder.itemView.setOnClickListener(v -> {
            if (chapterClick != null) {
                chapterClick.onClick(chapterList.get(position).getFileName(), chapterList.get(position).getTitle());
            }
        });
    }

    @Override
    public int getItemCount() {
        return chapterList.size();
    }

    public static class RecyclerAdapterHolder extends RecyclerView.ViewHolder {
        private final TextView textView;

        public RecyclerAdapterHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.chapterTitle);
        }

    }
}
