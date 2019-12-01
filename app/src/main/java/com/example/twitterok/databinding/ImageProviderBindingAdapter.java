package com.example.twitterok.databinding;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.example.twitterok.R;
import com.squareup.picasso.Picasso;

public class ImageProviderBindingAdapter {

    @BindingAdapter({"profileImage"})
    public static void loadProfileImage(ImageView imageView, String url){
        Picasso.get().load(url).error(R.mipmap.ic_launcher).into(imageView);
    }

    @BindingAdapter({"profileImgBitmap"})
    public static void loadProfileImgBitmap(ImageView imageView, Bitmap bitmap){
        imageView.setImageBitmap(bitmap);
    }

    @BindingAdapter({"contentImage"})
    public static void loadContentImage(ImageView imageView, String url){
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
