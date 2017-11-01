package navigation;

import java.util.EventListener;

public interface NavigationEvents extends EventListener {
	public void userMoved(Vertex v, int index, String dir);
	public void userArrived();
}