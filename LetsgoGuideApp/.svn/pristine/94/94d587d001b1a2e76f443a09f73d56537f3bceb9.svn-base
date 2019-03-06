package com.njz.letsgoappguides.ui.activites.mine;

import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.njz.letsgoappguides.Bean.MySelfInfo;
import com.njz.letsgoappguides.R;
import com.njz.letsgoappguides.base.BaseActivity;
import com.njz.letsgoappguides.model.mine.BinkIntoInfo;
import com.njz.letsgoappguides.model.mine.GetBackListInfo;
import com.njz.letsgoappguides.model.mine.GetBinkInfo;
import com.njz.letsgoappguides.presenter.mine.BinkSaveContract;
import com.njz.letsgoappguides.presenter.mine.BinkSavePresenter;
import com.njz.letsgoappguides.util.AppUtils;
import com.njz.letsgoappguides.util.LoginUtil;
import com.njz.letsgoappguides.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BindIdcarActivity extends BaseActivity implements BinkSaveContract.View {

    @BindView(R.id.title_tv)
    TextView titleTv;
    @BindView(R.id.et_idcard)
    EditText etIdcard;
    @BindView(R.id.et_number)
    EditText etNumber;
    @BindView(R.id.et_bink)
    EditText etBink;
    @BindView(R.id.et_card)
    EditText etCard;
    @BindView(R.id.tv_names)
    TextView tvName;
    BinkSavePresenter mBinkSavePresenter;
    @BindView(R.id.tv_idcard_save)
    TextView tvIdcardSave;
    @BindView(R.id.tv_idcard_reset)
    TextView tvIdcardReset;
    int binkId=0;//查询银行账号id
    int backType;//银行名称id
    List<GetBackListInfo> infoList=new ArrayList<>();
    List<String> infoListStr=new ArrayList<>();

    @Override
    protected void initView() {
        titleTv.setText("绑定银行卡");
        if (MySelfInfo.getInstance().isLogin()) {
            tvName.setText("您实名认证的姓名是" + MySelfInfo.getInstance().getName() + "，您只能绑定" + MySelfInfo.getInstance().getName() + "的银行卡卡号。");
        }
        etIdcard.setInputType(InputType.TYPE_CLASS_NUMBER);
        etNumber.setInputType(InputType.TYPE_CLASS_NUMBER);

        mBinkSavePresenter = new BinkSavePresenter(this, context);
        mBinkSavePresenter.getBackList();
        mBinkSavePresenter.getBinkinfo();

        etCard.setFocusable(false);
        etCard.setFocusableInTouchMode(false);
        etCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppUtils.HideKeyboard(etCard);
                //条件选择器
                OptionsPickerView pvOptions = new OptionsPickerBuilder(context,
                        new OnOptionsSelectListener() {
                            @Override
                            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                                if(infoListStr.size()>0){
                                    etCard.setText(infoListStr.get(options1));
                                    backType=infoList.get(options1).getId();
                                }
                            }
                        })
                        .build();
                pvOptions.setPicker(infoListStr, null, null);
                pvOptions.show();
            }
        });

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_bind_idcar;
    }

    @OnClick(R.id.left_iv)
    public void leftBack() {
        finish();
    }


    @OnClick({R.id.tv_idcard_save, R.id.tv_idcard_reset})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_idcard_save:
                if (etIdcard.getText().toString().equals("")) {
                    showShortToast("请输入储蓄卡卡号");
                    return;
                }
                if (!StringUtils.checkBankCard(etIdcard.getText().toString())) {
                    showShortToast("请输入正确的卡号");
                    return;
                }
                if (!LoginUtil.verifyPhone(etNumber.getText().toString()))
                    return;
                if (!LoginUtil.verifyBinkNames(etBink.getText().toString()))
                    return;
                BinkIntoInfo mBinkIntoInfo = new BinkIntoInfo();
                mBinkIntoInfo.setCardNo(etIdcard.getText().toString().trim());
                mBinkIntoInfo.setMobile(etNumber.getText().toString().trim());
                mBinkIntoInfo.setBackType(backType);//开户银行
                mBinkIntoInfo.setBackBranch(etBink.getText().toString().trim());//开户支行
                if(tvIdcardSave.getText().toString().equals("修改")){
                    mBinkIntoInfo.setId(binkId);
                    mBinkSavePresenter.toBinkUpdate(mBinkIntoInfo);
                }else{
                    mBinkSavePresenter.toBinkSave(mBinkIntoInfo);
                }
                break;
            case R.id.tv_idcard_reset:
                mBinkSavePresenter.getBinkinfo();
                break;
        }
    }

    //--------保存账号  start--------
    @Override
    public void toBinkSaveSuccess(String str) {
        showShortToast("保存成功");
        finish();
    }

    @Override
    public void toBinkSaveFailed(String msg) {
        showShortToast(msg);
    }

    //--------保存账号  end--------


    //--------修改账号  start--------
    @Override
    public void toBinkUpdateSuccess(String str) {
        showShortToast("修改成功");
        finish();
    }

    @Override
    public void toBinkUpdateFailed(String msg) {
        showShortToast(msg);
    }
    //--------修改账号  end--------


    //--------获取账号信息  start--------
    @Override
    public void getBinkinfoSuccess(GetBinkInfo infos) {
        if(null!=infos&&infos.getMobile()!=null){
            tvIdcardSave.setText("修改");
            etIdcard.setText(infos.getCardNo());
            etNumber.setText(infos.getMobile());
            etBink.setText(infos.getBackBranch());
            binkId=infos.getId();
            backType=infos.getBackType();
            etCard.setText(infos.getBackTypeName());

            tvIdcardSave.setEnabled(false);
            tvIdcardSave.setBackgroundResource(R.drawable.savegray_btn_style);

            initStyle();
        }


    }

    @Override
    public void getBinkinfoFailed(String msg) {
        showShortToast(msg);
    }
    //--------获取账号信息  end--------


    //获取银行信息列表
    @Override
    public void getBackListSuccess(List<GetBackListInfo> infos) {
        if(infos==null)return;
        infoList=infos;
        for (int i=0;i<infoList.size();i++){
            String name=infoList.get(i).getName();
            infoListStr.add(name);
        }

    }

    @Override
    public void getBackListFailed(String msg) {

    }

    TextWatcher mTextWatcher=new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            //设置按钮可点击并且改变背景颜色
            tvIdcardSave.setEnabled(true);
            tvIdcardSave.setBackgroundResource(R.drawable.login_btn_style);
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    public void initStyle(){
        tvIdcardSave.setEnabled(false);
        etIdcard.addTextChangedListener(mTextWatcher);
        etCard.addTextChangedListener(mTextWatcher);
        etNumber.addTextChangedListener(mTextWatcher);
        etBink.addTextChangedListener(mTextWatcher);
    }

}
