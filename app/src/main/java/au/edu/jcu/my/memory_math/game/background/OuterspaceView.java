package au.edu.jcu.my.memory_math.game.background;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.View;

import java.util.List;
import java.util.Random;

import au.edu.jcu.my.memory_math.R;

public class OuterspaceView extends View {
    private final int numStars;
    private final Paint starPaint;
    private PointF[] starField;
    private List<Dice> dice;

    public OuterspaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setBackgroundColor(Color.BLACK);
        starPaint = new Paint();
        starPaint.setColor(Color.WHITE);

        // apply custom attributes
        Resources.Theme theme = context.getTheme();
        int[] styleable = R.styleable.OuterspaceView;
        TypedArray customAttrs = theme.obtainStyledAttributes(attrs, styleable, 0, 0);
        try {
            numStars = customAttrs.getInteger(R.styleable.OuterspaceView_numStars, 100);
        } finally {
            customAttrs.recycle();
        }
    }

    @Override
    protected void onSizeChanged(int newWidth, int newHeight,
                                 int oldWidth, int oldHeight) {
        super.onSizeChanged(newWidth, newHeight, oldWidth, oldHeight);

        // redefine the star field based on new View dimensions
        starField = new PointF[numStars];
        Random random = new Random();
        for (int i = 0; i < numStars; ++i) {
            float x = random.nextFloat() * newWidth;
            float y = random.nextFloat() * newHeight;
            starField[i] = new PointF(x, y);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Draw background elements
        drawStarField(canvas);

        // Draw foreground elements
//        drawAliens(canvas);
    }

    private void drawStarField(Canvas canvas) {
        Random random = new Random();
        for (PointF point : starField) {
            int size = 1 + random.nextInt(4); // size range: [1,3]
            canvas.drawCircle(point.x, point.y, size, starPaint);
        }
    }

//    private void drawAliens(Canvas canvas) {
//        assert dice != null;
//
//        for (Dice dice : dice) {
//            dice.draw(canvas);
//        }
//    }

    public void setDice(List<Dice> dice) {
        this.dice = dice;
    }
}

