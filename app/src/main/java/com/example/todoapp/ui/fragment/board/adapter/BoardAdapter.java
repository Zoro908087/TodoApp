package com.example.todoapp.ui.fragment.board.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todoapp.R;
import com.example.todoapp.databinding.ItemBoardBinding;

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.BoardViewHolder> {
    private ItemBoardBinding binding;
    private final int[] img = {R.raw.anime_1,R.raw.anime_2,R.raw.anime_3};
    private String[] title = {"1", "2", "3"};
    private String[] description = {"one", "two", "three"};
    @NonNull
    @Override
    public BoardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemBoardBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new BoardViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull BoardViewHolder holder, int position) {
        holder.onBind(img[position], title[position], description[position]);
    }
    @Override
    public int getItemCount() {
        return img.length;
    }

    public class BoardViewHolder extends RecyclerView.ViewHolder {
        public BoardViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void onBind(int i, String s, String s1) {
           binding.animationView.setAnimation(i);
           binding.txtTitleBoard.setText(s);
           binding.txtDescriptionBoard.setText(s1);
        }
    }
}
