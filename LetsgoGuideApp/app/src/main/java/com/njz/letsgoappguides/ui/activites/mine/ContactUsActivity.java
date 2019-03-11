package com.njz.letsgoappguides.ui.activites.mine;

import android.content.Intent;
import android.view.View;

import com.njz.letsgoappguides.R;
import com.njz.letsgoappguides.base.BaseActivity;
import com.njz.letsgoappguides.constant.Constant;
import com.njz.letsgoappguides.customview.widget.MineItemView2;
import com.njz.letsgoappguides.ui.activites.other.WebViewActivity;
import com.njz.letsgoappguides.util.dialog.DialogUtil;

/**
 * Created by LGQ
 * Time: 2019/3/11
 * Function:
 */

public class ContactUsActivity extends BaseActivity implements View.OnClickListener {

    MineItemView2 mine_customer, mine_wxgzh, mine_wxkf, mine_network, mine_guide, mine_tourist;

    @Override
    protected void initView() {

        mine_customer = $(R.id.mine_customer);
        mine_wxgzh = $(R.id.mine_wxgzh);
        mine_network = $(R.id.mine_network);
        mine_guide = $(R.id.mine_guide);
        mine_tourist = $(R.id.mine_tourist);
        mine_wxkf = $(R.id.mine_wxkf);

        mine_customer.setOnClickListener(this);
        mine_wxgzh.setOnClickListener(this);
        mine_network.setOnClickListener(this);
        mine_tourist.setOnClickListener(this);
        mine_wxkf.setOnClickListener(this);
        mine_guide.setOnClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_contact_us;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mine_customer://电话
                DialogUtil.getInstance().showCustomerMobileDialog(context);
                break;
            case R.id.mine_wxgzh://微信公众号
                startContent(ContactUsContentActivity.CODE_WXGZH);
                break;
            case R.id.mine_wxkf://微信客服
                startContent(ContactUsContentActivity.CODE_WXKF);
                break;
            case R.id.mine_tourist://游客小程序
                startContent(ContactUsContentActivity.CODE_TOURIST);
                break;
            case R.id.mine_guide://导游
                startContent(ContactUsContentActivity.CODE_GUIDE);
                break;
            case R.id.mine_network:
                Intent intent = new Intent(context, WebViewActivity.class);
                intent.putExtra(Constant.EXTRA_URL, "www.njzou.com");
                startActivity(intent);
                break;
        }
    }

    public void startContent(int type) {
        Intent intent = new Intent(context, ContactUsContentActivity.class);
        intent.putExtra("ContactUs_Type", type);
        startActivity(intent);
    }
}
