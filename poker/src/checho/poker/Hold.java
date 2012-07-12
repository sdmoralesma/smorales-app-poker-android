package checho.poker;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Hold extends Sprite {

	public Hold(Bitmap bmp, int id) {
		super(bmp, id);
	}

	public void printSprite(Canvas canvas, int card) {

		switch (card) {
		case 1:
			x1 = 30;
			y1 = 0;
			break;
		case 2:
			x1 = 30;
			y1 = 90;
			break;
		case 3:
			x1 = 30;
			y1 = 180;
			break;
		case 4:
			x1 = 30;
			y1 = 270;
			break;
		case 5:
			x1 = 30;
			y1 = 360;
			break;
		default:
			break;
		}

		int srcX1 = col1 * width;
		int srcY1 = row1 * height;

		Rect src1 = new Rect(srcX1, srcY1, srcX1 + width, srcY1 + height);
		Rect dst1 = new Rect(x1, y1, x1 + width, y1 + height);

		canvas.drawBitmap(bmp, src1, dst1, null);
	}
}