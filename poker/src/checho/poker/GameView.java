package checho.poker;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView {
	private GameLoopThread gameLoopThread;
	private SpriteCartas sprite;
	private Bitmap bmp2, bmp3, bg, holdimg;
	private Bitmap bmpa, bmpb, bmpc, bmpd;
	private Cover cover;
	private Buttons buttons;
	private Hold hold;
	private Background background;
	private long lastClick;
	private int flag1;

	/* Sound variables */
	private SoundPool sounds;
	private int sbet, splay;

	private static MediaPlayer music;

	public GameView(Context context) {
		super(context);
		sounds = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
		sbet = sounds.load(context, R.raw.betsound, 1);
		splay = sounds.load(context, R.raw.playsound, 1);

		/* Game Music */
		music = MediaPlayer.create(context, R.raw.manhattan);

		gameLoopThread = new GameLoopThread(this);
		getHolder().addCallback(new SurfaceHolder.Callback() {

			public void surfaceDestroyed(SurfaceHolder holder) {

				/* Cut and Release Sounds */
				sounds.release();
				music.stop();
				music.release();

				boolean retry = true;
				gameLoopThread.setRunning(false);
				while (retry) {
					try {
						gameLoopThread.join();
						retry = false;
					} catch (InterruptedException e) {
					}
				}
			}

			public void surfaceCreated(SurfaceHolder holder) {
				gameLoopThread.setRunning(true);
				gameLoopThread.start();

			}

			public void surfaceChanged(SurfaceHolder holder, int format,
					int width, int height) {

			}
		});

		/* Background Image */
		bg = BitmapFactory.decodeResource(getResources(), R.drawable.bg_image);
		background = new Background(bg, 101);

		/* Buttons Play/Bet Image */
		bmp3 = BitmapFactory.decodeResource(getResources(), R.drawable.buttons);
		buttons = new Buttons(bmp3, 102);

		/* Covers Image */
		bmp2 = BitmapFactory.decodeResource(getResources(), R.drawable.cover);
		cover = new Cover(bmp2, 103);

		/* Hold Image */
		holdimg = BitmapFactory.decodeResource(getResources(), R.drawable.hold);
		hold = new Hold(holdimg, 104);

		/* Cards Image */
		bmpa = BitmapFactory.decodeResource(getResources(), R.drawable.spades);
		bmpb = BitmapFactory.decodeResource(getResources(), R.drawable.hearts);
		bmpc = BitmapFactory.decodeResource(getResources(), R.drawable.clubs);
		bmpd = BitmapFactory
				.decodeResource(getResources(), R.drawable.diamonds);
		sprite = new SpriteCartas(bmpa, bmpb, bmpc, bmpd, 100);

	}

	// Imprime el score de cada jugada y actualiza el valor de la apuesta
	// escogida
	public void printscore(Paint paint, Canvas canvas) {
		canvas.save();
		/* Rotación y Visualización de Texto */
		paint.setTextSize(18);
		paint.setColor(Color.YELLOW);
		canvas.rotate(90, 140, 100);
		paint.setStyle(Paint.Style.FILL);

		/* Descripción de Recompensas */
		canvas.drawText("Royal Flush", 50, -50, paint);
		canvas.drawText("Straight Flush", 50, -30, paint);
		canvas.drawText("4 of a Kind", 50, -10, paint);
		canvas.drawText("Full House", 50, 10, paint);
		canvas.drawText("Flush", 50, 30, paint);
		canvas.drawText("Straight", 50, 50, paint);
		canvas.drawText("3 of a Kind", 50, 70, paint);
		canvas.drawText("2 Pair", 50, 90, paint);
		canvas.drawText("Tens or Better", 50, 110, paint);
		canvas.restore();
	}

	@Override
	protected void onDraw(Canvas canvas) {

		// Runs Game Music
		if (!music.isPlaying()) {
			music.seekTo(0);
			music.start();
		}

		/* Generar Texto en Android */
		Paint paint = new Paint();
		paint.setColor(Color.BLUE);
		paint.setStyle(Style.FILL);
		canvas.drawPaint(paint);

		background.printSprite(canvas);
		buttons.printSprite(canvas);

		switch (flag1) {
		case 0:
			cover.printSprite(canvas);
			break;
		case 1:
			sprite.printSprite(canvas);
			break;
		case 2:
			sprite.printSprite(canvas);
			hold.printSprite(canvas);
			break;
		default:
			break;
		}

		printscore(paint, canvas);

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		if (System.currentTimeMillis() - lastClick > 300) {
			lastClick = System.currentTimeMillis();
			float x = event.getX();
			float y = event.getY();

			if (buttons.betCollition(x, y)) {
				sounds.play(sbet, 0.9f, 0.9f, 0, 0, 1f);
				flag1 = 0;
			}

			if (buttons.playCollition(x, y)) {
				sounds.play(splay, 0.9f, 0.9f, 0, 0, 1f);
				flag1 = 1;
			}

			synchronized (getHolder()) {
				if (sprite.isCollition(x, y)) {
					sounds.play(sbet, 0.7f, 0.7f, 0, 0, 1f);
					flag1 = 2;
				}
			}
		}
		return true;
	}

}
