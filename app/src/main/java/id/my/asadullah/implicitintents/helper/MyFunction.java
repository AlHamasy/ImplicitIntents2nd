package id.my.asadullah.implicitintents.helper;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import id.my.asadullah.implicitintents.R;

public class MyFunction extends AppCompatActivity {

    public Context c;
    Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        c = MyFunction.this;
    }

    public void myToast (String isipesan){
        Toast.makeText(c, isipesan, Toast.LENGTH_SHORT).show();
    }

    public void myIntent (Class kelastujuan){
        startActivity(new Intent(c,kelastujuan));
    }

    public  String currentDate(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public void myAnimation(EditText editText){
        animation = AnimationUtils.loadAnimation(c, R.anim.animasigetar);
        editText.setAnimation(animation);
    }

}