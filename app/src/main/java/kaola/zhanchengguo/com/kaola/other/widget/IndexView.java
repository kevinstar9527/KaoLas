package kaola.zhanchengguo.com.kaola.other.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import kaola.zhanchengguo.com.kaola.R;

/**
 * ViewPager的索引
 * Created by Administrator on 2016/6/6.
 */
public class IndexView extends View {

    private int count =4;//圆的个数
    private float radius=10;//圆点半径
    private int defaluColor= Color.GRAY;//默认颜色
    private int selectedColor=Color.RED;//选中颜色
    private float circlePadding=20;//圆点的间距
    private float fromX,fromY;//第一个圆点最左侧的坐标
    private int currIndex=0;//当前选中的索引

    private Paint paint = new Paint();

    public IndexView(Context context) {
        super(context);
    }

    public IndexView(Context context, AttributeSet attrs) {
        super(context, attrs);
        
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.index_view);

        count = typedArray.getInteger(R.styleable.index_view_count , 4);
        radius = typedArray.getFloat(R.styleable.index_view_radius,10);
        defaluColor = typedArray.getColor(R.styleable.index_view_defaluColor,Color.GRAY);
        selectedColor =  typedArray.getColor(R.styleable.index_view_selectedColor,Color.RED);
        circlePadding = typedArray.getFloat(R.styleable.index_view_circlePadding,5);

        typedArray.recycle();

    }

    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);

        //计算起始点坐标
        fromX = (getWidth() - radius*2*count - circlePadding*(count-1))/2;
        fromY = getHeight() / 2;

        //抗锯齿
        paint.setAntiAlias(true);

        for(int i = 0; i < count ; i++)
        {
            if( i == currIndex)
            {
                paint.setColor(selectedColor);
                paint.setStyle(Paint.Style.FILL);
            }else{
                paint.setColor(defaluColor);
                paint.setStyle(Paint.Style.STROKE);
            }

            canvas.drawCircle(fromX + radius + (radius * 2 + circlePadding) * i,fromY,radius,paint);
        }

    }
    public void setCurrIndex(int index)
    {

        currIndex =index;

        //重新绘制一次
        invalidate();
    }
}
