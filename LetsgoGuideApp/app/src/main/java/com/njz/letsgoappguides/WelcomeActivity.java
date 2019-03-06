package com.njz.letsgoappguides;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.njz.letsgoappguides.Bean.MySelfInfo;
import com.njz.letsgoappguides.base.BaseActivity;
import com.njz.letsgoappguides.ui.MainActivity;
import com.njz.letsgoappguides.ui.activites.LoginActivity;

import butterknife.BindView;

/***
 * 后期做欢迎页面
 */
public class WelcomeActivity extends BaseActivity {

    @BindView(R.id.tv_id)
    TextView text;
    @BindView(R.id.bu_id)
    Button button;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initView() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击按钮触发presenter里面的方法
//                callInfoPresenter.getCall();
                if(MySelfInfo.getInstance().isLogin()){
                    startActivity(new Intent(WelcomeActivity.this,MainActivity.class));
                    finish();
                }else{
                    startActivity(new Intent(WelcomeActivity.this,LoginActivity.class));
                    finish();
                }
            }
        });
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
    }
}
