package checho.poker;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Background extends Sprite {

	public Background(Bitmap bmp, int id) {
		super(bmp, id);
	}

	public void printSprite(Canvas canvas) {
		int srcX1 = col1 * width;
		int srcY1 = row1 * height;

		Rect src1 = new Rect(srcX1, srcY1, srcX1 + width, srcY1 + height);
		Rect dst1 = new Rect(x1, y1, x1 + width, y1 + height);

		canvas.drawBitmap(bmp, src1, dst1, null);
	}

}