package id.my.asadullah.implicitintents;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.my.asadullah.implicitintents.helper.MyFunction;

public class SmsActivity extends MyFunction {

    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 2;
    @BindView(R.id.edt)
    EditText edt;
    @BindView(R.id.edtmessage)
    EditText edtmessage;
    @BindView(R.id.btnsmsintent)
    Button btnsmsintent;
    @BindView(R.id.btnkirimsms)
    Button btnkirimsms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.edt, R.id.btnsmsintent, R.id.btnkirimsms})
    public void onViewClicked(View view) {
        String nohp = edt.getText().toString();
        String pesan = edtmessage.getText().toString();

        switch (view.getId()) {
            case R.id.edt:
                Intent intent = new Intent (Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
                startActivityForResult(intent, 1);
                break;
            case R.id.btnsmsintent:
                Intent sms = new Intent (Intent.ACTION_VIEW);
                sms.putExtra("sms_body", pesan);
                sms.putExtra("address", nohp);
                sms.setType("vnd.android-dir/mms-sms");
                startActivity(sms);
                break;
            case R.id.btnkirimsms:
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.SEND_SMS)) {
                    } else {
                        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, MY_PERMISSIONS_REQUEST_SEND_SMS);
                    }
                }
                else {
                    try{
                        SmsManager manager = SmsManager.getDefault();
                        manager.sendTextMessage(nohp, null, pesan, null, null);
                        myToast("berhasil mengirim sms");
                    }
                    catch (Exception e){
                        e.printStackTrace();
                        myToast("gagal mengirim pesan" + e.getMessage());
                    }

                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && resultCode == RESULT_OK){
            Cursor cursor = null;
            try{
                Uri uri = data.getData();
                cursor= getContentResolver().query(uri,new String[]{
                        ContactsContract.CommonDataKinds.Phone.NUMBER},null,null,null);
                if (cursor!=null&&cursor.moveToNext()){
                    String phone =cursor.getString(0);
                    edt.setText(phone);
                }
            }
            catch (Exception e){
                e.printStackTrace();
                myToast("gagal memilih no hp"+e.getMessage());
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
