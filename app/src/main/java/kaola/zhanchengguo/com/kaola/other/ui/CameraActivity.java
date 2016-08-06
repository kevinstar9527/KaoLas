package kaola.zhanchengguo.com.kaola.other.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import java.io.File;

import kaola.zhanchengguo.com.kaola.R;
import kaola.zhanchengguo.com.kaola.other.utils.FileUtil;

/**
 * Created by Administrator on 2016/6/18.
 */
public class CameraActivity extends AppCompatActivity {

    private ImageView imageView;

    private final int CODE_ALUMS = 3;

    private final int CODE_SOURE = 4;

    private File photo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_camera);

        imageView = (ImageView) findViewById(R.id.camera_photo_iv);

        photo = new File(FileUtil.DIR_IMAGE,"new.jpg");
    }

    /**
     * 获取缩略图
     * @param view
     */
    public void getAlbums(View view)
    {
        //指定打开照相机的操作意图
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        startActivityForResult(intent,CODE_ALUMS);

    }

    /**
     * 获取原图
     * @param view
     */
    public void getSource(View view )
    {
        //指定打开照相机的操作意图
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //指定保存路径
        Uri uri = Uri.fromFile(photo);
        //指定动作，拍完后保存到指定路径
        intent.putExtra(MediaStore.EXTRA_OUTPUT,uri);

        startActivityForResult(intent,CODE_SOURE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent result) {
        super.onActivityResult(requestCode, resultCode, result);

        if(requestCode == CODE_ALUMS)
        {
            Bitmap albums = result.getParcelableExtra("data");
            imageView.setImageBitmap(albums);
        }else if(requestCode == CODE_SOURE)
        {
            //对原图进行处理
            BitmapFactory.Options options = new BitmapFactory.Options();
            //设置图片的缩放比例，值越大，图越小，1表示为原始大小
            options.inSampleSize = 2;

            Bitmap source = BitmapFactory.decodeFile(photo.getAbsolutePath(),options);

            imageView.setImageBitmap(source);
        }
    }
}

