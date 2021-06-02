package com.example.payoneerchallenge.bindingadapters;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.payoneerchallenge.R;

public class BindingAdapters {

    @androidx.databinding.BindingAdapter("imageUrl")
    public static void bindImage(ImageView imageView, String imgUrl) {

        if (imgUrl != null) {
            Glide.with(imageView.getContext())
                    .load(imgUrl)
                    .diskCacheStrategy(DiskCacheStrategy.DATA)
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(imageView);
        }
    }
}
