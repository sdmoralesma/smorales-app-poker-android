package checho.poker;

import java.util.*;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class SpriteCartas extends Sprite {

	private int BMP_COLUMNS = 3;
	private int BMP_ROWS = 5;
	private int x1, x2, x3, x4, x5;
	private int y1, y2, y3, y4, y5;
	private int width, height;
	private Bitmap bmp2, bmp3, bmp4;
	private Bitmap[] imagenes = new Bitmap[5];
	int[] rows = new int[5];
	int[] cols = new int[5];

	public SpriteCartas(Bitmap bmp, Bitmap bmp2, Bitmap bmp3, Bitmap bmp4,
			int id) {
		super(bmp, id);
		this.bmp2 = bmp2;
		this.bmp3 = bmp3;
		this.bmp4 = bmp4;
		this.width = bmp.getWidth() / BMP_COLUMNS;
		this.height = bmp.getHeight() / BMP_ROWS;

		/*
		 * Selecciona aleatoriamente el color de la carta (Hearts, Clubs,
		 * Spades, Diamonds)
		 */
		Random r = new Random();
		int[] colores = new int[5];
		int[] cartas = new int[5];

		for (int i = 0; i < 5; i++) {
			/* Numero entre 1 (inclusivo) y 5 (exclusivo) */
			colores[i] = r.nextInt(5 - 1) + 1;
			/* Numero entre 1 (inclusivo) y 15 (exclusivo) */
			cartas[i] = r.nextInt(15 - 1) + 1;

			/*
			 * Selecciona entre los bmp de colores aleatoriamente y los guarda
			 * en el arreglo de bmp's imagenes[5]: Colores: 1=Hearts, 2=Clubs,
			 * 3=Diamonds, 4=Spades.
			 */
			switch (colores[i]) {
			case 1:
				imagenes[i] = bmp;
				break;
			case 2:
				imagenes[i] = this.bmp2;
				break;
			case 3:
				imagenes[i] = this.bmp3;
				break;
			case 4:
				imagenes[i] = this.bmp4;
				break;
			default:
				break;
			}

			switch (cartas[i]) {
			case 1:
				rows[i] = 0;
				cols[i] = 2;
				break;
			case 2:
				rows[i] = 1;
				cols[i] = 2;
				break;
			case 3:
				rows[i] = 2;
				cols[i] = 2;
				break;
			case 4:
				rows[i] = 3;
				cols[i] = 2;
				break;
			case 5:
				rows[i] = 4;
				cols[i] = 2;
				break;
			case 6:
				rows[i] = 0;
				cols[i] = 1;
				break;
			case 7:
				rows[i] = 1;
				cols[i] = 1;
				break;
			case 8:
				rows[i] = 2;
				cols[i] = 1;
				break;
			case 9:
				rows[i] = 3;
				cols[i] = 1;
				break;
			case 10:
				rows[i] = 4;
				cols[i] = 1;
				break;
			case 11:
				rows[i] = 0;
				cols[i] = 0;
				break;
			case 12:
				rows[i] = 1;
				cols[i] = 0;
				break;
			case 13:
				rows[i] = 2;
				cols[i] = 0;
				break;
			case 14:
				rows[i] = 3;
				cols[i] = 0;
				break;
			default:
				break;
			}

		}
	}

	
	public void printSprite(Canvas canvas) {
		x1 = x2 = x3 = x4 = x5 = 20;
		y1 = 0;
		y2 = 90;
		y3 = 180;
		y4 = 270;
		y5 = 360;

		int srcX1 = cols[0] * width;
		int srcY1 = rows[0] * height;

		int srcX2 = cols[1] * width;
		int srcY2 = rows[1] * height;

		int srcX3 = cols[2] * width;
		int srcY3 = rows[2] * height;

		int srcX4 = cols[3] * width;
		int srcY4 = rows[3] * height;

		int srcX5 = cols[4] * width;
		int srcY5 = rows[4] * height;

		Rect src1 = new Rect(srcX1, srcY1, srcX1 + width, srcY1 + height);
		Rect dst1 = new Rect(x1, y1, x1 + width, y1 + height);

		Rect src2 = new Rect(srcX2, srcY2, srcX2 + width, srcY2 + height);
		Rect dst2 = new Rect(x2, y2, x2 + width, y2 + height);

		Rect src3 = new Rect(srcX3, srcY3, srcX3 + width, srcY3 + height);
		Rect dst3 = new Rect(x3, y3, x3 + width, y3 + height);

		Rect src4 = new Rect(srcX4, srcY4, srcX4 + width, srcY4 + height);
		Rect dst4 = new Rect(x4, y4, x4 + width, y4 + height);

		Rect src5 = new Rect(srcX5, srcY5, srcX5 + width, srcY5 + height);
		Rect dst5 = new Rect(x5, y5, x5 + width, y5 + height);

		canvas.drawBitmap(imagenes[0], src1, dst1, null);
		canvas.drawBitmap(imagenes[1], src2, dst2, null);
		canvas.drawBitmap(imagenes[2], src3, dst3, null);
		canvas.drawBitmap(imagenes[3], src4, dst4, null);
		canvas.drawBitmap(imagenes[4], src5, dst5, null);

	}

	@Override
	public boolean isCollition(float xx, float yy) {
		return (xx > x1 && xx < x1 + width && yy > y1 && yy < y1 + height
				|| xx > x2 && xx < x2 + width && yy > y2 && yy < y2 + height
				|| xx > x3 && xx < x3 + width && yy > y3 && yy < y3 + height
				|| xx > x4 && xx < x4 + width && yy > y4 && yy < y4 + height || xx > x5
				&& xx < x5 + width && yy > y5 && yy < y5 + height);
	}

}
