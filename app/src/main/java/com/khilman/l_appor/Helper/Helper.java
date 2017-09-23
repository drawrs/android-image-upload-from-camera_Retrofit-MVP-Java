package com.khilman.l_appor.Helper;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v4.BuildConfig;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by root on 9/22/17.
 */

public class Helper {
    private static Context context;

    public static String mCurrentPhotoPath;
    // variabel penyimpan nomor request
    private static final int ambil_foto_request_code = 100;
    public static final int type_foto_code = 1;

    // nama folder penyimpanan
    private Uri fileUri; // file url to store image/video
    // nama folder penyimpanan
    private static final String folder_foto = "AplikasiKameraku";

    public Helper(Context con) {
        this.context = con;
    }


    public static void chechPermission(Activity activity){

        // konfirmasi permission
        if (
                Build.VERSION.SDK_INT >= 23
                        && activity.checkSelfPermission(Manifest.permission.RECORD_AUDIO)
                        != PackageManager.PERMISSION_GRANTED
                        && activity.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED
                        && activity.checkSelfPermission(Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED
                ) {
            activity.requestPermissions(new String[]{
                    Manifest.permission.RECORD_AUDIO
                    , Manifest.permission.WRITE_EXTERNAL_STORAGE
                    , Manifest.permission.CAMERA
            }, 0);
        } else {
            //Toast.makeText(activity, "Permission granted", Toast.LENGTH_SHORT).show();
        }
    }
    public static void checkCamera(Context context){
        // cek aplikasi pada perangkat yg memiliki fitur kamera
        if (!context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            // proses kode selanjutnya
            Toast.makeText(context, "Tidak support kamera", Toast.LENGTH_SHORT).show();
        }

    }
    private Uri ambilOutputMediaFileUri(Context con) {
        this.context = con;
        // mengambil alamat directory file
        // return Uri.fromFile(ambilOutputMediaFile(type_foto_code));
        return FileProvider.getUriForFile(context,
                BuildConfig.APPLICATION_ID + ".provider",
                ambilOutputMediaFile());
    }
    public static File ambilOutputMediaFile() {
        // atur alamat penyimpanan (SDCard/Pictures/folder_foto)
        File penyimpananMediaDir = new File(
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
                , folder_foto
        );
        Log.d("Directory Fileku", penyimpananMediaDir.getAbsolutePath());

        // cek keberadaan folder
        if (!penyimpananMediaDir.exists()) {
            // cek jika tidak bisa membuat folder baru
            if (!penyimpananMediaDir.mkdir()) {
                Toast.makeText(context, "Gagal membuat folder "
                        + folder_foto, Toast.LENGTH_SHORT).show();
                return null;
            }
        }

        // simpan format tanggal saat pengambilan foto
        String waktu = new SimpleDateFormat("yyyyMMdd_hhMss"
                , Locale.getDefault()).format(new Date());
        Log.d("Waktu Pengambilan", waktu);

        // variabel penampung nama file
        File mediaFile;
        // pasang nama foto dengan waktu
        if (type_foto_code == type_foto_code) {
            mediaFile = new File(penyimpananMediaDir.getPath() + File.separator
                    + "IMG" + waktu + ".jpg");
            Log.d("Nama FIle", mediaFile.getAbsolutePath());
        } else {
            return null;
        }
        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = "file:" + mediaFile.getAbsolutePath();

        return mediaFile;
    }

    public Uri upload() {
        // mengambil alamat directory file
        // return Uri.fromFile(ambilOutputMediaFile(type_foto_code));
        return FileProvider.getUriForFile(context,
                BuildConfig.APPLICATION_ID + ".provider",
                ambilOutputMediaFile());
    }
}
