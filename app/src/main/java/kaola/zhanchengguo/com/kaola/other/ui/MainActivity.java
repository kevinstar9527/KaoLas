package kaola.zhanchengguo.com.kaola.other.ui;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

import kaola.zhanchengguo.com.kaola.R;
import kaola.zhanchengguo.com.kaola.other.utils.Contants;
import kaola.zhanchengguo.com.kaola.other.utils.FileUtil;
import kaola.zhanchengguo.com.kaola.other.utils.KaoLaTask;
import kaola.zhanchengguo.com.kaola.other.utils.LogUtil;
import kaola.zhanchengguo.com.kaola.other.utils.OtherHttpUtil;

public class MainActivity extends AppCompatActivity {

    private String localVersion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //获取第一次使用的配置文件
        SharedPreferences preferences = getSharedPreferences(Contants.FLAG_FIRST_USED, Context.MODE_PRIVATE);

        boolean firstUsed = preferences.getBoolean(Contants.FLAG_FIRST_USED_VALUE,true);

        Intent intent = null;
        //如果是第一次使用，那么跳转到引导页
        if(firstUsed)
        {
            intent = new Intent(this,GuideActicity.class);
            startActivity(intent);
            finish();

        }else
        {
            //进入这个应用就先请求最新版本
            compareVersion();

        }

    }

    private void compareVersion() {
        //获取本地版本号
        try {
            PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(),0);

            localVersion = packageInfo.versionName;

            LogUtil.w("localVersion = " + localVersion);

            //如果清单里面和build.gradel里面都有版本信息，那么会以build.gradle的版本信息为准

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        OtherHttpUtil.getVersion(new KaoLaTask.IRequestCallback() {
            @Override
            public void success(Object object) {

                try {

                    JSONObject root = new JSONObject(object.toString());

                    String message = root.getString(Contants.FLAG_RESULT_MESSAGE);
                    if(Contants.FLAG_STATE_SUCCESS.equals(message))
                    {
                        JSONObject result = root.getJSONObject(Contants.FLAG_RESULT_RESULT);

                        //获取服务器版本
                        String netVersion = result.getString("updateVersion");

                        //如果是相同的版本，说明不需要更新
                        if(localVersion.equals(netVersion))
                        {
                            LogUtil.w("已经是最新的版本了");
                        }else
                        {
                            //显示更新对话框

                            String msg = result.getString("updateInfo");  //新版本信息
                            String updateUrl = result.getString("updateUrl");  //apk url

                            showUpgradeDialog(msg,updateUrl);
                        }

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void error(String msg) {

            }
        },localVersion);
    }

    /**
     * 显示更新对话框
     *
     * @param msg
     * @param apkUrl
     */
    public void showUpgradeDialog(String msg, final String apkUrl)
    {
        final Dialog dialog = new Dialog(this,R.style.dialog_upgrade);

        dialog.setContentView(R.layout.dialog_upgrade);

        TextView tvMsg = (TextView) dialog.findViewById(R.id.upgrade_msg_tv);

        tvMsg.setText(msg);

        Button btnCancel = (Button) dialog.findViewById(R.id.upgrade_cancel_btn);
        final Button btnDownload = (Button) dialog.findViewById(R.id.upgrade_download_btn);
        final ProgressBar progressBar  = (ProgressBar) dialog.findViewById(R.id.upgrade_pb);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

                //如果点击取消跳转到广告页
                Intent intent  = new Intent(MainActivity.this,BannerActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //如果已经开始下载，就不能在重复下载
                btnDownload.setEnabled(false);

                OtherHttpUtil.downloadApk(new KaoLaTask.IDdownloadListener() {
                    @Override
                    public void upgradeProgress(float progress) {
                            progressBar.setProgress((int) progress);
                    }

                    @Override
                    public void Completed(File file) {
                        FileUtil.installApk(MainActivity.this,file);
                        finish();//这一句可要可不要
                    }

                    @Override
                    public void error(String msg) {

                    }

                    @Override
                    public void start() {

                    }
                },apkUrl);
            }
        });

        //点击dialog以外的区域不让消失
        dialog.setCanceledOnTouchOutside(false);

        //不让取消
        dialog.setCancelable(false);

        //显示
        dialog.show();
    }
}
