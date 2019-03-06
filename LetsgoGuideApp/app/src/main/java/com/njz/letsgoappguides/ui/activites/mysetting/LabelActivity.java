package com.njz.letsgoappguides.ui.activites.mysetting;

import android.content.DialogInterface;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.njz.letsgoappguides.Bean.MySelfInfo;
import com.njz.letsgoappguides.R;
import com.njz.letsgoappguides.base.BaseActivity;
import com.njz.letsgoappguides.customview.widget.TitleView;
import com.njz.letsgoappguides.customview.widget.flowlayout.FlowLayout;
import com.njz.letsgoappguides.customview.widget.flowlayout.TagAdapter;
import com.njz.letsgoappguides.customview.widget.flowlayout.TagFlowLayout;
import com.njz.letsgoappguides.model.mine.LabelItemModel;
import com.njz.letsgoappguides.model.mine.LabelModel;
import com.njz.letsgoappguides.model.mine.MyInfoData;
import com.njz.letsgoappguides.presenter.mine.LabelContract;
import com.njz.letsgoappguides.presenter.mine.LabelPresenter;
import com.njz.letsgoappguides.presenter.mine.ModifyInfoContract;
import com.njz.letsgoappguides.presenter.mine.ModifyInfoPresenter;
import com.njz.letsgoappguides.util.GsonUtil;
import com.njz.letsgoappguides.util.dialog.DialogUtil;
import com.njz.letsgoappguides.util.log.LogUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by LGQ
 * Time: 2018/8/20
 * Function:
 */

public class LabelActivity extends BaseActivity implements LabelContract.View, View.OnClickListener,ModifyInfoContract.View {

    LabelPresenter mPresenter;
    ModifyInfoPresenter modifyPresenter;
    LinearLayout llParent;

    List<LabelItemModel> labelAll = new ArrayList<>();

    TitleView titleView;

    private ImageView tv_add;
    private TagFlowLayout flowlayout_custom;
    private List<LabelItemModel> customLabels = new ArrayList<>();
    private boolean isOnCreate = true;

    @Override
    public int getLayoutId() {
        return R.layout.activity_label;
    }

    @Override
    public void initView() {
        titleView = $(R.id.titleView);

        titleView.getRightTv().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuffer sb = new StringBuffer("");
                for (LabelItemModel item : labelAll) {
                    if (item.isSelect()) {
                        LogUtil.e(item.getName());
                        sb.append(item.getId() + ",");
                    }
                }
                String theLabel = sb.toString();
                theLabel = theLabel.endsWith(",") ? theLabel.substring(0, theLabel.length() - 1) : theLabel;
                myInfoData.setFreeLabel(customLabels);

                myInfoData.setTheLabel(theLabel);
                modifyPresenter.userUpdate(myInfoData);
            }
        });
        titleView.getLeftIv().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        titleView.getRightTv().setEnabled(false);
        titleView.getRightTv().setTextColor(ContextCompat.getColor(context, R.color.black_66));
        llParent = $(R.id.ll_parent);
        tv_add = $(R.id.tv_add);
        flowlayout_custom = $(R.id.flowlayout_custom);

        tv_add.setOnClickListener(this);

        initData();
    }

    public void initData() {
        mPresenter = new LabelPresenter(context, this);
        modifyPresenter = new ModifyInfoPresenter(context,this);
        mPresenter.userGetSign();

    }

    public void initFlow(final TagFlowLayout tagFlowLayout, final List<LabelItemModel> mVals) {
        initFlow(tagFlowLayout, mVals, false);
    }

    public void initFlow(final TagFlowLayout tagFlowLayout, final List<LabelItemModel> mVals, final boolean isCustom) {
        final LayoutInflater mInflater = LayoutInflater.from(activity);

        TagAdapter adapter1 = new TagAdapter<LabelItemModel>(mVals) {
            @Override
            public View getView(FlowLayout parent, int position, LabelItemModel s) {
                if (isCustom) {
                    final TextView tv = (TextView) mInflater.inflate(R.layout.item_flow_label_custom, tagFlowLayout, false);
                    tv.setText(s.getName());
                    tv.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            DialogUtil.getInstance().getDefaultDialog(context, "确认删除 " + tv.getText().toString() + " 标签?", new DialogUtil.DialogCallBack() {
                                @Override
                                public void exectEvent(DialogInterface alterDialog) {
                                    for (int i = 0; i < customLabels.size(); i++) {
                                        if (TextUtils.equals(customLabels.get(i).getName(), tv.getText().toString()))
                                            customLabels.remove(i);
                                    }
                                    initFlow(flowlayout_custom, customLabels, true);
                                    if (titleView.getRightTv().isEnabled())
                                        return;
                                    titleView.getRightTv().setEnabled(true);
                                    titleView.getRightTv().setTextColor(ContextCompat.getColor(context, R.color.color_theme));
                                }
                            }).show();
                            return false;
                        }
                    });
                    return tv;
                } else {
                    TextView tv = (TextView) mInflater.inflate(R.layout.item_flow_label, tagFlowLayout, false);
                    tv.setText(s.getName());
                    return tv;
                }
            }

            @Override
            public void onSelected(int position, View view) {
                if(isCustom) return;
                super.onSelected(position, view);
                if (!isOnCreate && getLabelSize() > 2) {
                    showShortToast("个人标签最多只能添加3个!");
                    return ;
                }

                view.setBackgroundResource(R.drawable.btn_theme33_hollow_r40);
                ((TextView)view).setTextColor(ContextCompat.getColor(context,R.color.color_theme));
                mVals.get(position).setSelect(true);
            }

            @Override
            public void unSelected(int position, View view) {
                if(isCustom) return;
                super.unSelected(position, view);
                view.setBackgroundResource(R.drawable.btn_gray_hollow_r40);
                ((TextView)view).setTextColor(ContextCompat.getColor(context,R.color.color_99));
                mVals.get(position).setSelect(false);
            }
        };

        adapter1.setSelectedList(getSelectIndex(mVals));
        tagFlowLayout.setAdapter(adapter1);

        tagFlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                if (titleView.getRightTv().isEnabled())
                    return true;
                titleView.getRightTv().setEnabled(true);
                titleView.getRightTv().setTextColor(ContextCompat.getColor(context, R.color.color_theme));

                return true;
            }
        });
    }

    public Set<Integer> getSelectIndex(List<LabelItemModel> sVals) {
        Set<Integer> ints = new HashSet<>();
        for (int i = 0; i < sVals.size(); i++) {
            if (sVals.get(i).isSelect() == true) {
                ints.add(i);
            }
        }
        return ints;
    }

    @Override
    public void userGetSignSuccess(List<LabelModel> str) {

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(0, 10, 0, 0);

        LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        for (int i = 0; i < str.size(); i++) {
            TextView tvTitle = new TextView(context);
            tvTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
            tvTitle.setLayoutParams(lp);
            LabelModel labelModel = str.get(i);
            tvTitle.setText(labelModel.getName());
            llParent.addView(tvTitle);


            if (labelModel.getSysMacroEntitys().size() > 0) {
                TagFlowLayout tagFlowLayout = new TagFlowLayout(context);
                tagFlowLayout.setLayoutParams(lp2);
                tagFlowLayout.setPadding(10, 10, 10, 10);
                tagFlowLayout.setMaxSelectCount(-1);
                llParent.addView(tagFlowLayout);

                List<LabelItemModel> labelItemModels = labelModel.getSysMacroEntitys();
                for (LabelItemModel item : labelItemModels) {
                    for (LabelItemModel item2 : MySelfInfo.getInstance().getLabels()) {
                        if (item.getId() == item2.getId()) {
                            item.setSelect(true);
                            continue;
                        }
                    }
                }
                labelAll.addAll(labelItemModels);
                initFlow(tagFlowLayout, labelItemModels);
            }
        }

        List<String> ss = GsonUtil.convertJson2Array(MySelfInfo.getInstance().getFreeLabels());
        for (int i=0;i<ss.size();i++){
            LabelItemModel labelItemModel = new LabelItemModel();
            labelItemModel.setSelect(true);
            labelItemModel.setName(ss.get(i));
            customLabels.add(labelItemModel);
        }
        initFlow(flowlayout_custom, customLabels, true);

        isOnCreate = false;
    }

    @Override
    public void userGetSignFailed(String msg) {
        showShortToast(msg);
    }

    public MyInfoData myInfoData = new MyInfoData();

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_add:
                if(getLabelSize() >2){
                    showShortToast("个人标签最多只能添加3个!");
                    return;
                }
                DialogUtil.getInstance().getEditDialog(context, new DialogUtil.DialogEditCallBack() {
                    @Override
                    public void exectEvent(DialogInterface alterDialog, String str) {
                        if (TextUtils.isEmpty(str)) return;
                        LabelItemModel labelItemModel = new LabelItemModel();
                        labelItemModel.setSelect(true);
                        labelItemModel.setName(str);
                        customLabels.add(labelItemModel);
                        initFlow(flowlayout_custom, customLabels, true);

                        if (titleView.getRightTv().isEnabled())
                            return;
                        titleView.getRightTv().setEnabled(true);
                        titleView.getRightTv().setTextColor(ContextCompat.getColor(context, R.color.color_theme));
                    }
                }).show();
                break;
        }
    }

    public int getLabelSize(){
        int size = 0;
        for (int i = 0;i<labelAll.size();i++){
            if(labelAll.get(i).isSelect()){
                size= size+1;
            }
        }
        size = size + customLabels.size();
        return size;
    }

    @Override
    public void userUpdateSuccess(String str) {
        showShortToast("修改成功");
        List<LabelItemModel> datas = new ArrayList<>();
        for (LabelItemModel item : labelAll) {
            if (item.isSelect()) {
                datas.add(item);
            }
        }
        MySelfInfo.getInstance().setLabels(datas);
        MySelfInfo.getInstance().setFreeLabels(customLabels);

        finish();
    }

    @Override
    public void userUpdateFailed(String msg) {
        showShortToast(msg);
    }
}
