package com.example.truonghoc.presentation.apdapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.truonghoc.databinding.ItemHocsinhListBinding;
import com.example.truonghoc.domain.HocSinhDangHoc;
import java.util.ArrayList;
import java.util.List;

public class HocSinhDangHocAdapter extends RecyclerView.Adapter<HocSinhDangHocAdapter.HocSinhDangHocViewHolder>{
    List<HocSinhDangHoc> danhSach ;

    public HocSinhDangHocAdapter() {
    }

    @NonNull
    @Override
    public HocSinhDangHocViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HocSinhDangHocViewHolder(ItemHocsinhListBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull HocSinhDangHocViewHolder holder, int position) {
        HocSinhDangHoc hocSinhDangHoc = danhSach.get(position);
        holder.itemBinding.setHocSinhDangHoc(hocSinhDangHoc);
    }

    @Override
    public int getItemCount() {
        if(danhSach!=null){
            return danhSach.size();
        }
        return 0;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setDanhSach(List<HocSinhDangHoc> danhSach) {
        this.danhSach = danhSach;
        notifyDataSetChanged();
    }


    public static class HocSinhDangHocViewHolder extends RecyclerView.ViewHolder {
        private final ItemHocsinhListBinding itemBinding;
        public HocSinhDangHocViewHolder(@NonNull ItemHocsinhListBinding itemHocsinhListBinding) {
            super(itemHocsinhListBinding.getRoot());
            this.itemBinding = itemHocsinhListBinding;
        }

    }
}
