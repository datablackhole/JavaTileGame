package data;

public enum TileType {

	Free("free"),Wall("wall"),Doge("doge"),Start("start"),End("end"),NULL("2"),Enemy("enemy");
	
	String textureName;
	   
	  TileType(String textureName){
		  this.textureName=textureName;
	  }
}
