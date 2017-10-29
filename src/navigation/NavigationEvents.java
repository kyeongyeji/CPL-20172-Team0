package navigation;

import java.util.EventListener;

public interface NavigationEvents extends EventListener {
	public void userMoved();
	public void userArrived();
}
