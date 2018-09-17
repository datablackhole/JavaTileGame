package helpers;

import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;
import static org.lwjgl.opengl.GL11.glVertex2f;

import java.io.IOException;
import java.io.InputStream;

import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.glBlendFunc;

import static org.lwjgl.opengl.GL11.glTexCoord2f;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class Artist {
	
public static final int WIDTH= 854,HEIGHT=640;

public static void BeginSession(){
	
   Display.setTitle("DogeGame"); //Title of Window
   
try {
	Display.setDisplayMode(new DisplayMode(854,640)); //Game window dimensions
	Display.create(); 
} catch (LWJGLException e) {

	
	e.printStackTrace();
}

    //To do before we run the game from LWJGL(Easier version to talk to OpenGL instead of standard OpenGL Library)
     glMatrixMode(GL_PROJECTION);//
     glLoadIdentity();
     
     glOrtho(0,WIDTH,HEIGHT,0,1,-1);//This Sets up our camera for 2D View
     glMatrixMode(GL_MODELVIEW);
     
     //Enables to use textures and bind them to coordinates
     glEnable(GL_TEXTURE_2D);
     
   
     glEnable(GL_BLEND);  //This allows images to blend.
    glBlendFunc(GL_SRC_ALPHA,GL_ONE_MINUS_SRC_ALPHA); //Transperancy
    
   }

  public static void DrawQuad(float x,float y,float width,float height){
		glBegin(GL_QUADS); //To draw in 2d
		glVertex2f(x,y);// top left corner |  '2f' means 2 floats to pass.
		glVertex2f(x+width,y);//top right corner
		glVertex2f(x+width,y+height);//bottom right corner
		glVertex2f(x,y+ height);//bottom left corner
		
		glEnd();  
  }
  public static void DrawQuadTex(Texture tex,float x,float y,float width,float height){
	  tex.bind(); //This binds the texture to the drawing above
	  glTranslatef(x,y,0); // no z because since 2d.This translates global coordinates to use local coordinates  
	  
	  glBegin(GL_QUADS);
	  
	  glTexCoord2f(0,0); //top left corner of texture since we translated it two statements back.
	  glVertex2f(0,0);
	  
	  glTexCoord2f(1,0);
	  glVertex2f(width,0);
	  
	  glTexCoord2f(1,1);
	  glVertex2f(width,height);
	  
	  glTexCoord2f(0,1);
	  glVertex2f(0,height);
	  
	 glEnd();
	  
      glLoadIdentity(); //To prevent screen tearing

  }
  
  public static Texture LoadTexture(String path, String fileType){
	  Texture tex=null;
	  InputStream in = ResourceLoader.getResourceAsStream(path);
	  try {
		tex = TextureLoader.getTexture(fileType, in);
	} catch (IOException e) {
		e.printStackTrace();
	}
	return tex;
	  
  }
  
  public static Texture QuickLoad(String name){
	  
	  Texture tex=null;
	  
	  tex= LoadTexture("res/"+ name +".PNG","PNG");
	  
	  return tex;
  }
}