package com.adult.android.model;

import java.util.Map;

import com.adult.android.R;
import com.adult.android.entity.ProdAttrVal;
import com.adult.android.entity.ProductDetail;
import com.adult.android.logic.UserLogic;
import com.adult.android.model.constants.ServiceUrlConstants;
import com.adult.android.model.internet.exception.ResponseException;
import com.adult.android.model.listener.SampleModelListener;
import com.adult.android.presenter.AgentApplication;
import com.adult.android.utils.ActionTool;

public class ProductDetailModel {
	/**
	 * 促销活动
	 */
	public enum PromotionType {
		WuHuodong(new int[] { -3 }, AgentApplication.get().getString(
				R.string.product_no_act), 0, 1), ManFan(
				new int[] { 22, 23, 30 }, AgentApplication.get().getString(
						R.string.man_fan), R.drawable.manfan, 2), ManZeng(
				new int[] { 24, 25, 29 }, AgentApplication.get().getString(
						R.string.man_zeng), R.drawable.manzeng, 3), ManJian(
				new int[] { 20, 21 }, AgentApplication.get().getString(
						R.string.man_jian), R.drawable.manjian, 4), MaiZeng(
				new int[] { 26 }, AgentApplication.get().getString(
						R.string.mai_zeng), R.drawable.maizeng, 5), ZhiJiang(
				new int[] { 27, 28 }, AgentApplication.get().getString(
						R.string.zhi_jiang), R.drawable.zhijiang, 6);
		private int[] typeId;
		private String description;
		private int iconResId;
		private int level; // 优先级，1为最低，以此递增。

		PromotionType(int[] typeId, String description, int iconResId, int level) {
			this.typeId = typeId;
			this.description = description;
			this.iconResId = iconResId;
			this.level = level;
		}

		public String getDescription() {
			return description;
		}

		public int[] getTypeId() {
			return typeId;
		}

		public int getIconResId() {
			return iconResId;
		}

		public int getLevel() {
			return level;
		}
	}

	/**
	 * 跨境购：10+ 直邮：海外直邮 11 备货：由国内保税区发货 12 一般贸易：0-9 国内现货（不是跨境购商品）1（默认值）
	 */
	public void initProductDetail(final InitProductDetailListener listener,
			String pid, String key) {
		// UserInfo userInfo = UserLogic.getInsatnace().getUserBean();
		Map<String, String> maps;
		if (UserLogic.getInsatnace().getIsLogin()) {
			maps = ActionTool.getActionSignParams(ServiceUrlConstants.MOTHOD,
					"product.detail.get", "pid", pid, "platformType", "1",
					"userId", UserLogic.getInsatnace().getUserBean()
							.getUserId());
		} else {
			maps = ActionTool.getActionSignParams(ServiceUrlConstants.MOTHOD,
					"product.detail.get", "pid", pid, "platformType", "1",
					"reqKey", key);
		}
		String url = ActionTool.getActionUrl(ServiceUrlConstants.getApiHost(),
				maps);

		// InternetClient.get(url, null, ProductDetailResponse.class,
		// new HttpResponseListener<ProductDetailResponse>() {
		// @Override
		// public void onStart() {
		// listener.onRequestStart();
		// }
		//
		// @Override
		// public void onSuccess(ProductDetailResponse t) {
		// ResponseException responseException = new ResponseException();
		// ProductDetailsDto productDetail = t.getData();
		// if (productDetail == null) {
		// responseException.setResultMsg("获取商品信息失败");
		// listener.onGetProductDetailFailed(responseException);
		// return;
		// }
		// ProductAtt productAtt = productDetail.getProduct();
		// if (productAtt == null) {
		// responseException.setResultMsg("获取商品信息失败");
		// listener.onGetProductDetailFailed(responseException);
		// return;
		// }
		// listener.onGetProductDetailFinish();
		// productAtt.setB2cDescription(ServiceUrlConstants
		// .getImageHost()
		// + productAtt.getB2cDescription());
		// ShowProdAttr showProdAttr = productAtt
		// .getShowProdAttr();
		// if (showProdAttr != null) {
		// List<ProdAttrVal> prodAttrVals = showProdAttr
		// .getProdAttrVals();
		// if (prodAttrVals != null && prodAttrVals.size() > 0) {
		// listener.onUpdateViewPager(
		// prodAttrVals.get(0),
		// OrderModel
		// .getOrderSupplyTypeById(productAtt
		// .getSupply() + ""));
		// }
		//
		// List<SkuShowAttr> SkuShowAttrs = productAtt
		// .getSkuShowList();
		// if (SkuShowAttrs != null && SkuShowAttrs.size() > 0) {
		//
		// }
		// }
		// listener.onRequestSuccess(productDetail);
		// // Evaluate comment = productDetail.getComment();
		// // if (comment != null) {
		// // listener.onUpdateComments(comment);
		// // }
		// }
		//
		// @Override
		// public void onHttpException(HttpResponseException e) {
		// e.setResultMsg("加载失败，请重试。");
		// listener.onGetProductDetailFailed(e);
		// }
		//
		// @Override
		// public void onBusinessException(BusinessException e) {
		// e.setResultMsg("加载失败，请重试。");
		// listener.onGetProductDetailFailed(e);
		// }
		//
		// @Override
		// public void onOtherException(Throwable throwable) {
		// ResponseException re = new ResponseException(throwable);
		// re.setResultMsg("加载失败，请重试。");
		// listener.onGetProductDetailFailed(re);
		// }
		//
		// @Override
		// public void onFinish() {
		// listener.onRequestFinish();
		// }
		// });
	}

	public interface InitProductDetailListener extends
			SampleModelListener<ProductDetail> {
		void onUpdateViewPager(ProdAttrVal prodAttrVal, String type);

		void onGetProductDetailFinish();

		// void onUpdateComments(Evaluate comment);

		void onTimeLimit();

		void onGetProductDetailFailed(final ResponseException e);

	}
}
