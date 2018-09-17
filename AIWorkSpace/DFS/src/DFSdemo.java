import java.util.Stack;

class Vertex {
	public char label;
	public boolean visited;
	public Vertex(char lab) {
		label=lab;
		visited=false;
		
	}
}

class Graph{
	private final int maxVertices=100;
	
	private Vertex vertexList[];
	private int adjMatrix[][];
	private int vertexCount;
	private Stack theStack;
	
	public Graph(){
		vertexList =new Vertex[maxVertices];
		adjMatrix = new int [maxVertices][maxVertices];
		vertexCount = 0;
		
		for(int i=0;i<maxVertices;i++)
			for(int j=0;j<maxVertices;j++)
				adjMatrix[i][j]=0;
	    theStack= new Stack();
	}
	
	public void addVertex(char lab){
		vertexList[vertexCount++]= new Vertex(lab);
	}
	public void addEdge(int start,int end){
		adjMatrix[start][end]=1;
		adjMatrix[end][start]=1;
	}
	
	public void displayVertex(int v){
		System.out.println(vertexList[v].label);
	}
	
	public void dfs(){
		vertexList[0].visited=true;
		displayVertex(0);
		theStack.push(0);
		while(!theStack.isEmpty()){
			//get an unvisited vertex adjacent to stack top
			int v= getAdjUnvisitedVertex( (int) theStack.peek());
			if(v==-1)
				theStack.pop();
			else{
				vertexList[v].visited = true;
				displayVertex(v);
				theStack.push(v);
			}
			
		}
		for (int j=0;j<vertexCount;j++)  //reset flags
			vertexList[j].visited=false;
	}
	
	public int getAdjUnvisitedVertex(int v){
		for (int j=0;j<vertexCount;j++)
			if(adjMatrix[v][j]==1 && vertexList[j].visited==false)
				return j;
		
		return -1;
	}
}



public class DFSdemo {
	public static void main(String args[]){
	Graph g1 =new Graph();     
	
	for(int i=0;i<100;i++){
		g1.addVertex((char) i);
	}
	
	for(int i=0,j=0,k=0,l=0;i<10;i++){
      g1.addEdge(i, j+1); 
      g1.addEdge(k, 10+l);
      g1.addEdge(i+90, j+1);
      g1.addEdge(9+k, l);
      i++;
      j++;
      k+=10;
      l+=10;
	}
	
	/*
	for(int i=0;i<10;i++){
		
	}
	
	g1.addVertex('a');
	g1.addVertex('b');
	g1.addVertex('c');
	g1.addVertex('d');
	g1.addVertex('e');
	g1.addVertex('f');
	g1.addVertex('g');
	g1.addVertex('h');
	
	g1.addEdge(0, 1);
	g1.addEdge(1, 7);
	g1.addEdge(1, 2);
	g1.addEdge(2, 4);
	g1.addEdge(2, 3);
	g1.addEdge(4, 7);
	g1.addEdge(4, 5);
	g1.addEdge(4, 6);
	
	
	g1.dfs();
	
     */
	}
}
