package navigation;

import java.util.ArrayList;

import receiver.VlcReceiverEvents;

public class NavigationMain implements Navigation {
	NavigationEventListener listener = NavigationEventListener.getInstance();
	
	Graph graph;
	ArrayList<Vertex> path;
	
	boolean navigationMode;
	
	public NavigationMain() {
		// ReceiverEventListener.getInstance().addReceiverEventListener(new ReceiverEvtHandler());
		navigationMode = false;
	}
	
	@Override
	public void startNavigation(String destination) {
		navigationMode = true;
		
		graph = new Graph();
		path = new ArrayList<Vertex>();
		
		graph.makeGraph();
		
		path = findPath();
	}
	
	private ArrayList<Vertex> findPath() {
		Vertex start = graph.light.get(12); // 출발지 : entrance
		Vertex end = graph.light.get(1); // 목적지 : 여자화장실
		ArrayList<Vertex> path = new ArrayList<Vertex>();
		
		path.add(start);
		path.add(graph.light.get(1));
		path.add(graph.light.get(2));
		path.add(graph.light.get(3));
		path.add(graph.light.get(4));
		path.add(graph.light.get(12));
		path.add(graph.light.get(11));
		path.add(graph.light.get(10));
		path.add(end);
		
		return path;
	}
	
	class ReceiverEvtHandler implements VlcReceiverEvents {

		@Override
		public void receiverSelected() {
		}

		@Override
		public void receiverHasError() {
		}

		@Override
		public void receiverHasMessage() {
		}

		@Override
		public void receivedSuccessfully() {
			Object[] listeners = listener.getListenerList();
			
			// 1. 현재 길 안내 모드인지 확인
			if(navigationMode) {
				// 1.a 해당 경로로 움직였는지 확인
				
				// 1.b 움직였다면 마지막 경로인지 확인
				// 마지막 경로 인 경우
//				for (int i = 0; i < listeners.length; i = i+2) {
//					if (listeners[i] == VlcReceiverEvents.class) {
//						((NavigationEvents)listeners[i+1]).userArrived();
//					}
//				}
//				
//				navigationMode = false;
				
				// 1.c 마지막 경로가 아닌 경우
				// 이동
				@SuppressWarnings("unused")
				Vertex v = path.remove(path.size() - 1);
				
				// 현재 위치를 UI에게 알려줌
				for (int i = 0; i < listeners.length; i = i+2) {
					if (listeners[i] == VlcReceiverEvents.class) {
						((NavigationEvents)listeners[i+1]).userMoved(); // 현재 위치를 인자로 넘겨줘야 됨
					}
				}
			} else {
				// 2 길안내 모드가 아닌 경우
				
				// 2.a 현재 위치에 해당하는 조명 ID 찾기
				// 2.b 현재 위치를 UI에게 알려줌
				
//				for (int i = 0; i < listeners.length; i = i+2) {
//					if (listeners[i] == VlcReceiverEvents.class) {
//						((NavigationEvents)listeners[i+1]).userMoved(); // 현재 위치를 인자로 넘겨줘야 됨
//					}
//				}
			}
		}
		
	}
	
	public static void main(String[] args) {
		new NavigationMain();
	}
//	
//	@Override
//	public void userArrived() {
//		String arriveMessage;
//		
//		arriveMessage = "Arrive!!";
//		
//		System.out.println(arriveMessage);
//	}
}
