package navigation;

public class Vertex {
	String id;
	String x, y;
	

	
	public Vertex(String x, String y) {
		this.x = x;
		this.y = y;
		
		this.id = x+y;
	}
	
	public Vertex() {
		
	}
	public String getId() {
		return id;
	}
	
	@Override
	public String toString() {
		return id;
	}
}