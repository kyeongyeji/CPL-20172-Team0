package receiver;

import jssc.SerialPort;
import jssc.SerialPortException;

public class Readdata {
	public static void main(String[] args) {
		
		SerialPort serialPort = new SerialPort("COM3");
		
		 try {
		        serialPort.openPort();//Open serial port
		        serialPort.setParams(115200, 8, 1, 0);//Set params.
		        byte[] buffer = serialPort.readBytes(100);//Read 10 bytes from serial port
		        StringBuilder sb = new StringBuilder();
		        for(byte b : buffer)
		        	sb.append(String.format("%02X", b));
		        
		        System.out.println(sb.toString());
		        serialPort.closePort();//Close serial port
		    }
		    catch (SerialPortException ex) {
		        System.out.println(ex);
		    }
//		String[] portnames = SerialPortList.getPortNames();
//		
//		for (int i=0; i<portnames.length; i++) {
//			System.out.println(portnames[i]);
//		}
	}
}
