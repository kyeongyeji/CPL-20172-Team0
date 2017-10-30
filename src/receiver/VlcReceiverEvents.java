package receiver;

import java.util.EventListener;
import jssc.SerialPortException;

public interface VlcReceiverEvents extends EventListener {
	public void receiverSelected();
	public void receiverHasError(SerialPortException ex);
	public void receiverHasMessage();
	public void receivedSuccessfully();
}
