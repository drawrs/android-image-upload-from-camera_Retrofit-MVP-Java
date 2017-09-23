package com.khilman.l_appor.Presenter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.khilman.l_appor.InitRetrofit.ApiServices;
import com.khilman.l_appor.InitRetrofit.InitLibrary;
import com.khilman.l_appor.MainActivity;
import com.khilman.l_appor.Model.ModelUpload;
import com.khilman.l_appor.ProgressRequestBody;
import com.khilman.l_appor.R;
import com.khilman.l_appor.Response.ResponseUpload;

import java.io.File;
import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Multipart;

/**
 * Created by root on 9/22/17.
 */

public class PresenterUpload implements ProgressRequestBody.UploadCallbacks {
    ModelUpload model;
    Activity con;
    ProgressBar progess;
    TextView txtProgress;
    public PresenterUpload(ModelUpload model, Activity context) {
        this.model = model;
        this.con = context;
    }
    public void uploadData(File filePath, String description){
        // inisialisasi retrofit
        ApiServices api = InitLibrary.getInstance();
        // siapkan file
        File file = filePath;
        // progress bar handler
        ProgressRequestBody fileBody = new ProgressRequestBody(file, this);
        MultipartBody.Part photo = MultipartBody.Part.createFormData("photo", file.getName(), fileBody);

        // siapkan request
        Call<ResponseUpload> call = api.request_upload(description, photo);
        call.enqueue(new Callback<ResponseUpload>() {
            @Override
            public void onResponse(Call<ResponseUpload> call, Response<ResponseUpload> response) {
                if (response.isSuccessful()){
                    //Toast.makeText(con, "Berhasil", Toast.LENGTH_SHORT).show();
                    ResponseUpload data = response.body();
                    String message = data.getMsg();
                    String status = data.getResult();
//                    Log.d("response", "" + data.getMsg());
//                    Log.d("response", "" + data.getResult());
//                    Log.d("response", "" + data.getFilename());

                   if (status.equals("true")){
                       model.Success(message, status);
                      // Toast.makeText(con, message, Toast.LENGTH_SHORT).show();
                   } else {
                       Toast.makeText(con, message, Toast.LENGTH_SHORT).show();
                   }
                }
            }

            @Override
            public void onFailure(Call<ResponseUpload> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    public void onProgressUpdate(int percentage) {
        progess = (ProgressBar) con.findViewById(R.id.progressBar);
        txtProgress = (TextView) con.findViewById(R.id.txtProges);
        progess.setProgress(percentage);
        txtProgress.setText(percentage + " %");
        //Log.d("progress", "" + percentage);
    }

    @Override
    public void onError() {

    }

    @Override
    public void onFinish() {
//        Log.d("progress", "Selesaaii");
        txtProgress.setText("100 %");
        progess.setProgress(100);
    }

}
