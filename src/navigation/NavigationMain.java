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
   int light_id;
   int des_id;

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
      
      switch(destination) {
      case 1:
    	  des_id = 3;
    	  break;
      case 3:
    	  des_id = 4;
    	  break;
      case 5:
    	  des_id = 5;
    	  break;
      case 11:
    	  des_id = 6;
    	  break;
      case 10:
    	  des_id = 2;
    	  break;
      } 
   }
   
   private ArrayList<Vertex> findPath(int destination) {
	  int length = graph.list.length;
	  boolean find = false;
	  int i;
      Vertex end = graph.vertex.get(8); // 목적지 : 여자화장실
      ArrayList<Vertex> path = new ArrayList<Vertex>();
      
      path.add(new Vertex("00", "00"));
      
      for(i = 0; i < length; i++){
    	  find = graph.list[i].findNode(graph.vertex.get(destination+length));
    	  if(find == true)
    		  break;
      }
      
      System.out.println("조명 ID " + light_id);
      
      switch (light_id) {
      case 1:
    	  graph.dfs(1, i, path);
    	  break;
      case 2:
    	  graph.dfs(4, i, path);
    	  break;
      case 3:
    	  graph.dfs(8, i, path);
    	  break;
      case 4:
    	  graph.dfs(11, i, path);
    	  break;
      case 5:
    	  graph.dfs(13, i, path);
    	  break;
      case 6:
    	  graph.dfs(16, i, path);
    	  break;
      }
     
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
      public void receivedSuccessfully(String message) { // message = id
         System.out.println(navigationMode);
         Object[] listeners = listener.getListenerList();
         
         light_id = Integer.parseInt(message);
         
         System.out.println("in receivedsuccessfully " + light_id);
         
         // 1. 현재 길 안내 모드인지 확인
         if (navigationMode) {
            // 1.a 해당 경로로 움직였는지 확인
            
            // 1.b 움직였다면 마지막 경로인지 확인
            // 마지막 경로 인 경우
            if (index <= 0) {
            	light_id = Integer.parseInt(message);
            	if (des_id == light_id) {
            		System.out.println("목적지 ID : " + light_id);
	                for (int i = 0; i < listeners.length; i = i+2) {
	                  if (listeners[i] == NavigationEvents.class) {
	                     ((NavigationEvents)listeners[i+1]).userArrived();
	                  }
	                }
	                
	                navigationMode = false; // 도착 -> if문 빠져나감
		            direction = null;
            	}  
            	
            	//navigationMode = false; // 도착 -> if문 빠져나감
	            //direction = null;
            }
            else {
	            // 1.c 마지막 경로가 아닌 경우
	            Vertex v = noticePath();
	            int index = graph.vertex.indexOf(v);
	            
	            // 현재 위치를 UI에게 알려줌
		        for (int i = 0; i < listeners.length; i = i+2) {
		           if (listeners[i] == NavigationEvents.class) {
		              ((NavigationEvents)listeners[i+1]).userMoved(v, index, direction); // 현재 위치를 인자로 넘겨줘야 됨
		           }
		        }
            }
        
         } else {
            // 2 길안내 모드가 아닌 경우
            
            // 2.a 현재 위치에 해당하는 조명 ID 찾기
            // 2.b 현재 위치를 UI에게 알려줌
            
//            for (int i = 0; i < listeners.length; i = i+2) {
//               if (listeners[i] == VlcReceiverEvents.class) {
//                  ((NavigationEvents)listeners[i+1]).userMoved(); // 현재 위치를 인자로 넘겨줘야 됨
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
                direction = "직진하세요";
             }
             else {
                changeDir = false;
                if(next_x == cur_x+1)
                      direction = "우회전하세요";
                else if (next_x == cur_x-1)
                      direction = "좌회전하세요";
               else if(next_y == cur_y+1)
                      direction = "좌회전하세요";
               else if(next_y == cur_y-1)
                      direction = "우회전하세요";    
             }
            }
            
            System.out.println("방향: " + direction);
            
            index--;
                  
            return v;
   }
   
   public static void main(String[] args) {
      new NavigationMain();
   }
}