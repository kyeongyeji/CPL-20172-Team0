package navigation;

import java.util.EventListener;

public interface NavigationEvents extends EventListener {
	public void userMoved(Vertex v, String dir);
	public void userArrived();
}