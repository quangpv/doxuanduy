package com.example.truonghoc.presentation.apdapter;

import android.annotation.SuppressLint;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.truonghoc.R;
import com.example.truonghoc.databinding.ItemHocsinhListBinding;
import com.example.truonghoc.domain.HocSinhDangHoc;
import java.util.List;

public class HocSinhDangHocAdapter extends RecyclerView.Adapter<HocSinhDangHocAdapter.HocSinhDangHocViewHolder>{
    List<HocSinhDangHoc> danhSach ;
    Context context;


    public HocSinhDangHocAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public HocSinhDangHocViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HocSinhDangHocViewHolder(ItemHocsinhListBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull HocSinhDangHocViewHolder holder, int position) {
        HocSinhDangHoc hs = danhSach.get(position);
        holder.itemBinding.hoten.setText(context.getResources().getText(R.string.ten,hs.getHocSinh().getHoVaTen()));
        holder.itemBinding.hoten.setText(hs.getHocSinh().getHoVaTen());
        holder.itemBinding.gioiTinh.setText(hs.getHocSinh().getGioiTinh());
        holder.itemBinding.ngaySinh.setText(hs.getHocSinh().getSinhNgay());
        holder.itemBinding.khoi.setText(hs.getKhoiLop().getKhoiLop());
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
