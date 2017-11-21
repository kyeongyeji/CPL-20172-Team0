package com.example.kimminji.jongpp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.*;
import com.jongpp.receiver.*;
import com.squareup.otto.Subscribe;


public class MainActivity extends AppCompatActivity{

    Button[] button;
    ButtonInfo[] binfo;
    String bname[];
    int bnum, lnum;
    LightInfo[] linfo;
    ImageView[] light;

    RelativeLayout layout;
    Button path,receiver;
    Intent intent;

    TextView t;

    TextView txt1;


    Bundle b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       // setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main_);

        ButtonListener buttonlistener = new ButtonListener();
        receiver = (Button)findViewById(R.id.receiver);
        receiver.setOnClickListener(buttonlistener);
        path = (Button)findViewById(R.id.path);
        path.setOnClickListener(buttonlistener);
        path.setEnabled(false);
        txt1=(TextView)findViewById(R.id.txt1);
        txt1.setText("txt1");

        //bus 등록
        //BusProvider.getInstance().register(this);
        CustomBus.getInstance().register(this);



        setInfo();
        setLayout();

    }

    //Bus subscribe 선언
    @Subscribe
    public void receiversel(VlcReceiverEvents_.receiverSelected evnt){
      //  Toast.makeText(MainActivity.this,"Bus Event : receiver is selected",Toast.LENGTH_SHORT).show();
        receiver.setEnabled(false);
    }

    @Subscribe
    public void receivererr(VlcReceiverEvents_.receiverHasError evnt){
        Toast.makeText(MainActivity.this,"Bus Event : receiver has error",Toast.LENGTH_SHORT).show();

    }

    @Subscribe
    public void receivermsg(VlcReceiverEvents_.receiverHasMessage evnt){
        Toast.makeText(MainActivity.this,"Bus Event : receiver has msg\n"+evnt.getMsg(),Toast.LENGTH_SHORT).show();
    }

    @Subscribe
    public void receiversuc(VlcReceiverEvents_.receivedSuccessfully evnt){
        txt1.setText(evnt.getMsg());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        CustomBus.getInstance().unregister(this);

    }
    class ButtonListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.receiver:
                    intent=new Intent(MainActivity.this, Receiver.class);
                         startActivity(intent);
                    break;
                case R.id.path:
                    Toast.makeText(MainActivity.this, ((Button)v).getText().toString()+" clicked",Toast.LENGTH_SHORT).show();
                    break;
            }

        }
    }

    class RoomListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Toast.makeText(MainActivity.this, "room "+((Button)v).getText().toString()+" clicked",Toast.LENGTH_SHORT).show();
            path.setEnabled(true);
        }
    }
   public void setInfo(){
        //일단 임의로 선언(button)

        bnum=12;
        binfo = new ButtonInfo[bnum];
        bname = new String[bnum];

        binfo[0]=new ButtonInfo(260,0,250,300);
        bname[0]="M";
        binfo[1]=new ButtonInfo(510,0,250,300);
        bname[1]="W";
        binfo[2]=new ButtonInfo(760,0,250,300);
        bname[2]="105";
        binfo[3]=new ButtonInfo(1010,0,450,300);
        bname[3]="104";
        binfo[4]=new ButtonInfo(1460,0,250,300);
        bname[4]="103";
        binfo[5]=new ButtonInfo(1710,0,450,300);
        bname[5]="101";

        binfo[6]=new ButtonInfo(100,300,160,230);
        bname[6]="Exit";
        binfo[7]=new ButtonInfo(2160,300,160,230);
        bname[7]="Exit";

        binfo[8]=new ButtonInfo(420,530,350,300);
        bname[8]="108";
        binfo[9]=new ButtonInfo(770,530,250,300);
        bname[9]="107";
        binfo[10]=new ButtonInfo(1460,530,450,300);
        bname[10]="106";

        binfo[11]=new ButtonInfo(1030,830,400,150);
        bname[11]="Exit";

        //(light)

        lnum=16;
        linfo=new LightInfo[lnum];
        linfo[0] = new LightInfo(350,380);
        linfo[1]=new LightInfo(540,380);
        linfo[2]=new LightInfo(730,380);
        linfo[3]=new LightInfo(920,380);
        linfo[4]=new LightInfo(1110,380);
        linfo[5]=new LightInfo(1300,380);
        linfo[6]=new LightInfo(1490,380);
        linfo[7]=new LightInfo(1680,380);
        linfo[8]=new LightInfo(1870,380);
        linfo[9]=new LightInfo(2060,380);

        linfo[10]=new LightInfo(1100,540);
        linfo[11]=new LightInfo(1100,640);
        linfo[12]=new LightInfo(1100,740);

        linfo[13]=new LightInfo(1300,540);
        linfo[14]=new LightInfo(1300,640);
        linfo[15]=new LightInfo(1300,740);
    }

    public void setLayout(){
        Display display = ((WindowManager) getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
        int orientation = display.getOrientation();
        //if(orientation== Configuration.ORIENTATION_LANDSCAPE) {
        layout = (RelativeLayout) findViewById(R.id.layout);
        RoomListener roomlistener = new RoomListener();

        button = new Button[bnum];

        for (int i = 0; i < bnum; i++) {
            button[i] = new Button(this);
            // button[i].setBackgroundColor(android.R.color.white);
            button[i].setBackground(getDrawable(R.drawable.bshape));
            button[i].setLayoutParams(new LinearLayout.LayoutParams(binfo[i].getWidth(), binfo[i].getHeight()));
            button[i].setX(binfo[i].getX());
            button[i].setY(binfo[i].getY());
            button[i].setText(bname[i]);
            button[i].setOnClickListener(roomlistener);
            layout.addView(button[i]);

        }

        light = new ImageView[lnum];

        for (int i = 0; i < lnum; i++) {
            light[i] = new ImageView(this);
            light[i].setImageResource(R.drawable.point);
            light[i].setX(linfo[i].getX());
            light[i].setY(linfo[i].getY());
            //안보이게
            light[i].setVisibility(View.INVISIBLE);
            layout.addView(light[i]);

        }

        //}
    }

}

