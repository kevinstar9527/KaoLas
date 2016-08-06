package kaola.zhanchengguo.com.kaola.other.widget;

import android.content.Context;
import android.support.v4.widget.SlidingPaneLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Administrator on 2016/6/17.
 */
public class KaolaSlidePaneLayout extends SlidingPaneLayout {

    private IIntercept intercept;

    public KaolaSlidePaneLayout(Context context) {
        super(context);
    }

    public KaolaSlidePaneLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        if(intercept == null)
        {
            return super.onInterceptTouchEvent(ev);
        }



        if(intercept.needIntercept())
        {
            return super.onInterceptTouchEvent(ev);
        }else
        {
            return false;
        }

    }

    public void setIIntercept(IIntercept iIntercept)
    {
        intercept = iIntercept;
    }



    public  interface IIntercept
    {
        boolean needIntercept();
    }
}
