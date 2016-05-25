package com.linjin.zhimi.rest;

import android.content.Context;
import android.text.TextUtils;

import com.linjin.zhimi.AppConstants;
import com.linjin.zhimi.Constants;
import com.linjin.zhimi.DataCenter;
import com.linjin.zhimi.ServerConstants;
import com.tinggu.common.utils.DeviceUtils;
import com.tinggu.common.utils.LogUtils;
import com.linjin.common.utils.MD5Utils;
import com.cyou.quick.QuickApplication;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by liujianguang on 2015/12/18.
 */
public class RestUtils implements AppConstants, ParameterKeys, ServerConstants {

    private static OkHttpClient okHttpClient;
    private static Retrofit retrofit;

    public static <T> T createApi(Class<T> clazz) {
        if (retrofit == null) {
            synchronized (RestUtils.class) {
                if (retrofit == null) {
                    retrofit = getRetrofit();
                }
            }
        }
        return retrofit.create(clazz);
    }


    public static Retrofit getRetrofit() {
        Retrofit.Builder builder = new Retrofit.Builder();
        //使用有@Expose注释的属性会被序列化 提高序列化的效率
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
//        RestAdapter restAdapter = latest RestAdapter.Builder()
//                .setConverter(latest GsonConverter(gson))
//                .build();
        builder.addConverterFactory(GsonConverterFactory.create(gson));
        builder.baseUrl(API_BASE_URL);//设置远程地址
        builder.addCallAdapterFactory(RxJavaCallAdapterFactory.create());
        builder.client(getOkHttpClient());
        return builder.build();
    }

    private static Interceptor getRequestInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                DataCenter dataCenter = DataCenter.getInstance();
                String time = dataCenter.getServerTime();
                Request.Builder newRequestBuilder = request.newBuilder()
//                        .header("Content-Type", "application/json")
                        .header(KEY_PLATFORM, PLATFORM_VALUE)
                        .header(KEY_TIME, time)
                        .header(KEY_USERID, dataCenter.getUserID())
                        .header(KEY_SIGN, createSign())
                        .header(KEY_TOKEN, dataCenter.getUserToken())
                        .header(KEY_DEVICEID, DeviceUtils.getIMEI(QuickApplication.getInstance()));
                String userToken = dataCenter.getUserToken();
                if (!TextUtils.isEmpty(userToken)) {
                    newRequestBuilder.header(KEY_TOKEN, userToken);
                }
                request = newRequestBuilder.build();
//                loggerRequest(request);
                Response response = chain.proceed(request);
                response = loggerResponse(request, response);
                return response;
            }
        };
    }

    private static Response loggerResponse(Request request, Response response) {
        try {
            if (Constants.DEBUG) {
                StringBuilder sb = new StringBuilder();
                String url = request.url().toString();
                Long start = System.currentTimeMillis();

                CacheBufferdSkin cache = new CacheBufferdSkin();
                request.body().writeTo(cache);
                String strBody = cache.bodyStr;
                
                sb.append("---------------------request--------------------------------")
                        .append("\nrequest-->url").append("|").append(url)
                        .append("\nrequest.headers").append(":{\n").append(request.headers()).append("}")
                        .append("\nrequest.body:").append(strBody);
                LogUtils.i("Retrofit", sb.toString());
                
                sb.delete(0, sb.length());
                sb.append("---------------------response--------------------------------")
                        .append("\nresponse-->url").append("|").append(url)
                        .append("\nresponse.body").append("|\n");

                LogUtils.i("Retrofit", sb.toString());
                final int LOG_CHUNK_SIZE = 4000;
                ResponseBody oldResponseBody = response.body();
                String oldBodyString = oldResponseBody.string();
                for (int i = 0, len = oldBodyString.length(); i < len; i += LOG_CHUNK_SIZE) {
                    int end = Math.min(len, i + LOG_CHUNK_SIZE);
                    LogUtils.i("Retrofit", oldBodyString.substring(i, end));
                }
                sb.delete(0, sb.length());
                sb.append("\nFly Time").append("|").append(System.currentTimeMillis() - start);
                LogUtils.i("Retrofit", sb.toString());

                ResponseBody newResponseBody = ResponseBody.create(oldResponseBody.contentType(),
                        oldBodyString);
                Response.Builder newBuilderder = response.newBuilder().body(newResponseBody);
                return newBuilderder.build();
            }
        } catch (Exception e) {
            return response;
        }
        return response;
    }

    private static void loggerRequest(Request request) {
        if (Constants.DEBUG) {
            try {
                String url = request.url().toString();
                CacheBufferdSkin cache = new CacheBufferdSkin();
                request.body().writeTo(cache);
                String strBody = cache.bodyStr;
                StringBuilder sb = new StringBuilder();
                sb.append("---------------------request--------------------------------")
                        .append("\nrequest-->url").append("|").append(url)
                        .append("\nrequest.headers").append(":{\n").append(request.headers()).append("}")
                        .append("\nrequest.body:").append(strBody);
                LogUtils.i("Retrofit", sb.toString());
               
            } catch (Exception e) {

            }
        }
    }

    public static OkHttpClient getOkHttpClient() {
        if (okHttpClient == null) {
            Context context = QuickApplication.getInstance();
            File cacheDir = new File(context.getCacheDir(), RESPONSE_CACHE);
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.cache(new Cache(cacheDir, RESPONSE_CACHE_SIZE))
                    .connectTimeout(HTTP_CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
                    .readTimeout(HTTP_READ_TIMEOUT, TimeUnit.MILLISECONDS).
                    addInterceptor(getRequestInterceptor());
            okHttpClient = builder.build();
//            okHttpClient = latest OkHttpClient();
//            okHttpClient.setCache(latest Cache(cacheDir, RESPONSE_CACHE_SIZE));
//            okHttpClient.setConnectTimeout(HTTP_CONNECT_TIMEOUT, TimeUnit.MILLISECONDS);
//            okHttpClient.setReadTimeout(HTTP_READ_TIMEOUT, TimeUnit.MILLISECONDS);
//            okHttpClient.interceptors().add(getRequestInterceptor());
        }
        return okHttpClient;
    }

    private static String createSign() {
        DataCenter dataCenter = DataCenter.getInstance();
        String sign = "groupSocial" + PLATFORM_VALUE + dataCenter.getUserID()
                + dataCenter.getServerTime();
        LogUtils.i("header sign", "PLATFORM_VALUE=" + PLATFORM_VALUE +
                " userId=" + dataCenter.getUserID() +
                " time=" + dataCenter.getServerTime());
        return MD5Utils.getStringMD5_32(sign + APP_SIGN_MD5);
    }
}
