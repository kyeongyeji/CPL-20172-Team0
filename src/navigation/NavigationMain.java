package navigation;

import java.util.ArrayList;

import receiver.VlcReceiverEventListener;
import receiver.VlcReceiverEvents;

public class NavigationMain implements Navigation {
	NavigationEventListener listener;
	//VlcReceiverEventListener ReceiverEvtListenr = VlcReceiverEventListener.getInstance();
	
	Graph graph;
	ArrayList<Vertex> path;
	
	boolean navigationMode;
	
	public NavigationMain() {
		listener = NavigationEventListener.getInstance();
		
		VlcReceiverEventListener.getInstance().addMyEventListener(new ReceiverEvtHandler());
		
		navigationMode = false;
	}
	
	@Override
	public void startNavigation(String destination) {
		navigationMode = true;
		
		graph = new Graph();
		path = new ArrayList<Vertex>();
		
		graph.makeGraph();
		
		path = findPath();
		
		System.out.println("start navigation");
		
		for (Vertex v : path) 
			System.out.println(v);
	}
	
	private ArrayList<Vertex> findPath() {
	      Vertex start = graph.light.get(1); // 출발지 : entrance
	      Vertex end = graph.light.get(8); // 목적지 : 여자화장실
	      ArrayList<Vertex> path = new ArrayList<Vertex>();
	      
	      path.add(start);
	      path.add(graph.light.get(2));
	      path.add(graph.light.get(3));
	      path.add(graph.light.get(11));
	      path.add(graph.light.get(10));
	      path.add(graph.light.get(9));
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
		public void receiverHasMessage(String message) {
			
		}

		@Override
		public void receivedSuccessfully(String message) {
			
			System.out.println("received successfully");
			System.out.println(message);
			
			Object[] listeners = listener.getListenerList();
			
			// 1. 현재 길 안내 모드인지 확인
			if(navigationMode) {
				// 1.a 해당 경로로 움직였는지 확인
				
				// 1.b 움직였다면 마지막 경로인지 확인
				// 마지막 경로 인 경우
				if (path.isEmpty()) {
					for (int i = 0; i < listeners.length; i = i+2) {
					if (listeners[i] == VlcReceiverEvents.class) {
						((NavigationEvents)listeners[i+1]).userArrived();
						}
					}
					navigationMode = false;
				}
				
				else {
				
				// 1.c 마지막 경로가 아닌 경우
				// 이동
					@SuppressWarnings("unused")
					Vertex v = path.remove(path.size() - 1);
		            Vertex next = path.get(path.size() - 1);
		            
		            String direction = null;
		            int dir_x, dir_y, v_x, v_y;
		            
		            v_x = Integer.parseInt(v.getX());
		            v_y = Integer.parseInt(v.getY());
		            dir_x = Integer.parseInt(next.getX());
		            dir_y = Integer.parseInt(next.getY());
		            
		            if(v_x == dir_x+1)
		            	direction = "좌회전하세요.";
		            else if (v_x == dir_x-1)
		            	direction = "우회전 동의? 어 보감.";
		            else if(v_y == dir_y+1)
		            	direction = "직진 용비? 어 천가~";
		            else if(v_y == dir_y-1)
		            	direction = "후진 동휘? 어 보검.";
					
					// 현재 위치를 UI에게 알려줌
					for (int i = 0; i < listeners.length; i = i+2) {
						if (listeners[i] == VlcReceiverEvents.class) {
							((NavigationEvents)listeners[i+1]).userMoved(v, direction); // 현재 위치를 인자로 넘겨줘야 됨
						}
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
}
