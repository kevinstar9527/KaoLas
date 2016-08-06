package kaola.zhanchengguo.com.kaola.other.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import kaola.zhanchengguo.com.kaola.R;
import kaola.zhanchengguo.com.kaola.other.utils.LogUtil;

/**
 * Created by Administrator on 2016/6/21.
 */
public class LineView extends View {

    private int count = 6; //线段的个数
    private int defaluColor= Color.GRAY;//默认颜色
    private int selectedColor=Color.RED;//选中颜色

    private  int length;//长度

    private int currIndex=0;//当前选中的索引

    private Paint paint = new Paint();

    public LineView(Context context) {
        super(context);
    }

    public LineView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.line_view);
        count = typedArray.getInteger(R.styleable.line_view_linecount,6);
        defaluColor = typedArray.getColor(R.styleable.line_view_linedefaluColor,Color.GRAY);
        selectedColor = typedArray.getColor(R.styleable.line_view_lineselectedColor,Color.RED);

        typedArray.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int length = getWidth() / count;
        //抗锯齿
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);

        LogUtil.w("width = " + getWidth() + " length = " + length);

        LogUtil.w(" currIndex = " + currIndex);

        for(int i = 0; i < count ; i++)
        {
            if (i == currIndex)
            {
                paint.setColor(selectedColor);
            } else
            {
                paint.setColor(defaluColor);
            }
            LogUtil.w(" i = " + i);
            canvas.drawRect( length*i, 0 ,length*(i+1),8,paint);
            LogUtil.w("x = " + length*i + "y = " + length*i + "toX = " + length*(i+1));
        }
    }

    public void setCurrIndex(int index)
    {
        currIndex = index % count;

        //重新绘制一次
        invalidate();
    }
}
