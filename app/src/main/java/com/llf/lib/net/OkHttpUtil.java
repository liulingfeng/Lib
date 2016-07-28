package com.llf.lib.net;

import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.llf.lib.App;
import com.squareup.okhttp.Cache;
import com.squareup.okhttp.CacheControl;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

/**
 * Created by llf on 2016/7/28.
 * okhttp网络请求
 */
public class OkHttpUtil {
    private static OkHttpUtil manager;
    private OkHttpClient okHttpClient;
    private Handler mDelivery;

    private static final String TAG = "OkHttpClientManager";

    // 私有的构造方法,防止外部直接new对象
    private OkHttpUtil() {
        okHttpClient = new OkHttpClient();
        okHttpClient.setConnectTimeout(1000, TimeUnit.MILLISECONDS);
        // okHttpClient.setWriteTimeout(2000, TimeUnit.MILLISECONDS);
        // okHttpClient.setReadTimeout(1000, TimeUnit.MILLISECONDS);
        // 检查sd是否可用
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            File sdcache = App.instance.getExternalCacheDir();
            int cacheSize = 10 * 1024 * 1024; // 10 MiB
            okHttpClient.setCache(new Cache(sdcache.getAbsoluteFile(), cacheSize));
        }
        mDelivery = new Handler(Looper.getMainLooper());
    }

    public static OkHttpUtil getInstanse() {
        // 加同步锁
        synchronized (OkHttpUtil.class) {
            if (manager == null) {
                manager = new OkHttpUtil();
            }
        }
        return manager;
    }

    // 异步请求返回string到主线程
    public void _postAsyn(String url, String userId,boolean hasnet, StringCallback callback) {
        Request request = null;
        FormEncodingBuilder builder = new FormEncodingBuilder();
        builder.add("userId", userId);
        if (hasnet) {
            request = new Request.Builder().cacheControl(CacheControl.FORCE_NETWORK).url(url).post(builder.build())
                    .build();
        } else {
            request = new Request.Builder().url(url).post(builder.build()).build();
        }
        deliveryResult(callback, request);
    }

    // get请求
    public void getAsyn(String url, boolean hasnet, StringCallback callback) {
        Log.e(TAG, "get方式");
        Request request = new Request.Builder().url(url).get().addHeader("apix-key", "34af394c0f464371731fe585061dab9f")
                .build();
        deliveryResult(callback, request);
    }

    // post请求
    public void getPostAsyn(String url, String sessionid,Callback callback) {
        Log.e(TAG, "post方式");
        FormEncodingBuilder builder = new FormEncodingBuilder();
        builder.add("sessionid", sessionid);
        Request request = new Request.Builder().url(url).post(builder.build()).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(callback);
    }

    // 上传文件
    public void uploadFile(String url, File file, Callback callback) {
        Log.e(TAG, "上传文件");
        RequestBody fileBody = RequestBody.create(MediaType.parse("application/octet-stream"), file);
        Request request = new Request.Builder().url(url).post(fileBody).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(callback);
    }

    // 下载文件
    public void downloadFile(InputStream inputStream, File file) {
        Log.e(TAG, "下载文件");
        byte[] buf = new byte[2048];
        int len = 0;
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            while ((len = inputStream.read(buf)) != -1) {
                fos.write(buf, 0, len);
            }
            fos.flush();
        } catch (Exception e) {
            Log.e(TAG, "下载文件出错" + e.getMessage());
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    Log.e(TAG, "关闭流出错" + e.getMessage());
                }
            }

            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    Log.e(TAG, "关闭流出错" + e.getMessage());
                }
            }
        }
    }

    private void deliveryResult(final StringCallback callback, Request request) {
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, final IOException e) {
                sendFailedStringCallback(request, e, callback);
            }

            @Override
            public void onResponse(Response response) {
                try {
                    String string = response.body().string();
                    sendSuccessStringCallback(string, callback);
                } catch (IOException e) {
                    sendFailedStringCallback(response.request(), e, callback);
                }

            }
        });
    }

    private void sendFailedStringCallback(final Request request, final IOException e, final StringCallback callback) {
        mDelivery.post(new Runnable() {
            @Override
            public void run() {
                if (callback != null)
                    callback.onFailure(request, e);
            }
        });
    }

    private void sendSuccessStringCallback(final String string, final StringCallback callback) {
        mDelivery.post(new Runnable() {
            @Override
            public void run() {
                if (callback != null)
                    callback.onResponse(string);
            }
        });
    }

    public interface StringCallback {
        void onFailure(Request request, IOException e);
        void onResponse(String response);
    }
}
