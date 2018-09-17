package data;

import org.lwjgl.input.Keyboard;

public class Player {

	private TileGrid grid;
	
	public Player(TileGrid grid){
		this.grid = grid;
	}
	
	public void SetTile(){
		
	}
	
	public int Update(){
		int key =0;
		while(Keyboard.next()){
	    	 if(Keyboard.getEventKey() == Keyboard.KEY_RIGHT && Keyboard.getEventKeyState()){
			System.out.println("right");
			key=1;
			break;
			
	      	} else  if(Keyboard.getEventKey() == Keyboard.KEY_LEFT && Keyboard.getEventKeyState()){
			System.out.println("left");
			key=2;
			break;
	      	}
	      	else  if(Keyboard.getEventKey() == Keyboard.KEY_UP && Keyboard.getEventKeyState()){
				System.out.println("up");
				key=3;
				break;
		      	}
	      	else  if(Keyboard.getEventKey() == Keyboard.KEY_DOWN && Keyboard.getEventKeyState()){
				System.out.println("down");
				key=4;
				break;
		      	}
	      	else{if(Keyboard.getEventKey() == Keyboard.KEY_ESCAPE && Keyboard.getEventKeyState()){break;}}
		 
		 
       }
		return key;
   }
}
