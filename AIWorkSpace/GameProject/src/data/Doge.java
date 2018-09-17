package data;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.opengl.Texture;
import static helpers.Clock.*;

import java.util.List;
import java.util.Stack;

import static helpers.Artist.*; 

public class Doge 
{
 private static final float NULL = 0;
private int width,height;
 public float x,y;
 private float speed;
 private Texture texture;
 public Tile startTile;
 private boolean first=true;
 private TileGrid grid;
 private int count=0;
 private float targetr,targetl,targetu,targetd,target;

 public int stuck=0,l,r,u,d;
 
 public Doge(){}
 
 public Doge(Texture texture,Tile startTile,TileGrid grid,int width,int height,float speed)
 {
	 this.texture=texture;
	 this.x=startTile.getX();
	 this.y=startTile.getY();
	 this.width=width;
	 this.height=height;
	 this.speed=speed;
	 this.grid=grid;
	 
 }
 public int getXPlace() {
		return (int)x/64;
	}
	
	public int getYPlace() {
		return (int)y/64;
	}

 public void Update(int key){
	 
	 Tile myTile = grid.GetTile((int) (x/64), (int) (y/64) );
	 Tile leftTile=  grid.GetTile((int)(x/64)-1, (int) (y/64));
	 Tile upTile = grid.GetTile((int) (x/64), (int) (y/64)-1);
	 Tile downTile = grid.GetTile((int) (x/64), (int) (y/64)+1);
	 Tile rightTile = grid.GetTile((int) (x/64)+1, (int) (y/64) );
	 Tile endTile = grid.GetTile(1,1 );
	 if(first)
		 first=false;
	 else{ 
		
	   if(leftTile == endTile || upTile == endTile || downTile == endTile || rightTile == endTile){
		endTile.setType(TileType.Free);   
	   } else endTile.setType(TileType.Wall);
		 
	     check_stuck();
	     
	     move(key);
		   
		
       }
   
 }
 
 
 public void check_stuck(){
	 Tile myTile = grid.GetTile((int) (x/64), (int) (y/64) );
	 Tile leftTile=  grid.GetTile((int)(x/64)-1, (int) (y/64));
	 Tile upTile = grid.GetTile((int) (x/64), (int) (y/64)-1);
	 Tile downTile = grid.GetTile((int) (x/64), (int) (y/64)+1);
	 Tile rightTile = grid.GetTile((int) (x/64)+1, (int) (y/64) );
	
	if(rightTile.getType() == TileType.Wall)
	  {
		r=1;
			
	   } 
	 if(leftTile.getType()== TileType.Wall)
	  {
	     l=1;
	   
	   }  
	 if( upTile.getType()== TileType.Wall)
	  {
	     u=1;					
	  }
	 if( downTile.getType()== TileType.Wall)
	  {
		 d=1;						
	  }
			  
	 stuck=l+r+u+d;
	 
	 if(myTile.getXPlace() == 9 && myTile.getYPlace() == 9 && (myTile.getType() != TileType.Free)){
		 stuck=4;
	 }
	 
	 if(stuck == 3 && (myTile.getXPlace() == 9 || myTile.getYPlace() == 9)){
		 stuck=4;
	 }
    l=0;r=0;u=0;d=0;
    
 }

 
 public void move(int key){
	 Tile myTile = grid.GetTile((int) (x/64), (int) (y/64) );
	 Tile leftTile=  grid.GetTile((int)(x/64)-1, (int) (y/64));
	 Tile upTile = grid.GetTile((int) (x/64), (int) (y/64)-1);
	 Tile downTile = grid.GetTile((int) (x/64), (int) (y/64)+1);
	 Tile rightTile = grid.GetTile((int) (x/64)+1, (int) (y/64) );
	 Tile endTile= grid.GetTile(1, 1);
		
	 if(rightTile.getType() == TileType.Free  && key==1)
	  {
			
	  targetr = x+64;
	  }  
	 if(leftTile.getType()== TileType.Free   && key==2)
	 {
	  targetl = x-64;
		   
		   }  
	 if( upTile.getType()== TileType.Free && key==3)
	  {
		targetu = y-64;
	  }
	 if( downTile.getType()== TileType.Free && key==4)
	  {
		targetd = y+64;
	  }
		
	 if(targetr != NULL){
	 x+=Delta()*speed;
	
	 if (x>=targetr){
	 x=targetr;
	 //  myTile.setType(TileType.Free);

	 //leftTile.setType(TileType.Free);
	 targetr=NULL;
	 }
	}  
	 else if(targetl != NULL){
	 x-=Delta()*speed;
	
	 if (x<=targetl){
	 x=targetl;
	  // myTile.setType(TileType.Free);

	 //rightTile.setType(TileType.Free);
	 targetl=NULL;
	 }
	}
	 
	else if(targetu != NULL){
	 y-=Delta()*speed;
	if (y<=targetu){
	 y=targetu;
	 //  myTile.setType(TileType.Free);

	// downTile.setType(TileType.Free);
	 targetu=NULL;
	 }
	} 
	 else if(targetd != NULL){
	 y+=Delta()*speed;
	 if (y>=targetd){
	 y=targetd;
	 //  myTile.setType(TileType.Free);

	// upTile.setType(TileType.Free);
	 targetd=NULL;
	 }
	} 
	
			
 }
 //private boolean pathContinues(){
	// boolean answer=true;
	 
	 //Tile myTile = grid.GetTile((int) (x/64), (int) (y/64) );
	// Tile nextTile = grid.GetTile((int) (x/64)+1, (int) (y/64));
	 
	// if(myTile.getType() != nextTile.getType())
	//	 answer=false;
	 
//	 return answer;
 //}
 
 public void Draw(){
	 DrawQuadTex(texture,x,y,width,height);
 } 
 public TileGrid getTileGrid(){
	 return grid;
 }

 
}
