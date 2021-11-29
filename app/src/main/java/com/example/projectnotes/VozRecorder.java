package com.example.projectnotes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.projectnotes.activities.EditTextActivity;

import java.io.IOException;

public class VozRecorder extends AppCompatActivity {
    private MediaRecorder grabacion;
    private String archivoSalida=null;
    private Button Rec;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voz_recorder);
        Rec= (Button) findViewById(R.id.grabar);
        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(VozRecorder.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO}, 1000);
        }
    }
    public void Recorder (View view){
        if (grabacion == null) {
            archivoSalida = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Grabacion.mp3";
            grabacion = new MediaRecorder();
            grabacion.setAudioSource(MediaRecorder.AudioSource.MIC);
            grabacion.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            grabacion.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);

            grabacion.setOutputFile(archivoSalida);
            try {
                grabacion.prepare();
                grabacion.start();

            } catch (IOException e) {

            }
            Rec.setBackgroundResource(R.drawable.rec);
            Toast.makeText(getApplicationContext(), "grabando...", Toast.LENGTH_SHORT).show();
        }else if (grabacion !=null){
            grabacion.stop();
            grabacion.release();
            Rec.setBackgroundResource(R.drawable.stop_rec);
            Toast.makeText(getApplicationContext(), "grabacion finalizada", Toast.LENGTH_SHORT).show();
        }
    }
    public void reproducir (View view){
        MediaPlayer mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(archivoSalida);
            mediaPlayer.prepare();
        }catch (IOException e){
        }
        mediaPlayer.start();
        Toast.makeText(getApplicationContext(), "Reproduciendo", Toast.LENGTH_SHORT).show();
    }
    public void regresarAlanota (View view){
        Intent regresarAlanota = new Intent(this, EditTextActivity.class);
        startActivity(regresarAlanota);
    }
}