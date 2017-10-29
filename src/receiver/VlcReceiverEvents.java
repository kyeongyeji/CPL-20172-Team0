package receiver;

import java.util.EventListener;

public interface VlcReceiverEvents extends EventListener {
	public void receiverSelected();
	public void receiverHasError();
	public void receiverHasMessage();
	public void receivedSuccessfully();
}
