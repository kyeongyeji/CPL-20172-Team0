package receiver;

import jssc.SerialPort;
import jssc.SerialPortException;

public class ReceiverThread extends Thread {
	VlcReceiverEventListener listener;
	SerialPort serialPort;
	
	String portName;
	int baudRate;
	
	String message;
	String prev_message = null;
	int success;
	
	public ReceiverThread(String portName, int baudRate) {
		this.portName = portName;
		this.baudRate = baudRate;
		
		listener = VlcReceiverEventListener.getInstance();
	}
	
	public int getSuccess() {
		return success;
	}
	
	public String getMessage() {
		return message;
	}
	
	@Override
	public void run() {
		
		Object[] listeners = listener.getListenerList();
		
		try {
			serialPort = new SerialPort(portName);
			serialPort.openPort();
			serialPort.setParams(baudRate, 8, 1, 0);
			
			receiveVlcFrame();
			//prev_message = message;	
		}
		catch (SerialPortException ex) {
			System.err.println(ex);
			
			try {
				if(serialPort != null)
					serialPort.closePort();
			} catch (SerialPortException e) {
				e.printStackTrace();
			}
			
			for (int i = 0; i < listeners.length; i = i+2) {
				if (listeners[i] == VlcReceiverEvents.class) {
					((VlcReceiverEvents)listeners[i+1]).receiverHasError();
				}
			}
		}
	}

	private void receiveVlcFrame() {
		while(true) {
			System.out.println("Receiving Frame");
			
			Object[] listeners = listener.getListenerList();
			
			try {
		        byte[] buffer = serialPort.readBytes(22);
		        
		        StringBuilder sb = new StringBuilder();
		        
		        for(byte b : buffer)
		        	sb.append(String.format("%02X", b));
		        
		        message = sb.toString();
		       // prev_message = message;	
		           
		        for (int i = 0; i < listeners.length; i = i+2) {
					if (listeners[i] == VlcReceiverEvents.class) {
						((VlcReceiverEvents)listeners[i+1]).receiverHasMessage(message);
						// ((VlcReceiverEvents)listeners[i+1]).receivedSuccessfully(message);
					}
				}
		        
		        if (prev_message != message) {
			        for (int i = 0; i < listeners.length; i = i+2) {
			        	if (listeners[i] == VlcReceiverEvents.class) {
							((VlcReceiverEvents)listeners[i+1]).receivedSuccessfully(message);
						}
					}
		        }
		        
		        prev_message = message;	
			}
			catch (SerialPortException ex) {
				System.err.println(ex);
				
				try {
					if(serialPort != null)
						serialPort.closePort();
				} catch (SerialPortException e) {
					e.printStackTrace();
				}
				
				for (int i = 0; i < listeners.length; i = i+2) {
					if (listeners[i] == VlcReceiverEvents.class) {
						((VlcReceiverEvents)listeners[i+1]).receiverHasError();
					}
				}
			}
		}
	}
}
