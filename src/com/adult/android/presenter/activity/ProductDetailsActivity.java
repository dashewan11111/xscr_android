package com.adult.android.presenter.activity;

/**
 * @ClassName: ProductDetailsFragment
 * @Description: 产品详情页面
 * @date 2015年2月4日 下午2:13:01
 */
public class ProductDetailsActivity extends BaseActivity {
	// implements
	// OnPageChangeListener, OnClickListener, InitProductDetailListener,
	// OnStateChangedListener, OnGetCarsCountCompletedListener {
	public static final String EXTRA_PRODUCT_ID = "extra_product_id";
	// Timer timer;
	// private BitmapUtils bitmapUtils;
	// private LoadingDialog loadingDialog;
	// private ProductDetailModel pModel = new ProductDetailModel();
	// private CarsModel carsModel = new CarsModel();
	// private UserActionModel userModel = new UserActionModel();
	// private ProductAtt productAtt;
	// private ProdAttrVal currentProdAttrVal;
	// private ProductDetail prod;
	// private List<PromotionDto> promotionDtos;
	// private TextView txt_cars_count;
	// private TextView txtProductName, txtSendTo, txtStock;
	// private ProductDetailsSecondView secondView;
	// // 交易价格
	// private TextView txtProductUnitPrice, product_details_firsr_txt_time;
	// // 税率 //默认属性
	// private TextView txt_product_tariff, product_details_selected_attr_txt;
	// private ImageView img_product_tariff_desc, imageTOP;
	// private TextView txt_product_originplaceName;
	// // 广告栏ViewPager
	// private ViewPager mViewPager;
	// // 广告栏ViewPager适配器
	// private ProductDetailPicAdapter productDetailPicAdapter;
	// // 评论列表 // 促销容器
	// private LinearLayout product_commnets_ll, llyt_product_promotion;
	// // 国家图片
	// private ImageView product_country_image, top_back_btn, top_menu_btn;
	// private ScrollViewContainer scrollViewContainer;
	// private LinearLayout relativeLayoutCart;
	// private SampleWebView webView2, webView3;
	// private GraphicView webView1;
	// private ProgressBar progress_bar;
	// private MyScrollView mScrollView;
	// private RelativeLayout relytTopButtons;
	// /**
	// * 黑色半透明*
	// */
	// private LinearLayout product_details_selected_attr;
	// private TextView txt_comment_totalComments;
	// private TextView txt_comment_praise;
	// private String productAttrUrl, b2cDescription;
	// private TextView img_add_shopping_cart;
	// private int myEditTextNum;
	// private RelativeLayout llyt_product_pull;
	// private LinearLayout rl_sort_price, rl_sort_sales, rl_sort_focus;
	// private int nums;
	// private String pid;
	// private TextView tv_sort_price, tv_sort_sales, tv_sort_focus;
	// private View tabview1, tabview2, tabview3;
	// private SkuShowAttr currentSkuShowAttr;
	// private String sku_id;
	// private SharePopupWindow sharePopupWindow;
	// /**
	// * 配送方式
	// **/
	// private TextView product_details_send_way;
	//
	// /** 添加图片的layout **/
	// private LinearLayout product_details_promotion_iv_layout;
	// /** 活动展示的原因或者倒计时 **/
	// private TextView product_details_promotion_details;
	// /** 展示活动列表 **/
	// private LLayoutToListView product_details_promotion_lv;
	//
	// private RelativeLayout product_all_promotion_layout;
	//
	// private View product_details_has_promotion_line;
	// /** 闪购倒计时 **/
	// private TextView product_details_flash_buy_count;
	// /** "1"表示闪购, "0"表示不是闪购 **/
	// private String flashBuy = "";
	// /** 闪购结束时间 **/
	// private String flashBuyEndDate = "";
	// /** 闪购 **/
	// private LinearLayout product_details_flash_buy_layout;
	//
	// private SimpleDateFormat format = new SimpleDateFormat(
	// "yyyy-MM-dd HH:mm:ss");
	// /** 进入商品详情的访问时间 **/
	// private long accessTime = 0l;
	// /** 倒计时时钟 **/
	// private ImageView product_details_time_clock;
	// /** 闪购倒计时 **/
	// private CcigmallCountDownTimer flashCountDownTimer;
	//
	// /** 活动倒计时 **/
	// private CcigmallCountDownTimer promotionCountDownTimer;
	// /** 添加商品详情描述、规格参数和购买须知 **/
	// private FrameLayout product_details_web_layout;
	// /** 添加商品详情first **/
	// private LinearLayout product_details_first_layout;
	// /** viewPager底部的点 **/
	// private LinearLayout pointLinear;
	// /**
	// * 更新计时器
	// */
	// private Handler handler = new Handler() {
	// public void handleMessage(android.os.Message msg) {
	// switch (msg.what) {
	// case 1:
	// long msgObj = (long) msg.obj;
	// accessTime += msgObj;
	// break;
	// case 2:
	// String html = (String) msg.obj;
	// webView1.loadDataWithBaseURL(null, html, "text/html", "utf-8",
	// null);
	// break;
	// }
	// }
	// };
	//
	// /**
	// * 判断是否是数字
	// *
	// * @param str
	// * @return
	// */
	// private static boolean isNum(String str) {
	// try {
	//
	// new BigDecimal(str);
	// return true;
	// } catch (Exception e) {
	// return false;
	// }
	// }
	//
	// @Override
	// protected boolean isFullScreen() {
	// return true;
	// }
	//
	// @SuppressWarnings("deprecation")
	// @Override
	// protected void onCreate(Bundle savedInstanceState) {
	// super.onCreate(savedInstanceState);
	// initViews();// 初始化控件
	// pModel.initProductDetail(this, pid, Misc.getDeviceId(this));// 获取商品详情
	//
	// int cartCount = SharedPreferencesUtil.getSharedPreferences(
	// SharedPreferencesConstants.FILES.FILE_SHOPPING_CART,
	// SharedPreferencesConstants.PARAMS.CART_COUNT, 0);
	// String number = CarsModel.formatCartCount(cartCount + "");
	// setCatsCount(number);
	// if (savedInstanceState != null && sharePopupWindow != null) {
	// sharePopupWindow.handleWeiboResponse(getIntent());
	// }
	// }
	//
	// @Override
	// protected void onNewIntent(Intent intent) {
	// super.onNewIntent(intent);
	// if (sharePopupWindow != null) {
	// sharePopupWindow.handleWeiboResponse(intent);
	// }
	// }
	//
	// @Override
	// public void onGetCarsCountStart() {
	// }
	//
	// @Override
	// public void onHttpException(HttpResponseException e) {
	// }
	//
	// @Override
	// public void onGetCarsCountFinish() {
	// }
	//
	// @Override
	// public void onGetCarsCountFailed(ResponseException e) {
	// }
	//
	// @Override
	// public void onGetCarsCountCompleted(GetCarsCountResponse info) {
	// String number = CarsModel.formatCartCount(info.getData());
	// try {
	// SharedPreferencesUtil.setSharedPreferences(
	// SharedPreferencesConstants.FILES.FILE_SHOPPING_CART,
	// SharedPreferencesConstants.PARAMS.CART_COUNT,
	// Integer.parseInt(info.getData()));
	// } catch (NumberFormatException e) {
	// e.printStackTrace();
	// }
	// setCatsCount(number);
	// }
	//
	// /**
	// * 获取配送地址
	// */
	// private void getReceiveArea() {
	// userModel.getReceiveArea(Misc.getDeviceId(ProductDetailsActivity.this),
	// new SampleModelListener<Object>() {
	// @Override
	// public void onRequestStart() {
	// Log.e("获取配送地址", "onRequestStart");
	// }
	//
	// @Override
	// public void onRequestSuccess(Object object) {
	// ReceiveAreaResponse result = (ReceiveAreaResponse) object;
	// if (null != result.getData().getMessage()) {
	// Log.e("获取配送地址", "" + result.getData().getMessage());
	// String after = "";
	// try {
	// after = URLDecoder.decode(result.getData()
	// .getMessage(), "UTF-8");
	// } catch (UnsupportedEncodingException e) {
	// e.printStackTrace();
	// }
	// String[] array = after.split(",");
	// txtSendTo.setText(array[3]);
	// }
	// }
	//
	// @Override
	// public void onRequestFail(ResponseException e) {
	// Log.e("获取配送地址", "onRequestFail");
	// }
	//
	// @Override
	// public void onRequestFinish() {
	// Log.e("获取配送地址", "onRequestFinish");
	// }
	// });
	// }
	//
	// @Override
	// public void onPageScrollStateChanged(int arg0) {
	// }
	//
	// @Override
	// public void onPageScrolled(int arg0, float arg1, int arg2) {
	// }
	//
	// @Override
	// public void onPageSelected(int i) {
	//
	// final int childCount = pointLinear.getChildCount();
	// if (childCount != 0 && i < childCount) {
	// for (int j = 0; j < childCount; j++) {
	// View view = pointLinear.getChildAt(j);
	// if (view != null) {
	// if (j == i) {
	// view.setBackgroundResource(R.drawable.recommend_gallery_select);
	// } else {
	// view.setBackgroundResource(R.drawable.recommend_gallery);
	// }
	// }
	// }
	// }
	// }
	//
	// @Override
	// protected void onActivityResult(int requestCode, int resultCode, Intent
	// data) {
	// super.onActivityResult(requestCode, resultCode, data);
	// if (LoginActivity.FROM_RRODUCT_DETATIL == requestCode) {
	// if (11 == resultCode && UserLogic.getInsatnace().getIsLogin()) {
	// // 登陆成功之后直接跳转到提交订单页面
	// submitOrder();
	// }
	// }
	//
	// // SSO授权回调
	// // 重要：发起 SSO 登陆的 Activity 必须重写 onActivityResults
	// // 分享必须用到
	// if (sharePopupWindow != null) {
	// sharePopupWindow.authorizeCallBack(requestCode, resultCode, data);
	// }
	// }
	//
	// private void setCatsCount(String number) {
	// if (!TextUtils.isEmpty(number)) {
	// txt_cars_count.setVisibility(View.VISIBLE);
	// txt_cars_count.setText(number);
	// } else {
	// txt_cars_count.setVisibility(View.GONE);
	// }
	// }
	//
	// /**
	// * 设置进度条的显示隐藏
	// *
	// * @param number
	// */
	// private void setProgressBarView(int number) {
	// if (number == 100) {
	// progress_bar.setVisibility(View.INVISIBLE);
	// } else {
	// if (progress_bar.getVisibility() == View.INVISIBLE)
	// progress_bar.setVisibility(View.VISIBLE);
	// progress_bar.setProgress(number);
	// }
	// }
	//
	// private void submitOrder() {
	// final int numbers = myEditTextNum;
	//
	// carsModel.quickBuy(currentSkuShowAttr, numbers,
	// new SampleModelListener<CartDTO>() {
	//
	// @Override
	// public void onRequestStart() {
	// loadingDialog.show();
	// }
	//
	// @Override
	// public void onRequestSuccess(CartDTO t) {
	// Intent intent = new Intent(ProductDetailsActivity.this,
	// ProductOrderActivity.class);
	// intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
	// | Intent.FLAG_ACTIVITY_SINGLE_TOP);
	// intent.putExtra("CartDto", t);
	// startActivity(intent);
	// }
	//
	// @Override
	// public void onRequestFail(ResponseException e) {
	// ToastUtil.showToastShort(ProductDetailsActivity.this,
	// e.getResultMsg());
	// }
	//
	// @Override
	// public void onRequestFinish() {
	// loadingDialog.dismiss();
	// }
	// });
	// }
	//
	// @Override
	// public void onClick(View view) {
	// switch (view.getId()) {
	// case R.id.product_details_firsr_llyt_send_to:
	// showAddressPopupWindow();
	// break;
	//
	// case R.id.img_onekey_buy:
	// final int numbers = myEditTextNum;
	// if (0 == numbers) {
	// ToastUtil.showToastShort(getApplicationContext(),
	// R.string.product_no_stock);
	// return;
	// }
	// if (!UserLogic.getInsatnace().getIsLogin()) {
	// // 未登录
	// Intent intent2 = new Intent(this, LoginActivity.class);
	// intent2.putExtra("isFromDetail", true);
	// startActivityForResult(intent2,
	// LoginActivity.FROM_RRODUCT_DETATIL);
	// return;
	// }
	//
	// if (currentSkuShowAttr == null) {
	// ToastUtil.showToastShort(this, R.string.select_product_hint);
	// return;
	// }
	//
	// if (OrderModel.OrderSupplyType.OverseasDirectMail.getTypeId()
	// .equals(String.valueOf(productAtt.getSupply()))
	// || OrderModel.OrderSupplyType.BondedAreaSend.getTypeId()
	// .equals(String.valueOf(productAtt.getSupply()))) {
	// if (numbers * currentSkuShowAttr.getUnitPrice().doubleValue() > 1000
	// && numbers > 1) {
	// ToastUtil.showToastShort(this,
	// R.string.single_order_money_limit);
	// } else {
	// submitOrder();
	// }
	// } else {
	// submitOrder();
	// }
	// break;
	// case R.id.img_add_shopping_cart:
	// /** 添加购物车 */
	// addToCars();
	// break;
	// case R.id.shopping_cart_layout:
	// Intent intent2 = new Intent(this, CarsActivity.class);
	// intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	// // intent2.putExtra(MainActivity.SWITCH_INDEX, 2);
	// startActivity(intent2);
	// // finish();
	// break;
	// case R.id.product_details_selected_attr:
	// showAttrsPopupWindow();
	// break;
	// case R.id.rl_sort_price:
	// selectProductTab(0);
	// break;
	// case R.id.rl_sort_sales:
	// selectProductTab(1);
	// break;
	// case R.id.rl_sort_focus:
	// selectProductTab(2);
	// break;
	// case R.id.llyt_product_pull:
	// scrollViewContainer.moveToBottom();
	// break;
	// case R.id.img_info:
	// /** 显示描述的Dialog */
	// SampleDialog dialog = new SampleDialog(this) {
	// @Override
	// public View getContentView() {
	// return null;
	// }
	// };
	// dialog.setTitleText(R.string.taxation_explain, R.color.gray_3);
	// dialog.setContentText(
	// Misc.ToDBC(getString(R.string.taxation_explain_content)),
	// R.color.gray_1);
	// dialog.setContentTextGravity(Gravity.LEFT);
	// dialog.setSingleButton(R.string.ok, R.color.main_red,
	// new DialogInterface.OnClickListener() {
	//
	// @Override
	// public void onClick(DialogInterface arg0, int arg1) {
	// arg0.dismiss();
	// }
	// });
	// dialog.show();
	// break;
	//
	// // case R.id.default_top_bar_right_btn:
	// // showSharePopupwindow();
	// // break;
	// case R.id.top_back_btn:
	// finish();
	// break;
	// case R.id.top_menu_btn:
	// showActionsPopupwindow();
	// break;
	// }
	// }
	//
	// /**
	// * 添加到购物车
	// */
	// private void addToCars() {
	// nums = myEditTextNum;
	// carsModel.addToCart(nums, currentSkuShowAttr,
	// new OnAddToCarsCompletedListener() {
	//
	// @Override
	// public void onCompleted(AddToCarsResponse info) {
	// if ("50001".equals(info.getData().getMessage())) {
	// ToastUtil.showToastShort(getApplicationContext(),
	// R.string.product_no_stock);
	// return;
	// }
	//
	// /** 刷新购物车数据 */
	// sendBroadcast(new Intent(
	// CarsFragment.INTENT_ACTION_REFRESH_CART));
	//
	// ToastUtil.showToastShort(getApplicationContext(),
	// R.string.add_shop_car_success);
	// /** 获取购物车数量 */
	// int cartCount = SharedPreferencesUtil
	// .getSharedPreferences(
	// SharedPreferencesConstants.FILES.FILE_SHOPPING_CART,
	// SharedPreferencesConstants.PARAMS.CART_COUNT,
	// 0);
	// final int newCount = nums + cartCount;
	// SharedPreferencesUtil
	// .setSharedPreferences(
	// SharedPreferencesConstants.FILES.FILE_SHOPPING_CART,
	// SharedPreferencesConstants.PARAMS.CART_COUNT,
	// newCount);
	// String number = CarsModel
	// .formatCartCount(newCount + "");
	// setCatsCount(number);
	// }
	//
	// @Override
	// public void onFailed(ResponseException e) {
	// ToastUtil.showToastShort(getApplicationContext(),
	// e.getResultMsg());
	// }
	//
	// @Override
	// public void onHttpException(HttpResponseException e) {
	// ToastUtil.showToastShort(getApplicationContext(),
	// R.string.add_shop_car_error);
	// }
	//
	// @Override
	// public void onStart() {
	// loadingDialog.show();
	// }
	//
	// @Override
	// public void onFinish() {
	// loadingDialog.dismiss();
	// }
	// });
	// }
	//
	// /**
	// * 显示地址选择弹框
	// */
	// private void showAddressPopupWindow() {
	// new AddressPopupWindow(this, pop_layout,
	// new AddressPopupWindow.onConfirmCompletedListener() {
	// @Override
	// public void onConfirmCompleted(String provinceId,
	// String cityId, String areaId, final String address) {
	// userModel.setReceiveArea(
	// Misc.getDeviceId(ProductDetailsActivity.this),
	// provinceId, cityId, areaId, address,
	// new SampleModelListener<Object>() {
	// @Override
	// public void onRequestStart() {
	// }
	//
	// @Override
	// public void onRequestSuccess(Object object) {
	// Result result = (Result) object;
	// txtSendTo.setText(address);
	// currentProdAttrVal = null;
	// currentSkuShowAttr = null;
	// pModel.initProductDetail(
	// ProductDetailsActivity.this,
	// pid,
	// Misc.getDeviceId(ProductDetailsActivity.this));
	// /** 获取购物车数量 */
	// carsModel.getCarsCount(
	// ProductDetailsActivity.this,
	// ProductDetailsActivity.this);
	// }
	//
	// @Override
	// public void onRequestFail(
	// ResponseException e) {
	// }
	//
	// @Override
	// public void onRequestFinish() {
	// }
	// });
	// }
	// });
	// }
	//
	// @Override
	// protected void onRestart() {
	// super.onRestart();
	// /** 获取购物车数量 */
	// int cartCount = SharedPreferencesUtil.getSharedPreferences(
	// SharedPreferencesConstants.FILES.FILE_SHOPPING_CART,
	// SharedPreferencesConstants.PARAMS.CART_COUNT, 0);
	// String number = CarsModel.formatCartCount(cartCount + "");
	// setCatsCount(number);
	// }
	//
	// /**
	// * 弹出第三方分享
	// */
	// private void showSharePopupwindow() {
	// /** 第三方分享 **/
	// if (sharePopupWindow != null) {
	// sharePopupWindow.show();
	// }
	// }
	//
	// /**
	// * 显示操作的pop
	// */
	// private void showActionsPopupwindow() {
	// View contentView = (View) getLayoutInflater().inflate(
	// R.layout.action_popup_window, null);
	// final PopupWindow actionPop = new PopupWindow(contentView,
	// LinearLayout.LayoutParams.WRAP_CONTENT,
	// LinearLayout.LayoutParams.WRAP_CONTENT, true);
	// actionPop.setBackgroundDrawable(new ColorDrawable(0));
	// LinearLayout llytActionShare = (LinearLayout) contentView
	// .findViewById(R.id.action_pop_share);
	// LinearLayout llytActionHome = (LinearLayout) contentView
	// .findViewById(R.id.action_pop_home);
	// actionPop.setOutsideTouchable(true);
	// llytActionShare.setOnClickListener(new OnClickListener() {
	// @Override
	// public void onClick(View view) {
	// showSharePopupwindow();
	// actionPop.dismiss();
	// }
	// });
	// llytActionHome.setOnClickListener(new OnClickListener() {
	// @Override
	// public void onClick(View view) {
	// Intent intent = new Intent(ProductDetailsActivity.this,
	// MainActivity.class);
	// intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
	// | Intent.FLAG_ACTIVITY_SINGLE_TOP);
	// intent.putExtra(MainActivity.SWITCH_INDEX, 0);
	// startActivity(intent);
	// actionPop.dismiss();
	// finish();
	// }
	// });
	// actionPop.showAsDropDown(top_menu_btn, -Misc.dip2px(this, 55), 0);
	// }
	//
	// private void selectProductTab(int index) {
	//
	// switch (index) {
	// case 0:
	// tabview1.setVisibility(View.VISIBLE);
	// tabview2.setVisibility(View.INVISIBLE);
	// tabview3.setVisibility(View.INVISIBLE);
	//
	// webView1.setVisibility(View.VISIBLE);
	// webView2.setVisibility(View.GONE);
	// webView3.setVisibility(View.GONE);
	// if (!TextUtils.isEmpty(b2cDescription)) {
	// new Thread(new Runnable() {
	// @Override
	// public void run() {
	// try {
	// final String html = Misc
	// .getHtmlOfUrl(b2cDescription);
	// handler.obtainMessage(2, html).sendToTarget();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }
	// }).start();
	// }
	// break;
	// case 1:
	// tabview1.setVisibility(View.INVISIBLE);
	// tabview2.setVisibility(View.VISIBLE);
	// tabview3.setVisibility(View.INVISIBLE);
	// webView1.setVisibility(View.GONE);
	// webView2.setVisibility(View.VISIBLE);
	// webView3.setVisibility(View.GONE);
	// if (!TextUtils.isEmpty(productAttrUrl)) {
	// webView2.loadDataWithBaseURL(null, productAttrUrl, "text/html",
	// "utf-8", null);
	// }
	// break;
	// case 2:
	// tabview1.setVisibility(View.INVISIBLE);
	// tabview2.setVisibility(View.INVISIBLE);
	// tabview3.setVisibility(View.VISIBLE);
	// webView1.setVisibility(View.GONE);
	// webView2.setVisibility(View.GONE);
	// webView3.setVisibility(View.VISIBLE);
	//
	// if (Integer.valueOf(productAtt.getSupply()) > 10
	// && Integer.valueOf(productAtt.getSupply()) < 20) {
	// webView3.loadUrl("http://api.ccigmall.com/cross_purchase_notes.jsp");
	// } else {
	// webView3.loadUrl("http://api.ccigmall.com/purchase_notes.jsp");
	// }
	// break;
	// }
	// }
	//
	// @Override
	// public void onRequestSuccess(ProductDetail prod) {
	// relativeLayoutCart.setVisibility(View.VISIBLE);
	// this.prod = prod;
	// productAtt = prod.getProduct();
	// // 商品种类（1国内商品，11海外直邮，12保税区发货）
	// final int supplyId = productAtt.getSupply();
	// if (supplyId == 1 || 21 == supplyId) {
	// // txt_product_tar.setVisibility(View.GONE);
	// txt_product_tariff.setVisibility(View.GONE);
	// img_product_tariff_desc.setVisibility(View.GONE);
	// } else {
	// // txt_product_tar.setVisibility(View.VISIBLE);
	// txt_product_tariff.setVisibility(View.VISIBLE);
	// img_product_tariff_desc.setVisibility(View.VISIBLE);
	// }
	// promotionDtos = prod.getPromotionDto();
	// productAttrUrl = productAtt.getProductAttrUrl();
	// b2cDescription = productAtt.getB2cDescription();
	// addWebView();
	// selectProductTab(0);
	// txt_product_originplaceName.setText(productAtt.getOriginplaceName());
	//
	// bitmapUtils.display(
	// product_country_image,
	// PictureModel.DisplayModule.CountryIcon.urlWithHost(
	// productAtt.getOriginplaceImage(), null));
	// updateSkuAttrs();// 设置默认的SKU信息
	//
	// final String pName = txtProductName.getText().toString().trim();
	// final String productImageUrl = PictureModel.DisplayModule.SharePicture
	// .urlWithHost(productDetailPicAdapter.getDataList().get(0), null);
	// final String shareProductPrice = txtProductUnitPrice.getText()
	// .toString().trim().substring(1);
	// final String productUrl = ServiceUrlConstants.getWapUrl()
	// + ServiceUrlConstants.PRODUCT_DETAILS_WAP_SHARE
	// + productAtt.getProductId();
	//
	// sharePopupWindow = new SharePopupWindow(this, pop_layout, pName,
	// productImageUrl, productUrl, shareProductPrice);
	// // PhpStatModel phpStatModel = new PhpStatModel(
	// // ProductDetailsActivity.this);
	// // if (UserLogic.getInsatnace().getUserBean() != null) {
	// // phpStatModel.viewGoodsDetails(prod.getProduct().getProductId(),
	// // prod.getProduct().getB2cProductName(), "", "", "", "", prod
	// // .getProduct().getBrandId(), UserLogic
	// // .getInsatnace().getUserBean().getUserName(), "");
	// // } else {
	// // phpStatModel.viewGoodsDetails(prod.getProduct().getProductId(),
	// // prod.getProduct().getB2cProductName(), "", "", "", "", prod
	// // .getProduct().getBrandId(), "", "");
	// // }
	//
	// }
	//
	// /**
	// * 设置SKU信息
	// */
	// private void updateSkuAttrs() {
	// try {
	// txtProductUnitPrice.setText(getResources().getString(R.string.rmb)
	// + Misc.scale(null == currentSkuShowAttr ? productAtt
	// .getSkuShowList().get(0).getUnitPrice()
	// .doubleValue() : currentSkuShowAttr.getUnitPrice()
	// .doubleValue(), 2));
	// Misc.setPrice(ProductDetailsActivity.this, txtProductUnitPrice,
	// true);
	// } catch (NumberFormatException e) {
	// txtProductUnitPrice.setText(R.string.unknown);
	// } catch (NullPointerException e) {
	// txtProductUnitPrice.setText(R.string.unknown);
	// }
	// int tariff = productAtt.getTariff();
	// txt_product_tariff.setText("（" + getString(R.string.tax_rate) + tariff
	// + "%）");
	// if (null == currentSkuShowAttr) {
	// currentSkuShowAttr = productAtt.getSkuShowList().get(0);
	// }
	// if (currentSkuShowAttr.getSkuQty() < 1) {
	// myEditTextNum = 0;// 无货;
	// txtStock.setText(R.string.local_address_no_goods);
	// txtStock.setTextColor(getResources().getColor(R.color.gray_1));
	// product_details_firsr_txt_time.setVisibility(View.GONE);
	// } else {
	// myEditTextNum = 1;// 现货
	// txtStock.setText(R.string.now_has_goods);
	// txtStock.setTextColor(getResources().getColor(R.color.main_red));
	// product_details_firsr_txt_time.setVisibility(View.VISIBLE);
	// }
	// StringBuffer selectedSkuInfo = new StringBuffer();
	// selectedSkuInfo.append(null == currentProdAttrVal ? productAtt
	// .getShowProdAttr().getProdAttrVals().get(0).getAttrValNameCn()
	// : currentProdAttrVal.getAttrValNameCn());
	// selectedSkuInfo.append("   ");
	// selectedSkuInfo.append(null == currentSkuShowAttr ? productAtt
	// .getSkuShowList().get(0).getSkuNameCn() : currentSkuShowAttr
	// .getSkuNameCn());
	// selectedSkuInfo.append("   ");
	// selectedSkuInfo.append(this.myEditTextNum);
	// selectedSkuInfo.append(getString(R.string.piece));
	// product_details_selected_attr_txt.setText(selectedSkuInfo.toString());
	// sku_id = currentSkuShowAttr.getSkuId();
	// if (promotionDtos != null && promotionDtos.size() > 0) {
	// startJishi();
	// boolean hasPromotion = false;
	// for (PromotionDto promotionDto : promotionDtos) {
	// if (sku_id != null && sku_id.equals(promotionDto.getSkuId())) {
	// hasPromotion = true;
	// updatePromotionDto(promotionDto);
	// break;
	// }
	// }
	// if (!hasPromotion) {
	// updatePromotionDto(null);
	// }
	// } else {
	// setProductName(false);
	// // 没有任何促销活动
	// llyt_product_promotion.setVisibility(View.GONE);
	// product_details_has_promotion_line.setVisibility(View.GONE);
	// }
	// }
	//
	// /**
	// * 显示促销信息
	// */
	// private void updatePromotionDto(PromotionDto promotionDto) {
	//
	// String nowTime = promotionDto == null ? null : promotionDto
	// .getNewDate();
	//
	// setProductName(false);
	//
	// /** 满减 **/
	// List<FullOrBuyGiftsInfo> fullReductions = promotionDto == null ? null
	// : promotionDto.getFullReductions();
	// /** 直降 **/
	// List<FullOrBuyGiftsInfo> priceDowns = promotionDto == null ? null
	// : promotionDto.getPriceDowns();
	// /** 满赠 **/
	// List<FullOrBuyGiftsInfo> withIncreasings = promotionDto == null ? null
	// : promotionDto.getWithIncreasings();
	// /** 买赠 **/
	// List<FullOrBuyGiftsInfo> Buys = promotionDto == null ? null
	// : promotionDto.getBuys();
	// /** 满返 **/
	// List<FullOrBuyGiftsInfo> Fullbacks = promotionDto == null ? null
	// : promotionDto.getFullbacks();
	//
	// long flash_now_time = 0l;
	// long flash_end_date = 0l;
	// if (flashBuyEndDate != null && nowTime != null) {
	// try {
	// if (flashBuyEndDate != null) {
	// flash_end_date = format.parse(flashBuyEndDate).getTime();
	// }
	// if (nowTime != null) {
	// flash_now_time = format.parse(nowTime).getTime();
	// }
	//
	// } catch (ParseException e) {
	// e.printStackTrace();
	// }
	// if (flash_end_date - flash_now_time > 0) {
	// flashCountDownTimer = new CcigmallCountDownTimer(
	// product_details_flash_buy_count, flash_end_date
	// - flash_now_time, 1000, "flash");
	// flashCountDownTimer.start();
	// }
	// }
	// if ((fullReductions == null || fullReductions.size() == 0)
	// && (priceDowns == null || priceDowns.size() == 0)
	// && (withIncreasings == null || withIncreasings.size() == 0)
	// && (Buys == null || Buys.size() == 0)
	// && (Fullbacks == null || Fullbacks.size() == 0)) {
	// // 没有任何促销活动
	// llyt_product_promotion.setVisibility(View.GONE);
	// product_details_has_promotion_line.setVisibility(View.GONE);
	// return;
	// }
	// /** 所有活动的列表 **/
	// List<FullOrBuyGiftsInfo> allPromotionList = new
	// ArrayList<FullOrBuyGiftsInfo>();
	// product_details_promotion_iv_layout.removeAllViews();
	// // 直降
	// if (priceDowns != null && priceDowns.size() > 0) {
	// for (FullOrBuyGiftsInfo fullOrBuyGiftsInfo : priceDowns) {
	// fullOrBuyGiftsInfo.setPromotionType("priceDown");
	// allPromotionList.add(fullOrBuyGiftsInfo);
	// }
	// ImageView iv = new ImageView(this);
	// iv.setImageResource(Misc.switchPromotionIcon("priceDown"));
	// product_details_promotion_iv_layout.addView(iv);
	// }
	//
	// // 买赠
	// if (Buys != null && Buys.size() > 0) {
	// for (FullOrBuyGiftsInfo fullOrBuyGiftsInfo : Buys) {
	// fullOrBuyGiftsInfo.setPromotionType("buy");
	// allPromotionList.add(fullOrBuyGiftsInfo);
	// }
	// ImageView iv = new ImageView(this);
	// iv.setImageResource(Misc.switchPromotionIcon("buy"));
	// product_details_promotion_iv_layout.addView(iv);
	// }
	//
	// // 满减
	// if (fullReductions != null && fullReductions.size() > 0) {
	// for (FullOrBuyGiftsInfo fullOrBuyGiftsInfo : fullReductions) {
	// fullOrBuyGiftsInfo.setPromotionType("fullReduction");
	// allPromotionList.add(fullOrBuyGiftsInfo);
	// }
	// ImageView iv = new ImageView(this);
	// iv.setImageResource(Misc.switchPromotionIcon("fullReduction"));
	// product_details_promotion_iv_layout.addView(iv);
	// }
	//
	// // 满赠
	// if (withIncreasings != null && withIncreasings.size() > 0) {
	// for (FullOrBuyGiftsInfo fullOrBuyGiftsInfo : withIncreasings) {
	// fullOrBuyGiftsInfo.setPromotionType("withIncreasing");
	// allPromotionList.add(fullOrBuyGiftsInfo);
	// }
	// ImageView iv = new ImageView(this);
	// iv.setImageResource(Misc.switchPromotionIcon("withIncreasing"));
	// product_details_promotion_iv_layout.addView(iv);
	// }
	//
	// // 满返
	// if (Fullbacks != null && Fullbacks.size() > 0) {
	// for (FullOrBuyGiftsInfo fullOrBuyGiftsInfo : Fullbacks) {
	// fullOrBuyGiftsInfo.setPromotionType("fullBack");
	// allPromotionList.add(fullOrBuyGiftsInfo);
	// }
	// ImageView iv = new ImageView(this);
	// iv.setImageResource(Misc.switchPromotionIcon("fullBack"));
	// product_details_promotion_iv_layout.addView(iv);
	// }
	//
	// // 已经过滤掉allPromotionList.size()为0的这里allPromotionList.size()必大于0
	// llyt_product_promotion.setVisibility(View.VISIBLE);
	// product_details_has_promotion_line.setVisibility(View.VISIBLE);
	// if (allPromotionList.size() == 1) {
	// product_all_promotion_layout.setVisibility(View.GONE);
	// product_details_promotion_lv.setVisibility(View.VISIBLE);
	// } else {
	// if (product_details_promotion_lv.getVisibility() == View.GONE) {
	// product_all_promotion_layout.setVisibility(View.VISIBLE);
	// product_details_promotion_lv.setVisibility(View.GONE);
	// } else {
	// product_all_promotion_layout.setVisibility(View.GONE);
	// product_details_promotion_lv.setVisibility(View.VISIBLE);
	// }
	// if (allPromotionList.get(0).getPromotionType().equals("priceDown")) {
	// // 倒计时,只有直降才会有倒计时
	// if (nowTime != null
	// && !TextUtils.isEmpty(allPromotionList.get(0)
	// .getEndTime())) {
	// try {
	// long endTime = format.parse(
	// allPromotionList.get(0).getEndTime()).getTime();
	// long firstNowTime = format.parse(nowTime).getTime();
	// if (endTime - firstNowTime - accessTime > 0) {
	// product_details_time_clock
	// .setVisibility(View.VISIBLE);
	// if (promotionCountDownTimer != null) {
	// promotionCountDownTimer.cancel();
	// }
	// promotionCountDownTimer = new CcigmallCountDownTimer(
	// product_details_promotion_details, endTime
	// - firstNowTime - accessTime, 1000,
	// "act");
	// promotionCountDownTimer.start();
	// } else {
	// product_details_time_clock.setVisibility(View.GONE);
	// product_details_promotion_details
	// .setText(allPromotionList.get(0)
	// .getPromotionName());
	// }
	// } catch (ParseException e) {
	// e.printStackTrace();
	// }
	//
	// } else {
	// product_details_time_clock.setVisibility(View.GONE);
	// product_details_promotion_details.setText(allPromotionList
	// .get(0).getPromotionName());
	// }
	//
	// } else {
	// product_details_time_clock.setVisibility(View.GONE);
	// product_details_promotion_details.setText(allPromotionList.get(
	// 0).getPromotionName());
	//
	// }
	// // 点击事件
	// product_all_promotion_layout
	// .setOnClickListener(new OnClickListener() {
	// @Override
	// public void onClick(View v) {
	// if (product_details_promotion_lv.getVisibility() == View.GONE) {
	// product_details_promotion_lv
	// .setVisibility(View.VISIBLE);
	// product_all_promotion_layout
	// .setVisibility(View.GONE);
	// }
	// }
	// });
	// }
	// product_details_promotion_lv.setLayoutAdapter(null);
	// product_details_promotion_lv.setLayoutAdapter(new PromotionLvAdapter(
	// product_all_promotion_layout, product_details_promotion_lv,
	// this, allPromotionList, nowTime, accessTime));
	// }
	//
	// /**
	// * 商品名
	// */
	// private void setProductName(boolean isFreeShopping) {
	// String i = String.valueOf(productAtt.getSupply());
	// String productWay = "";
	//
	// if (OrderModel.OrderSupplyType.OverseasDirectMail.getTypeId().equals(i))
	// {
	// productWay = OrderModel.OrderSupplyType.OverseasDirectMail
	// .getDescription();
	// } else if (OrderModel.OrderSupplyType.BondedAreaSend.getTypeId()
	// .equals(i)) {
	// productWay = OrderModel.OrderSupplyType.BondedAreaSend
	// .getDescription();
	// } else if (OrderModel.OrderSupplyType.InternalSend.getTypeId()
	// .equals(i)) {
	// productWay = OrderModel.OrderSupplyType.InternalSend
	// .getDescription();
	// } else if (OrderModel.OrderSupplyType.KoreaDirectMail.getTypeId()
	// .equals(i)) {
	// productWay = OrderModel.OrderSupplyType.KoreaDirectMail
	// .getDescription();
	// } else if (OrderModel.OrderSupplyType.ZhuoYue.getTypeId().equals(i)) {
	// productWay = OrderModel.OrderSupplyType.ZhuoYue.getDescription();
	// }
	//
	// if (isFreeShopping) {
	// txtProductName.setText(productAtt.getB2cProductName());
	// product_details_send_way.setText(R.string.free_express);
	// } else {
	// txtProductName.setText(productAtt.getB2cProductName());
	// product_details_send_way.setText(productWay);
	// }
	//
	// }
	//
	// @Override
	// public void onGetProductDetailFailed(ResponseException e) {
	// ToastUtil.showToastShort(this, "" + e.getResultMsg());
	// finish();
	// }
	//
	// @Override
	// public void onArrivedTop() {
	// llyt_product_pull.setVisibility(View.VISIBLE);
	// }
	//
	// @Override
	// public void onArrivedBottom() {
	// llyt_product_pull.setVisibility(View.GONE);
	// }
	//
	// @Override
	// public void onRequestStart() {
	// loadingDialog.show();
	// }
	//
	// @Override
	// public void onRequestFail(ResponseException e) {
	// ToastUtil.showToastShort(this, e.getResultMsg() + "");
	// }
	//
	// @Override
	// public void onRequestFinish() {
	// loadingDialog.dismiss();
	// }
	//
	// // @Override
	// // public void onUpdateComments(Evaluate comment) {
	// // txt_comment_totalComments.setText("(" + comment.getTotalComments()
	// // + "人评价)");
	// // txt_comment_praise.setText(comment.getPraise());
	// // List<Comment> commentList = comment.getComments();
	// // if (commentList != null && commentList.size() > 0) {
	// // if (null != product_commnets_ll && 0 <
	// // product_commnets_ll.getChildCount()) {
	// // product_commnets_ll.removeAllViews();
	// // }
	// // for (Comment commentItem : commentList) {
	// // View convertView = layoutInflater.inflate(
	// // R.layout.product_comment_summary_item_layout, null);
	// // /** 文字 */
	// // ((TextView) convertView.findViewById(R.id.name))
	// // .setText(TextUtils.isEmpty(commentItem.getUserName()) ? ""
	// // : commentItem.getUserName());
	// // ((TextView) convertView.findViewById(R.id.content))
	// // .setText(commentItem.getValue());
	// // ((TextView) convertView.findViewById(R.id.time))
	// // .setText(commentItem.getCreatTime());
	// // RatingBar ratingBar = (RatingBar) convertView
	// // .findViewById(R.id.ratingBar);
	// // ratingBar.setRating(commentItem.getLevel());
	// // product_commnets_ll.addView(convertView);
	// // }
	// // }
	// // }
	//
	// @Override
	// public void onUpdateViewPager(ProdAttrVal prodAttrVal,
	// OrderModel.OrderSupplyType type) {
	// updateViewPager(prodAttrVal, type);
	// }
	//
	// @Override
	// public void onGetProductDetailFinish() {
	// // TODO 初始化VIEW
	// addDetailsFirst();
	// }
	//
	// private void updateViewPager(ProdAttrVal prodAttrVal,
	// OrderModel.OrderSupplyType type) {
	// pointLinear.removeAllViews();
	// pointLinear.setBackgroundColor(Color.TRANSPARENT);
	// List<String> imageUrls = prodAttrVal.getImages();
	// if (imageUrls != null && imageUrls.size() > 0) {
	// mViewPager.setCurrentItem(0);
	// final int diameter = Misc.dip2px(this, 8);
	// for (int i = 0; i < imageUrls.size(); i++) {
	// LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
	// diameter, diameter);
	// layoutParams.setMargins(6, 0, 6, 0);
	// ImageView pointView = new ImageView(this);
	// pointView.setLayoutParams(layoutParams);
	// if (i == 0) {
	// pointView
	// .setBackgroundResource(R.drawable.recommend_gallery_select);
	// } else
	// pointView
	// .setBackgroundResource(R.drawable.recommend_gallery);
	// pointLinear.addView(pointView);
	// }
	// }
	// productDetailPicAdapter.setType(type);
	// productDetailPicAdapter.setUrls(imageUrls);
	// }
	//
	// /**
	// * 开始计时
	// */
	// private void startJishi() {
	// timer = new Timer();
	// TimerTask tt = new TimerTask() {
	// @Override
	// public void run() {
	// Message msg = new Message();
	// msg.what = 1;
	// msg.obj = 1000l;
	// handler.sendMessage(msg);
	// }
	// };
	// timer.schedule(tt, 0, 1000);
	// }
	//
	// @Override
	// protected void onDestroy() {
	// super.onDestroy();
	//
	// if (timer != null) {
	// timer.cancel();
	// accessTime = 0l;
	// }
	//
	// if (flashCountDownTimer != null) {
	// flashCountDownTimer.cancel();
	// }
	// if (product_details_promotion_lv != null) {
	// BaseAdapter baseAdapter = product_details_promotion_lv
	// .getLayoutAdapter();
	// if (baseAdapter != null
	// && baseAdapter instanceof PromotionLvAdapter) {
	// ((PromotionLvAdapter) baseAdapter).cancelAllTimer();
	// }
	// }
	//
	// }
	//
	// @Override
	// public void onTimeLimit() {
	//
	// }
	//
	// ProductAttrsPopupWindow productAttrsPopupWindow;
	//
	// /**
	// * 显示商品属性
	// */
	// private void showAttrsPopupWindow() {
	// if (productAttrsPopupWindow == null) {
	// productAttrsPopupWindow = new ProductAttrsPopupWindow(this,
	// pop_layout,
	// new ProductAttrsPopupWindow.OnAttrsChangedListener() {
	// @Override
	// public void onAttrsChanged(
	// SkuShowAttr currentSkuShowAttr,
	// ProdAttrVal currentProdAttrVal,
	// int myEditTextNum) {
	// ProductDetailsActivity.this.myEditTextNum = myEditTextNum;
	// ProductDetailsActivity.this.currentSkuShowAttr = currentSkuShowAttr;
	// ProductDetailsActivity.this.currentProdAttrVal = currentProdAttrVal;
	// updateViewPager(currentProdAttrVal, OrderModel
	// .getOrderSupplyTypeById(productAtt
	// .getSupply() + ""));
	// updateSkuAttrs();
	// // ToastUtil.showToastLong(ProductDetailsActivity.this,
	// // "myEditTextNum : " + myEditTextNum);
	// }
	//
	// @Override
	// public void onCarsCountChanged(int num) {
	// SharedPreferencesUtil
	// .setSharedPreferences(
	// SharedPreferencesConstants.FILES.FILE_SHOPPING_CART,
	// SharedPreferencesConstants.PARAMS.CART_COUNT,
	// num);
	// setCatsCount(CarsModel.formatCartCount(num + ""));
	// }
	// });
	// }
	// if (!productAttrsPopupWindow.isShowing()) {
	// productAttrsPopupWindow.initData(this.prod,
	// this.currentSkuShowAttr, this.currentProdAttrVal,
	// this.myEditTextNum);
	// View attrBaseView = findViewById(R.id.attrBaseView);
	// productAttrsPopupWindow.show(attrBaseView);
	// }
	// }
	//
	// /**
	// * 初始化控件
	// */
	// private void initViews() {
	// Intent intent = getIntent();
	//
	// secondView = (ProductDetailsSecondView)
	// findViewById(R.id.prodcut_details_second_view);
	// pid = intent.getStringExtra(EXTRA_PRODUCT_ID);
	// /** "1"表示闪购, "2"表示不是闪购 **/
	// flashBuy = intent.getStringExtra("pageType");
	// flashBuyEndDate = intent.getStringExtra("endDate");
	// loadingDialog = new LoadingDialog(this);
	//
	// bitmapUtils = new BitmapUtils(this);
	// bitmapUtils.configDefaultLoadingImage(R.drawable.img_default_114);
	// bitmapUtils.configDefaultLoadFailedImage(R.drawable.img_default_114);
	// /** init ScrollView Container */
	// DisplayMetrics metric = new DisplayMetrics();
	// getWindowManager().getDefaultDisplay().getMetrics(metric);
	//
	// relativeLayoutCart = (LinearLayout) findViewById(R.id.ll_cart);
	//
	// scrollViewContainer = (ScrollViewContainer)
	// findViewById(R.id.scrollview_container);
	// scrollViewContainer.setOnStateChangedListener(this);
	//
	// rl_sort_price = (LinearLayout) findViewById(R.id.rl_sort_price);
	// rl_sort_sales = (LinearLayout) findViewById(R.id.rl_sort_sales);
	// rl_sort_focus = (LinearLayout) findViewById(R.id.rl_sort_focus);
	//
	// rl_sort_price.setOnClickListener(this);
	// rl_sort_sales.setOnClickListener(this);
	// rl_sort_focus.setOnClickListener(this);
	//
	// progress_bar = (ProgressBar) findViewById(R.id.progress_bar);
	//
	// img_add_shopping_cart = (TextView)
	// findViewById(R.id.img_add_shopping_cart);
	// img_add_shopping_cart.setOnClickListener(this);
	//
	// imageTOP = (ImageView) findViewById(R.id.imgTop);
	// imageTOP.setOnClickListener(new OnClickListener() {
	// @Override
	// public void onClick(View view) {
	// scrollViewContainer.moveToTop();
	// imageTOP.setVisibility(View.GONE);
	// // webView1.getProductWebView().scrollTo(0, 0);
	// }
	// });
	//
	// top_back_btn = (ImageView) findViewById(R.id.top_back_btn);
	// top_menu_btn = (ImageView) findViewById(R.id.top_menu_btn);
	// top_back_btn.setOnClickListener(this);
	// top_menu_btn.setOnClickListener(this);
	//
	// relytTopButtons = (RelativeLayout)
	// findViewById(R.id.product_details_relyt_top_buttons);
	//
	// findViewById(R.id.shopping_cart_layout).setOnClickListener(this);
	//
	// tv_sort_price = (TextView) rl_sort_price
	// .findViewById(R.id.product_detail_tv_sort_price);
	// tv_sort_sales = (TextView) rl_sort_sales
	// .findViewById(R.id.product_detail_tv_sort_sales);
	// tv_sort_focus = (TextView) rl_sort_focus
	// .findViewById(R.id.product_detail_tv_sort_focus);
	//
	// tabview1 = (View) findViewById(R.id.producy_detail_tab_1);
	// tabview2 = (View) findViewById(R.id.producy_detail_tab_2);
	// tabview3 = (View) findViewById(R.id.producy_detail_tab_3);
	//
	// product_details_first_layout = (LinearLayout)
	// findViewById(R.id.product_details_first_layout);
	// product_details_web_layout = (FrameLayout)
	// findViewById(R.id.product_details_web_layout);
	//
	// // 评论
	// // findViewById(R.id.ll_see_all_comments).setOnClickListener(this);
	// // product_commnets_ll = (LinearLayout)
	// // findViewById(R.id.product_commnets_ll);
	// // 一键购买
	// findViewById(R.id.img_onekey_buy).setOnClickListener(this);
	// txt_cars_count = (TextView) findViewById(R.id.txt_cars_count);
	// // 设置分享按钮
	// setRighButtonBackGround(R.drawable.btn_actionbar_share_normal);
	// // 注册分享按钮点击事件
	// setRightButtonOnClickListener(this);
	// mScrollView = (MyScrollView)
	// findViewById(R.id.product_details_scrollview);
	// /*
	// * mScrollView.setOnScrollViewChangeListner(new
	// * MyScrollView.OnScrollViewChangeListner() {
	// *
	// * @Override public void onScrollChanged(int l, int t, int oldl, int
	// * oldt) { int alpha = t * 255 /
	// * (Misc.getScreenDisplay(ProductDetailsActivity.this)[1] / 2); if (0 >
	// * alpha || 255 < alpha) { return; }
	// * relytTopButtons.setBackgroundColor(Color.argb(alpha, 255, 255, 255));
	// * } });
	// */
	//
	// }
	//
	// /**
	// * 添加商品详情描述、规格参数和购买须知
	// */
	// private void addWebView() {
	// product_details_web_layout.removeAllViews();
	// View web_layout = LayoutInflater.from(this).inflate(
	// R.layout.product_details_web_layout, null);
	// webView1 = (GraphicView) web_layout.findViewById(R.id.web_view_1);
	// webView2 = (SampleWebView) web_layout.findViewById(R.id.web_view_2);
	// webView3 = (SampleWebView) web_layout.findViewById(R.id.web_view_3);
	//
	// secondView.addOnPullDownListener(webView1);
	// secondView.addOnPullDownListener(webView2);
	// secondView.addOnPullDownListener(webView3);
	//
	// webView1.getProductWebView().setWebChromeClient(new WebChromeClient() {
	// @Override
	// public void onProgressChanged(WebView view, int progress) {
	// setProgressBarView(progress);
	// }
	// });
	// webView1.getProductWebView().setCustomScroolChangeListener(
	// new ProductWebView.OnCustomScroolChangeListener() {
	// @Override
	// public void onCustomScroolChange(int l, int t, int oldl,
	// int oldt) {
	// if (oldt > 0 && t > 0) {
	// imageTOP.setVisibility(View.VISIBLE);
	// } else {
	// imageTOP.setVisibility(View.GONE);
	// }
	// }
	// });
	//
	// webView2.setWebChromeClient(new WebChromeClient() {
	// @Override
	// public void onProgressChanged(WebView view, int progress) {
	// setProgressBarView(progress);
	// }
	// });
	//
	// webView3.setWebChromeClient(new WebChromeClient() {
	// @Override
	// public void onProgressChanged(WebView view, int progress) {
	// setProgressBarView(progress);
	// }
	// });
	//
	// product_details_web_layout.addView(web_layout);
	// }
	//
	// /**
	// * 添加商品详情描述
	// */
	// private void addDetailsFirst() {
	// product_details_first_layout.removeAllViews();
	// View product_details_first = LayoutInflater.from(this).inflate(
	// R.layout.product_details_first, null);
	// pointLinear = (LinearLayout) product_details_first
	// .findViewById(R.id.gallery_point_linear);
	// mViewPager = (ViewPager) product_details_first
	// .findViewById(R.id.view_pager_product_image);
	// ViewGroup.LayoutParams mViewPagerlayoutParams = mViewPager
	// .getLayoutParams();
	// if (mViewPagerlayoutParams != null) {
	// mViewPagerlayoutParams.width = Misc.getScreenDisplay(this)[0];
	// mViewPagerlayoutParams.height = (int) (mViewPagerlayoutParams.width);
	// mViewPager.setLayoutParams(mViewPagerlayoutParams);
	// }
	// productDetailPicAdapter = new ProductDetailPicAdapter(this,
	// OrderModel.OrderSupplyType.InternalSend);
	// mViewPager.setAdapter(productDetailPicAdapter);
	// mViewPager.setOnPageChangeListener(this);
	//
	// product_details_flash_buy_layout = (LinearLayout) product_details_first
	// .findViewById(R.id.product_details_flash_buy_layout);
	// // 判断闪购是否显示
	// if (flashBuy != null && flashBuy.equals("1")
	// && !TextUtils.isEmpty(flashBuyEndDate)) {
	// product_details_flash_buy_layout.setVisibility(View.VISIBLE);
	// } else {
	// product_details_flash_buy_layout.setVisibility(View.GONE);
	// }
	//
	// product_details_flash_buy_count = (TextView) product_details_first
	// .findViewById(R.id.product_details_flash_buy_count);
	//
	// txtProductName = (TextView) product_details_first
	// .findViewById(R.id.txt_product_name);
	//
	// txtProductUnitPrice = (TextView) product_details_first
	// .findViewById(R.id.txt_product_unit_price);
	// txt_product_tariff = (TextView) product_details_first
	// .findViewById(R.id.txt_product_tariff);
	// img_product_tariff_desc = (ImageView) product_details_first
	// .findViewById(R.id.img_info);
	// img_product_tariff_desc.setOnClickListener(this);
	//
	// product_country_image = (ImageView) product_details_first
	// .findViewById(R.id.product_country_image);
	//
	// txt_product_originplaceName = (TextView) product_details_first
	// .findViewById(R.id.txt_product_originplaceName);
	// product_details_send_way = (TextView) product_details_first
	// .findViewById(R.id.product_details_send_way);
	// product_details_has_promotion_line = product_details_first
	// .findViewById(R.id.product_details_has_promotion_line);
	// llyt_product_promotion = (LinearLayout) product_details_first
	// .findViewById(R.id.llyt_product_promotion);
	// product_all_promotion_layout = (RelativeLayout) product_details_first
	// .findViewById(R.id.product_all_promotion_layout);
	// product_details_promotion_iv_layout = (LinearLayout)
	// product_details_first
	// .findViewById(R.id.product_details_promotion_iv_layout);
	//
	// product_details_time_clock = (ImageView) product_details_first
	// .findViewById(R.id.product_details_time_clock);
	//
	// product_details_promotion_details = (TextView) product_details_first
	// .findViewById(R.id.product_details_promotion_details);
	// product_details_promotion_lv = (LLayoutToListView) product_details_first
	// .findViewById(R.id.product_details_promotion_lv);
	// product_details_selected_attr = (LinearLayout) product_details_first
	// .findViewById(R.id.product_details_selected_attr);
	// product_details_selected_attr.setOnClickListener(this);
	// product_details_selected_attr_txt = (TextView) product_details_first
	// .findViewById(R.id.product_details_selected_attr_txt);
	// product_details_first.findViewById(
	// R.id.product_details_firsr_llyt_send_to).setOnClickListener(
	// this);
	// txtSendTo = (TextView) product_details_first
	// .findViewById(R.id.product_details_firsr_txt_send_to);
	// txtStock = (TextView) product_details_first
	// .findViewById(R.id.product_details_firsr_txt_stock);
	// product_details_firsr_txt_time = (TextView) product_details_first
	// .findViewById(R.id.product_details_firsr_txt_time);
	//
	// llyt_product_pull = (RelativeLayout) product_details_first
	// .findViewById(R.id.llyt_product_pull);
	// llyt_product_pull.setOnClickListener(this);
	//
	// product_details_first_layout.addView(product_details_first);
	//
	// getReceiveArea();// 获取配送地址
	// }
}
