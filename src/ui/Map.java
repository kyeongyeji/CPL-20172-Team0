package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Map {


	public JPanel getMappanel() {
		return mappanel;
	}

	private JButton[] buttons;
	private JLabel[] lights;
	private JPanel mappanel;
	private String[] rname={"toiletm", "toiletw", "r105", "r104", "r103",
			"r101","r108","r107","r106","entrancew","entrancee","entrances"};
	//JButton toiletm, toiletw, r101, r103, r104, r105, r106, r107, r108;
	//JButton entrancew, entrancee, entrances;
	
	public Map(){
	
		mappanel = new JPanel();
		mappanel.setLayout(null);
		//mappanel.setBackground(Color.WHITE);
		mappanel.setPreferredSize(new Dimension(1000,800));
		
		setRoom();
		setLight();
		
	}
	
	public void setLight(){
		lights = new JLabel[16];
		
		for(int i=0;i<10;i++){
			lights[i]=new JLabel("O");
			lights[i].setBounds(100+30*i, 230, 30, 30);
			mappanel.add(lights[i]);
		}
	}
	
	class EventListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	public void setRoom(){
		buttons = new JButton[12];

		for(int i=0;i<12;i++){
			buttons[i]=new JButton(rname[i]);
			buttons[i].setBackground(Color.WHITE);
			buttons[i].addActionListener(new EventListener());
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
