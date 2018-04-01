package id.my.asadullah.implicitintents;

import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.my.asadullah.implicitintents.helper.MyFunction;

public class AudioManagerActivity extends MyFunction {

    @BindView(R.id.ring)
    Button ring;
    @BindView(R.id.vibrate)
    Button vibrate;
    @BindView(R.id.silent)
    Button silent;
    // 1,deklarasi variabel global
    AudioManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_manager);
        ButterKnife.bind(this);
        // 2, deklarasi varabel local
        manager = (AudioManager)getSystemService(AUDIO_SERVICE);


    }

    @OnClick({R.id.ring, R.id.vibrate, R.id.silent})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ring:
                // 3,
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    manager.adjustStreamVolume(AudioManager.STREAM_NOTIFICATION, AudioManager.ADJUST_UNMUTE, 0);
                    manager.adjustStreamVolume(AudioManager.STREAM_ALARM, AudioManager.ADJUST_UNMUTE, 0);
                    manager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_UNMUTE, 0);
                    manager.adjustStreamVolume(AudioManager.STREAM_RING, AudioManager.ADJUST_UNMUTE, 0);
                    manager.adjustStreamVolume(AudioManager.STREAM_SYSTEM, AudioManager.ADJUST_UNMUTE, 0);
                }
                else{
                    manager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                    myToast ("dalam mode normal/ring");
                }
                break;
            case R.id.vibrate:
                //5,
                manager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
                myToast("dalam mode vibrate/getar");
                break;
            case R.id.silent:
                // 6,
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    manager.adjustStreamVolume(AudioManager.STREAM_NOTIFICATION, AudioManager.ADJUST_MUTE, 0);
                    manager.adjustStreamVolume(AudioManager.STREAM_ALARM, AudioManager.ADJUST_MUTE, 0);
                    manager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_MUTE, 0);
                    manager.adjustStreamVolume(AudioManager.STREAM_RING, AudioManager.ADJUST_MUTE, 0);
                    manager.adjustStreamVolume(AudioManager.STREAM_SYSTEM, AudioManager.ADJUST_MUTE, 0);
                }
                else{
                    manager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
                    myToast ("dalam mode silent");
                }
                break;
        }
    }
}
