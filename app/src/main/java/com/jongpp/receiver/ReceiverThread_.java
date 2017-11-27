package com.jongpp.receiver;

/**
 * Created by Owner on 2017-11-21.
 */

import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbManager;
import android.os.Handler;

import com.example.kimminji.jongpp.BusUtil;
import com.example.kimminji.jongpp.CustomBus;
import com.hoho.android.usbserial.driver.UsbSerialDriver;
import com.hoho.android.usbserial.driver.UsbSerialPort;

import java.io.IOException;

public class ReceiverThread_ extends Thread{


    int baudRate;
    UsbSerialDriver driver;
    UsbManager manager;
    UsbDeviceConnection connection;


    UsbSerialPort mPort;
    String message;
    String prev_message = null;


    public ReceiverThread_(UsbManager manager,UsbSerialDriver driver, int baudRate) {
        this.driver = driver;
        this.baudRate = baudRate;
        this.manager= manager;

        CustomBus.getInstance().register(this);

        connection = manager.openDevice(driver.getDevice());
        if(connection == null){
            CustomBus.getInstance().post(new VlcReceiverEvents_.receiverHasError("error in connection"));
        }

        mPort = driver.getPorts().get(0);
        if(mPort==null){
            CustomBus.getInstance().post(new VlcReceiverEvents_.receiverHasError("error in port"));
        }



    }


    public String getMessage() {
        return message;
    }


    // @Override
    public void run()
    {

        try{

            mPort.open(connection);
            mPort.setParameters(baudRate, 8, 1, 0);

            while(true) {


                try {
                    byte buffer[]= new byte[22];
                    try {
                        int numBytesRead = mPort.read(buffer, 1000);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    StringBuilder sb = new StringBuilder();

                    for(int i = 0; i < 22; i++)
                        if (i == 3)
                            sb.append(String.format("%02X", buffer[i]));

                    message = sb.toString();

                    if (prev_message != message) {

                        BusUtil.postOnMain(CustomBus.getInstance(),new VlcReceiverEvents_.receivedSuccessfully(message));
                    }

                    prev_message = message;
                }
                catch (Exception ex) {

                    try {
                        if(mPort != null)
                            mPort.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    BusUtil.postOnMain(CustomBus.getInstance(),new VlcReceiverEvents_.receiverHasError("error in read from port"));

                }
            }

        }catch (IOException e){

            try{
                if(mPort!=null)
                    mPort.close();

            }catch (IOException ex){

            }
            BusUtil.postOnMain(CustomBus.getInstance(),new VlcReceiverEvents_.receiverHasError("error in port open"));
    }


    }	// End of run()




}
