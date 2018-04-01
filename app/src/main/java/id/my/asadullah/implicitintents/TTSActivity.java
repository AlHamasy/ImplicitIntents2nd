package id.my.asadullah.implicitintents;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.my.asadullah.implicitintents.helper.MyFunction;

public class TTSActivity extends MyFunction implements TextToSpeech.OnInitListener {

    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.editText)
    EditText editText;
    @BindView(R.id.btnSpeech)
    Button btnSpeech;
    TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tts);
        ButterKnife.bind(this);
        tts = new TextToSpeech(c, this);
    }

    @OnClick(R.id.btnSpeech)
    public void onViewClicked() {
        String text = editText.getText().toString();
        tts.speak(text,TextToSpeech.QUEUE_FLUSH, null);
    }

    @Override
    public void onInit(int i) {

        if (i == TextToSpeech.SUCCESS){

            /*Locale indo = new Locale("en_US", "ENG");
            int hasil = tts.setLanguage(Locale.ENGLISH);*/

            Locale indo = new Locale("id","ID");
            int hasil = tts.setLanguage(indo);

            if (hasil == TextToSpeech.LANG_MISSING_DATA || hasil == TextToSpeech.LANG_NOT_SUPPORTED){
                myToast("bahasa tidak mendukung");
            }
            else {
                onViewClicked();
                btnSpeech.setEnabled(true);
            }
        }
        else{
            myToast("TTS tidak support");
        }
    }
}
