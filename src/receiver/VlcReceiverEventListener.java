package receiver;

import javax.swing.event.EventListenerList;

public class VlcReceiverEventListener {
	static VlcReceiverEventListener instance;
	
	protected EventListenerList listenerList;
	
	private VlcReceiverEventListener() {
		listenerList = new EventListenerList();
	}
	
	public static VlcReceiverEventListener getInstance() {
		if(instance == null)
			instance = new VlcReceiverEventListener();
		
		return instance;
	}
	
	public void addMyEventListener(VlcReceiverEvents listener) {
	    listenerList.add(VlcReceiverEvents.class, listener);
	}
	
	public void removeMyEventListener(VlcReceiverEvents listener) {
	    listenerList.remove(VlcReceiverEvents.class, listener);
	}
	
	public Object[] getListenerList() {
		return listenerList.getListenerList();
	}
}
