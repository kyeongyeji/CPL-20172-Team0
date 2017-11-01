package receiver;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.MutableComboBoxModel;

import jssc.SerialPortList;

public class Receiver implements VlcReceiver, ActionListener {
	JButton accept = new JButton("OK");
	JButton cancel = new JButton("Cancel");
	JTextField baudrateTf = new JTextField(5);
	MutableComboBoxModel<String> strlist = new DefaultComboBoxModel<String>();
	@SuppressWarnings({ "rawtypes", "unchecked" })
	JComboBox interfTf = new JComboBox(strlist);
	
	ReceiverThread thread = null;

	JFrame rFrame;
	
	public String getMessage() {
		return thread.getMessage();
	}

	public void setMessage(String message) {
		thread.message = message;
	}
	
	public int getSuccess() {
		return thread.getSuccess();
	}
	
	@Override
	public void selectReceiver() {
		rFrame = new JFrame("Serial Communication");
		
		rFrame.setSize(300, 120);
		
		rFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		rFrame.setLayout(new FlowLayout());
		
		JLabel interfLabel = new JLabel("Interface");
		JLabel baudLabel = new JLabel("Baudrate");
		
		rFrame.add(interfLabel);
		rFrame.add(interfTf);
	
		baudLabel.setLocation(100, 100);
		
		rFrame.add(baudLabel);
		rFrame.add(baudrateTf);
		
		rFrame.add(accept);
		rFrame.add(cancel);
		
		accept.addActionListener(this);
		cancel.addActionListener(this);
		
		for(String port : SerialPortList.getPortNames()) {
			strlist.addElement(port);
		}

		rFrame.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource().equals(accept)) { // serial 통신 시작
			acceptClicked();
			
		}
		else if (e.getSource().equals(cancel)){ // 프로그램 종료
			rFrame.dispose();
		}
	}
	
	public void acceptClicked() { // OK 클릭시
		System.out.println("Accept clicked");
		
		String baudrateS = baudrateTf.getText();
		int baudrate = Integer.parseInt(baudrateS);
		
		String portname = (String)interfTf.getSelectedItem();
		
		thread = new ReceiverThread(portname, baudrate);
		
		thread.start();
		
		rFrame.dispose();
	}

	public static void main(String[] args) {
		Receiver receiver = new Receiver();
		receiver.selectReceiver();
	}
}



