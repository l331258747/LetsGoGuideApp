package com.njz.letsgoappguides.ui.im;

import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.njz.letsgoappguides.R;
import com.njz.letsgoappguides.adapter.EndlessRecyclerOnScrollListener;
import com.njz.letsgoappguides.adapter.LoadMoreWrapper;
import com.njz.letsgoappguides.adapter.server.ServerListAdapter2;
import com.njz.letsgoappguides.base.BaseActivity;
import com.njz.letsgoappguides.constant.Constant;
import com.njz.letsgoappguides.customview.widget.popupwindow.ServerListPop;
import com.njz.letsgoappguides.model.other.PopupSelectModel;
import com.njz.letsgoappguides.model.server.CityModel;
import com.njz.letsgoappguides.model.server.GetServeDicListModel;
import com.njz.letsgoappguides.model.server.GetServiceCityModel;
import com.njz.letsgoappguides.model.server.NjzGuideServeDicVoListBean;
import com.njz.letsgoappguides.model.server.ServerListModel;
import com.njz.letsgoappguides.model.server.ServerTypeModel;
import com.njz.letsgoappguides.presenter.server.GetCityContract;
import com.njz.letsgoappguides.presenter.server.GetCityPresenter;
import com.njz.letsgoappguides.presenter.server.ServerListContract;
import com.njz.letsgoappguides.presenter.server.ServerListPresenter;
import com.njz.letsgoappguides.presenter.server.ServerTypeContract;
import com.njz.letsgoappguides.presenter.server.ServerTypePresenter;
import com.njz.letsgoappguides.util.rxbus.RxBus2;
import com.njz.letsgoappguides.util.rxbus.busEvent.SendServerEvent;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by liguoqiang on 2019/8/3.
 */

public class SelectServerActivity extends BaseActivity implements GetCityContract.View,ServerTypeContract.View,ServerListContract.View{

    @BindView(R.id.ll_location)
    LinearLayout llLocation;
    @BindView(R.id.ll_server_type)
    LinearLayout llServerType;

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

    GetCityPresenter cityPresenter;
    ServerTypePresenter sTypePresenter;
    ServerListPresenter sListPresenter;

    List<PopupSelectModel> citys,sTypes;

    ServerListPop serverListPop;

    int locationId = -1;
    int serverTypeId = -1;
    int serverStatusId = 1;

    public static final int LOCATION_POSITION = 0;
    public static final int SERVER_TYPE_POSITION = 1;

    ServerListAdapter2 mAdapter;
    LoadMoreWrapper loadMoreWrapper;
    int page;
    int isLoadType = 1;//1下拉刷新，2上拉加载
    boolean isLoad = false;//是否在加载，重复加载问题

    @Override
    protected int getLayoutId() {
        return R.layout.activity_select_server;
    }

    @Override
    protected void initView() {
        initRecycler();
        initSwipeLayout();

        initData();
    }

    //初始化recyclerview
    private void initRecycler() {
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        mAdapter = new ServerListAdapter2(context, new ArrayList<ServerListModel>());
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

        mAdapter.setOnItemClickListener(new ServerListAdapter2.OnItemClickListener() {
            @Override
            public void onItemClick(ServerListModel model) {
                SendServerEvent entity = new SendServerEvent();
                entity.setServerId(model.getId());
                entity.setServerImg(model.getTitleImgStr());
                entity.setServerArea(model.getAddress());
                entity.setServerName(model.getTitle());
                entity.setServerPrice(model.getServePriceStr());
                RxBus2.getInstance().post(entity);
                finish();
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
        cityPresenter.cityGetCity();
        swipeRefreshLayout.setRefreshing(true);
        isLoad = true;
        page = Constant.DEFAULT_PAGE;
        isLoadType = 1;
        sListPresenter.serveList(Constant.DEFAULT_PAGE,Constant.DEFAULT_LIMIT,""+locationId,""+serverTypeId,""+serverStatusId,"");
    }

    private void getMoreData() {
        isLoad = true;
        page = page + 1;
        isLoadType = 2;
        sListPresenter.serveList(page,Constant.DEFAULT_LIMIT,""+locationId,""+serverTypeId,""+serverStatusId,"");
    }

    private void initData() {
        cityPresenter = new GetCityPresenter(context,this);
        sTypePresenter = new ServerTypePresenter(context,this);
        sListPresenter = new ServerListPresenter(context,this);

        citys = new ArrayList<>();
        sTypes = new ArrayList<>();

        sTypePresenter.getServeDicList(false);//服务类型
        getRefreshData();
    }

    @OnClick({R.id.ll_location, R.id.ll_server_type})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_location:
                showPop(citys,LOCATION_POSITION);
                break;
            case R.id.ll_server_type:
                showPop(sTypes,SERVER_TYPE_POSITION);
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
        }
    }

    private void changeTopTextColor(int position){
        if(position==LOCATION_POSITION){
            tvLocation.setTextColor(ContextCompat.getColor(context,R.color.color_theme));
        }else if(position==SERVER_TYPE_POSITION){
            tvServerType.setTextColor(ContextCompat.getColor(context,R.color.color_theme));
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
    }

    @Override
    public void serveListFailed(String msg) {
        showShortToast(msg);
        isLoad = false;
        swipeRefreshLayout.setRefreshing(false);
        loadMoreWrapper.setLoadState(loadMoreWrapper.LOADING_COMPLETE);
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

}
