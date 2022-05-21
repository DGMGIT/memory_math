package au.edu.jcu.my.memory_math.game.background;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;

import java.util.Random;

public class Dice {
    private int x;
    private int y;
    private final int boundingWidth;
    private final int boundingHeight;
    private int xDir;
    private int yDir;
    private final int speed;
    private final int color;
    private final Bitmap bitmap;

    Dice(Bitmap bitmap, int boundingWidth, int boundingHeight) {
        this.bitmap = bitmap;
        this.boundingWidth = boundingWidth;
        this.boundingHeight = boundingHeight;

        // randomly set a position, speed and direction
        Random random = new Random();
        x = random.nextInt(boundingWidth);
        y = random.nextInt(boundingHeight);
        speed = 1 + random.nextInt(21); // speed range: [1,20]
        xDir = random.nextBoolean() ? 1 : -1;
        yDir = random.nextBoolean() ? 1 : -1;

        // randomly select a color
        int red = random.nextInt(256);
        int green = random.nextInt(256);
        int blue = random.nextInt(256);
        color = Color.rgb(red, green, blue);
    }

    void draw(Canvas canvas) {
        canvas.save();

        float top = y - bitmap.getWidth() / 2.0f;
        float left = x - bitmap.getHeight() / 2.0f;

        Paint paint = new Paint();
        paint.setColorFilter(new PorterDuffColorFilter(color, PorterDuff.Mode.SRC_ATOP));
        canvas.drawBitmap(bitmap, left, top, paint);

        canvas.restore();
    }

    boolean move() {
        boolean bounced = false;
        x += xDir * speed;
        y += yDir * speed;

        if ((x < 1 && xDir < 0) || (x >= boundingWidth && xDir > 0)) {
            xDir *= -1;
            bounced = true;
        }

        if ((y < 1 && yDir < 0) || (y >= boundingHeight && yDir > 0)) {
            yDir *= -1;
            bounced = true;
        }

        return bounced;
    }
}
