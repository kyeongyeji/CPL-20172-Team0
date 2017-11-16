package com.jongpp.receiver;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.*;
import com.example.kimminji.jongpp.MainActivity;
import com.example.kimminji.jongpp.R;

import java.util.ArrayList;

public class Receiver extends AppCompatActivity implements View.OnClickListener{

    Button ok,cancel;
    Spinner spinner;
    EditText baudrate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_receiver);

        ok=(Button)findViewById(R.id.ok);
        ok.setOnClickListener(this);

        cancel=(Button)findViewById(R.id.cancel);
        cancel.setOnClickListener(this);

        spinner=(Spinner)findViewById(R.id.spinner);
        ArrayList<String> arrlist = new ArrayList<String>();
       // arrlist.add("COM3");
        ArrayAdapter<String> arradapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrlist);
        spinner.setAdapter(arradapter);

        baudrate = (EditText)findViewById(R.id.baudrate);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ok:

                break;
            case R.id.cancel:

                break;
        }
        Toast.makeText(Receiver.this, baudrate.getText().toString(),Toast.LENGTH_SHORT).show();
        finish();
    }
}
