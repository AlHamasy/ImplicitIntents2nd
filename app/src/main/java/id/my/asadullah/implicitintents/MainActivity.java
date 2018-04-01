package id.my.asadullah.implicitintents;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.my.asadullah.implicitintents.helper.MyFunction;

public class MainActivity extends MyFunction {

    @BindView(R.id.btnaudiomanager)
    Button btnaudiomanager;
    @BindView(R.id.btnaudiorecorder)
    Button btnaudiorecorder;
    @BindView(R.id.btnbluetooth)
    Button btnbluetooth;
    @BindView(R.id.btnbrowser)
    Button btnbrowser;
    @BindView(R.id.btncamera)
    Button btncamera;
    @BindView(R.id.btnemail)
    Button btnemail;
    @BindView(R.id.btnphone)
    Button btnphone;
    @BindView(R.id.btnsms)
    Button btnsms;
    @BindView(R.id.btntts)
    Button btntts;
    @BindView(R.id.btnwifi)
    Button btnwifi;
    @BindView(R.id.btnvideo)
    Button btnvideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btnaudiomanager, R.id.btnaudiorecorder, R.id.btnbluetooth, R.id.btnbrowser, R.id.btncamera, R.id.btnemail, R.id.btnphone, R.id.btnsms, R.id.btntts, R.id.btnwifi, R.id.btnvideo})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnaudiomanager:
                myIntent(AudioManagerActivity.class);
                break;
            case R.id.btnaudiorecorder:
                myIntent(AudioRecorderActivity.class);
                break;
            case R.id.btnbluetooth:
                myIntent(BluetoothActivity.class);
                break;
            case R.id.btnbrowser:
                myIntent(BrowserActivity.class);
                break;
            case R.id.btncamera:
                myIntent(CameraActivity.class);
                break;
            case R.id.btnemail:
                myIntent(EmailActivity.class);
                break;
            case R.id.btnphone:
                myIntent(PhoneActivity.class);
                break;
            case R.id.btnsms:
                myIntent(SmsActivity.class);
                break;
            case R.id.btntts:
                myIntent(TTSActivity.class);
                break;
            case R.id.btnwifi:
                myIntent(WifiActivity.class);
                break;
            case R.id.btnvideo:
                myIntent(VideoActivity.class);
                break;
        }
    }

    public void onAlarm(View view) {
        myIntent(AlarmActivity.class);
    }
}
