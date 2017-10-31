package ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import ui.Map.EventListener;

public class UiMain extends JFrame {
	
	private JButton receiver, guide;
	private JPanel north, northe, center;
//	private JLabel maplabel;
	//private JTextField text;
	private JTextArea text;
	private BorderLayout layout;
	private ImageIcon point;
	//private ImageIcon map;
	
	private JButton[] buttons;
	private JLabel[] lights;
	private JPanel mappanel;
	private String[] rname={"toiletm", "toiletw", "r105", "r104", "r103",
			"r101","r108","r107","r106","entrancew","entrancee","entrances"};
	
	
	public UiMain(String title) {
		super(title);
		
		layout = new BorderLayout();
		setLayout(layout);
		
		//North
		text= new JTextArea("",10,15);
		
		receiver = new JButton("수신기선택");
		receiver.setBackground(Color.WHITE);
		receiver.addActionListener(new EventListener());
		
		guide = new JButton("안내시작");
		guide.setBackground(Color.white);
		guide.addActionListener(new EventListener());
		guide.setEnabled(false);
		
		
		north=new JPanel();
		north.setLayout(new GridLayout(1,2,50,50));
		
		north.add(text);
	
		
		northe = new JPanel();
		northe.setLayout(new GridLayout(2,1,20,20));
		northe.add(receiver);
		northe.add(guide);
		
		north.add(northe);
		
		
		//center

//		Map map = new Map();
		mappanel = new JPanel();
		mappanel.setLayout(null);
		//mappanel.setBackground(Color.WHITE);
		mappanel.setPreferredSize(new Dimension(1000,800));
		
		setRoom();
		setLight();
		
		center = new JPanel();
		center.add(mappanel);
		add(north, BorderLayout.NORTH);
		add(center,BorderLayout.CENTER);
		
	}
	
	public void start() {
		
		
	}
	
	public void setLight(){
		lights = new JLabel[16];
		point = new ImageIcon("point_mini.png");
		for(int i=0;i<10;i++){
		//	lights[i]=new JLabel("O");
		//	lights[i].setForeground(Color.yellow);
			lights[i]= new JLabel(point);
			lights[i].setBounds(150+70*i, 225, 20, 20);
			lights[i].setVisible(true);
			mappanel.add(lights[i]);
		}
		for(int i=10;i<13;i++){
			lights[i]=new JLabel(point);
			lights[i].setBounds(440,310+50*(i-10),20,20);
			lights[i].setVisible(false);
			mappanel.add(lights[i]);
		}
		for(int i=13;i<16;i++){
			lights[i]=new JLabel(point);
			lights[i].setBounds(520,310+50*(i-13),20,20);
			lights[i].setVisible(false);
			mappanel.add(lights[i]);
		}
	}
	
	class RoomListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
				guide.setEnabled(true);
		}
		
	}
	
	class EventListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			if(e.getSource()==receiver){
				//text.setText("receiver");
			} else if(e.getSource()==guide){
				//text.setText("guide");
			}
		}
		
	}
	public void setRoom(){
		buttons = new JButton[12];

		for(int i=0;i<12;i++){
			buttons[i]=new JButton(rname[i]);
			buttons[i].setBackground(Color.WHITE);
			buttons[i].addActionListener(new RoomListener());
			mappanel.add(buttons[i]);	
		}
		
		buttons[0].setBounds(80, 50, 100, 120);
		buttons[1].setBounds(180,50,100,120);
		buttons[2].setBounds(280, 50, 100, 120);
		buttons[3].setBounds(380, 50, 200, 120);
		buttons[4].setBounds(580, 50, 100, 120);
		buttons[5].setBounds(680, 50, 200, 120);
		buttons[6].setBounds(150, 300, 150, 150);
		buttons[7].setBounds(300,300,100,150);
		buttons[8].setBounds(580, 300, 200, 150);
		buttons[9].setBounds(10, 200, 70, 100);
		buttons[10].setBounds(410, 450, 160, 60);
		buttons[11].setBounds(880,200,70,100);
		
		
		
		for(int i =0;i<12;i++)
		{
			mappanel.add(buttons[i]);
		}
	}
}
