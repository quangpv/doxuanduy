package com.example.truonghoc.presentation.apdapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.util.Consumer;
import androidx.recyclerview.widget.RecyclerView;

import com.example.truonghoc.R;
import com.example.truonghoc.databinding.ItemHocsinhListBinding;
import com.example.truonghoc.domain.HocSinhDangHoc;

import java.util.List;

public class HocSinhDangHocAdapter extends RecyclerView.Adapter<HocSinhDangHocAdapter.HocSinhDangHocViewHolder> {
    public Consumer<HocSinhDangHoc> onItemClick;
    List<HocSinhDangHoc> danhSach;

    public HocSinhDangHocAdapter() {

    }

    @NonNull
    @Override
    public HocSinhDangHocViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HocSinhDangHocViewHolder(ItemHocsinhListBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HocSinhDangHocViewHolder holder, int position) {
        HocSinhDangHoc hs = danhSach.get(position);

        holder.itemBinding.hoten.setText(holder.itemView.getResources().getString(R.string.ten, hs.getHocSinh().getHoVaTen()));
        holder.itemBinding.gioiTinh.setText(holder.itemView.getResources().getString(R.string.gioi_tinh, hs.getHocSinh().getGioiTinh()));
        holder.itemBinding.ngaySinh.setText(holder.itemView.getResources().getString(R.string.sinh_ngay, hs.getHocSinh().getSinhNgay()));
        holder.itemBinding.khoi.setText(holder.itemView.getResources().getString(R.string.lop, hs.getKhoiLop().getKhoiLop()));
        holder.itemView.setOnClickListener(v -> {
            if (onItemClick != null) onItemClick.accept(hs);
        });
    }

    @Override
    public int getItemCount() {
        if (danhSach != null) {
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
