package kaola.zhanchengguo.com.kaola.other.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.io.File;
import java.util.List;

import kaola.zhanchengguo.com.kaola.other.utils.DeviceUtil;
import kaola.zhanchengguo.com.kaola.other.utils.FileUtil;
import kaola.zhanchengguo.com.kaola.other.utils.HttpUtil;
import kaola.zhanchengguo.com.kaola.other.utils.KaoLaTask;
import kaola.zhanchengguo.com.kaola.other.utils.LogUtil;

/**
 * Created by Administrator on 2016/6/12.
 */
public class TypeTopLayout extends View {

    /**
     * 最小格子的边长
     */
    private int size;

    /**
     * 用来存放七个方块的数据
     */
    private TypeRect[] typeRects;

    /**
     * 间隔线的宽度
     */
    private final int lineWidth = 5;

    private Paint paint = new Paint();

    /**
     * 用来保存点击的坐标
     */
    private float x, y;

    private IOnItemClickListener onItemClickListener;

    public TypeTopLayout(Context context)
    {
        super(context);
    }

    public TypeTopLayout(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    /**
     * 设置点击方块的监听
     *
     * @param itemClickListener
     */
    public void setOnItemClickListener(IOnItemClickListener itemClickListener)
    {
        this.onItemClickListener = itemClickListener;
    }

    /**
     * 设置图片的地址
     *
     * @param list
     */
    public void setImageUrlList(final List<String> list)
    {
        computeRects();

        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                for (int i = 0; i < list.size(); i++)
                {
                    final int index = i;
                    String url = list.get(i);
                    HttpUtil.downLoad(url, FileUtil.DIR_IMAGE,
                            FileUtil.getFileNameByHashCode(url),
                            new KaoLaTask.IDdownloadListener() {
                                @Override
                                public void upgradeProgress(float progress) {

                                }

                                @Override
                                public void Completed(File file) {

                                    // 从缓存目录解析源图片
                                    Bitmap bitmap = BitmapFactory
                                            .decodeFile(file.getAbsolutePath());

                                    // 根据源图片修改图片
                                    bitmap = transformation(bitmap, index);

                                    typeRects[index].bitmap = bitmap;

                                    // 重绘操作要放在主线程中执行
                                    post(new Runnable()
                                    {
                                        @Override
                                        public void run()
                                        {
                                            invalidate();
                                        }
                                    });
                                }

                                @Override
                                public void error(String msg) {

                                }

                                @Override
                                public void start() {

                                }
                            });
                }

            }
        }).start();
    }

    @Override
    protected void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
        typeRects = null;
    }

    /**
     *  如果该控件没有指定宽和高，那么调用invalidate()，不会执行onDraw()方法
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        if (typeRects == null)
        {
            return;
        }

        for (int i = 0; i < typeRects.length; i++)
        {
            Bitmap bitmap = typeRects[i].bitmap;
            TypeRect rect = typeRects[i];
            if (bitmap != null)
            {
                canvas.drawBitmap(bitmap, rect.fromX, rect.fromY, paint);
            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        // 根据屏幕的宽度计算小格子的边长
        int width = getResources().getDisplayMetrics().widthPixels
                - DeviceUtil.dip2px(getContext(), 40);

        size = (width - lineWidth * 3) / 4;
        int height = size * 3 + lineWidth * 2;

        // 设置该控件的宽高
        setMeasuredDimension(width, height);
    }

    /**
     * 触摸屏幕会调用这个方法
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event)
    {

        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                x = event.getX();
                y = event.getY();
                //按下的时候一定要return true,不然后面的move,up事件都不会执行
                return true;

            case MotionEvent.ACTION_UP:

                if (onItemClickListener != null)
                {
                    onItemClickListener.onItemClick(getClickPosition(x, y));
                }
                break;
        }
        return super.onTouchEvent(event);
    }

    /**
     * 获取点击的方块在整个视图中的索引
     *
     * @param x
     * @param y
     * @return
     */
    private int getClickPosition(float x, float y)
    {
        LogUtil.d("x = " + x + " , y = " + y);
        for (int i = 0; i < typeRects.length; i++)
        {
            TypeRect typeRect = typeRects[i];

            if (typeRect.include(x, y))
            {
                return i;
            }
        }

        return 0;
    }

    /**
     * 设置每个方块的大小和位置
     */
    private void computeRects()
    {
        LogUtil.d("width = " + getWidth() + " , size = " + size);

        typeRects = new TypeRect[]{
                new TypeRect(size * 0 + lineWidth * 0, size * 0 + lineWidth * 0,
                        size * 2 + lineWidth * 1, size * 2 + lineWidth * 1),

                new TypeRect(size * 2 + lineWidth * 2, size * 0 + lineWidth * 0,
                        size * 2 + lineWidth * 1, size * 1 + lineWidth * 0),

                new TypeRect(size * 2 + lineWidth * 2,
                        size * 1 + lineWidth * 1, size * 2 + lineWidth * 1,
                        size * 1 + lineWidth * 0),

                new TypeRect(size * 0 + lineWidth * 0,
                        size * 2 + lineWidth * 2, size * 1 + lineWidth * 0,
                        size * 1 + lineWidth * 0),

                new TypeRect(size * 1 + lineWidth * 1,
                        size * 2 + lineWidth * 2, size * 1 + lineWidth * 0,
                        size * 1 + lineWidth * 0),

                new TypeRect(size * 2 + lineWidth * 2,
                        size * 2 + lineWidth * 2, size * 1 + lineWidth * 0,
                        size * 1 + lineWidth * 0),

                new TypeRect(size * 3 + lineWidth * 3,
                        size * 2 + lineWidth * 2, size * 1 + lineWidth * 0,
                        size * 1 + lineWidth * 0)};
    }

    /**
     * 图片处理
     *
     * @param src
     * @param i
     * @return
     */
    private Bitmap transformation(Bitmap src, int i)
    {
        int sWidth = src.getWidth();
        int sHeight = src.getHeight();

        TypeRect rect = typeRects[i];
        Bitmap target = null;
        Matrix matrix = new Matrix();
        float sx, sy = 1.0f;
        switch (i)
        {
            case 0:
            case 3:
            case 4:
            case 5:
            case 6:
                sx = 1.0f * rect.width / sWidth;//指定缩放后的宽度/原始宽度=横向缩放比例
                sy = 1.0f * rect.height / sHeight;
                matrix.setScale(sx, sy);//设置横纵座标的缩放比例
                target = Bitmap.createBitmap(src, 0, 0, sWidth, sHeight,
                        matrix, true);
                break;

            case 1:
            case 2:
                //获取剪裁纵向一半高度最中间的图片
                Bitmap bitmap = Bitmap.createBitmap(src, 0, sHeight / 4,
                        sWidth, sHeight / 2);
                int tWidth = bitmap.getWidth();
                int tHeight = bitmap.getHeight();

                sx = 1.0f * rect.width / tWidth;//指定缩放后的宽度/原始宽度=横向缩放比例
                sy = 1.0f * rect.height / tHeight;
                matrix.setScale(sx, sy);//设置横纵座标的缩放比例
                target = Bitmap.createBitmap(bitmap, 0, 0, tWidth, tHeight,
                        matrix, true);
                break;

        }

        return target;
    }

    /**
     * 每一个方块对象包含：要显示的图片，显示位置，宽高
     */
    private class TypeRect
    {
        Bitmap bitmap;

        int fromX;

        int fromY;

        int width;

        int height;

        Rect rect;

        public TypeRect(int fromX, int fromY, int width, int height)
        {
            this.fromX = fromX;
            this.fromY = fromY;
            this.width = width;
            this.height = height;

            rect = new Rect(fromX, fromY, width, height);
            LogUtil.d(toString());
        }

        /**
         * 判断该方块是否包含某一点
         *
         * @param x
         * @param y
         * @return
         */
        boolean include(float x, float y)
        {
            return x > fromX && x < fromX + width && y > fromY && y < fromY + height;
        }

        @Override
        public String toString()
        {
            return "TypeRect{" + "fromX=" + fromX + ", fromY=" + fromY
                    + ", width=" + width + ", height=" + height + '}';
        }
    }

    /**
     * 点击方块的监听
     */
    public interface IOnItemClickListener
    {
        /**
         * 回调方法
         *
         * @param position
         *            所在整个大方块里的索引
         */
        void onItemClick(int position);
    }
}
