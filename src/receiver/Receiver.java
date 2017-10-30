package receiver;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.MutableComboBoxModel;

import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;

public class Receiver implements VlcReceiver, ActionListener, VlcReceiverEvents {
	
	JButton accept = new JButton("OK");
	JButton cancel = new JButton("Cancel");
	JTextField BaudrateTf = new JTextField(5);
	MutableComboBoxModel<String> strlist = new DefaultComboBoxModel<String>();
	JComboBox interfTf = new JComboBox(strlist);
	
	int baudrateI;
	SerialPort serialPort;

	@Override
	public void selectReceiver() {
		// TODO Auto-generated method stub
		JFrame rFrame = new JFrame("Serial Communication");
		JLabel interfLabel = new JLabel("Interface");
		JLabel BaudLabel = new JLabel("Baudrate");
		//MutableComboBoxModel<String> strlist = new DefaultComboBoxModel<String>();
		
		String[] portnames = SerialPortList.getPortNames();
		
		rFrame.setSize(300, 120);
		
		rFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		rFrame.setLayout(new FlowLayout());
		rFrame.add(interfLabel);
		rFrame.add(interfTf);
	
		for (int i=0; i<portnames.length; i++) {
			strlist.addElement(portnames[i]);
			//System.out.println(portnames[i]);
		}
		//strlist.addElement("item1");
		
		BaudLabel.setLocation(100, 100);
		rFrame.add(BaudLabel);
		rFrame.add(BaudrateTf);
	
		rFrame.add(accept);
		rFrame.add(cancel);
		
		accept.addActionListener(this);
		cancel.addActionListener(this);

		rFrame.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource().equals(accept)) { // serial 통신 시작
			receiverSelected();
		}
		else if (e.getSource().equals(cancel)){ // 프로그램 종료
			System.exit(0);
		}
	}
	
	public void receiverSelected() {
		
		String baudrateS = BaudrateTf.getText();
		baudrateI = Integer.parseInt(baudrateS);
		
		String portname = (String)interfTf.getSelectedItem();
		serialPort = new SerialPort(portname);
		
		try {
			serialPort.openPort();
			serialPort.setParams(baudrateI, 8, 1, 0);
		}
		catch (SerialPortException ex) {
			receiverHasError(ex);
		}
		
		
		receiverHasMessage();
	}
	
	public void receiverHasError(SerialPortException ex) {
		
		JOptionPane.showMessageDialog(null, ex);
	};
	
	public void receiverHasMessage() {
			
		try {
		        byte[] buffer = serialPort.readBytes(100);//Read 10 bytes from serial port
		        StringBuilder sb = new StringBuilder();
		        for(byte b : buffer)
		        	sb.append(String.format("%02X", b));
		        
		        System.out.println(sb.toString());
		        //serialPort.closePort();//Close serial port
		}
		catch (SerialPortException ex) {
				receiverHasError(ex);
		        //System.out.println(ex);
		}
		
	};
	
	public void receivedSuccessfully(){
		
		try {
			serialPort.closePort();
		} catch (SerialPortException ex) {
			receiverHasError(ex);
			// TODO Auto-generated catch block
			//System.out.println(ex);
		}
	};

	public static void main(String[] args) {
		Receiver receiver = new Receiver();
		receiver.selectReceiver();
	}
}



