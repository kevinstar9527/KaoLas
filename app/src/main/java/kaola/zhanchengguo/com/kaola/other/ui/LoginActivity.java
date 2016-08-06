package kaola.zhanchengguo.com.kaola.other.ui;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.tencent.connect.UserInfo;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.PlatformDb;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;
import kaola.zhanchengguo.com.kaola.R;
import kaola.zhanchengguo.com.kaola.other.utils.LogUtil;

/**
 * Created by Administrator on 2016/6/18.
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView ivcandcle,ivQQ;

    private static final String APP_ID = "1105482962";

    private static final int RESULT_ID = 3;

    private static final int RESULT_CANCLE= 5;

    private String  backString;

    private Tencent tencent;


    /**
     * 回调接口
     */
    private IUiListener uiListener = new IUiListener() {
        @Override
        public void onComplete(Object o) {

            LogUtil.e("onComplete  >>"+o.toString());

            try {

                JSONObject root = new JSONObject(o.toString());

                String openid = root.getString("openid");//你在那台机子上登录的

                String expires_in = root.getString("expires_in");//登录有效期

                String access_token = root.getString("access_token");//登录的token

                tencent.setAccessToken(access_token,expires_in);
                tencent.setOpenId(openid);//重新设置设备的id

                final UserInfo userInfo = new UserInfo(LoginActivity.this,tencent.getQQToken());

                userInfo.getUserInfo(new IUiListener() {
                    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                    @Override
                    public void onComplete(Object o) {
                        LogUtil.e("getUserInfo onComplete >>>>" + o.toString());
                        backString = o.toString();
                        finish();


                    }

                    @Override
                    public void onError(UiError uiError) {

                        LogUtil.e("getUserInfo onError >>>>" + uiError.toString());
                    }

                    @Override
                    public void onCancel() {

                        LogUtil.e("getUserInfo onCancel >>>>");
                    }
                });



            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onError(UiError uiError) {

            LogUtil.e("onError"+uiError.toString());
        }

        @Override
        public void onCancel() {

            LogUtil.e("onCancel");
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ivcandcle = (ImageView) findViewById(R.id.login_cancle_iv);
        ivcandcle.setOnClickListener(this);
        ivQQ = (ImageView) findViewById(R.id.login_qq_iv);

        tencent = Tencent.createInstance(APP_ID,getApplicationContext());

        ivQQ.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        Tencent.onActivityResultData(requestCode,resultCode,data,uiListener);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.login_cancle_iv:
                finish();
                break;

            case R.id.login_qq_iv:

                loginByQQ();

                //loginByShareSDK();
        }
    }


    private void loginByShareSDK()
    {
        //创建平台
        Platform qq = ShareSDK.getPlatform(QQ.NAME);

        //如果已经登录了
        if(qq.isValid())
        {
            qq.removeAccount(); //注销登录
            return;
        }


        //设置请求回调
        qq.setPlatformActionListener(new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                LogUtil.e("onComplete " + platform.toString());

               PlatformDb db = platform.getDb();

                LogUtil.e("qqInfo = " + db.toString() + ">>>>" + db.getPlatformNname() + " , " + db.getToken() + " , " + db.getUserName() + " , " + db.getUserGender());
            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {

                LogUtil.e("onError " + platform.toString());
            }

            @Override
            public void onCancel(Platform platform, int i) {

                LogUtil.e("onCancel");
            }
        });

        //授权
        qq.authorize();
    }




    /**
     * 使用QQ官方登录
     */
    private void loginByQQ() {
        //是否已经登录
        if(tencent.isSessionValid())
        {
            //可以注销
            //tencent.logout(LoginActivity.this);//注销登录
            LogUtil.e("已经登录过了");
        }else
        {
            //登录，第二个参数表示你想要获取哪些权限
            tencent.login(LoginActivity.this,"all",uiListener);
        }
    }

    @Override
    public void finish() {

        if(backString!=null)
        {
            Intent intent = new Intent();
            intent.putExtra("result",backString);
            setResult(RESULT_ID,intent);

        }else
        {
            setResult(RESULT_CANCLE);
        }

        super.finish();
    }
}
