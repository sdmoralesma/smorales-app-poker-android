package checho.poker;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Buttons extends Sprite{
	private static final int BMP_COLUMNS = 2;
	private static final int BMP_ROWS = 1;
	private int x2, y2;	
	private int col2, row2;
	
	public Buttons(Bitmap bmp, int id) {
		super(bmp, id);
		this.width = bmp.getWidth() / BMP_COLUMNS;
		this.height = bmp.getHeight() / BMP_ROWS;

		row1 = row2 = 0;
		col1 = 0;
		col2 = 1;
	}	
	
	@Override
	public void printSprite(Canvas canvas) {
		x1 = 230;
		x2 = 280;
		y1 = 350;
		y2 = 350;

		int srcX1 = col1 * width; 
		int srcY1 = row1 * height;

		int srcX2 = col2 * width; 
		int srcY2 = row2 * height;

		Rect src1 = new Rect(srcX1, srcY1, srcX1 + width, srcY1 + height);
		Rect dst1 = new Rect(x1, y1, x1 + width, y1 + height);

		Rect src2 = new Rect(srcX2, srcY2, srcX2 + width, srcY2 + height);
		Rect dst2 = new Rect(x2, y2, x2 + width, y2 + height);

		canvas.drawBitmap(bmp, src1, dst1, null);
		canvas.drawBitmap(bmp, src2, dst2, null);
	}

	public boolean playCollition(float xx, float yy) {
		return (xx > x2 && xx < x2 + width && yy > y2 && yy < y2 + height);
	}

	public boolean betCollition(float xx, float yy) {
		return (xx > x1 && xx < x1 + width && yy > y1 && yy < y1 + height);
	}
}