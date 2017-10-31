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
		Vertex start = graph.light.get(12); // ����� : entrance
		Vertex end = graph.light.get(1); // ������ : ����ȭ���
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
			
			// 1. ���� �� �ȳ� ������� Ȯ��
			if(navigationMode) {
				// 1.a �ش� ��η� ���������� Ȯ��
				
				// 1.b �������ٸ� ������ ������� Ȯ��
				// ������ ��� �� ���
//				for (int i = 0; i < listeners.length; i = i+2) {
//					if (listeners[i] == VlcReceiverEvents.class) {
//						((NavigationEvents)listeners[i+1]).userArrived();
//					}
//				}
//				
//				navigationMode = false;
				
				// 1.c ������ ��ΰ� �ƴ� ���
				// �̵�
				@SuppressWarnings("unused")
				Vertex v = path.remove(path.size() - 1);
				
				// ���� ��ġ�� UI���� �˷���
				for (int i = 0; i < listeners.length; i = i+2) {
					if (listeners[i] == VlcReceiverEvents.class) {
						((NavigationEvents)listeners[i+1]).userMoved(); // ���� ��ġ�� ���ڷ� �Ѱ���� ��
					}
				}
			} else {
				// 2 ��ȳ� ��尡 �ƴ� ���
				
				// 2.a ���� ��ġ�� �ش��ϴ� ���� ID ã��
				// 2.b ���� ��ġ�� UI���� �˷���
				
//				for (int i = 0; i < listeners.length; i = i+2) {
//					if (listeners[i] == VlcReceiverEvents.class) {
//						((NavigationEvents)listeners[i+1]).userMoved(); // ���� ��ġ�� ���ڷ� �Ѱ���� ��
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
