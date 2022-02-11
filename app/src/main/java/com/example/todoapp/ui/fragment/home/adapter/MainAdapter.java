package com.example.todoapp.ui.fragment.home.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todoapp.databinding.ItemMainRvBinding;
import com.example.todoapp.domain.models.kimchin.Kimchin;

import java.util.ArrayList;
import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {
 private List<Kimchin> list = new ArrayList<>();
 private ItemMainRvBinding binding;
 private Click click ;

    public MainAdapter(Click click) {
        this.click = click;
    }
    public void delete(Kimchin kimchin){
        this.list.remove(kimchin);
        notifyDataSetChanged();
    }
    public void setList(List<Kimchin> list){
        this.list = list ;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemMainRvBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new MainViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        holder.onBind(list.get(position).getTitle());
        holder.itemView.setOnClickListener(v -> {
            click.update(list.get(holder.getAdapterPosition()));
        });
        holder.itemView.setOnLongClickListener(v -> {
            click.delete(list.get(holder.getAdapterPosition()));
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MainViewHolder extends  RecyclerView.ViewHolder {
        ItemMainRvBinding binding;
        public MainViewHolder(@NonNull ItemMainRvBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void onBind(String title) {
            binding.txtTitle.setText(title);
        }
    }
    public interface Click {
        void update(Kimchin kimchin);
        void delete(Kimchin kimchin);
    }
}
