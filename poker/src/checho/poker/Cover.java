package checho.poker;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Cover extends Sprite {
	private int x1, x2, x3, x4, x5;
	private int y1, y2, y3, y4, y5;

	public Cover(Bitmap bmp, int id) {
		super(bmp, id);
	}

	public void printSprite(Canvas canvas) {
		x1 = x2 = x3 = x4 = x5 = 20;
		y1 = 0;
		y2 = 90;
		y3 = 180;
		y4 = 270;
		y5 = 360;

		int srcX1 = col1 * width;
		int srcY1 = row1 * height;

		Rect src1 = new Rect(srcX1, srcY1, srcX1 + width, srcY1 + height);
		
		Rect dst1 = new Rect(x1, y1, x1 + width, y1 + height);
		Rect dst2 = new Rect(x2, y2, x2 + width, y2 + height);
		Rect dst3 = new Rect(x3, y3, x3 + width, y3 + height);
		Rect dst4 = new Rect(x4, y4, x4 + width, y4 + height);
		Rect dst5 = new Rect(x5, y5, x5 + width, y5 + height);

		canvas.drawBitmap(bmp, src1, dst1, null);
		canvas.drawBitmap(bmp, src1, dst2, null);
		canvas.drawBitmap(bmp, src1, dst3, null);
		canvas.drawBitmap(bmp, src1, dst4, null);
		canvas.drawBitmap(bmp, src1, dst5, null);
	}
}