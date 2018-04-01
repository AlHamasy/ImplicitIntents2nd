package id.my.asadullah.implicitintents;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.my.asadullah.implicitintents.helper.MyFunction;


public class AudioRecorderActivity extends MyFunction {

    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.btnPlay)
    Button btnPlay;
    @BindView(R.id.btnRecordStop)
    Button btnRecordStop;
    MediaRecorder record;
    String outputfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_recorder);
        ButterKnife.bind(this);
        btnPlay.setEnabled(false);
    }

    @OnClick({R.id.btnPlay, R.id.btnRecordStop})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnPlay:
                MediaPlayer player = new MediaPlayer();
                try {
                    player.setDataSource(outputfile);
                    player.prepare();
                    player.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btnRecordStop:
                if (btnRecordStop.getText().toString().equalsIgnoreCase("RECORD")) {
                    try {
                        record = new MediaRecorder();
                        outputfile = Environment.getExternalStorageDirectory().getAbsolutePath() + "/REC" + currentDate()
                                + ".3gp";
                        record.setAudioSource(MediaRecorder.AudioSource.MIC);
                        record.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                        record.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
                        record.setOutputFile(outputfile);
                        record.prepare();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    record.start();
                    btnRecordStop.setText("STOP");
                } else if (btnRecordStop.getText().toString().equalsIgnoreCase("STOP")) {
                    record.stop();
                    record.release();
                    record = null;
                    btnPlay.setEnabled(true);
                    btnRecordStop.setText("RECORD");
                }
                break;
        }
    }
}