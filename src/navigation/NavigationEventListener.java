package navigation;

import javax.swing.event.EventListenerList;

public class NavigationEventListener {
   static NavigationEventListener instance;
   
   protected EventListenerList listenerList = new EventListenerList();
   
   private NavigationEventListener() {
      
   }
   
   public static NavigationEventListener getInstance() {
      if(instance == null)
         instance = new NavigationEventListener();
      
      return instance;
   }
   
   public Object[] getListenerList() {
      return listenerList.getListenerList();
   }
   
   public void addNavigationEventListener(NavigationEvents i) {
      listenerList.add(NavigationEvents.class, i);
   }
   
   public void removeNavigationEventListener(NavigationEvents i) {
      listenerList.remove(NavigationEvents.class, i);
   }
}