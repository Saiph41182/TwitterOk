package com.example.twitterok.view.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.library.baseAdapters.BR;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseAdapter<T> extends RecyclerView.Adapter<BaseAdapter.CommonViewHolder>{

    @LayoutRes protected int header;
    protected List<T> data;
    private final T EMPTY_OBJ_FOR_HEADER_POSITION = null;


    public BaseAdapter(){
        data = new ArrayList<>();
        data.add(EMPTY_OBJ_FOR_HEADER_POSITION);
    }

    @NonNull
    @Override
    public CommonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(inflater,viewType,parent,false);
        return new CommonViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseAdapter.CommonViewHolder holder, int position) {
        Object obj = getObjectForPosition(position);
        holder.bind(obj);
    }


    protected abstract Object getObjectForPosition(int position);
    protected abstract int getLayoutIdForPosition(int position);

    public void setHeader(@LayoutRes int headerId){
        this.header = headerId;
    }

    @Override
    public int getItemViewType(int position) {
        return getLayoutIdForPosition(position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void updateData(@Nullable List<T> data){
        if(data == null || data.isEmpty()){
            this.data.clear();
        }else {
            this.data.addAll(data);
        }
        notifyDataSetChanged();
    }

    public class CommonViewHolder extends RecyclerView.ViewHolder{
        ViewDataBinding binding;

        public CommonViewHolder(@NonNull ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Object obj){
            if(obj != null) {
                binding.setVariable(BR.viewModel, obj);
                binding.executePendingBindings();
            }
        }
    }
}

