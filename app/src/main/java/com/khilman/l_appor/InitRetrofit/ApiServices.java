package com.khilman.l_appor.InitRetrofit;

import com.khilman.l_appor.Response.ResponseUpload;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by root on 9/22/17.
 */

public interface ApiServices {
    @Multipart
    @POST("upload.php")
    Call<ResponseUpload> request_upload(
            @Part("description") String description,
            @Part MultipartBody.Part photo
            );
}
