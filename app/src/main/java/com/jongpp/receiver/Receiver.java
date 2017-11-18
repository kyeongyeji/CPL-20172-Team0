package com.jongpp.receiver;


import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.*;
import com.example.kimminji.jongpp.MainActivity;
import com.example.kimminji.jongpp.R;
import com.hoho.android.usbserial.driver.UsbSerialDriver;
import com.hoho.android.usbserial.driver.UsbSerialPort;
import com.hoho.android.usbserial.driver.UsbSerialProber;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

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

        //////

        UsbManager manager = (UsbManager) getSystemService(Context.USB_SERVICE);
       /* HashMap<String, UsbDevice> deviceList = manager.getDeviceList();
        Iterator<UsbDevice> deviceIterator = deviceList.values().iterator();
        while(deviceIterator.hasNext()) {
            UsbDevice device = deviceIterator.next();
            arrlist.add(device.getSerialNumber());

        }*/

         List<UsbSerialDriver> availableDrivers = UsbSerialProber.getDefaultProber().findAllDrivers(manager);
         if(availableDrivers.isEmpty()){
             Toast.makeText(Receiver.this,"empty device",Toast.LENGTH_SHORT).show();

         }

         UsbSerialDriver driver = availableDrivers.get(0);
         arrlist.add(driver.getDevice().getSerialNumber());
        /*Toast.makeText(Receiver.this,"serial num : "+driver.getDevice().getSerialNumber()
                +"\ndevice name : "+driver.getDevice().getDeviceName()
                +"\nproduct name : "+driver.getDevice().getProductName(),Toast.LENGTH_SHORT).show();*/
         if(driver==null){
             Toast.makeText(Receiver.this,"empty driver",Toast.LENGTH_SHORT).show();
         }

        UsbDeviceConnection connection = manager.openDevice(driver.getDevice());

        if(connection == null){
            Toast.makeText(Receiver.this,"connection null",Toast.LENGTH_SHORT).show();

        }

        UsbSerialPort port = driver.getPorts().get(0);
        if(port==null){
            Toast.makeText(Receiver.this,"port null",Toast.LENGTH_SHORT).show();
        }
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
