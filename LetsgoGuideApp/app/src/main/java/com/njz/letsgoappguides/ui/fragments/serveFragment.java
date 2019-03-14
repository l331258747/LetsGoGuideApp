package com.njz.letsgoappguides.ui.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.njz.letsgoappguides.Bean.MySelfInfo;
import com.njz.letsgoappguides.R;
import com.njz.letsgoappguides.adapter.EndlessRecyclerOnScrollListener;
import com.njz.letsgoappguides.adapter.LoadMoreWrapper;
import com.njz.letsgoappguides.adapter.server.ServerListAdapter;
import com.njz.letsgoappguides.base.BaseFragment;
import com.njz.letsgoappguides.constant.Constant;
import com.njz.letsgoappguides.customview.widget.emptyView.EmptyClickLisener;
import com.njz.letsgoappguides.customview.widget.emptyView.EmptyView;
import com.njz.letsgoappguides.customview.widget.emptyView.EmptyView2;
import com.njz.letsgoappguides.customview.widget.popupwindow.ServerListPop;
import com.njz.letsgoappguides.model.other.PopupSelectModel;
import com.njz.letsgoappguides.model.server.AutoServiceModel;
import com.njz.letsgoappguides.model.server.CityModel;
import com.njz.letsgoappguides.model.server.GetServeDicListModel;
import com.njz.letsgoappguides.model.server.GetServiceCityModel;
import com.njz.letsgoappguides.model.server.GetUpdateServiceInfo;
import com.njz.letsgoappguides.model.server.NjzGuideServeDicVoListBean;
import com.njz.letsgoappguides.model.server.ServerListModel;
import com.njz.letsgoappguides.model.server.ServerTypeModel;
import com.njz.letsgoappguides.presenter.server.GetCityContract;
import com.njz.letsgoappguides.presenter.server.GetCityPresenter;
import com.njz.letsgoappguides.presenter.server.GetUpdateServInfoContract;
import com.njz.letsgoappguides.presenter.server.GetUpdateServInfoPresenter;
import com.njz.letsgoappguides.presenter.server.ServerListContract;
import com.njz.letsgoappguides.presenter.server.ServerListPresenter;
import com.njz.letsgoappguides.presenter.server.ServerTypeContract;
import com.njz.letsgoappguides.presenter.server.ServerTypePresenter;
import com.njz.letsgoappguides.ui.activites.service.AddServicesActivity;
import com.njz.letsgoappguides.ui.activites.service.ServicePreviewActivity;
import com.njz.letsgoappguides.util.AppUtils;
import com.njz.letsgoappguides.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class serveFragment extends BaseFragment  implements GetCityContract.View,ServerTypeContract.View,ServerListContract.View,GetUpdateServInfoContract.View{

    @BindView(R.id.tv_search)
    EditText tvSearch;
    @BindView(R.id.iv_release)
    ImageView ivRelease;
    @BindView(R.id.ll_location)
    LinearLayout llLocation;
    @BindView(R.id.ll_server_type)
    LinearLayout llServerType;
    @BindView(R.id.ll_server_status)
    LinearLayout llServerStatus;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.ll_option)
    LinearLayout llOption;
    @BindView(R.id.tv_location)
    TextView tvLocation;
    @BindView(R.id.tv_server_type)
    TextView tvServerType;
    @BindView(R.id.tv_server_status)
    TextView tvServerStatus;
    @BindView(R.id.view_empty)
    EmptyView2 view_empty;
    @BindView(R.id.view_empty1)
    EmptyView view_empty1;

    GetCityPresenter cityPresenter;
    ServerTypePresenter sTypePresenter;
    ServerListPresenter sListPresenter;
    GetUpdateServInfoPresenter mUpdateServInfoPresenter;

    List<PopupSelectModel> citys,sTypes,sStatus;

    ServerListPop serverListPop;

    int locationId = -1;
    int serverTypeId = -1;
    int serverStatusId = -2;

    public static final int LOCATION_POSITION = 0;
    public static final int SERVER_TYPE_POSITION = 1;
    public static final int SERVER_STATUS_POSITION = 2;

    ServerListAdapter mAdapter;
    LoadMoreWrapper loadMoreWrapper;
    int page;
    int isLoadType = 1;//1下拉刷新，2上拉加载
    boolean isLoad = false;//是否在加载，重复加载问题

    public int isIdentif=0;
    String searchName="";


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_serve;
    }

    @Override
    protected void initView() {
        initRecycler();
        initSwipeLayout();

        initData();

        tvSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    searchName=tvSearch.getText().toString();
                    getRefreshData();
                    AppUtils.HideKeyboard(tvSearch);
                    return true;
                }
                return false;
            }
        });
    }

    //初始化recyclerview
    private void initRecycler() {
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        mAdapter = new ServerListAdapter(context, new ArrayList<ServerListModel>());
        loadMoreWrapper = new LoadMoreWrapper(mAdapter);
        recyclerView.setAdapter(loadMoreWrapper);

        recyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener() {
            @Override
            public void onLoadMore() {
                if (isLoad || loadMoreWrapper.getLoadState() == LoadMoreWrapper.LOADING_END) return;
                loadMoreWrapper.setLoadState(loadMoreWrapper.LOADING);
                getMoreData();
            }
        });

        mAdapter.setOnItemClickListener(new ServerListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int njzGuideServeId) {//修改
                Intent intent=new Intent(context,AddServicesActivity.class);
                intent.putExtra("serviceTypeID",njzGuideServeId);
                intent.putExtra("ID",2);
                startActivity(intent);
            }

            @Override
            public void onItemClickCopy(int njzGuideServeId) {//复制
                Intent intent=new Intent(context,AddServicesActivity.class);
                intent.putExtra("serviceTypeID",njzGuideServeId);
                intent.putExtra("ID",3);
                startActivity(intent);
            }

            @Override
            public void onItemClickDelete(final int njzGuideServeId) {
                isIdentif=10;

                new AlertDialog.Builder(context).setTitle("请问确定删除吗？").setPositiveButton("确定",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // TODO Auto-generated method stub
                                AutoServiceModel auto=new AutoServiceModel();
                                auto.setId(njzGuideServeId);
                                auto.setIsEnabled("1");
                                mUpdateServInfoPresenter.updaServiceInfo(auto);
                            }
                        }).setNegativeButton("取消", null).show();

            }

            @Override
            public void onItemClickToUp(int njzGuideServeId,float price) {
                isIdentif=11;
                AutoServiceModel auto=new AutoServiceModel();
                auto.setId(njzGuideServeId);
                auto.setStatus(new Long((long)1));
                auto.setServePrice(price);
                mUpdateServInfoPresenter.updaServiceInfo(auto);
            }

            @Override
            public void onItemClickToDown(final int njzGuideServeId,final float price) {
                isIdentif=12;
                new AlertDialog.Builder(context).setTitle("请问确定下架吗？").setPositiveButton("确定",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // TODO Auto-generated method stub
                                AutoServiceModel auto=new AutoServiceModel();
                                auto.setId(njzGuideServeId);
                                auto.setStatus(new Long((long)0));
                                auto.setServePrice(price);
                                mUpdateServInfoPresenter.updaServiceInfo(auto);
                            }
                        }).setNegativeButton("取消", null).show();
            }

            @Override
            public void onItemClickPreview(int njzGuideServeId) {
                Intent intent = new Intent(context, ServicePreviewActivity.class);
                intent.putExtra("SERVICE_ID",njzGuideServeId);
                intent.putExtra("PREVIEWID",Constant.PREVIEWIDDETAIL);
                startActivity(intent);
            }
        });

    }

    //初始化SwipeLayout
    private void initSwipeLayout() {
        swipeRefreshLayout.setColorSchemeColors(getResColor(R.color.color_theme));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (isLoad) return;
                getRefreshData();
            }
        });
    }

    private void getRefreshData() {
        searchName=tvSearch.getText().toString();
        swipeRefreshLayout.setRefreshing(true);
        isLoad = true;
        page = Constant.DEFAULT_PAGE;
        isLoadType = 1;
        sListPresenter.serveList(Constant.DEFAULT_PAGE,Constant.DEFAULT_LIMIT,""+locationId,""+serverTypeId,""+serverStatusId,searchName);
    }

    private void getMoreData() {
        searchName=tvSearch.getText().toString();
        isLoad = true;
        page = page + 1;
        isLoadType = 2;
        sListPresenter.serveList(page,Constant.DEFAULT_LIMIT,""+locationId,""+serverTypeId,""+serverStatusId,searchName);
    }

    private void initData() {
        cityPresenter = new GetCityPresenter(context,this);
        sTypePresenter = new ServerTypePresenter(context,this);
        sListPresenter = new ServerListPresenter(context,this);
        mUpdateServInfoPresenter=new GetUpdateServInfoPresenter(context,this);

        citys = new ArrayList<>();
        sTypes = new ArrayList<>();
        sStatus = new ArrayList<>();

    }

    boolean isExecuted;//获取城市，服务类型，上架状态，运行过一次就不用运行了
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);//比oncreate先执行
        if (isVisibleToUser && isPrepared && !isLoad) {
            if(!isExecuted){
                cityPresenter.cityGetCity();
                sTypePresenter.getServeDicList(false);//服务类型
                setServerStatus();
            }
            isExecuted = true;
            getRefreshData();
        }
    }



    @Override
    public void loadData() {

    }


    @OnClick({R.id.iv_release, R.id.ll_location, R.id.ll_server_type, R.id.ll_server_status})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_release:
                if (MySelfInfo.getInstance().isLogin()) {
                    if (""!=MySelfInfo.getInstance().getCardViable() ){
                        int cardViable=Integer.parseInt(MySelfInfo.getInstance().getCardViable());
                        if(cardViable!=2){
                            showShortToast("必须实名认证成功之后才可以添加服务!");
                        }else{
                            Intent intent=new Intent(context, AddServicesActivity.class);
                            intent.putExtra("ID",1);
                            startActivity(intent);
                        }
                    }
                }
                break;
            case R.id.ll_location:
                showPop(citys,LOCATION_POSITION);
                break;
            case R.id.ll_server_type:
                showPop(sTypes,SERVER_TYPE_POSITION);
                break;
            case R.id.ll_server_status:
                showPop(sStatus,SERVER_STATUS_POSITION);
                break;
        }
    }

    public void showPop(List<PopupSelectModel> datas,final int popPosition){
        if(datas == null || datas.size() == 0) return;
        serverListPop = new ServerListPop(context, llOption,datas);
        serverListPop.showPopupWindow(llOption);
        changeTopTextColor(popPosition);
        serverListPop.setOnitemClickLisener(new ServerListPop.OnItemClickLisener() {
            @Override
            public void onClick(int position) {
                if(popPosition == LOCATION_POSITION){
                    locationId = citys.get(position).getId();
                    tvLocation.setText(citys.get(position).getName());
                    if(position==0) {
                        tvLocation.setText("目的地");
                    }
                    setSelectes(citys,locationId);
                }else if(popPosition == SERVER_TYPE_POSITION){
                    serverTypeId = sTypes.get(position).getId();
                    tvServerType.setText(sTypes.get(position).getName());
                    if(position==0) {
                        tvServerType.setText("服务类型");
                    }
                    setSelectes(sTypes,serverTypeId);
                }else if(popPosition == SERVER_STATUS_POSITION){
                    serverStatusId = sStatus.get(position).getId();
                    tvServerStatus.setText(sStatus.get(position).getName());
                    if(position==0) {
                        tvServerStatus.setText("上架状态");
                    }
                    setSelectes(sStatus,serverStatusId);
                }
                getRefreshData();
            }
        });

        serverListPop.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                resetTopTextColor(popPosition);
            }
        });
    }



    public void setServerTypes(List<NjzGuideServeDicVoListBean> datas){
        PopupSelectModel model0 = new PopupSelectModel();
        model0.setId(-1);
        model0.setName("全部");
        sTypes.add(model0);
        for (NjzGuideServeDicVoListBean item : datas){
            PopupSelectModel model = new PopupSelectModel();
            model.setId(item.getId());
            model.setName(item.getName());
            sTypes.add(model);
        }
        setSelectes(sTypes,serverTypeId);
    }

    public void setServerStatus(){//服务状态 1上架  0下架  -1强制下架  2审核中  3审核未通过
        PopupSelectModel mode12 = new PopupSelectModel();
        mode12.setId(-2);
        mode12.setName("全部");
        sStatus.add(mode12);

        PopupSelectModel model11 = new PopupSelectModel();
        model11.setId(-1);
        model11.setName("强制下架");
        sStatus.add(model11);

        PopupSelectModel model0 = new PopupSelectModel();
        model0.setId(0);
        model0.setName("未上架");
        sStatus.add(model0);

        PopupSelectModel model1 = new PopupSelectModel();
        model1.setId(1);
        model1.setName("已上架");
        sStatus.add(model1);

        PopupSelectModel model2 = new PopupSelectModel();
        model2.setId(2);
        model2.setName("审核中");
        sStatus.add(model2);

        PopupSelectModel model3 = new PopupSelectModel();
        model3.setId(3);
        model3.setName("审核未通过");
        sStatus.add(model3);

        setSelectes(sStatus,serverStatusId);
    }

    public void setSelectes(List<PopupSelectModel> datas,int id){
        for (PopupSelectModel item : datas){
            item.setSelect(false);
            if(id == item.getId())
                item.setSelect(true);
        }
    }

    private void resetTopTextColor(int position){
        if(position==LOCATION_POSITION){
            tvLocation.setTextColor(ContextCompat.getColor(context,R.color.color_text));
        }else if(position==SERVER_TYPE_POSITION){
            tvServerType.setTextColor(ContextCompat.getColor(context,R.color.color_text));
        }else if(position==SERVER_STATUS_POSITION){
            tvServerStatus.setTextColor(ContextCompat.getColor(context,R.color.color_text));
        }
    }


    private void changeTopTextColor(int position){
        if(position==LOCATION_POSITION){
            tvLocation.setTextColor(ContextCompat.getColor(context,R.color.color_theme));
        }else if(position==SERVER_TYPE_POSITION){
            tvServerType.setTextColor(ContextCompat.getColor(context,R.color.color_theme));
        }else if(position==SERVER_STATUS_POSITION){
            tvServerStatus.setTextColor(ContextCompat.getColor(context,R.color.color_theme));
        }
    }

    //-------------服务列表 start-----------
    @Override
    public void serveListSuccess(List<ServerListModel> models) {
        List<ServerListModel> datas = models;

        if (isLoadType == 1) {
            mAdapter.setData(datas);
        } else {
            mAdapter.addData(datas);
        }
        isLoad = false;
        if (datas.size() >= Constant.DEFAULT_LIMIT) {
            loadMoreWrapper.setLoadState(loadMoreWrapper.LOADING_COMPLETE);
        } else {
            // 显示加载到底的提示
            loadMoreWrapper.setLoadState(loadMoreWrapper.LOADING_END);
        }
        swipeRefreshLayout.setRefreshing(false);

        view_empty1.setVisible(false);
        view_empty.setVisible(false);
        if(locationId==-1&&serverTypeId==-1&&serverStatusId==-2&&searchName.equals("")) {
            if(mAdapter.getDatas().size()!=0)return;
            view_empty.setVisible(true);
            view_empty.setEmptyData(R.mipmap.empty_service, "您还没发布服务信息", "请点击右上角图标进行添加", true);
            view_empty.setEmptyBackground(R.color.color_window_background);
        }else{
            view_empty.setVisible(false);
            view_empty1.setVisible(false);
        }
    }

    @Override
    public void serveListFailed(String msg) {
        showShortToast(msg);
        isLoad = false;
        swipeRefreshLayout.setRefreshing(false);
        loadMoreWrapper.setLoadState(loadMoreWrapper.LOADING_COMPLETE);

        if(msg.startsWith("-")){
            view_empty1.setVisible(true);
            view_empty1.setEmptyData(R.mipmap.empty_network, "网络竟然崩溃了", "别紧张，试试看刷新页面~", "点击刷新");
            view_empty1.setBtnClickLisener(new EmptyClickLisener() {
                @Override
                public void onClick() {
                    getRefreshData();
                }
            });
        }
    }
    //-------------服务列表 end-----------

    //-------------服务类型 start-----------
    @Override
    public void getServeDicListSuccess(GetServeDicListModel models) {
        if(null==models)return;
        List<NjzGuideServeDicVoListBean> getServeDicVoList=models.getNjzGuideServeDicVoList();
        setServerTypes(getServeDicVoList);
    }

    @Override
    public void getServeDicListFailed(String msg) {
        showShortToast(msg);
    }

    @Override
    public void serveGetServeSuccess(List<ServerTypeModel> datas) {

    }
    @Override
    public void serveGetServeFailed(String msg) {
        showShortToast(msg);
    }
    //-------------服务类型 end-----------




    //--------------城市 start--------------

    @Override
    public void getServiceCityListSuccess(List<GetServiceCityModel> datas) {
//        Log.e("test",datas.toString());

//        List<CityModel> lists=new ArrayList<>();
//        if (datas!=null){
//            for (int i=0;i<datas.size();i++){
//                List<GetServiceCityModel.ChildrensBeanX> childrens=datas.get(i).getChildrens();
//                for (int a=0;a<childrens.size();a++){
//                    List<GetServiceCityModel.ChildrensBeanX.ChildrensBean> childrens2=childrens.get(a).getChildrens();
//                    for (int b=0;b<childrens2.size();b++){
//                        if(!childrens2.get(b).equals("")){
//                            CityModel citys=new CityModel();
//                            citys.setId(childrens2.get(b).getId());
//                            citys.setName(childrens2.get(b).getAddressName());
//                            lists.add(citys);
//                        }
//                    }
//                }
//            }
//            setLocations(lists);
//        }

    }

    @Override
    public void getServiceCityListFailed(String msg) {

    }

    @Override
    public void cityGetCitySuccess(List<CityModel> datas) {
        setLocations(datas);
    }

    @Override
    public void cityGetCityFailed(String msg) {
        showShortToast(msg);
    }


    public void setLocations(List<CityModel> datas){
        citys.clear();
        PopupSelectModel model1 = new PopupSelectModel();
        model1.setId(-1);
        model1.setName("全部");
        citys.add(model1);
        for (CityModel item : datas){
            PopupSelectModel model = new PopupSelectModel();
            model.setId(item.getId());
            model.setName(item.getName());
            citys.add(model);
        }
        setSelectes(citys,locationId);
    }
    //--------------城市 end--------------


    //------------删除  上下架 start--------------

    @Override
    public void updaServiceInfoSuccess(String infos) {
        switch (isIdentif){
            case 10:
                showShortToast("删除成功");
                break;
            case 11:
                showShortToast("上架成功");
                break;
            case 12:
                showShortToast("下架成功");
                break;
        }
        getRefreshData();
    }

    @Override
    public void updaServiceInfoFailed(String msg) {
        showShortToast(msg);
    }

    @Override
    public void getUpdateServInfoSuccess(GetUpdateServiceInfo infos) {
    }

    @Override
    public void getUpdateServInfoFailed(String msg) {

    }

    //------------删除  上下架 end--------------


    @Override
    public void onResume() {
        super.onResume();
        if (getUserVisibleHint() && isPrepared && !isLoad) {
            getRefreshData();
        }
    }
}