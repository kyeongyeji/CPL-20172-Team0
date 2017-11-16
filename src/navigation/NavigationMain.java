package navigation;
 
 import java.util.ArrayList;
 
 import receiver.VlcReceiverEventListener;
 import receiver.VlcReceiverEvents;
 
public class NavigationMain implements Navigation {
   NavigationEventListener listener = NavigationEventListener.getInstance();
   //VlcReceiverEventListener ReceiverEvtListenr = VlcReceiverEventListener.getInstance();
   
   Graph graph;
   ArrayList<Vertex> path;
   String direction = null;
   int index;

   boolean navigationMode;
   
   public NavigationMain() {
       listener = NavigationEventListener.getInstance();
       
       VlcReceiverEventListener.getInstance().addMyEventListener(new ReceiverEvtHandler());
       
       navigationMode = false;
    }
   
   @Override
   public void startNavigation(int destination) {
      navigationMode = true;
      
      graph = new Graph();
      path = new ArrayList<Vertex>();
      
      graph.makeGraph();
      graph.createList();
      path = findPath(destination);
      
      System.out.println(destination);
   }
   
   private ArrayList<Vertex> findPath(int destination) {
	  int length = graph.list.length;
	  boolean find = false;
	  int i;
      Vertex end = graph.vertex.get(8); // ������ : ����ȭ���
      ArrayList<Vertex> path = new ArrayList<Vertex>();
      
      path.add(new Vertex("00", "00"));
      
      for(i = 0; i < length; i++){
    	  find = graph.list[i].findNode(graph.vertex.get(destination+length));
    	  if(find == true)
    		  break;
      }
      
      
      graph.dfs(1, i, path);
      index = path.size() - 1;
      
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
    	  
         //System.out.println("received successfully");
         System.out.println(navigationMode);
         Object[] listeners = listener.getListenerList();
         
         // 1. ���� �� �ȳ� ������� Ȯ��
         if(navigationMode) {
            // 1.a �ش� ��η� ���������� Ȯ��
            
            // 1.b �������ٸ� ������ ������� Ȯ��
            // ������ ��� �� ���
            if (index == 0) {
               for (int i = 0; i < listeners.length; i = i+2) {
                  if (listeners[i] == NavigationEvents.class) {
                     ((NavigationEvents)listeners[i+1]).userArrived();
                  }
               }
               navigationMode = false;
               direction = null;
            }
            
            // 1.c ������ ��ΰ� �ƴ� ���
            // �̵�
            
            Vertex v = noticePath();
            int index = graph.vertex.indexOf(v);
            
            // ���� ��ġ�� UI���� �˷���
	            for (int i = 0; i < listeners.length; i = i+2) {
	               if (listeners[i] == NavigationEvents.class) {
	                  ((NavigationEvents)listeners[i+1]).userMoved(v, index, direction); // ���� ��ġ�� ���ڷ� �Ѱ���� ��
	               }
	            }
        
         } else {
            // 2 ��ȳ� ��尡 �ƴ� ���
            
            // 2.a ���� ��ġ�� �ش��ϴ� ���� ID ã��
            // 2.b ���� ��ġ�� UI���� �˷���
            
//            for (int i = 0; i < listeners.length; i = i+2) {
//               if (listeners[i] == VlcReceiverEvents.class) {
//                  ((NavigationEvents)listeners[i+1]).userMoved(); // ���� ��ġ�� ���ڷ� �Ѱ���� ��
//               }
//            }
         }
      }
      
   }
   
   public Vertex noticePath() {
        boolean changeDir = false;
        boolean turnRight = false, turnLeft = false;
        int bef_x, bef_y, cur_x, cur_y, next_x, next_y;
        
            Vertex v = path.get(index);         
            Vertex next;
            
            if (index > 0) {
            	next = path.get(index-1);
             
	             cur_x = Integer.parseInt(v.getX());
	             cur_y = Integer.parseInt(v.getY());
	             next_x = Integer.parseInt(next.getX());
	             next_y = Integer.parseInt(next.getY());
             
             if(index < path.size()-1) {
               Vertex before = path.get(index+1);
               bef_x = Integer.parseInt(before.getX());
               bef_y = Integer.parseInt(before.getY());
               
               if(bef_x == cur_x && cur_x != next_x)
                  changeDir = true;
               
               else if(bef_x != cur_x && cur_x == next_x)
                  changeDir = true;
             }
 
             
             if(changeDir == false) {
                direction = "�����ϼ���";
             }
             else {
                changeDir = false;
                if(next_x == cur_x+1)
                      direction = "��ȸ���ϼ���";
                else if (next_x == cur_x-1)
                      direction = "��ȸ���ϼ���";
               else if(next_y == cur_y+1)
                      direction = "��ȸ���ϼ���";
               else if(next_y == cur_y-1)
                      direction = "��ȸ���ϼ���";    
             }
            }
           //  System.out.println(cur_x + ", " + cur_y + "    " + next_x + ", " + next_y);
             System.out.println("����: " + direction);
                	 
            index--;
            
            return v;
   }
   
   public static void main(String[] args) {
      new NavigationMain();
   }

}