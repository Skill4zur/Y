package utils;

public class QuadTree<T> {

	private T value;
	//  N     N  W
	// W E ou 
	//  S     E  S
	private QuadTree<T> north, east, south, west;
	
	public QuadTree(T value) {
		this.value = value;
	}
	
	public void addChild(T north, T east, T south, T west) {
		this.north = new QuadTree<T>(north);
		this.east = new QuadTree<T>(east);
		this.south = new QuadTree<T>(south);
		this.west = new QuadTree<T>(west);
	}

}
