package kaola.zhanchengguo.com.kaola.other.utils;

import android.graphics.Bitmap;

import com.squareup.picasso.Transformation;

/**
 * Created by Administrator on 2016/6/14.
 */
public class KaolaTranformation implements Transformation {

    @Override
    public Bitmap transform(Bitmap source) {

        int width = source .getWidth();
        int height = source .getHeight();

        Bitmap bitmap  = Bitmap.createBitmap(source , 0 ,0 , width , height );

        source.recycle();

        return bitmap;
    }

    @Override
    public String key() {
        return "KaolaTranformation";

    }
}
