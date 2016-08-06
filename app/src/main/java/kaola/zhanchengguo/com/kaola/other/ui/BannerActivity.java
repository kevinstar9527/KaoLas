package kaola.zhanchengguo.com.kaola.other.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

import kaola.zhanchengguo.com.kaola.R;
import kaola.zhanchengguo.com.kaola.other.utils.FileUtil;
import kaola.zhanchengguo.com.kaola.other.utils.HttpUtil;
import kaola.zhanchengguo.com.kaola.other.utils.KaoLaTask;
import kaola.zhanchengguo.com.kaola.other.utils.KaolaShapeTransform;
import kaola.zhanchengguo.com.kaola.other.utils.LogUtil;
import kaola.zhanchengguo.com.kaola.other.utils.OtherHttpUtil;

/**
 * 进入对应的广告页
 * Created by Administrator on 2016/6/6.
 */
public class BannerActivity extends AppCompatActivity{

    private ImageView imageView;
    private View btn_jump;

    private Timer timer;

    private TimerTask task;

    private Intent intent = null;

    private  int time = 3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_banner);

        imageView = (ImageView) findViewById(R.id.banner_content_iv);
        btn_jump =  (ImageButton)findViewById(R.id.banner_bt_jump);

        //跳转页面
        intent = new Intent(BannerActivity.this,HomeActivity.class);

        timer = new Timer();

        btn_jump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
                finish();
            }
        });

        task = new TimerTask() {
            @Override
            public void run() {

                startActivity(intent);
                finish();

            }
        };


        //3秒后开始执行
        timer.schedule(task,10000);


        OtherHttpUtil.getBanner(new KaoLaTask.IRequestCallback() {
            @Override
            public void success(Object object) {
                JSONObject root = null;

                try {
                    root = new JSONObject(object.toString());

                    String message = root.getString("message");

                    if ("success".equals(message)) {

                        JSONObject result = root.getJSONObject("result");
                        String img = result.getString("img");
                        LogUtil.w("img = " + img);

                        //showImage(img);

                        KaolaShapeTransform.Shape shape = KaolaShapeTransform.Shape.Circle;


                        Picasso.with(BannerActivity.this)
                                .load(img)  //url
                                .error(R.drawable.no_net_error_icon) //加载失败时显示的图片
                                .placeholder(R.drawable.album_hidden) //正在加载中显示的图片  .resize(300,200) //把图片缩放成多大？
                                .transform(new KaolaShapeTransform(shape))
                                .into(imageView); //显示到哪个控件中去

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void error(String msg) {

            }
        });

    }

    private void showImage(final String img) {

        final String rename = FileUtil.getFileNameByHashCode(img);

        File image = new File(FileUtil.DIR_IMAGE,rename);

        //判断这个图片有没有下载过，如果存在的话，表示下载过
        if(!image.exists())
        {
            new KaoLaTask(new KaoLaTask.IDownLoadRequest() {
                @Override
                public Object doRequest(KaoLaTask.IDdownloadListener listener) {

                    return HttpUtil.downLoad(img, FileUtil.DIR_IMAGE, rename, listener);
                }
            }, new KaoLaTask.IDdownloadListener() {
                @Override
                public void upgradeProgress(float progress) {

                }

                @Override
                public void Completed(File file) {

                    //解析图片
                    final Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                    //显示图片
                    imageView.setImageBitmap(bitmap);
                }

                @Override
                public void error(String msg) {
                    Toast.makeText(BannerActivity.this, msg, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void start() {

                }
            }).execute();






            /*KaoLaTask.IRequest request = new KaoLaTask.IRequest() {
                @Override
                public Object doRequest() {
                    //下载图片
                    return HttpUtil.downLoad(img, FileUtil.DIR_IMAGE , rename);
                }
            };

            KaoLaTask.IRequestCallback callback = new KaoLaTask.IRequestCallback() {
                @Override
                public void success(Object object) {

                    File file = (File) object;
                    //解析图片
                    final Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                    //显示图片
                    imageView.setImageBitmap(bitmap);
                }

                @Override
                public void error(String msg) {

                }
            };

            KaoLaTask task = new KaoLaTask(request,callback);
            task.execute();*/

        }else
        {
            //解析图片
            final Bitmap bitmap = BitmapFactory.decodeFile(image.getAbsolutePath());
            //显示图片
            imageView.setImageBitmap(bitmap);
        }

    }

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //结束线程
        timer.cancel();
        task.cancel();
    }
}
