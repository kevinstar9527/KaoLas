package kaola.zhanchengguo.com.kaola.other.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2016/6/15.
 */
public class OverlapRoundRect extends View {

    private Paint paint = new Paint();


    public OverlapRoundRect(Context context) {
        super(context);
    }

    public OverlapRoundRect(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint.setColor(Color.BLACK);
        paint.setAntiAlias(true);


    }
}
