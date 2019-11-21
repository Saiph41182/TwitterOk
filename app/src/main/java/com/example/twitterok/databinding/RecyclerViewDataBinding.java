package com.example.twitterok.databinding;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.twitterok.R;
import com.example.twitterok.adapters.MainAdapter;
import com.example.twitterok.adapters.SearchAdapter;
import com.example.twitterok.model.TweetModel;
import com.squareup.picasso.Picasso;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.core.models.User;

import java.util.List;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

public class RecyclerViewDataBinding {
    /**
     * Binds the data to the {@link RecyclerView.Adapter}, sets the
     * recycler view on the adapter, and performs some other recycler view initialization.
     *
     * @param recyclerView passed in automatically with the data binding
     * @param adapter      must be explicitly passed in
     * @param data         must be explicitly passed in
     */


    @BindingAdapter({"app:adapter", "app:data"})
    public void bind(RecyclerView recyclerView, MainAdapter adapter, List<TweetModel> data){
        recyclerView.setAdapter(adapter);
        adapter.updateData(data);
    }

    @BindingAdapter({"profileImage"})
    public void loadProfileImage(ImageView imageView, String url){
        Picasso.get().load(url).error(R.mipmap.ic_launcher).into(imageView);
    }

    @BindingAdapter({"profileImgBitmap"})
    public void loadProdileImgBitmap(ImageView imageView, Bitmap bitmap){
        imageView.setImageBitmap(bitmap);
    }

    @BindingAdapter({"contentImage"})
    public void loadContentImage(ImageView imageView, String url){
        if(url == null || url.isEmpty()){
            imageView.setVisibility(View.GONE);
        }else{
            imageView.setVisibility(View.VISIBLE);
            Picasso.get()
                    .load(url)
                    .error(R.mipmap.ic_launcher)
                    .into(imageView);
        }
    }

}
