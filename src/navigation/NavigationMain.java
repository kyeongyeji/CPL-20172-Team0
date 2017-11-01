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
	      
	      path.add(end);
	      path.add(graph.light.get(9));
	      path.add(graph.light.get(10));
	      path.add(graph.light.get(11));
	      path.add(graph.light.get(3));
	      path.add(graph.light.get(2));
	      path.add(start);
	      
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
			System.out.println(navigationMode);
			
			Object[] listeners = listener.getListenerList();
			
			// 1. 현재 길 안내 모드인지 확인
			if(navigationMode) {
				// 1.a 해당 경로로 움직였는지 확인
				
				// 1.b 움직였다면 마지막 경로인지 확인
				// 마지막 경로 인 경우
				if (path.isEmpty()) {
					for (int i = 0; i < listeners.length; i = i+2) {
					if (listeners[i] == NavigationEvents.class) {
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
					Vertex next;
					String direction = null;
		            int dir_x, dir_y, v_x, v_y;	         
		            
		            int index;
		            index = graph.light.indexOf(v);
		            
					if (path.size()-1 >= 1)  {
						next = path.get(path.size() - 1);
  
			            v_x = Integer.parseInt(v.getX());
			            v_y = Integer.parseInt(v.getY());
			            dir_x = Integer.parseInt(next.getX());
			            dir_y = Integer.parseInt(next.getY());
						
			            
			            if(dir_x == v_x+1)
			                direction = "우회전하세요.";
			            else if (dir_x == v_x-1)
			                direction = "좌회전하세요.";
			            else if(dir_y == v_y+1)
			                direction = "직진하세요.";
			            else if(dir_y == v_y-1)
			                direction = "후진하세요.";
					}
					
					System.out.println("방향:" + direction);
					System.out.printf("index: %d", index);
					// 현재 위치를 UI에게 알려줌
					for (int i = 0; i < listeners.length; i = i+2) {
						if (listeners[i] == NavigationEvents.class) {
							((NavigationEvents)listeners[i+1]).userMoved(v, index, direction); // 현재 위치를 인자로 넘겨줘야 됨
						}
					}
				}
			} else {
				System.out.println("안내 모드 x...");
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
