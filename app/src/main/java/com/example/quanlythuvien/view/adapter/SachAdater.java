package com.example.quanlythuvien.view.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.util.Consumer;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlythuvien.R;
import com.example.quanlythuvien.databinding.ItemListSachBinding;
import com.example.quanlythuvien.domain.ui.ISachItemList;

import java.util.List;

public class SachAdater extends RecyclerView.Adapter<SachAdater.SachViewHodel> {
    List<ISachItemList> danhSach;
    public Consumer<ISachItemList> onItemClick,onClickXoa;

    public SachAdater(RecyclerView recyclerView) {
        recyclerView.setAdapter(this);
    }

    @NonNull
    @Override
    public SachViewHodel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SachViewHodel(ItemListSachBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SachViewHodel holder, int position) {
        ISachItemList sach = danhSach.get(position);
        holder.itemBinding.tensach.setText(sach.getTenSach());
        holder.itemBinding.tonkho.setText(holder.itemView.getResources().getString(R.string.tongsach,Integer.toString(sach.getTonSach())));
        holder.itemBinding.tong.setText(holder.itemView.getResources().getString(R.string.tonsach,Integer.toString(sach.getTongSach())));
        holder.itemView.setOnClickListener(v -> clickSach(sach));
        holder.itemBinding.xoa.setOnClickListener(v -> onClickXoaSach(sach,position));
    }

    private void onClickXoaSach(ISachItemList sach, int position) {
        danhSach.remove(sach);
        notifyItemRemoved(position);
        if (onClickXoa != null) onClickXoa.accept(sach);
    }

    private void clickSach(ISachItemList sach) {
        if (onItemClick != null) onItemClick.accept(sach);
    }

    @Override
    public int getItemCount() {
        if (danhSach != null) {
            return danhSach.size();
        }
        return 0;
    }
    @SuppressLint("NotifyDataSetChanged")
    public void setDanhSach(List<ISachItemList> danhSach) {
        this.danhSach = danhSach;
        notifyDataSetChanged();
    }



    public static class SachViewHodel extends RecyclerView.ViewHolder {
        private final ItemListSachBinding itemBinding;

        public SachViewHodel(ItemListSachBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;
        }
    }
}
