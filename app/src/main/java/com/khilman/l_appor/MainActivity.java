package com.khilman.l_appor;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.TextInputEditText;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.khilman.l_appor.Helper.Helper;
import com.khilman.l_appor.Model.ModelUpload;
import com.khilman.l_appor.Presenter.PresenterUpload;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements ModelUpload {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.pilihImage)
    TextView pilihImage;
    @BindView(R.id.imgLapor)
    ImageView imgLapor;
    @BindView(R.id.txtKet)
    TextInputEditText txtKet;
    @BindView(R.id.btnSubmit)
    Button btnSubmit;
    String mCurrentPhotoPath;

    // variabel penyimpan nomor request
    private static final int ambil_foto_request_code = 100;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.panelUplad)
    LinearLayout panelUplad;
    @BindView(R.id.txtFilename)
    TextView txtFilename;
    @BindView(R.id.txtProges)
    TextView txtProges;

    // nama folder penyimpanan
    private Uri fileUri; // file url to store image/video
    // nama folder penyimpanan
    private static final String folder_foto = "AplikasiKameraku";
    // file photo
    private File file;
    PresenterUpload presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        // check permission
        Helper.chechPermission(MainActivity.this);
        // check camera support
        Helper.checkCamera(MainActivity.this);

        presenter = new PresenterUpload(this, MainActivity.this);

    }

    @OnClick(R.id.pilihImage)
    public void onPilihImageClicked() {
        ambilGambar();
    }


    @OnClick(R.id.imgLapor)
    public void onImgLaporClicked() {
        ambilGambar();
    }

    @OnClick(R.id.btnSubmit)
    public void onBtnSubmitClicked() {
        // Siapkan file
        File photoToUpload = file;
        String description = txtKet.getText().toString();
        //Toast.makeText(this, "" + description, Toast.LENGTH_SHORT).show();
        if (file != null){
            presenter.uploadData(file, description);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ambil_foto_request_code && resultCode != 0) {
            // Toast.makeText(this, "Dipilihh", Toast.LENGTH_SHORT).show();
            tampilkanGambar();
        } else if (resultCode == RESULT_CANCELED) {
            Toast.makeText(this, "Tidak ada gambar terpilih", Toast.LENGTH_SHORT).show();
        } else {

        }
    }

    private void ambilGambar() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        fileUri = getOutputMediaFileUri();
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
        startActivityForResult(intent, ambil_foto_request_code);
    }

    private void tampilkanGambar() {
        // reset progressbar & text progress dulu
        txtProges.setText("0 %");
        progressBar.setProgress(0);
        // Siapkan file
        Uri imageUri = Uri.parse(mCurrentPhotoPath);
        file = new File(imageUri.getPath());
        if (file.exists()) {
            try {
                InputStream ims = new FileInputStream(file);
                imgLapor.setImageBitmap(BitmapFactory.decodeStream(ims));
                panelUplad.setVisibility(View.VISIBLE);
                pilihImage.setVisibility(View.GONE);
                txtFilename.setText(file.getName());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private Uri getOutputMediaFileUri() {
        return FileProvider.getUriForFile(MainActivity.this, BuildConfig.APPLICATION_ID + ".provider", getOutputMediaFile());
    }

    private File getOutputMediaFile() {

        File storage = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), folder_foto);
        // cek keberadaan foto
        if (!storage.exists()) {
            if (!storage.mkdirs()) {
                Toast.makeText(this, "Gagal membuat folder "
                        + folder_foto, Toast.LENGTH_SHORT).show();
                return null;
            }
        }
        // simpan format tanggal saat pengambilan foto
        String waktu = new SimpleDateFormat("yyyyMMdd_hhMss"
                , Locale.getDefault()).format(new Date());
        File mediaFile;
        mediaFile = new File(storage.getPath() + File.separator + "IMG" + waktu + ".jpg");
        mCurrentPhotoPath = "file:" + mediaFile.getAbsolutePath();
        return mediaFile;
    }


    @Override
    public void Success(String message, String status) {
        Toast.makeText(this, "" + message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void Error(String error) {

    }
}
