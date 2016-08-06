package kaola.zhanchengguo.com.kaola.other.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.EditText;

import kaola.zhanchengguo.com.kaola.R;

/**
 * Created by Administrator on 2016/6/20.
 */
public class MyEditText extends EditText {

    private Paint mpaint;
    private int lineColor = Color.WHITE;

    public MyEditText(Context context) {
        super(context);


    }
    public MyEditText(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray attrArrays = context.obtainStyledAttributes(attrs, R.styleable.LineEditeText);

        mpaint = new Paint();
        int lenght = attrArrays.getIndexCount();
        for (int i = 0; i < lenght; i++) {
            int index = attrArrays.getIndex(i);
            switch (index) {
                case R.styleable.LineEditeText_lineColorEt:
                    lineColor = attrArrays.getColor(index, 0xFFF);
                    break;
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mpaint.setStyle(Paint.Style.STROKE);
        mpaint.setColor(lineColor);
        canvas.drawLine(0, getHeight() - 1, getWidth() - 1, getHeight() - 1, mpaint);
    }
}
