package com.njz.letsgoappguides.ui.activites.mine;


import android.view.View;

import com.njz.letsgoappguides.R;
import com.njz.letsgoappguides.base.BaseActivity;
import com.njz.letsgoappguides.customview.widget.TitleView;

/**
 * Created by LGQ
 * Time: 2019/3/11
 * Function:
 */

public class ContactUsContentActivity extends BaseActivity {

    public static final int CODE_WXGZH = 0;
    public static final int CODE_WXKF = 1;
    public static final int CODE_TOURIST = 2;//tourist
    public static final int CODE_GUIDE = 3;

    int type = 0;

    TitleView titleView;


    @Override
    public int getLayoutId() {
        type = getIntent().getIntExtra("ContactUs_Type", 0);

        switch (type) {
            case CODE_WXGZH:
                return R.layout.layout_contact_us_wxgzh;
            case CODE_WXKF:
                return R.layout.layout_contact_us_wxkf;
            case CODE_GUIDE:
                return R.layout.layout_contact_us_guide;
            case CODE_TOURIST:
                return R.layout.layout_contact_us_tourist;
        }
        return R.layout.layout_contact_us_wxgzh;
    }

    @Override
    public void initView() {
        titleView = $(R.id.titleView);
        titleView.getLeftIv().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        switch (type) {
            case CODE_WXGZH:
                titleView.setTitle("微信公众号");
                break;
            case CODE_WXKF:
                titleView.setTitle("微信客服");
                break;
            case CODE_TOURIST:
                titleView.setTitle("游客端访问方式");
                break;
            case CODE_GUIDE:
                titleView.setTitle("向导入驻");
                break;
        }
    }

}
