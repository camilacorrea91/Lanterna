package com.example.lanterna;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    Button btn = (Button) findViewById(R.id.button);
    ImageView imageView = (ImageView) findViewById(R.id.imageView2);

    CameraManager cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
    final String[] cameraId = {null};

    final boolean[] on = {false};

    btn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        if(!on[0]){
          try{
            cameraId[0] = cameraManager.getCameraIdList()[0];
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
              cameraManager.setTorchMode(cameraId[0], true);
              imageView.setImageResource(R.drawable.lampada_apagada);
              on[0] = true;
            }
          }catch (CameraAccessException e){
            e.printStackTrace();
          }
        } else {
          try {
            cameraId[0] = cameraManager.getCameraIdList()[0];
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
              cameraManager.setTorchMode(cameraId[0], false);
              imageView.setImageResource(R.drawable.lampada_acesa);
              on[0] = false;
            }
          } catch (CameraAccessException e) {
            e.printStackTrace();
          }
        }
      }
    });

  }
}