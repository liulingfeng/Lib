package com.llf.lib.imageloaderreal;

import android.content.Context;

import com.squareup.picasso.Picasso;

/**
 * Created by llf on 2016/7/22.
 */
public class PicassoImageLoaderStrategy implements BaseImageLoaderStrategy{
    @Override
    public void loadImage(Context ctx, ImageLoader img) {
        Picasso.with(ctx).load(img.getUrl()).centerCrop().placeholder(img.getPlaceHolder()).into(img.getImgView());
    }
}
