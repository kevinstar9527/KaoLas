package kaola.zhanchengguo.com.kaola.other.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import java.util.List;

/**
 *
 * 纵向滚动的TextView
 * Created by Administrator on 2016/6/8.
 */
public class VerticalScrollTextView  extends View{

    private List<String> nlist;
    private List<String> dlist;

    /**
     * 每次向上移动的偏移量
     */
    private int speed = 5;

    /**
     * 每滑动一个偏移量的时间间隔
     */
    private int interval ;

    /**
     * 滚动时间
     */
    private int scrollTime = 1000;

    /**
     * 停顿的时间
     */
    private int delayTime = 3000;

    /**
     * 滑动停止后的字符的Y坐标
     */
    private int fromY;

    /**
     * 记录text的Y坐标
     */
    private int currY1,currY2;

    /**
     * 用来记录正在滑动的两个字符
     */
    private String text1,text2,text3,text4;

    /**
     * 字符颜色
     */
    private int textColor = Color.BLACK;

    Paint paint = new Paint();

    /**
     * 控件的高度
     */
    private  int height;

    private float textSize = 25;

    /**
     * 当前选中的是哪一个
     */
    private int currIndex = -1;

    //是否停止滑动，用来防止退出界面的时候，线程还在继续执行
    private boolean stop;


    public VerticalScrollTextView(Context context,List<String> nlist ,List<String> dlist) {
        super(context);
        this.nlist = nlist;
        this.dlist = dlist;

        paint.setColor(textColor);
        paint.setTextSize(textSize);

        start();


    }

    public VerticalScrollTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    /**
     * 获取当前选中的下标
     * @return
     */
    public int getCurrIndex()
    {
        if(currIndex == 0)
        {
            return 0;
        }
        return ( currIndex + 1 ) % dlist.size();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        if(text1 == null || text2 == null)
        {
            return;
        }

        //画第一个

        int Textwidth = getTextWidth(text3);

        RectF rect1 = new RectF(0 , 5 , Textwidth+6 , getHeight());
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        paint.setColor(Color.RED);
        canvas.drawRoundRect(rect1,10,10,paint);
        canvas.drawText(text3 , 3 , currY1 , paint);

        paint.setColor(textColor);
        canvas.drawText(text1 , 60 , currY1 , paint);

        //第二个
        paint.setColor(Color.RED);
        canvas.drawText(text4 , 3 , currY2 , paint);
        paint.setColor(textColor);
        canvas.drawText(text2 , 60 , currY2 , paint);

    }


    /**
     * 获取文字的高度
     *
     * @return
     */
    private int getTextHeight(String text)
    {
        Rect rect = new Rect();
        //获取文字的边界值
        paint.getTextBounds(text , 0 , text.length() , rect );
        //文字的高度等于(底减高)
        return rect.bottom - rect.top;
    }


    /**
     * 获取文字的宽度
     *
     * @return
     */
    private int getTextWidth(String text)
    {
        Rect rect = new Rect();
        //获取文字的边界值
        paint.getTextBounds(text , 0 , text.length() , rect );
        //文字的高度等于(底减高)
        return rect.right - rect.left;
    }



    private void start()
    {
        //在构造方法直接调用getHeight是0，所以必须要等测量结束后再获取
        post(new Runnable() {
            @Override
            public void run() {

                //获取控件的高度
                height = getHeight();

                //偏移的次数
                int count = height / speed;
                if(count == 0)
                {
                    count  = 1;
                }
                //我要在一秒钟内完成，计算每次偏移的时间间隔
                interval = scrollTime / count;

                prepareScroll();
            }
        });


    }

    /**
     * 准备滚动
     */
    private void prepareScroll()
    {
        currIndex ++;

        int index1 = currIndex % dlist.size();
        int index2 = (index1 + 1) % dlist.size();


        text1 = dlist.get(index1);
        text2 = dlist.get(index2);

        text3 = nlist.get(index1);
        text4 = nlist.get(index2);


        fromY = height - (height - getTextHeight(text1)) / 2 ;

        //重置Y坐标
        currY1 = fromY;

        currY2 = currY1 + height;

        scroll();
    }




    /**
     * 开始滚动
     */
    private void scroll()
    {
        if(stop)
        {
            return;
        }

        //LogUtil.w("scroll currY2 = " + currY2 );

        //重置Y坐标
        currY1 -=speed;
        currY2 = currY1 + height;

        //重绘一次
        invalidate();
        //是暂停还是继续滚动？
        if(needPause())
        {
            pause();
        }else
        {
            goOnScroll();
        }
    }

    /**
     * 继续滚动
     */
    private void goOnScroll()
    {
        //隔一个时间片在进行下一次滑动
        postDelayed(new Runnable() {
            @Override
            public void run() {
                scroll();
            }
        },interval);

    }

    /**
     * 停止滚动
     */
    private void pause()
    {
        //停留三秒，准备下一次滚动
        postDelayed(new Runnable() {
            @Override
            public void run() {
                prepareScroll();
            }
        },delayTime);

    }

    /**
     * 是否需要停止?
     *
     * @return
     */
    private boolean needPause()
    {

        return currY2 <= fromY;
    }


    /**
     * 在控件脱离窗口的时候会调用这个方法
     *
     */
    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stop = true;
    }
}
