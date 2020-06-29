package com.khalilpan.audiorecordersimple;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    MediaRecorder mediaRecorder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //to check the permissions and ask for user
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, PackageManager.PERMISSION_GRANTED);


        mediaRecorder=new MediaRecorder();
        }


        public void startRecording(View view){

            try {

                mediaRecorder.setAudioSource(MediaRecorder.AudioSource.DEFAULT);
                mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);

                File path= Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
                File file=new File(path,"/RecordedAudio.3gp");

                mediaRecorder.setOutputFile(file);
                mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

                mediaRecorder.prepare();
                mediaRecorder.start();
                Toast.makeText(this, "Recording Audio started...", Toast.LENGTH_LONG).show();

            } catch (IOException e) {
                e.printStackTrace();
            }


        }

        public void  stopRecording(View view){

            mediaRecorder.stop();
            mediaRecorder.release();

            Toast.makeText(this, "Recording Audio Stopped.", Toast.LENGTH_LONG).show();
        }



}