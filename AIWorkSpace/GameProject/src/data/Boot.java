package data;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.opengl.Texture;

import helpers.Clock;

import static org.lwjgl.opengl.GL11.*;

import static helpers.Artist.*;

public class Boot {

	public Boot() {
		BeginSession();
		
		/*
		 * int[][] map = new int[10][10];
		 * 
		 * int a[]={0,1};
		 * 
		 * for(int i=0;i<10;i++){ for (int j=0;j <10;j++){
		 * map[i][j]=(int)(a.length*Math.random()%2);
		 * 
		 * map[j][3]=0; map[3][j]=0;
		 * 
		 * 
		 * map[j][5]=0; map[5][j]=0; map[j][9]=0; map[9][j]=0;
		 * 
		 * 
		 * } map[i][0]=1; map[0][i]=1;
		 * 
		 * map[i][9]=1; map[9][i]=1;
		 * 
		 * map[0][9]=2; map[9][0]=3;
		 * 
		 * }	
		 */
		 

		int[][] map = { 
				        { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }, 
				        { 1, 3, 0, 0, 0, 0, 1, 0, 0, 0 },
				        { 1, 1, 0, 1, 1, 0, 1, 0, 1, 0 }, 
				        { 1, 1, 0, 0, 0, 0, 0, 0, 1, 0 }, 
				        { 1, 1, 1, 1, 1, 4, 1, 1, 0, 0 },
				        { 1, 0, 0, 0, 0, 0, 0, 0, 1, 0 }, 
				        { 1, 0, 1, 0, 1, 0, 1, 0, 1, 0 }, 
				        { 1, 0, 0, 1, 0, 0, 1, 0, 1, 0 },
				        { 1, 1, 0, 1, 1, 0, 1, 0, 1, 0 }, 
				        { 1, 2, 0, 0, 0, 0, 1, 0, 0, 0 }, 
				      };

		TileGrid grid = new TileGrid(map);

		Doge doge = new Doge(QuickLoad("doge"), grid.GetTile(1, 9), grid, 64, 64, 150);

		Enemy[] enemy = new Enemy[6];

		enemy[0] = new Enemy(QuickLoad("enemy"), grid.GetTile(2, 1), grid, 64, 64, 20, 0);
		enemy[1] = new Enemy(QuickLoad("enemy"), grid.GetTile(9, 9), grid, 64, 64, 22, 0);
		enemy[2] = new Enemy(QuickLoad("enemy"), grid.GetTile(5, 5), grid, 64, 64, 21, 0);
		enemy[3] = new Enemy(QuickLoad("enemy"), grid.GetTile(2, 9), grid, 64, 64, 24, 0);
		enemy[4] = new Enemy(QuickLoad("enemy"), grid.GetTile(2, 7), grid, 64, 64, 23, 0);
		enemy[5] = new Enemy(QuickLoad("enemy"), grid.GetTile(7, 7), grid, 64, 64, 25, 0);

		Player player = new Player(grid);

		while (!Display.isCloseRequested())// while window is not closed
		{


													if ((doge.getXPlace() == 5 && doge.getYPlace()== 4)) {

																					grid = new TileGrid(map);
																	
																					doge = new Doge(QuickLoad("doge"), grid.GetTile(1, 9), grid, 64, 64, 150);
																					enemy[0] = new Enemy(QuickLoad("enemy"), grid.GetTile(2, 1), grid, 64, 64, 20, 0);
																					enemy[1] = new Enemy(QuickLoad("enemy"), grid.GetTile(9, 9), grid, 64, 64, 22, 0);
																					enemy[2] = new Enemy(QuickLoad("enemy"), grid.GetTile(5, 5), grid, 64, 64, 21, 0);
																					enemy[3] = new Enemy(QuickLoad("enemy"), grid.GetTile(2, 9), grid, 64, 64, 24, 0);
																					enemy[4] = new Enemy(QuickLoad("enemy"), grid.GetTile(2, 7), grid, 64, 64, 23, 0);
																					enemy[5] = new Enemy(QuickLoad("enemy"), grid.GetTile(7, 7), grid, 64, 64, 25, 0);
																												}
			
			Clock.update();
			grid.Draw();
				DrawQuadTex(QuickLoad("7"), 8 * 64, 4 * 64, 64, 64);
				DrawQuadTex(QuickLoad("7"), 3 * 64, 6 * 64, 64, 64);
				DrawQuadTex(QuickLoad("7"), 4 * 64, 7 * 64, 64, 64);

			doge.Draw();

			doge.Update(player.Update());
		
			DrawQuadTex(QuickLoad("red"), 5 * 64, 4 * 64, 64, 64);
			
            if (doge.getXPlace() == 1 && doge.getYPlace() == 1) {
				DrawQuadTex(QuickLoad("win"), 640, 0, 400, 1500);
			
			} else{
				DrawQuadTex(QuickLoad("nope"), 640, 0, 400, 1500);
				
				for(int i=0;i<6;i++){
					enemy[i].Update();
					enemy[i].Draw();
				}		
			}

		
			
		  for (int i = 0; i <= 5; i++) {
							
				if (doge.getXPlace() == enemy[i].getXPlace() && doge.getYPlace() == enemy[i].getYPlace()) {
					grid.SetTile(doge.getXPlace(), doge.getYPlace(), TileType.Free);
					grid = new TileGrid(map);
					doge = new Doge(QuickLoad("doge"), grid.GetTile(1, 9), grid, 64, 64, 150);
					enemy[0] = new Enemy(QuickLoad("enemy"), grid.GetTile(2, 1), grid, 64, 64, 20, 0);
					enemy[1] = new Enemy(QuickLoad("enemy"), grid.GetTile(9, 9), grid, 64, 64, 22, 0);
					enemy[2] = new Enemy(QuickLoad("enemy"), grid.GetTile(5, 5), grid, 64, 64, 21, 0);
					enemy[3] = new Enemy(QuickLoad("enemy"), grid.GetTile(2, 9), grid, 64, 64, 24, 0);
					enemy[4] = new Enemy(QuickLoad("enemy"), grid.GetTile(2, 7), grid, 64, 64, 23, 0);
					enemy[5] = new Enemy(QuickLoad("enemy"), grid.GetTile(7, 7), grid, 64, 64, 25, 0);
				}
				if (!enemy[i].isAlive()) {
					int x = enemy[i].getXPlace();
					int y = enemy[i].getYPlace();
					enemy[i] = new Enemy(QuickLoad("enemy"), grid.GetTile(x, y), grid, 64, 64, 25, 0);
				}


			}


			// grid.SetTile(enemy.getXPlace()+1, enemy.getYPlace(),
			// TileType.Free);
			//DrawQuadTex(QuickLoad("1"), doge.getXPlace() * 64, doge.getYPlace() * 64, 64, 64);
			//
			// grid.SetTile(enemy.getXPlace()+1, enemy.getYPlace(),
			// TileType.Free);
			// DrawQuadTex(QuickLoad("1"),enemy.getXPlace()*64,enemy.getYPlace()*64,64,64);

			// grid.SetTile(enemy0.getXPlace(), enemy0.getYPlace(),
			// TileType.Free);
			// DrawQuadTex(QuickLoad("1"),enemy0.getXPlace()*64,enemy0.getYPlace()*64,64,64);

			

			// enemy0.Draw();
			player.Update();
			grid.SetTile(1, 1, TileType.End);
			//grid.Draw();
		//	DrawQuadTex(QuickLoad("7"), 5 * 64, 4 * 64, 64, 64);

			Display.update();
			Display.sync(60);// sync the loop with the display 60 times per
								// second.
		}

		Display.destroy(); // end game
	}

	public static void main(String[] args) {
		new Boot();
	}
}
