package navigation;

import java.util.ArrayList;
import java.util.LinkedList;

import navigation.MyLinkedList.Node;

public class Graph {
   int numOfLight = 16;
   int numOfRoom = 12;
   ArrayList<Vertex> vertex = new ArrayList<Vertex>(); 
   MyLinkedList[] list = new MyLinkedList[numOfLight+1];
   boolean[] visited = new boolean[numOfLight+1];
   
   public void makeGraph() {
      vertex.add(new Vertex("05", "01")); // 무의미한..
      vertex.add(new Vertex("05", "01")); // light 1: 0501
      vertex.add(new Vertex("05", "02")); 
      vertex.add(new Vertex("05", "03"));
      vertex.add(new Vertex("06", "01"));
      vertex.add(new Vertex("06", "02"));
      vertex.add(new Vertex("06", "03"));
      
      vertex.add(new Vertex("01", "04")); // light 7
      vertex.add(new Vertex("02", "04"));
      vertex.add(new Vertex("03", "04")); 
      vertex.add(new Vertex("04", "04")); 
      vertex.add(new Vertex("05", "04")); 
      vertex.add(new Vertex("06", "04"));
      vertex.add(new Vertex("07", "04"));
      vertex.add(new Vertex("08", "04"));
      vertex.add(new Vertex("09", "04"));
      vertex.add(new Vertex("10", "04")); // light 16
      
      vertex.add(new Vertex("01", "05")); // toilet_man (17)
      vertex.add(new Vertex("02", "05")); // toilet_woman (18)
      vertex.add(new Vertex("03", "05")); // 105 (19)
      vertex.add(new Vertex("04", "05")); // 104 (20)
      vertex.add(new Vertex("05", "05")); // 103 (21)
      vertex.add(new Vertex("06", "05")); // 101 (22)
      vertex.add(new Vertex("02", "02")); // 108 (23)
      vertex.add(new Vertex("04", "02")); // 107 (24)
      vertex.add(new Vertex("07", "02")); // 106 (25)
      
      vertex.add(new Vertex("00", "04")); // 왼쪽 entrance (26)
      vertex.add(new Vertex("05", "00")); // 아래쪽 entrance (27)
      vertex.add(new Vertex("11", "04")); // 오른쪽 entrance (28)
   }
   
   public void createList()
   {
      for(int i = 0; i <= numOfLight; i++) {
         if(list[i] == null) {
            //System.out.println(i);
            list[i] = new MyLinkedList();
         }
      }
      
      list[1].add(vertex.get(2));
      list[1].add(vertex.get(27));
      
      list[2].add(vertex.get(1));
      list[2].add(vertex.get(3));

      list[3].add(vertex.get(2));
      list[3].add(vertex.get(11));

      list[4].add(vertex.get(5));
      list[4].add(vertex.get(27));

      list[5].add(vertex.get(4));
      list[5].add(vertex.get(6));

      list[6].add(vertex.get(5));
      list[6].add(vertex.get(12));

      for(int i = 7; i < numOfLight; i++) {
         list[i].add(vertex.get(i+1));
      }
      
      for(int i = numOfLight; i > 7; i--) {
         list[i].add(vertex.get(i-1));
      }
      
      
      list[7].add(vertex.get(17));
      list[7].add(vertex.get(26));
      
      list[8].add(vertex.get(18));
      list[8].add(vertex.get(23));
      
      list[10].add(vertex.get(20));
      list[10].add(vertex.get(24));
      
      list[11].add(vertex.get(20));
      
      list[12].add(vertex.get(20));
      
      list[13].add(vertex.get(21));
   
      list[14].add(vertex.get(25));
      
      list[15].add(vertex.get(25));
      
      list[16].add(vertex.get(22));
      list[16].add(vertex.get(28));
      
/*      System.out.println("*****************************");
      for(int i = 1; i <= numOfLight; i++) {
         System.out.print(i + " | ");
         list[i].printList();
      }*/
   }
   
   public int dfs(int i, int des, ArrayList<Vertex> path)
   {
      visited[i] = true;
         
      if(i == des) {
         path.add(vertex.get(i));
         return 1;
      }
      
      for(int j = 1;  j <= numOfLight; j++) {
         if(visited[j] == false && list[i].findNode(vertex.get(j))) {
            
            if(dfs(j, des, path) == 1) {path.add(vertex.get(i)); return 1;}
            
         }
      }
      return 0;
   }
}