package com.llf.lib.imageloader;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.ImageDownloader;
import java.io.File;

/**
 * Created by llf on 2016/7/22.
 */
public class UniversalImageLoaderStractery implements BaseImageLoaderStrategy{
    /**
     * 初始化Universal-Image-Loader框架的参数设置
     *
     * @param context
     */
    public static void init(Context context) {
        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(context);
        config.threadPriority(Thread.NORM_PRIORITY - 2);
        config.denyCacheImageMultipleSizesInMemory();
        config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
        config.diskCacheSize(50 * 1024 * 1024); // 50 MiB
        config.tasksProcessingOrder(QueueProcessingType.LIFO);
        config.writeDebugLogs();
        com.nostra13.universalimageloader.core.ImageLoader.getInstance().init(config.build());
    }

    @Override
    public void loadImage(Context ctx, ImageLoader img) {
        DisplayImageOptions options = new DisplayImageOptions.Builder().showImageOnLoading(img.getPlaceHolder())
                .showImageForEmptyUri(img.getPlaceHolder()).showImageOnFail(img.getPlaceHolder()).cacheInMemory(true)
                .cacheOnDisk(true).considerExifParams(true).bitmapConfig(Bitmap.Config.RGB_565).build();
        com.nostra13.universalimageloader.core.ImageLoader.getInstance().displayImage(img.getUrl(), img.getImgView(), options);
    }

    public void displayImage(ImageView imageView, File imageFile,ImageLoader img) {

        DisplayImageOptions options = new DisplayImageOptions.Builder().showImageOnLoading(img.getPlaceHolder())
                .showImageForEmptyUri(img.getPlaceHolder()).showImageOnFail(img.getPlaceHolder()).cacheInMemory(true) // 加载本地图片不需要再做SD卡缓存，只做内存缓存即可
                .considerExifParams(true).bitmapConfig(Bitmap.Config.RGB_565).build();
        String uri = ImageDownloader.Scheme.FILE.wrap(imageFile.getAbsolutePath());
        com.nostra13.universalimageloader.core.ImageLoader.getInstance().displayImage(uri, imageView, options);
    }
}
