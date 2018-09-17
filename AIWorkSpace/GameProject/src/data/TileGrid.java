package data;
import static helpers.Artist.*;
public class TileGrid {

	public Tile[][] map;
	private int tilesWide,tilesHigh;
	
	public TileGrid(){
		map=new Tile[tilesWide][tilesHigh];
		for(int i=0;i<map.length;i++){
			for (int j=0;j <map[i].length;j++){
				map[i][j] = new Tile(i*64,j* 64,64,64, TileType.Free);
			}
		}
		
	}
	
	public TileGrid(int[][] newMap){
		this.tilesWide=newMap[0].length;
		this.tilesHigh=newMap.length;
		
		map= new Tile[10][10];
		for(int i=0;i<map.length;i++){
			for (int j=0;j <map[i].length;j++){
	       
	        	  switch(newMap[j][i]){
	        	  case 0:
	        		  map[i][j] = new Tile(i*64,j* 64,64,64, TileType.Free);
	        		  break;
	        	  case 1:
		              map[i][j] = new Tile(i*64,j* 64,64,64, TileType.Wall);
		              break;
	        	  case 2:
	        		  map[i][j] = new Tile(i*64,j* 64,64,64, TileType.Start);
	        		  break;
	        	  case 3:
	        		  map[i][j] = new Tile(i*64,j* 64,64,64, TileType.End);
                      break;
	        	  case 4:
	        		  map[i][j] = new Tile(i*64,j* 64,64,64, TileType.Free);
                      break;
                  //add more objects here
                      
	        	  }
	        	  
	          
			}
		}
		
	}
	
	public void SetTile(int xCoord,int yCoord, TileType type){
		map[xCoord][yCoord]= new Tile(xCoord*64,yCoord*64,64,64,type);
	}
	

	
	public Tile GetTile(int xPlace,int yPlace){
		if(xPlace < tilesWide && yPlace < tilesHigh && xPlace > -1 && yPlace > -1)
		return map[xPlace][yPlace];
		else return new Tile(0,0,0,0,TileType.NULL);
	}
	
	public boolean ItsOutOfBounds(int xPlace,int yPlace){
		if(xPlace < tilesWide && yPlace < tilesHigh && xPlace >= 0 && yPlace >= 0)
		return false;
		else return true;
	}
	
	public void Draw(){
		for (int i=0;i<map.length;i++){
			for(int j=0;j<map[i].length;j++){
				map[i][j].Draw();
			}
		}
		
	}

	public int getTilesWide() {
		return tilesWide;
	}

	public void setTilesWide(int tilesWide) {
		this.tilesWide = tilesWide;
	}

	public int getTilesHigh() {
		return tilesHigh;
	}

	public void setTilesHigh(int tilesHigh) {
		this.tilesHigh = tilesHigh;
	}
	
}
