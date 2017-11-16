package navigation;

public class MyLinkedList {
   
   private Node header = null;
   private Node p = null;
   
   class Node{
      Vertex v;
      Node next = null;
   }
   
   public void add(Vertex v) {
      Node newnode = new Node();
      newnode.v = v;
      newnode.next = null;
      
      if(header == null) {
         header = newnode;
         return;
      }
      
      p = header;
      while(p.next != null) {
         p = p.next;
      }
      
      p.next = newnode;
   }
   
   public void printList() {
      if(header == null) {
         System.out.println("������ ���Ḯ��Ʈ ����");
         return;
      }
      p = header;
      
      while(p.next != null) {
         System.out.print(p.v.getId() + " -> ");
         p = p.next;
      }
      System.out.println(p.v.getId());
   }
   
   public boolean findNode(Vertex v)
   {
      Node pointer = header;
      
      while(pointer != null) {
         if(pointer.v.equals(v) )
            return true;
         pointer = pointer.next;
      }
      
      return false;
   }
}