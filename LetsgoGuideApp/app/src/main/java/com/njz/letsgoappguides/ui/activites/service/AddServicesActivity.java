package com.njz.letsgoappguides.ui.activites.service;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.njz.letsgoappguides.Bean.MySelfInfo;
import com.njz.letsgoappguides.R;
import com.njz.letsgoappguides.base.BaseActivity;
import com.njz.letsgoappguides.constant.Constant;
import com.njz.letsgoappguides.customview.widget.LinearServiceView;
import com.njz.letsgoappguides.customview.widget.MineTextView;
import com.njz.letsgoappguides.customview.widget.TextServiceView;
import com.njz.letsgoappguides.customview.widget.calendarDecorators.PriceModel;
import com.njz.letsgoappguides.model.login.GuideMacroEntityList;
import com.njz.letsgoappguides.model.mine.BatchUploadInfo;
import com.njz.letsgoappguides.model.server.AutoServiceModel;
import com.njz.letsgoappguides.model.server.CityModel;
import com.njz.letsgoappguides.model.server.GetServeDicListModel;
import com.njz.letsgoappguides.model.server.GetServiceCityModel;
import com.njz.letsgoappguides.model.server.GetUpdateServiceInfo;
import com.njz.letsgoappguides.model.server.PurchaseRulesVo;
import com.njz.letsgoappguides.model.server.ServerTypeModel;
import com.njz.letsgoappguides.model.server.ServicePreviewInfo;
import com.njz.letsgoappguides.presenter.mine.GetLanguageContract;
import com.njz.letsgoappguides.presenter.mine.GetLanguagePresenter;
import com.njz.letsgoappguides.presenter.other.BatchUploadContract;
import com.njz.letsgoappguides.presenter.other.BatchUploadPresenter;
import com.njz.letsgoappguides.presenter.server.AddServiceContract;
import com.njz.letsgoappguides.presenter.server.AddServicePresenter;
import com.njz.letsgoappguides.presenter.server.GetCityContract;
import com.njz.letsgoappguides.presenter.server.GetCityPresenter;
import com.njz.letsgoappguides.presenter.server.GetUpdateServInfoContract;
import com.njz.letsgoappguides.presenter.server.GetUpdateServInfoPresenter;
import com.njz.letsgoappguides.presenter.server.ServerTypeContract;
import com.njz.letsgoappguides.presenter.server.ServerTypePresenter;
import com.njz.letsgoappguides.util.AppUtils;
import com.njz.letsgoappguides.util.DateUtil;
import com.njz.letsgoappguides.util.ServiceUtil;
import com.njz.letsgoappguides.util.StringUtils;
import com.njz.letsgoappguides.util.accessory.ImageUtils;
import com.njz.letsgoappguides.util.accessory.PhotoAdapter;
import com.njz.letsgoappguides.util.accessory.RecyclerItemClickListener;
import com.njz.letsgoappguides.util.dialog.LoadingDialog;
import com.njz.letsgoappguides.util.photo.TackPicturesUtil;
import com.njz.letsgoappguides.util.rxbus.RxBus2;
import com.njz.letsgoappguides.util.rxbus.busEvent.ServiceTypeEvent;
import com.njz.letsgoappguides.util.rxbus.busEvent.UpLoadPhotos;
import com.njz.letsgoappguides.util.thread.MyThreadPool;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import me.iwf.photopicker.PhotoPicker;
import me.iwf.photopicker.PhotoPreview;


/**
 * 新增服务
 */
public class AddServicesActivity extends BaseActivity implements ServerTypeContract.View,
        BatchUploadContract.View,
        GetLanguageContract.View,
        AddServiceContract.View,
        GetCityContract.View,
        GetUpdateServInfoContract.View {

    @BindView(R.id.title_tv)
    TextView titleTv;
    ServerTypePresenter sTypePresenter;
    @BindView(R.id.left_iv)
    ImageView leftIv;
    @BindView(R.id.title_layout)
    RelativeLayout titleLayout;
    @BindView(R.id.service_type)
    MineTextView serviceType;
    @BindView(R.id.service_title)
    MineTextView serviceTitle;
    @BindView(R.id.service_price)//私人订制价格
    MineTextView servicePrice;
    @BindView(R.id.tv_service_lan)
    TextView tvServiceLan;
    @BindView(R.id.recycler_view)
    RecyclerView mPhotoRecyclerView;
    @BindView(R.id.ll_vai_text)
    LinearLayout ll_vai_text;
    @BindView(R.id.ll_service_notime)
    MineTextView llServiceNotime;
    @BindView(R.id.ll_service_ind)
    MineTextView llServiceInd;
    @BindView(R.id.ll_service_city)
    MineTextView llServiceCity;
    @BindView(R.id.tv_qianming)
    TextView tvQianming;
    @BindView(R.id.ll_service_priceinfo)
    EditText llServicePriceinfo;
    @BindView(R.id.serve_btn_save)
    TextView serveBtnSave;
    @BindView(R.id.serve_btn_save_sub)
    TextView serveBtnSaveSub;
    @BindView(R.id.tv_textid)
    TextView tv_textid;
    @BindView(R.id.tv_textid2)
    TextView tv_textid2;
    @BindView(R.id.edit_text_1)
    TextView edit_text1;
    @BindView(R.id.edit_text_2)
    EditText edit_text2;
    @BindView(R.id.edit_text_3)
    TextView edit_text3;
    @BindView(R.id.edit_text_4)
    EditText edit_text4;
    //    @BindView(R.id.ll_car_id1)
//    LinearLayout llPCarId1;
    @BindView(R.id.ll_langage_id1)
    LinearLayout llLangageId1;
    @BindView(R.id.ll_wai_id)
    LinearLayout llWaiId;
    @BindView(R.id.ll_penalty)
    LinearLayout llPenalty;//违约金
    @BindView(R.id.ll_isCheckCar)
    RelativeLayout ll_IsCheckCar;//选中自驾车
    @BindView(R.id.iv_isagreement)
    ImageView iv_isagreement;
    @BindView(R.id.ll_isagreement)
    LinearLayout ll_isagreement;

    boolean isSelect=false;//向导陪游是否选中自驾车

    List<String> sTypes;
    List<String> langages;
    List<ServerTypeModel> serverTypeModels;
    List<GuideMacroEntityList> langageTypeModels;
    int serverTypeId = -1;
    int langagesId = -1;

    LoadingDialog loadingDialog;
    private Disposable disposable;
    private PhotoAdapter photoAdapter;
    private ArrayList<String> selectedPhotos = new ArrayList<>();
    private ArrayList<String> upLoadPhotos = new ArrayList<>();
    String upUrls = "";
    private List<String> list=new ArrayList<>();

    //------------城市------------
    List<String> listModels=new ArrayList<>();
    List<List<String>> listModelsChildrens=new ArrayList<>();
    List<List<List<String>>> listModelsChildrensbean=new ArrayList<>();
    List<GetServiceCityModel> listCity=new ArrayList<>();

    int cityTypeId=0;
    //-------------end----------------


    List<LinearServiceView> linears=new ArrayList<>();
    List<AutoServiceModel.NjzGuideServeFormatDtosBean> serveFormatList=new ArrayList<>();
    List<GetServeDicListModel.NjzGuideServeDicVoListBean> getServeDicVoListisTrue=new ArrayList<>();

    String myFeature="";//服务特色

    BatchUploadPresenter mBatchUploadPresenter;
    GetLanguagePresenter mGetLanguagePresenter;
    AddServicePresenter mAddServicePresenter;
    GetCityPresenter mGetCityPresenter;
    GetUpdateServInfoPresenter mGetUpdateServInfoPresenter;

    int serviceId;
    int toId;

    List<List<GetUpdateServiceInfo.ServeGroupListInfo>> infos1=new ArrayList<>();//得到详情值
    int saleNum=0;
    float minprice;

    ArrayList<String> notimeDate=new ArrayList<>();

    int uniqueId=0;
    List<PriceModel> priceCalendar=new ArrayList<>();

    @Override
    protected void initView() {
        titleTv.setText("新增服务");

        serviceId=getIntent().getIntExtra("serviceTypeID",0);//
        toId=getIntent().getIntExtra("ID",0);//1、新增   2、修改

        initAddPhoto();//先执行
        initDate();

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_services;
    }

    //----------服务类型选择 start
    Disposable serviceTypeDisposable;
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(serviceTypeDisposable != null && !serviceTypeDisposable.isDisposed())
            serviceTypeDisposable.dispose();
        if(loadingDialog!=null){
            loadingDialog.dismiss();
        }
    }

    public void initServiceTypeDisposable(){
        serviceTypeDisposable = RxBus2.getInstance().toObservable(ServiceTypeEvent.class, new Consumer<ServiceTypeEvent>() {
            @Override
            public void accept(ServiceTypeEvent serviceTypeEvent) throws Exception {
                if (getServeDicVoListisTrue.size() > 0) {
                    serviceType.setEditContent(serviceTypeEvent.getModel().getName());
                    serverTypeId = serviceTypeEvent.getModel().getId();
                    List<GetServeDicListModel.NjzGuideServeDicVoListBean.NjzGuideServeFormatDicVosBean> getServeFormatDicVosBeanList=serviceTypeEvent.getModel().getNjzGuideServeFormatDicVos();
                    int getServeFormatDicVosSize=getServeFormatDicVosBeanList.size();
                    ll_vai_text.removeAllViews();
                    llWaiId.removeAllViews();
                    linears.clear();
                    serveFormatList.clear();
                    for (int i=0;i<getServeFormatDicVosSize;i++){
                        GetServeDicListModel.NjzGuideServeDicVoListBean.NjzGuideServeFormatDicVosBean getServeFormatDicVosBean= getServeFormatDicVosBeanList.get(i);
                        if(getServeFormatDicVosBean.isIsPower()){//当前用户是否用上传该规格的权限
                            String formatName=getServeFormatDicVosBean.getFormatName();
                            String uniqueValue=getServeFormatDicVosBean.getUniqueValue();
                            if(getServeFormatDicVosBean.isIsRequisite()) {//去掉删除按钮
                                int isDelete = 1;
                                initAddLayout(formatName, uniqueValue, isDelete);
                            }
                            initTextLayout(formatName,uniqueValue);
                        }
                    }
                }
                if(serverTypeId==3){//私人订制
                    servicePrice.setInputTypes(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);//小数点和整数
                    servicePrice.setVisibility(View.VISIBLE);//价格
                    llPenalty.setVisibility(View.GONE);//违约金
                    llServiceNotime.setVisibility(View.GONE);
                }else{
                    servicePrice.setVisibility(View.GONE);
                    llPenalty.setVisibility(View.VISIBLE);
                    llServiceNotime.setVisibility(View.VISIBLE);
                }
            }
        });
    }
    //----------服务类型选择 end

    public void initDate() {
        sTypes = new ArrayList<>();
        serverTypeModels = new ArrayList<>();
        langages = new ArrayList<>();
        langageTypeModels = new ArrayList<>();
        mBatchUploadPresenter = new BatchUploadPresenter(context, this);
        mGetLanguagePresenter = new GetLanguagePresenter(context, this);
        mAddServicePresenter = new AddServicePresenter(context, this);
        mGetCityPresenter=new GetCityPresenter(context,this);
        sTypePresenter = new ServerTypePresenter(context, this);
        mGetUpdateServInfoPresenter=new GetUpdateServInfoPresenter(context,this);
        loadingDialog = new LoadingDialog(context);

        if(MySelfInfo.getInstance().isLogin()){
            if(MySelfInfo.getInstance().getServiceIden()==0){//只在登录之后第一次获取服务语言、城市
                mGetLanguagePresenter.userGetLanguage();
                mGetCityPresenter.getServiceCityList();
            }
        }


        if(toId==2){//修改
            titleTv.setText("修改服务");
            mGetUpdateServInfoPresenter.getUpdateServInfo(serviceId);
        }else if(toId==1){
            sTypePresenter.getServeDicList(true);
        }else if(toId==3){
            titleTv.setText("新增服务");
            mGetUpdateServInfoPresenter.getUpdateServInfo(serviceId);
        }

        initServiceTypeDisposable();
        //服务类型
        serviceType.setFocusables(false, new View.OnClickListener() {//设置可点击但是不可编辑
            @Override
            public void onClick(View view) {
                AppUtils.HideKeyboard(serviceType);
                if(toId==1||toId==3){
                    uniqueId=0;
                    Intent intent = new Intent(context, ServiceTypeActivity.class);
                    intent.putExtra("SERVER_TYPE",serverTypeId);
                    startActivity(intent);
                }else{
                    Toast.makeText(context,"修改服务不能修改服务类型",Toast.LENGTH_SHORT).show();
                }

            }
        });

        llServiceInd.setFocusables(false, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,ServicesFeatureActivity.class);
                intent.putExtra("ID",101);
                intent.putExtra("myFeature",myFeature);
                startActivityForResult(intent,101);
            }
        });

        llServiceCity.setFocusables(false, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    AppUtils.HideKeyboard(llServiceCity);
                    showPickerView();
            }
        });

        llServiceNotime.setFocusables(false, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,CalendarActivity.class);
                if(notimeDate.size()>0){
                    intent.putStringArrayListExtra(Constant.CALENDARNOTIME,notimeDate);
                }
                startActivityForResult(intent, Constant.CALENDARID);
            }
        });

        edit_text1.setEnabled(false);
        edit_text3.setEnabled(false);
        edit_text4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(TextUtils.isEmpty(edit_text4.getText().toString())) return;
                if(Integer.valueOf(edit_text4.getText().toString())>10){
                    edit_text4.setText("");
                    showShortToast("天数不能超过10日");
                }
                if(TextUtils.isEmpty(edit_text4.getText().toString())) return;
                if(Integer.valueOf(edit_text4.getText().toString())<=10){
                    edit_text1.setText(Integer.valueOf(edit_text4.getText().toString())+1+"");
                }
            }
        });

        edit_text2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(TextUtils.isEmpty(edit_text2.getText().toString())) return;
                if(Integer.valueOf(edit_text2.getText().toString())>15){
                    edit_text2.setText("");
                    showShortToast("天数不能超过15日");
                }
            }
        });
    }


    @OnClick({R.id.left_iv})
    public void bottonClick() {
        new AlertDialog.Builder(context).setTitle("您确定要放弃此次编辑？").setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        finish();
                    }
                }).setNegativeButton("取消", null).show();
    }


    public boolean checkYulanName(){
        if(serviceType.getEditContent().equals("")){
            showShortToast("请选择服务类型");
            return false;
        }else if(serviceTitle.getEditContent().equals("")) {
            showShortToast("请输入标题");
            return false;
        }
        return true;
    }

    public boolean checkName(){
        if(serviceType.getEditContent().equals("")){
            showShortToast("请选择服务类型");
            return false;
        }else if(serviceTitle.getEditContent().equals("")) {
            showShortToast("请输入标题");
            return false;
        } else if(upUrls.equals("")){
            showShortToast("请上传标题图");
            return false;
        }
        for (int a=0;a<linears.size();a++){
            String con=linears.get(a).getContent();
            String con2=linears.get(a).getContent2();
            if(con.equals("")){
                showShortToast(linears.get(a).getHint());
                return false;
            }else if(con2.equals("")){
                showShortToast(linears.get(a).getHint2());
                return false;
            }else if(!ServiceUtil.getPriceCheck(con2,context)){
                return ServiceUtil.getPriceCheck(con2,context);
            }
        }
        if(serverTypeId==3) {//私人订制
            String context2=servicePrice.getEditContent().trim();
            if(!ServiceUtil.getPriceCheck(context2,context)){
                return false;
            }
        }
        if(llServiceCity.getEditContent().equals("")){
            showShortToast("请选择城市");
            return false;
        }else if(myFeature.equals("")){
            showShortToast("请输入服务特色");
            return false;
        }else if(llServicePriceinfo.getText().toString().equals("")){
            showShortToast("请输入费用说明");
            return false;
        }else if(edit_text1.getText().toString().equals("")
                ||edit_text2.getText().toString().equals("")
                ||edit_text3.getText().toString().equals("")
                ||edit_text4.getText().toString().equals("")){
            showShortToast("请输入违约金参数");
            return false;
        }else if(Integer.valueOf(edit_text2.getText().toString())<Integer.valueOf(edit_text1.getText().toString())){
            showShortToast("违约金参数设置不正确");
            return false;
        }else if(Integer.valueOf(edit_text4.getText().toString())<Integer.valueOf(edit_text3.getText().toString())){
            showShortToast("违约金参数设置不正确");
            return false;
        }else if(Integer.valueOf(edit_text4.getText().toString())<=1){
            showShortToast("违约金参数设置不正确");
            return false;
        }else{
            return true;
        }
    }

    @OnClick(R.id.title_yulan)
    public void yulan(){
        if(checkYulanName()) {
            setServeFormatList();
            minprice=ServiceUtil.getMinPrices(serveFormatList,serverTypeId);
            Intent intent = new Intent(context, ServicePreviewActivity.class);
            ServicePreviewInfo model=new ServicePreviewInfo();
            model.setTitleImg(upUrls);
            model.setServeFeature(myFeature);
            model.setServeType(serverTypeId);
            model.setTitle(serviceTitle.getEditContent());
            model.setRenegePriceThree(edit_text1.getText().toString()+","+edit_text2.getText().toString());//违约金参数
            model.setRenegePriceFive(edit_text3.getText().toString()+","+edit_text4.getText().toString());// 违约金参数
            model.setAddress(llServiceCity.getEditContent());
            model.setAddressId(cityTypeId);
            model.setServeTypeName(serviceType.getEditContent());
            model.setSaleNum(saleNum);
            model.setServePrice(minprice);
            if(serverTypeId==3){//私人订制
                model.setServePrice(Float.valueOf(servicePrice.getEditContent()));
            }
            model.setCostExplain(llServicePriceinfo.getText().toString());
            intent.putExtra("ServicePreviewInfo",model);
            intent.putExtra("PREVIEWID",Constant.PREVIEWID);
            startActivity(intent);
        }
    }

    public void setServeFormatList(){//服务规格
        for (int i=0;i<linears.size();i++){
            String serveFormatName=linears.get(i).getContent();//规格名称
            String serveFPrice=linears.get(i).getContent2();//规格默认价格

            if(toId==3){//复制
                serveFormatList.get(i).setId(null);
            }else{
                if(!linears.get(i).getIdContent().equals("")){//修改
                    serveFormatList.get(i).setId(Long.parseLong(linears.get(i).getIdContent()));
                }
            }

            serveFormatList.get(i).setGuideServeFormatName(serveFormatName);
            if(serveFPrice.equals("")){
                serveFormatList.get(i).setServeDefaultPrice(0);
            }else{
                serveFormatList.get(i).setServeDefaultPrice(Float.valueOf(serveFPrice));
            }

        }
    }

    public void setPriceCalender(List<PriceModel> priceCalendar,int uniqueId){//日历价格
        List<AutoServiceModel.NjzGuideServeFormatDtosBean.NjzGuideServeFormatPriceEntities> list3=new ArrayList<>();
        //----------------价格日历
        for (int s=0;s<priceCalendar.size();s++){
            AutoServiceModel.NjzGuideServeFormatDtosBean.NjzGuideServeFormatPriceEntities list4=new AutoServiceModel.NjzGuideServeFormatDtosBean.NjzGuideServeFormatPriceEntities();
            PriceModel priceModel=priceCalendar.get(s);
            list4.setYearInt(Integer.parseInt(priceModel.getYear()));
            list4.setMonthInt(Integer.parseInt(priceModel.getMonth()));
            list4.setDateInt(Integer.parseInt(priceModel.getDay()));
            list4.setPrice(Float.valueOf(priceModel.getPrice()));
            list3.add(list4);
        }
        if(toId==1){//增加
            serveFormatList.get(uniqueId-1).setServePriceSelect(list3);
        }else if(toId==2||toId==3){//修改
            for (int i=0;i<linears.size();i++){
                if(!linears.get(i).getIdContent().equals("")){
                    String id=linears.get(i).getIdContent();
                    if(id.equals(uniqueId+"")){
                        serveFormatList.get(i).setServePriceSelect(list3);
                    }
                }else{
                    serveFormatList.get(uniqueId-1).setServePriceSelect(list3);
                }
            }
        }


        //-------------------

    }

    @OnClick({R.id.serve_btn_save, R.id.serve_btn_save_sub})
    public void saveOnclick(View view) {
        if(checkName()){
            setServeFormatList();
            minprice=ServiceUtil.getMinPrices(serveFormatList,serverTypeId);
            AutoServiceModel model=new AutoServiceModel();//标题图
            model.setTitleImg(upUrls);
            model.setServeFeature(myFeature);
            model.setServeType(serverTypeId);
            model.setNjzGuideServeFormatDtos(serveFormatList);
            model.setTitle(serviceTitle.getEditContent());
            model.setRenegePriceThree(edit_text1.getText().toString()+","+edit_text2.getText().toString());//违约金参数
            model.setRenegePriceFive(edit_text3.getText().toString()+","+edit_text4.getText().toString());// 违约金参数
            model.setAddress(llServiceCity.getEditContent());
            model.setAddressId(cityTypeId);
            model.setServeTypeName(serviceType.getEditContent());

            //-----------非空闲时间----------
            List<AutoServiceModel.NjzGuideServeNoTimeEntitiesBean> list=new ArrayList<>();
            AutoServiceModel.NjzGuideServeNoTimeEntitiesBean lists2;
            if(notimeDate.size()>0){
                for (int i=0;i<notimeDate.size();i++){
                    lists2=new AutoServiceModel.NjzGuideServeNoTimeEntitiesBean();
                    String date=notimeDate.get(i).toString();
                    lists2.setDateValue(date);
                    list.add(lists2);
                }
            }
            model.setNjzGuideServeNoTimeEntities(list);
            model.setCostExplain(llServicePriceinfo.getText().toString());//费用说明
            model.setServeMaxNum(1);
            model.setServeMinNum(1);
            model.setServePrice(minprice);
            if(serverTypeId==3){//私人订制
                model.setServePrice(Float.valueOf(servicePrice.getEditContent()));
            }
            //----------自驾车传参------------start
            if(isSelect){
                List<PurchaseRulesVo> purchaseRules=new ArrayList<>();
                PurchaseRulesVo purchaseRulesVo=new PurchaseRulesVo();
                purchaseRulesVo.setRuleClassName("QuantitativeRulesHandle");
                purchaseRulesVo.setUniqueValue("xdpy_cx");
                int[] data = {0,1};
                purchaseRulesVo.setEntityList(data);
                purchaseRules.add(purchaseRulesVo);
                String purchaseRulesString=purchaseRules.toString();
                model.setPurchaseRules(purchaseRulesString);
            }
            //----------自驾车传参------------end

            if(toId==2){//修改
                model.setId(serviceId);//服务ID
                switch (view.getId()) {
                    case R.id.serve_btn_save:
                        mGetUpdateServInfoPresenter.updaServiceInfo(model);
                        break;
                    case R.id.serve_btn_save_sub:
                        if(serveBtnSaveSub.getText().equals("修改并上架")){
                        model.setStatus(new Long((long)1));
                    }else{
                        model.setStatus(new Long((long)0));
                    }
                        mGetUpdateServInfoPresenter.updaServiceInfo(model);
                        break;
                }
            }else if(toId==1||toId==3){
                switch (view.getId()) {
                    case R.id.serve_btn_save:
                        model.setStatus(new Long((long)0));
                        mAddServicePresenter.addService(model);
                        break;
                    case R.id.serve_btn_save_sub:
                        model.setStatus(new Long((long)1));
                        mAddServicePresenter.addService(model);
                        break;
                }
            }

            Log.e("test","提交的值"+model.toString());
        }
    }

    public void initTextLayout(final String formatName,final String uniqueValue){
        TextServiceView mTextServiceView=new TextServiceView(context);
        mTextServiceView.setContent("添加"+formatName);
        mTextServiceView.setOnclick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int isDelete=0;
                initAddLayout(formatName,uniqueValue,isDelete);
            }
        });
        ll_vai_text.addView(mTextServiceView);
    }

    public void initAddLayout(String formatName,String uniqueValue,int isDelete){
        uniqueId++;
        final LinearServiceView mLinearServiceView=new LinearServiceView(context);
        if(isDelete==1){// 1、隐藏删除按钮
            mLinearServiceView.setDeleteBtnVisibility(View.GONE);
        }else{
            mLinearServiceView.setDeleteBtnVisibility(View.VISIBLE);
        }
        if(uniqueValue.equals("xdpy_cx")){
            ll_IsCheckCar.setVisibility(View.VISIBLE);
            setIv_isagreement();
        }else{
            getisCarType();
        }
        mLinearServiceView.setId(uniqueId);
        mLinearServiceView.setTitleContent(formatName);
        mLinearServiceView.setTitle2Content("价格");
        mLinearServiceView.setEditHint1("请输入"+formatName);
        if(formatName.equals("服务语言")) {
            mLinearServiceView.setBtnVisibility(View.VISIBLE);
            langageTypeModels=MySelfInfo.getInstance().getServiceMacroEntityList();
            langages=MySelfInfo.getInstance().getServiceLangages();
            if(langageTypeModels!=null&&langageTypeModels.size()>0&&langages!=null){
                mLinearServiceView.setContent(langageTypeModels.get(0).getName());
                langagesId = langageTypeModels.get(0).getId();
                mLinearServiceView.setOnclick(false, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AppUtils.HideKeyboard(mLinearServiceView);
                        //条件选择器
                        OptionsPickerView pvOptions = new OptionsPickerBuilder(context,
                                new OnOptionsSelectListener() {
                                    @Override
                                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                                        if (langageTypeModels.size() > 0) {
                                            mLinearServiceView.setContent(langageTypeModels.get(options1).getName());
                                            langagesId = langageTypeModels.get(options1).getId();
                                        }
                                    }
                                }).build();
                        pvOptions.setPicker(langages, null, null);
                        pvOptions.show();
                    }
                });
            }
        }
        mLinearServiceView.setButtonOnListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                priceCalendar.clear();
                if(ServiceUtil.getPriceCheck(mLinearServiceView.getContent2(),context)){
                    priceCalendar=getIdPrice(mLinearServiceView.getId()-1);
                    if(toId==2||toId==3){//修改
                        Intent intent=new Intent(context,PriceCalendarActivity.class);
                        if(!mLinearServiceView.getIdContent().equals("")){
                            intent.putExtra("uniqueId",Integer.parseInt(mLinearServiceView.getIdContent()));//规格ID
                            intent.putExtra("uniquevalue","标识");
                        }else{
                            intent.putExtra("uniqueId",mLinearServiceView.getId());
                            intent.putExtra("uniquevalue","");
                        }
                        intent.putParcelableArrayListExtra(Constant.PRICECALENDERINFO, (ArrayList) priceCalendar);
                        if(!mLinearServiceView.getContent2().equals("")){
                            intent.putExtra(Constant.DEFAULT_PRICE,mLinearServiceView.getContent2());
                        }
                        intent.putExtra("TOID",toId);
                        startActivityForResult(intent,Constant.DEFAULT_PRICEID);
                    }else if(toId==1){
                        Intent intent=new Intent(context,PriceCalendarActivity.class);
                        intent.putExtra("uniqueId",mLinearServiceView.getId());
                        if(!mLinearServiceView.getContent2().equals("")){
                            intent.putExtra(Constant.DEFAULT_PRICE,mLinearServiceView.getContent2());
                        }
                        intent.putExtra("TOID",toId);
                        intent.putParcelableArrayListExtra(Constant.PRICECALENDERINFO, (ArrayList) priceCalendar);
                        startActivityForResult(intent,Constant.DEFAULT_PRICEID);
                    }
                }
            }
        });
        final AutoServiceModel.NjzGuideServeFormatDtosBean serveFormatDtosBean=new AutoServiceModel.NjzGuideServeFormatDtosBean();
        serveFormatDtosBean.setNjzGuideServeFormatDic(uniqueValue);
        serveFormatList.add(serveFormatDtosBean);
        mLinearServiceView.setOnclickDelete(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uniqueId--;
                serveFormatList.remove(serveFormatDtosBean);
                linears.remove(mLinearServiceView);
                llWaiId.removeView(mLinearServiceView);
                getisCarType();
            }
        });
        linears.add(mLinearServiceView);
        llWaiId.addView(mLinearServiceView);

    }

    public void getisCarType(){
        boolean isCarType=false;
        for (int i=0;i<serveFormatList.size();i++){
            if(serveFormatList.get(i).getNjzGuideServeFormatDic().equals("xdpy_cx")){
                isCarType=true;
            }
        }
        if (!isCarType){
            ll_IsCheckCar.setVisibility(View.GONE);
            isSelect=false;
        }
    }

    @OnClick(R.id.ll_isagreement)
    public void isagreement(){
        if (isSelect) {
            iv_isagreement.setImageDrawable(ContextCompat.getDrawable(context,R.mipmap.no_check));
            isSelect=false;
        }else{
            iv_isagreement.setImageDrawable(ContextCompat.getDrawable(context,R.mipmap.reg_check));
            isSelect=true;
        }
    }

    public void setIv_isagreement(){
        if (isSelect) {
            iv_isagreement.setImageDrawable(ContextCompat.getDrawable(context,R.mipmap.reg_check));
        }else{
            iv_isagreement.setImageDrawable(ContextCompat.getDrawable(context,R.mipmap.no_check));
        }
    }


    public List<PriceModel> getIdPrice(int id){
        if(serveFormatList.get(id).getServePriceSelect()!=null){
            List<AutoServiceModel.NjzGuideServeFormatDtosBean.NjzGuideServeFormatPriceEntities> list=serveFormatList.get(id).getServePriceSelect();
            if(list.size()>0){
                for (int i=0;i<list.size();i++){
                    PriceModel priceModel=new PriceModel();
                    priceModel.setPrice(list.get(i).getPrice()+"");
                    priceModel.setTime(list.get(i).getYearInt()+"-"+ DateUtil.getWith0(list.get(i).getMonthInt())+"-"+DateUtil.getWith0(list.get(i).getDateInt()));
                    priceCalendar.add(priceModel);
                }
            }
        }
        return priceCalendar;
    }


    //---------服务类型---- start----
    @Override
    public void serveGetServeSuccess(List<ServerTypeModel> datas) {

    }

    @Override
    public void serveGetServeFailed(String msg) {

    }

    @Override
    public void getServeDicListSuccess(GetServeDicListModel datas) {
        if (datas != null) {
            if(serviceType == null) return;
            String serviceName=serviceType.getEditContent();

            if(datas.getNjzGuideServeDicVoList()!=null){
                List<GetServeDicListModel.NjzGuideServeDicVoListBean> getServeDicVoList=datas.getNjzGuideServeDicVoList();
                for (int i = 0; i < getServeDicVoList.size(); i++) {
                    if(getServeDicVoList.get(i).isIsShow()){
                        getServeDicVoListisTrue.add(getServeDicVoList.get(i));
                        String name = getServeDicVoList.get(i).getName();
                        sTypes.add(name);
                    }
                }
                //---服务类型 start----------
                int isshowdelete=0;
                for (int a=0;a<getServeDicVoList.size();a++){
                    if(toId==2||toId==3){//修改
                        if(getServeDicVoList.get(a).getName().equals(serviceName)){
                            List<GetServeDicListModel.NjzGuideServeDicVoListBean.NjzGuideServeFormatDicVosBean> info=getServeDicVoList.get(a).getNjzGuideServeFormatDicVos();
                            if(info.size()>0){
                                String name="";
                                float defaultPrice=0;
                                for (int v=0;v<infos1.size();v++){//初始化更新的组件
                                        List<GetUpdateServiceInfo.ServeGroupListInfo> info2=infos1.get(v);
                                        for (GetUpdateServiceInfo.ServeGroupListInfo infoq: info2) {
                                            for (int c=0;c<info.size();c++){
                                                if(info.get(c).getUniqueValue().equals(infoq.getNjzGuideServeFormatDic())){
                                                    int isDelete=0;
                                                    if(info.get(c).isIsRequisite()) {
                                                        if(isshowdelete==0){
                                                            isDelete=1;
                                                        }else{
                                                            isDelete=0;
                                                        }
                                                        initAddLayout(info.get(c).getFormatName(),info.get(c).getUniqueValue(),isDelete);
                                                        isshowdelete++;
                                                    }else{
                                                        isDelete=0;
                                                        initAddLayout(info.get(c).getFormatName(),info.get(c).getUniqueValue(),isDelete);
                                                    }
//                                                    serverTypeId=info.get(c).getId();
                                                }
                                            }
                                        }
                                }
                                for (int c=0;c<info.size();c++){
                                    if(info.get(c).isIsPower()) {//是否可以上传该规格
                                        initTextLayout(info.get(c).getFormatName(), info.get(c).getUniqueValue());
                                    }
                                }
                                int o=0;
                                for (List<GetUpdateServiceInfo.ServeGroupListInfo> in:
                                infos1) {
                                    for (GetUpdateServiceInfo.ServeGroupListInfo infoq: in) {
                                        if(linears.size()>0){
                                            LinearServiceView views=linears.get(o);
                                            name=infoq.getGuideServeFormatName();//名称
                                            defaultPrice=infoq.getServeDefaultPrice();//默认价格
                                            int serveGroupId=infoq.getId();
                                            views.setContent(name);
                                            views.setContent2(""+defaultPrice);
                                            views.setIdContent(""+serveGroupId);
                                            o++;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                //---服务类型 end----------
                if(getServeDicVoListisTrue.size()>0){
                    int dicvos=getServeDicVoListisTrue.get(0).getNjzGuideServeFormatDicVos().size();
                    if(dicvos>0){
                        for (int s=0;s<dicvos;s++){
                            if(toId==1){//新增
                                GetServeDicListModel.NjzGuideServeDicVoListBean listTrue= getServeDicVoListisTrue.get(0);
                                GetServeDicListModel.NjzGuideServeDicVoListBean.NjzGuideServeFormatDicVosBean listDicVosBean= listTrue.getNjzGuideServeFormatDicVos().get(s);
                                serviceType.setEditContent(listTrue.getName());
                                serverTypeId=listTrue.getId();
                                if(listTrue.getNjzGuideServeFormatDicVos().get(s).isIsPower()){
                                    String formatName=listDicVosBean.getFormatName();
                                    String uniqueValue=listDicVosBean.getUniqueValue();
                                    if(listTrue.getNjzGuideServeFormatDicVos().get(s).isIsRequisite()){//必须上传该规格  隐藏删除按钮
                                        int isDelete=1;
                                        initAddLayout(formatName,uniqueValue,isDelete);
                                    }
                                    initTextLayout(formatName,uniqueValue);
                                }
                            }
                        }
                    }
                }
            }
            if(serverTypeId==3){//私人订制
                servicePrice.setInputTypes(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);//小数点和整数
                servicePrice.setVisibility(View.VISIBLE);//价格
                llPenalty.setVisibility(View.GONE);//违约金
                llServiceNotime.setVisibility(View.GONE);
            }else{
                servicePrice.setVisibility(View.GONE);
                llPenalty.setVisibility(View.VISIBLE);
                llServiceNotime.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void getServeDicListFailed(String msg) {

    }

    //---------服务类型---- end----


    //---------------语言---------start


    @Override
    public void getCheckLanguageSuccess(List<GuideMacroEntityList> datas) {
    }

    @Override
    public void getCheckLanguageFailed(String msg) {

    }
    @Override
    public void userGetLanguageSuccess(List<GuideMacroEntityList> datas) {
        if(MySelfInfo.getInstance().isLogin()) {
            if (MySelfInfo.getInstance().getServiceIden() == 0) {
                if (datas != null) {
                    for (int i = 0; i < datas.size(); i++) {
                        String name = datas.get(i).getName();
                        langages.add(name);
                    }
                    MySelfInfo.getInstance().setServiceLangages(langages);
                    MySelfInfo.getInstance().setServiceMacroEntityList(datas);
                    MySelfInfo.getInstance().setServiceIden(1);
                }
            }
        }

    }


    @Override
    public void userGetLanguageFailed(String msg) {

    }
    //---------------语言---------end


    //----------------end 多图片上传------------
    private void initAddPhoto() {
        //------------附件
        photoAdapter = new PhotoAdapter(context, selectedPhotos);
        mPhotoRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(PhotoAdapter.IMAGE_LINE, OrientationHelper.VERTICAL));
        mPhotoRecyclerView.setAdapter(photoAdapter);
        mPhotoRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(context,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        if (photoAdapter.getItemViewType(position) == PhotoAdapter.TYPE_ADD) {
                            PhotoPicker.builder()
                                    .setPhotoCount(PhotoAdapter.MAX)
                                    .setShowCamera(true)
                                    .setPreviewEnabled(false)
                                    .setSelected(selectedPhotos)
                                    .start(AddServicesActivity.this);
                        } else {
                            PhotoPreview.builder()
                                    .setPhotos(selectedPhotos)
                                    .setCurrentItem(position)
                                    .start(AddServicesActivity.this);
                        }
                    }
                }));
    }

    /**
     * 获取图片回调
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) {  //特色说明
            case 101: //返回的请求码
                //操作
                myFeature = data.getExtras().getString("myFeature");
                if(!myFeature.equals("")){
                    llServiceInd.setEditContent("已编辑");
                }
                break;
            case Constant.CALENDARID://非空闲时间
                notimeDate=data.getStringArrayListExtra(Constant.CALENDARNOTIME);
                if(notimeDate.size()>0){
                    llServiceNotime.setEditContent(notimeDate.toString().substring(1,notimeDate.toString().length()-1));
                }else{
                    llServiceNotime.setEditContent("");
                }
                break;
            case Constant.DEFAULT_PRICEID://日历价格
                priceCalendar=data.getParcelableArrayListExtra(Constant.PRICECALENDERINFO);
                int uniqueId=data.getIntExtra("uniqueId",0);
                setPriceCalender(priceCalendar,uniqueId);
                break;
        }
        //多图片上传图片回调
        if (resultCode == -1 &&
                (requestCode == PhotoPicker.REQUEST_CODE || requestCode == PhotoPreview.REQUEST_CODE)) {
            List<String> photos = null;
            if (data != null) {
                photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
            }
            selectedPhotos.clear();
            if (photos != null) {
                selectedPhotos.addAll(photos);
            }
            upUrls="";
            photoAdapter.notifyDataSetChanged();
            int a=0;
            for (int i=0;i<selectedPhotos.size();i++){
                if(selectedPhotos.get(i).startsWith("http")){
                    upUrls+=selectedPhotos.get(i).toString()+",";
                }else {
                    a++;
                    if(a==1){
//                        updateBack();
                        upFile2();
                    }
                }
            }
//            if(!image.equals(upUrls)){
//                updateBack();
//            }
            initAddPhoto();
        }
    }

    public void upFile2() {
        disposable = RxBus2.getInstance().toObservable(UpLoadPhotos.class, new Consumer<UpLoadPhotos>() {
            @Override
            public void accept(UpLoadPhotos upLoadPhoto) throws Exception {
                //构建要上传的文件
                mBatchUploadPresenter.batchUpload(upLoadPhotos);
                disposable.dispose();
            }
        });

        loadingDialog.showDialog("上传图片...");
        loadingDialog.setCancelable(false);
        compressImage2();
    }

    private void compressImage2() {
        MyThreadPool.getInstance().submit(new Runnable() {
            @Override
            public void run() {
                upLoadPhotos.clear();
                for (String path : selectedPhotos) {
                    list.clear();
                    if(path.startsWith("http")){
                        list.add(path);//添加网络图片，不需要上传
                    }else{
                        File file = new File(path);
                        if(!file.getName().startsWith("crop") || file.length()>1024*100) {
                            String savePath = TackPicturesUtil.IMAGE_CACHE_PATH + "crop" + file.getName();
                            ImageUtils.getImage(path, savePath);
                            upLoadPhotos.add(savePath);
                        }else{
                            upLoadPhotos.add(path);
                        }
                    }
                }
                RxBus2.getInstance().post(new UpLoadPhotos());
            }
        });
    }

    @Override
    public void batchUploadSuccess(BatchUploadInfo data) {
        if(loadingDialog!=null){
            loadingDialog.dismiss();
        }
        if(data!=null){
            String[] url=data.getUrl();
            if(list.size()>0){
                for (int i=0;i<list.size();i++){
                    upUrls+=list.get(i)+",";
                }
            }
            for (int i=0;i<url.length;i++){
                upUrls+=url[i]+",";
            }
        }
        selectedPhotos=StringUtils.stringToList(upUrls);
        photoAdapter.notifyDataSetChanged();
    }

    @Override
    public void batchUploadFailed(String msg) {
        if(loadingDialog!=null){
            loadingDialog.dismiss();
        }
    }

    //----------------end 多图片上传------------


    //-----------city  显示 start----------

    private void showPickerView() {// 弹出选择器
        getServiceCityLists();
        if(listCity==null||listModelsChildrens==null||listModelsChildrensbean==null||listModels==null)return;
        if(listModels.size()>0&&listModelsChildrens.size()>0&&listModelsChildrensbean.size()>0){
            OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
                @Override
                public void onOptionsSelect(int options1, int options2, int options3, View v) {
                    //返回的分别是三个级别的选中位置
                    if(listCity.get(options1).getChildrens().get(options2).getChildrens().size()>0){
                        String addressName=listCity.get(options1).getChildrens().get(options2).getChildrens().get(options3).getAddressName();
                        cityTypeId=listCity.get(options1).getChildrens().get(options2).getChildrens().get(options3).getId();
                        llServiceCity.setEditContent(addressName);
                    }else{
                        String addressName=listCity.get(options1).getChildrens().get(options2).getAddressName();
                        cityTypeId=listCity.get(options1).getChildrens().get(options2).getId();
                        llServiceCity.setEditContent(addressName);
                    }
                }
            })
                    .setTitleText("城市选择")
                    .setDividerColor(Color.BLACK)
                    .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                    .setContentTextSize(16)
                    .build();

    //        pvOptions.setPicker(listModels);//一级选择器
    //        pvOptions.setPicker(listModels, listModelsChildrens);//二级选择器
            pvOptions.setPicker(listModels, listModelsChildrens, listModelsChildrensbean);//三级选择器
            pvOptions.show();
        }
    }

    @Override
    public void getServiceCityListSuccess(List<GetServiceCityModel> datas) {
        if(datas.size()>0){
            if(MySelfInfo.getInstance().isLogin()){
                MySelfInfo.getInstance().setServiceCitys(datas);
                MySelfInfo.getInstance().setServiceIden(1);
            }
        }
     }

     public void getServiceCityLists(){
         if(MySelfInfo.getInstance().getServiceCitys()==null)return;
         List<GetServiceCityModel> datas=MySelfInfo.getInstance().getServiceCitys();
         listCity=datas;
         for (int i=0;i<datas.size();i++){
             List<String> names=new ArrayList<>();
             List<List<String>> names5=new ArrayList<>();
             String name1=datas.get(i).getAddressName();
             listModels.add(name1);
             for (int j=0;j<datas.get(i).getChildrens().size();j++){
                 List<String> names4=new ArrayList<>();
                 String name2=datas.get(i).getChildrens().get(j).getAddressName();
                 names.add(name2);
                 names5.add(names4);
                 for(int x=0;x<datas.get(i).getChildrens().get(j).getChildrens().size();x++){
                     String name3=datas.get(i).getChildrens().get(j).getChildrens().get(x).getAddressName();
                     names4.add(name3);
                 }
             }
             listModelsChildrens.add(names);
             listModelsChildrensbean.add(names5);
         }
     }

    @Override
    public void getServiceCityListFailed(String msg) {
        Log.e("test",msg);
    }

    @Override
    public void cityGetCitySuccess(List<CityModel> datas) {

    }
    @Override
    public void cityGetCityFailed(String msg) {

    }
    //-----------city   end----------


    //-----------新增服务  start-----------
    @Override
    public void addServiceSuccess(String str) {
        showShortToast("新增成功");
        finish();
    }

    @Override
    public void addServiceFailed(String msg) {
        showShortToast(msg);
    }

    //-----------新增服务  end-----------


    //--------获取修改服务详情  start----------
    @Override
    public void getUpdateServInfoSuccess(GetUpdateServiceInfo infos) {
        if(infos!=null){
            if(infos.getNjzGuideServeEntity()!=null){
                GetUpdateServiceInfo.ServeUpdataBean serveUpdataBean= infos.getNjzGuideServeEntity();

                if(serveUpdataBean.getPurchaseRules()!=null&&!serveUpdataBean.getPurchaseRules().equals("")){
                    iv_isagreement.setImageDrawable(ContextCompat.getDrawable(context,R.mipmap.reg_check));
                    isSelect=true;
                }else{
                    iv_isagreement.setImageDrawable(ContextCompat.getDrawable(context,R.mipmap.no_check));
                    isSelect=false;
                }

                serviceType.setEditContent(serveUpdataBean.getServeTypeName());
                serviceTitle.setEditContent(serveUpdataBean.getTitle());
                llServiceCity.setEditContent(serveUpdataBean.getAddress());
                servicePrice.setEditContent(""+serveUpdataBean.getServePrice());
                llServicePriceinfo.setText(serveUpdataBean.getCostExplain());
                ServiceUtil.strSplitNum(serveUpdataBean.getRenegePriceThree(),edit_text1,edit_text2);
                ServiceUtil.strSplitNum(serveUpdataBean.getRenegePriceFive(),edit_text3,edit_text4);
                String image= serveUpdataBean.getTitleImg();
                if(!image.equals("")){
                    upUrls=image;
                    selectedPhotos=StringUtils.stringToList(image);
                    initAddPhoto();//先执行
                }
                if(infos.getNjzGuideServeNoTimeEntities()!=null){
                    //非空闲时间
                    List<GetUpdateServiceInfo.NjzGuideServeNoTimeEntities> noTimeEntities=infos.getNjzGuideServeNoTimeEntities();
                    if(noTimeEntities.size()>0){
                        for (int i=0;i<noTimeEntities.size();i++){
                            String time=noTimeEntities.get(i).getDateValue();
                            notimeDate.add(time);
                        }
                        llServiceNotime.setEditContent(notimeDate.toString().substring(1,notimeDate.toString().length()-1));
                    }
                }
                llServiceInd.setEditContent("请点击修改...");
                if(toId==2){
                    serveBtnSave.setText("修改");
                    serveBtnSaveSub.setText("修改并"+serveUpdataBean.getStatusString());
                }

                if(infos.getserveGroupListInfo()!=null){
                    infos1=infos.getserveGroupListInfo();
                }
                serverTypeId=serveUpdataBean.getServeType();
                serviceId=serveUpdataBean.getId();
                cityTypeId=serveUpdataBean.getAddressId();
                myFeature=serveUpdataBean.getServeFeature();
                saleNum=infos.getSaleNum();
            }
        }
        sTypePresenter.getServeDicList(true);
    }

    @Override
    public void getUpdateServInfoFailed(String msg) {
        showShortToast(msg);
    }


    //--------获取服务详情  end----------


    //------------更新服务 start------------

    @Override
    public void updaServiceInfoSuccess(String infos) {
        showShortToast("修改成功");
        finish();
    }

    @Override
    public void updaServiceInfoFailed(String msg) {
        showShortToast(msg);
    }

    //------------更新服务 end------------


}
