package data;


import org.newdawn.slick.opengl.Texture;
import static helpers.Clock.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static helpers.Artist.*; 

public class Enemy 
{
 private int width,height,type,currentCheckpoint;
 public float x,y;
 private float speed;
 private Texture texture;
 private Tile startTile;
 private boolean first=true,alive=true;
 private TileGrid grid;
 private int dir =1,d=1; 
 private ArrayList<Checkpoint> checkpoints;
 private int[] directions;
 
 public int getXPlace() {
		return (int)x/64;
	}
	
	public int getYPlace() {
		return (int)y/64;
	}
 public Enemy(){
	  Tile myTile = grid.GetTile((int) (x/64), (int) (y/64) );
	  myTile.setType(TileType.Free);
 }
 public Enemy(Texture texture,
		      Tile startTile,
		      TileGrid grid,
		      int width, 
		      int height,
		      float speed,
		      int type)
 {
	 this.texture=texture;
	 this.startTile=startTile;
	 this.x=startTile.getX();
	 this.y=startTile.getY();
	 this.width=width;
	 this.height=height;
	 this.speed=speed;
	 this.grid=grid;
	 this.type=type;
	 
	 
	 this.checkpoints= new ArrayList<Checkpoint>();
	 this.directions = new int[2];
	 this.directions[0]=0; 
	 //X direction
	 this.directions[1]=0;
	 //Y direction
	 
	directions=FindNextD(startTile);
	
	this.currentCheckpoint=0;
	PopulateCheckpointList();

 }

 public void Update(){


	
			switch(type){
			case 0:
				
		        		  if(CheckpointReached()){
		        			  
		        			  if(currentCheckpoint + 1 ==  checkpoints.size()) // Check if checkpoint actually exists before reaching endof maze
		        			  {
		        				  Die();
		        				  System.out.println("Enemy Reached End of Maze");
		        				  }
		        			  else 
		        			  currentCheckpoint++;
		        		  }
		        		  else{
		        			  x+=Delta() * checkpoints.get(currentCheckpoint).getxDirection()*speed;
		        			  y+=Delta() * checkpoints.get(currentCheckpoint).getyDirection()*speed;

		        		  }
		        		  
			case 1:
		
		
		   }
			
		 
	
   
 }
 private boolean CheckpointReached(){
	
	 boolean reached =false; 
	 Tile t = checkpoints.get(currentCheckpoint).getTile();
	 //Check if position reached tile within variance of 3(arbitrary)
	 if(x> t.getX() -3 &&
			 x<t.getX() +3 &&
			 y> t.getY()-3 &&
			 y< t.getY() + 3)
		 {
		 reached = true;
		    x=t.getX();
		    y=t.getY();
			
		 }
	 return reached;
 }
 private void PopulateCheckpointList(){
	 checkpoints.add(FindNextC(startTile,directions =  FindNextD(startTile)));
	 int counter =0;
	 boolean cont = true;
	 while (cont){
		 int[] currentD= FindNextD(checkpoints.get(counter).getTile());
		 
		 //Check if a next direction/checkpoint exists,end after 20 checkpoints(arbitrary)
		 
		 if(currentD[0] == 2 || counter == 50){   //if unable to find any new checkpoints then stop continuing
			 cont=false;
		 } 
		 else {
			 checkpoints.add(FindNextC(checkpoints.get(counter).getTile(),
					 directions = FindNextD(checkpoints.get(counter).getTile())));
		 }
		 
		 counter++;
	 }
 }
 
 private Checkpoint FindNextC(Tile start,int[] dir){
	 Tile next = null;
	 Checkpoint c = null;
	 
	 //Boolean to decide if next checkpoint is found
	 boolean found =false;
	 
	 int counter=1;
	 
	 while(!found){
		
		 
		 //if current TileType == next tiletype then keep moving or else enemy arrives at the a new checkpoint 
		 if(start.getXPlace() +dir[0] * counter == grid.getTilesWide() ||
				 start.getYPlace() + dir[1] * counter == grid.getTilesHigh() ||
				 start.getType()!= 
				 grid.GetTile(start.getXPlace()+ dir[0]*counter, 
						 start.getYPlace()+ dir[1] *counter).getType())
		 {
			 found =true;//We found the checkpoint!!!
			 
			 counter-=1; //Move counter back 1 to find tile before new tiletype 
			 
			 next = grid.GetTile(start.getXPlace()+ dir[0]*counter,
					 start.getYPlace()+ dir[1] *counter);
			 
		 }
		  
		 counter++;
		 
	 }
	 
	 c = new Checkpoint(next, dir[0],dir[1]);
	 
	 return c;
 }
 
 private int[] FindNextD(Tile start){
	 
	 int[] dir= new int[2];

	 
	 
	 Tile u=grid.GetTile(start.getXPlace(), start.getYPlace()-1);
	 Tile r=grid.GetTile(start.getXPlace()+1, start.getYPlace());
	 Tile d=grid.GetTile(start.getXPlace(), start.getYPlace()+1);
	 Tile l=grid.GetTile(start.getXPlace()-1, start.getYPlace());
	 
	 if(start.getType() == u.getType() && directions[1]!=1 ){
		 dir[0]=0;
		 dir[1]=-1;
	 }else if (start.getType() == r.getType() && directions[0]!=-1){
		 dir[0]=1;
		 dir[1]=0;
	 }
	 else if (start.getType() == d.getType() && directions[1]!=-1){
		 dir[0]=0;
		 dir[1]=1;
	 }
	 else if (start.getType() == l.getType() && directions[0]!=1){
		 dir[0]=-1;
		 dir[1]=0;
	 } else{
		 dir[0]=2;
		 dir[1]=2;
		//System.out.println("NO DIRECTION FOUND");
	 } 
	 
	 return dir;
 }

 
 
 /*
 static final int[] moveX = new int[]{0, 1, 0, -1};   //UP, RIGHT, DOWN, LEFT
 static final int[] moveY = new int[]{1, 0, -1, 0};
 
 static final String[] moves = new String[]{"UP", "RIGHT", "DOWN", "LEFT"};
 
 public void dfs(Tile start, Tile[][] grid){
	 boolean[][] visited = new boolean[10][10];
	 
	 Stack<Tile> stack = new Stack<Tile>();
	 
	 stack.push(start);
	 Tile currentTile;
	 
	 while(!stack.isEmpty()){
		 currentTile = stack.pop();
		 
		 for(int i = 0; i < 4; i++){
			 int X = currentTile.getXPlace() + moveX[i];
			 int Y = currentTile.getYPlace() + moveY[i];
			 
			 if(!isValid(X, Y))
				 continue;
			 
			 if(grid[X][Y].getType() == TileType.Wall){
				 visited[X][Y] = true;
				 continue;
			 }
			 
			 if(!visited[X][Y]){
				 stack.push(grid[X][Y]);
				 visited[X][Y] = true;
				 System.out.print(moves[i] + " ");
			 }
		 }
	 }
	 
 }
 
 private boolean isValid(int x, int y){
	 return (x < 10 && x >=0) && (y < 10 & y >= 10);
 }

 
 public boolean CanVisit(){
	 boolean can = false;
	 
	return can;	 
 }
 */
 public void move_direction(String i){
	 
	 switch (i){
	 case "Right":
		 x+=Delta()*speed;
		 break;
	 case "Left":
		 x-=Delta()*speed;
		 break;
	 case "Up":
		 y-=Delta()*speed;
		 break;
	 case "Down":
		 y+=Delta()*speed;
		 break;
	 }
 }
 
 
 public void Die(){
	 alive=false;
 }
 public void Draw(){
	 DrawQuadTex(texture,x,y,width,height);
 } 
 public TileGrid getTileGrid(){
	 return grid;
 }
 public boolean isAlive(){
	 return alive;
 }
 
}
