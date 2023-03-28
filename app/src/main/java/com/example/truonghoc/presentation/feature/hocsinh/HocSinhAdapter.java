package com.example.truonghoc.presentation.feature.hocsinh;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.util.Consumer;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import com.example.truonghoc.databinding.ItemHocsinhBinding;
import com.example.truonghoc.domain.ui.IHocSinh;

import java.util.List;

public class HocSinhAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<IHocSinh> mItems;
    public Consumer<IHocSinh> onItemClick;

    public HocSinhAdapter(RecyclerView rvList) {
        rvList.setAdapter(this);
        rvList.addItemDecoration(new DividerItemDecoration(rvList.getContext(), RecyclerView.VERTICAL));
    }

    @SuppressLint("NotifyDataSetChanged")
    public void submit(List<IHocSinh> items) {
        mItems = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemHolder(ItemHocsinhBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ItemHolder holder1 = (ItemHolder) holder;
        holder1.bind(mItems.get(position));
    }

    @Override
    public int getItemCount() {
        if (mItems == null) return 0;
        return mItems.size();
    }

    class ItemHolder extends RecyclerView.ViewHolder {

        private final ItemHocsinhBinding binding;

        public ItemHolder(@NonNull ItemHocsinhBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(IHocSinh item) {
            binding.txtHoten.setText(item.getName());
            binding.txtGioiTinh.setText(item.getGender());
            binding.txtNgaySinh.setText(item.getDob().toString());
            itemView.setOnClickListener(v -> {
                if (onItemClick != null) onItemClick.accept(item);
            });
        }
    }
}
