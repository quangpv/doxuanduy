package com.example.quanlythuvien.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.util.Consumer;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlythuvien.databinding.ItemListDocgiaBinding;
import com.example.quanlythuvien.databinding.ItemListSachBinding;
import com.example.quanlythuvien.domain.DocGia;

import java.util.List;

public class DocGiaAdapter extends RecyclerView.Adapter<DocGiaAdapter.DocGiaViewHolder> {
    List<DocGia> danhSach;
    public Consumer<DocGia> onItemClick,onClickXoa;

    @NonNull
    @Override
    public DocGiaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DocGiaViewHolder(ItemListDocgiaBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DocGiaViewHolder holder, int position) {
        DocGia docGia = danhSach.get(position);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class DocGiaViewHolder extends RecyclerView.ViewHolder{
        private final ItemListDocgiaBinding binding;
        public DocGiaViewHolder(@NonNull ItemListDocgiaBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
