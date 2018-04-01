package id.my.asadullah.implicitintents;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.widget.Button;


import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.my.asadullah.implicitintents.helper.MyFunction;

public class VideoActivity extends MyFunction {

    @BindView(R.id.btnvideo)
    Button btnvideo;
    Uri lokasifile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        ButterKnife.bind(this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                    && checkSelfPermission(Manifest.permission.CAMERA)
                    != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(
                        new String[]{Manifest.permission.CAMERA},
                        110);
            }
            return;
        }
    }

    @OnClick(R.id.btnvideo)
    public void onViewClicked() {
        String folderkamera = "videoku";
        File file = new File (Environment.getExternalStorageDirectory(),folderkamera);
        if (!file.exists()){
            file.mkdir();
        }
        File file1 = new File (Environment.getExternalStorageDirectory().getAbsolutePath()+"/"+folderkamera+"/VID"+currentDate()+".mp4");
        // akses kamera
        Intent intent = new Intent (MediaStore.ACTION_VIDEO_CAPTURE);
        lokasifile = Uri.fromFile(file1);
        //lokasifile = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID+".provider",file);

        intent.putExtra(MediaStore.EXTRA_OUTPUT, lokasifile);
        startActivityForResult(intent, 1);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1){
            if  (resultCode == RESULT_OK){
                myToast("berhasil menyimpan gambar \n lokasi file " +lokasifile.toString());
            }
            else if (requestCode == RESULT_CANCELED){
                myToast("cancel");
            }
            else{
                myToast("gagal mengambil gambar");
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
