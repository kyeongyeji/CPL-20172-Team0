package navigation;

import java.util.ArrayList;

public class Graph {
   public Graph() {
		super();
	}

   ArrayList<Vertex> light = new ArrayList<Vertex>(); 
   ArrayList<Vertex> room = new ArrayList<Vertex>();
   ArrayList<Edge> edge = new ArrayList<Edge>();
   
   public void makeGraph() {
      int i;
      
      light.add(new Vertex("05", "01")); // 무의미한..
      light.add(new Vertex("05", "01")); // light 1: 0501
      light.add(new Vertex("05", "02")); 
      light.add(new Vertex("05", "03"));
      light.add(new Vertex("06", "01"));
      light.add(new Vertex("06", "02"));
      light.add(new Vertex("06", "03"));
      
      light.add(new Vertex("01", "04")); // light 7
      light.add(new Vertex("02", "04"));
      light.add(new Vertex("03", "04")); 
      light.add(new Vertex("04", "04")); 
      light.add(new Vertex("05", "04")); 
      light.add(new Vertex("06", "04"));
      light.add(new Vertex("07", "04"));
      light.add(new Vertex("08", "04"));
      light.add(new Vertex("09", "04"));
      light.add(new Vertex("10", "04")); // light 16
      
      room.add(new Vertex("01", "05")); // toilet_man
      room.add(new Vertex("02", "05")); // toilet_woman
      room.add(new Vertex("03", "05")); // 105
      room.add(new Vertex("04", "05")); // 104
      room.add(new Vertex("05", "05")); // 103
      room.add(new Vertex("06", "05")); // 101
      room.add(new Vertex("02", "02")); // 108
      room.add(new Vertex("04", "02")); // 107
      room.add(new Vertex("07", "02")); // 106
      
      room.add(new Vertex("00", "04")); // 왼쪽 entrance
      room.add(new Vertex("11", "04")); // 오른쪽 entrance
      room.add(new Vertex("05", "00")); // 아래쪽 entrance
      
      
      /* 조명끼리 edge 연결 */
      for(i = 0; i < 9; i++) 
         edge.add(new Edge(light.get(i), light.get(i+1)));
      
      edge.add(new Edge(light.get(10), light.get(11)));
      edge.add(new Edge(light.get(12), light.get(13)));
      edge.add(new Edge(light.get(14), light.get(15)));
      
      edge.add(new Edge(light.get(4), light.get(10)));
      edge.add(new Edge(light.get(10), light.get(12)));
      edge.add(new Edge(light.get(12), light.get(14)));
      
      edge.add(new Edge(light.get(5), light.get(11)));
      edge.add(new Edge(light.get(11), light.get(13)));
      edge.add(new Edge(light.get(13), light.get(15)));
      
      /* 공간과 인접 조명 edge 연결 */
      edge.add(new Edge(room.get(0), light.get(0))); // toilet_man -- light 0
      edge.add(new Edge(room.get(1), light.get(1))); // toilet_wom -- light 1
      edge.add(new Edge(room.get(2), light.get(2))); // 105 -- light 2
      
      edge.add(new Edge(room.get(3), light.get(3))); // 104 -- light
      edge.add(new Edge(room.get(3), light.get(4))); 
      edge.add(new Edge(room.get(3), light.get(5))); 
      
      edge.add(new Edge(room.get(4), light.get(6))); // 103 -- light
      edge.add(new Edge(room.get(4), light.get(7))); 
      
      edge.add(new Edge(room.get(5), light.get(8))); // 101 -- light
      edge.add(new Edge(room.get(5), light.get(9)));
      
      edge.add(new Edge(room.get(6), light.get(1))); // 108 -- light 
      
      edge.add(new Edge(room.get(7), light.get(2))); // 107 -- light
      edge.add(new Edge(room.get(7), light.get(3))); 
      edge.add(new Edge(room.get(7), light.get(10))); 
      edge.add(new Edge(room.get(7), light.get(12))); 
      edge.add(new Edge(room.get(7), light.get(14))); 
      
      edge.add(new Edge(room.get(8), light.get(6))); // 106 -- light
      edge.add(new Edge(room.get(8), light.get(7))); 
      edge.add(new Edge(room.get(8), light.get(8))); 
      edge.add(new Edge(room.get(8), light.get(11))); 
      edge.add(new Edge(room.get(8), light.get(13))); 
      edge.add(new Edge(room.get(8), light.get(15)));
      
      edge.add(new Edge(room.get(9), light.get(0))); // entrance
      edge.add(new Edge(room.get(10), light.get(9))); 
      edge.add(new Edge(room.get(11), light.get(14)));
      edge.add(new Edge(room.get(11), light.get(15))); 
   }
}

class Edge{
   Vertex v1, v2;
   
   public Edge(Vertex v1, Vertex v2){
      this.v1 = v1;
      this.v2 = v2;
   }
}