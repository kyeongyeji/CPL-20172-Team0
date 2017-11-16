package com.example.kimminji.jongpp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class Intro extends Activity implements View.OnClickListener{

    ImageButton pathbutton;
    Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        LinearLayout layout = new LinearLayout(this);
        layout.setBackgroundResource(R.drawable.intro);
        setContentView(layout);

        pathbutton=new ImageButton(this);
        pathbutton.setImageDrawable(getDrawable(R.drawable.pathbutton_m));
        pathbutton.setX(300);
        pathbutton.setY(1500);
        pathbutton.setBackground(getDrawable(R.color.colorback));
        pathbutton.setOnClickListener(this);
        layout.addView(pathbutton);



    }

    @Override
    public void onClick(View v) {
        i = new Intent(Intro.this, MainActivity.class);
        finish();
        startActivity(i);

    }
}
