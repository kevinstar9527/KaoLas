package kaola.zhanchengguo.com.kaola.other.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.se7en.endecryption.DES;
import com.se7en.endecryption.MD5;

import java.io.UnsupportedEncodingException;

import kaola.zhanchengguo.com.kaola.R;
import kaola.zhanchengguo.com.kaola.other.utils.LogUtil;

/**
 * Created by Administrator on 2016/6/22.
 */
public class SecretActivity extends AppCompatActivity {

    private EditText etContent,etKey;

    private Button btnMD5,btnBase64,btnDES;

    private TextView tvMD5,tvBase64,tvDES;

    private String content,key;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_secret);

        etContent = (EditText) findViewById(R.id.secret_top_et);
        etKey = (EditText) findViewById(R.id.secret_Second_et);

        btnBase64 = (Button) findViewById(R.id.secret_base64_btn);
        btnMD5 = (Button) findViewById(R.id.secret_md5_btn);
        btnDES = (Button) findViewById(R.id.secret_des_btn);

        tvMD5 = (TextView) findViewById(R.id.secret_md5_tv);
        tvBase64 = (TextView) findViewById(R.id.secret_base64_tv);
        tvDES = (TextView) findViewById(R.id.secret_des_tv);

        etContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                content = s.toString().trim();
            }
        });

        etKey.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                key = s.toString().trim();
            }
        });

    }

    public void getMd5(View view)
    {
        //md5加密
        String s = MD5.md5(content);

        //显示结果
        tvMD5.setText(s);
    }

    public void getBase64(View view )
    {
        byte[] encode =  Base64.encode(content.getBytes(),Base64.DEFAULT);

        try {
            String str = new String(encode,"utf-8");

            tvBase64.setText(str);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        byte[] decode = Base64.decode(encode,Base64.DEFAULT);

        try {
            String dec = new String(decode,"utf-8");

            LogUtil.w("dec = " + dec);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void getDes(View view )
    {
        if(key.length() < 8)
        {
            return;
        }

        byte[] encrypt = DES.encrypt(content.getBytes(),key);

        try {

            String string = new String(encrypt,"utf-8");
            tvDES.setText(string);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        try {
            byte[] decrypt = DES.decrypt(encrypt,key);

            String dec = new String(decrypt,"utf-8");

            LogUtil.w("dec = " + dec);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
