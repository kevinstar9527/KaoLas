package kaola.zhanchengguo.com.kaola.other.ui;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import kaola.zhanchengguo.com.kaola.R;
import kaola.zhanchengguo.com.kaola.other.utils.ShakeSensorListener;

import static kaola.zhanchengguo.com.kaola.R.raw.shake_match;

/**
 * Created by Administrator on 2016/6/18.
 */
public class ShakeActivity extends AppCompatActivity {

    private ImageView imageView;

    private  ShakeSensorListener shakeListener;

    private AnimationDrawable animationDrawable;

    private Handler handler = new Handler();

    //播放器
    private MediaPlayer mPlayer = null;

    //震动服务
    private Vibrator vibrator;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_shake);

        imageView = (ImageView) findViewById(R.id.shake_content_iv);

        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        animationDrawable = (AnimationDrawable) getResources().getDrawable(R.drawable.anim_shake);

        shakeListener = new ShakeSensorListener(this);

        shakeListener.setIShakeListener(new ShakeSensorListener.IShakeListener() {
            @Override
            public void OnShake() {
                //摇动的时候

                //1.让树摇起来
                imageView.setImageDrawable(animationDrawable);
                animationDrawable.start();



                //2.播放声音或者震动

                //有节奏的震动
                long[] patter = new long[]{200,500,300,600,200,700};
                vibrator.vibrate(patter,0);

                //播放声音
                mPlayer = MediaPlayer.create(getApplicationContext(),R.raw.shake_sound_male);
                mPlayer.setLooping(true);
                mPlayer.start();

                //3.执行网络请求
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        animationDrawable.stop();
                        imageView.setImageResource(R.drawable.a9);
                        //停止震动
                        vibrator.cancel();
                        //停止播放
                        mPlayer.stop();
                    }
                },5000);
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        shakeListener.unregister();
    }
}
