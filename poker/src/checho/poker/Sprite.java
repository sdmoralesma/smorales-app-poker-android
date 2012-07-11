package checho.poker;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Sprite {
	
	protected int x1, y1;
	protected int col1, row1;
	protected int width, height;
	protected Bitmap bmp;
	protected int id;

	/*Constructor de Sprite, este Sprite consta de una única imagen estática (eg. carta de poker)*/
	public Sprite(Bitmap bmp, int id) {
		this.bmp = bmp;
		this.id = id;
		this.width = bmp.getWidth(); 
		this.height = bmp.getHeight();
		
		//Dado que el Sprite consta de solo una imagen (Columna=0, Fila=0)
		col1 = row1 = 0;
	}

	/*Método para Obtener el ID del Sprite*/
	public int getId() {
		return this.id;
	}
	
	/*Metodo impresión del Sprite*/
	public void printSprite(Canvas canvas) {
		/* 
		 * Estas sección indica la(s) región(es) en la cual se va a dibujar el
		 * Sprite, en este caso x1,y1 es el punto de referencia   
		 */  
		x1 = y1 = 0;

		int srcX1 = col1 * width; 
		int srcY1 = row1 * height;
		
		Rect src1 = new Rect(srcX1, srcY1, srcX1 + width, srcY1 + height);
		Rect dst1 = new Rect(x1, y1, x1 + width, y1 + height);

		canvas.drawBitmap(bmp, src1, dst1, null);
		
	}
	
	/*Metodo de Detección de Colisión con el Sprite*/
	public boolean isCollition(float xx, float yy) {
		return (xx > x1 && xx < x1 + width && yy > y1 && yy < y1 + height);
	}

}
