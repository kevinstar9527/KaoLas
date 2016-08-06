package kaola.zhanchengguo.com.kaola.other.ui;

import android.content.ContentResolver;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.VideoView;

import kaola.zhanchengguo.com.kaola.R;

/**
 * 引导页每一页的内容
 * Created by Administrator on 2016/6/6.
 */
public class GuideFragment extends Fragment {

    private int videoId;
    private int ivLeftId,ivRightId;
    private ImageView ivLeft,ivRight;
    private VideoView videoView;
    private int position;

    public GuideFragment(int videoId, int ivLeftId, int ivRightId,int position) {
        this.videoId = videoId;
        this.ivLeftId = ivLeftId;
        this.ivRightId = ivRightId;
        this.position = position;
    }

    public GuideFragment() {
    }

    public GuideFragment(Bundle bundle) {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_guide,null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        videoView = (VideoView) getView().findViewById(R.id.guide_vv);
        ivLeft = (ImageView) getView().findViewById(R.id.guide_left_iv);
        ivRight = (ImageView) getView().findViewById(R.id.guide_right_iv);

        //设置图片显示内容
        ivLeft.setImageResource(ivLeftId);
        ivRight.setImageResource(ivRightId);

        //表示当前页面是第一个页面，那么执行动画
        if(position == 0)
        {
            showAnim();
        }

        //拼接视频的绝对路径
        Uri uri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + getActivity().getPackageName() + "/" + videoId);
        //设置循环播放
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.start();
            }
        });
        //设置播放路径
        videoView.setVideoURI(uri);
        //开始播放
        videoView.start();
    }

    /**
     * 当页面隐藏时候调用该方法
     */
    public void hideImages()
    {
        ivLeft.setVisibility(View.GONE);
        ivRight.setVisibility(View.GONE);

    }

    /**
     * 页面进入时执行动画
     */
    public void showAnim() {

        ivRight.setVisibility(View.INVISIBLE);
        ivLeft.setVisibility(View.INVISIBLE);

        videoView.start();

        //加载动画
        final Animation animLeft = AnimationUtils.loadAnimation(getContext(), R.anim.guide_iv_left);
        Animation animRight = AnimationUtils.loadAnimation(getContext(), R.anim.guide_iv_right);

        animRight.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {


                ivLeft.startAnimation(animLeft);

                ivRight.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        animLeft.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                ivLeft.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        //执行动画
        //先执行右边动画
        ivRight.startAnimation(animRight);

    }

    @Override
    public void onPause() {
        super.onPause();

        videoView.pause();
    }
}


