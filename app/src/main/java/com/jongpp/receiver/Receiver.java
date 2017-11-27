package com.jongpp.receiver;


import android.content.Context;
import android.hardware.usb.UsbManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.*;

import com.example.kimminji.jongpp.CustomBus;
import com.example.kimminji.jongpp.R;
import com.hoho.android.usbserial.driver.UsbSerialDriver;
import com.hoho.android.usbserial.driver.UsbSerialProber;


import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

public class Receiver extends AppCompatActivity implements View.OnClickListener, VlcReceiver{

    Button ok,cancel;
    Spinner spinner;
    EditText baudrate;
    ArrayList<String> arrlist;
    ReceiverThread_ thread = null;
    UsbSerialDriver driver;
    UsbManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_receiver);

        ok=(Button)findViewById(R.id.ok);
        ok.setOnClickListener(this);

        cancel=(Button)findViewById(R.id.cancel);
        cancel.setOnClickListener(this);

        baudrate = (EditText)findViewById(R.id.baudrate);

        spinner=(Spinner)findViewById(R.id.spinner);
        arrlist = new ArrayList<String>();

        selectReceiver();

        ArrayAdapter<String> arradapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrlist);
        spinner.setAdapter(arradapter);

        //bus 등록
        CustomBus.getInstance().register(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ok:
                acceptClicked();
                break;
            case R.id.cancel:
                finish();
                break;
        }

    }

    public void acceptClicked(){

        String baudrateS = baudrate.getText().toString();
        int baudrate = Integer.parseInt(baudrateS);


        thread = new ReceiverThread_(manager,driver, baudrate);

        thread.start();

       try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        CustomBus.getInstance().post(new VlcReceiverEvents_.receiverSelected(1));

        finish();


    }

    @Override
    public void selectReceiver() {
        manager = (UsbManager) getSystemService(Context.USB_SERVICE);


        List<UsbSerialDriver> availableDrivers = UsbSerialProber.getDefaultProber().findAllDrivers(manager);
        if(availableDrivers.isEmpty()){
            Toast.makeText(Receiver.this,"empty device",Toast.LENGTH_SHORT).show();

        }

        driver = availableDrivers.get(0);

        if(driver==null){
            Toast.makeText(Receiver.this,"empty driver",Toast.LENGTH_SHORT).show();
        }
        arrlist.add(driver.getDevice().getSerialNumber());

    }
 @Override
    protected void onDestroy() {
        super.onDestroy();
        CustomBus.getInstance().unregister(this);

    }
}
