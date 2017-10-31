package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.sun.corba.se.impl.orbutil.graph.Graph;

import navigation.NavigationEvents;
import navigation.NavigationMain;
//import navigation.Graph;
import navigation.Vertex;
import receiver.Receiver;
import receiver.VlcReceiverEventListener;
import receiver.VlcReceiverEvents;

//import ui.Map.EventListener;

public class UiMain extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1945875378328319477L;
	
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
	
	private Receiver receiverFrame = new Receiver();
	private NavigationMain navigation = new NavigationMain();
	//private Graph graph = new Graph();
	
	public UiMain(String title) {
		super(title);
		
		layout = new BorderLayout();
		setLayout(layout);
		
		//North
		text= new JTextArea("",10,15);
		
		receiver = new JButton("수신기 선택");
		receiver.setBackground(Color.WHITE);
		receiver.addActionListener(new EventListener());
		
		VlcReceiverEventListener.getInstance().addMyEventListener(new VlcEventListener());
		
		guide = new JButton("안내 시작");
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
	
	public void start(int width, int height) {
		this.setSize(width, height);
		
		this.setVisible(true);	
	}
	
	public void setLight() {
		lights = new JLabel[17];
		point = new ImageIcon("point_mini.png");
		for(int i=7; i<=16; i++){
		//	lights[i]=new JLabel("O");
		//	lights[i].setForeground(Color.yellow);
			lights[i]= new JLabel(point);
			lights[i].setBounds(150+70*(i-7), 225, 20, 20);
			lights[i].setVisible(false);
			mappanel.add(lights[i]);
		}
		for(int i=3; i>=1; i--){
			lights[i]=new JLabel(point);
			lights[i].setBounds(440,310+50*(3-i),20,20);
			lights[i].setVisible(false);
			mappanel.add(lights[i]);
		}
		for(int i=6; i>=4; i--){
			lights[i]=new JLabel(point);
			lights[i].setBounds(520,310+50*(6-i),20,20);
			lights[i].setVisible(false);
			mappanel.add(lights[i]);
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
	
	class RoomListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
				guide.setEnabled(true);
		}
		
	}
	
	class EventListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			if(e.getSource() == receiver)
				receiverFrame.selectReceiver();
			else if(e.getSource() == guide) {
				text.setText("");
				navigation.startNavigation("0204");
			}
		}
		
	}
	
	class VlcEventListener implements VlcReceiverEvents {

		@Override
		public void receiverSelected() {
			receiver.setEnabled(false);
		}

		@Override
		public void receiverHasError() {
			receiver.setEnabled(true);		
		}

		@Override
		public void receiverHasMessage(String message) {
			text.append(message);
		}

		@Override
		public void receivedSuccessfully() {
			
		}
	}
	
	class NavigationEventListener implements NavigationEvents {

		@Override
		public void userArrived() {
			// TODO Auto-generated method stub
			text.setText("도착했습니다!");
		}

		@Override
		public void userMoved(Vertex v, String dir) {
			// TODO Auto-generated method stub
			text.append(dir);
		}
		
	}
}