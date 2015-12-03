package com.adult.android.model;

import java.util.Map;

import com.adult.android.entity.ProductCommentsInfo;
import com.adult.android.entity.ProductCommentsResponse;
import com.adult.android.model.constants.ServiceUrlConstants;
import com.adult.android.model.internet.InternetClient;
import com.adult.android.model.internet.exception.BusinessException;
import com.adult.android.model.internet.exception.HttpResponseException;
import com.adult.android.model.internet.exception.ResponseException;
import com.adult.android.model.internet.listener.HttpResponseListener;
import com.adult.android.utils.ActionTool;

/**
 * @ClassName: CommentModel
 * @Description:
 * @author JingYuchuan
 * @date 2015年3月31日 下午8:39:43
 * 
 */
public class CommentModel {
	/**
	 * 评论类型(请求类型 0:全部 1:好评 2:中评 3:差评)
	 */
	public static enum CommentType {
		ALL("全部", 0), GOOD("好评", 1), OK("中评", 2), BAD("差评", 3);
		private String tag;
		private int type;

		private CommentType(String tag, int type) {
			this.tag = tag;
			this.type = type;
		}

		public String getTag() {
			return tag;
		}

		public int getType() {
			return type;
		}
	}

	public void getCommentListOfProduct(final String pid,
			final int currentPage, final int pageSize,
			final CommentType commentType,
			final OnGetProductCommentsListener listener) {
		Map<String, String> maps = ActionTool.getActionSignParams(
				ServiceUrlConstants.MOTHOD, "comment.list.get");
		/**
		 * 
		 */
		String url = ActionTool.getActionUrl(ServiceUrlConstants.getApiHost(),
				maps, "pid", pid, "page", "" + (currentPage + 1), "pageSize",
				"" + pageSize, "commentType", commentType.getType() + "");
		InternetClient.get(url, null, ProductCommentsResponse.class,
				new HttpResponseListener<ProductCommentsResponse>() {
					@Override
					public void onStart() {

					}

					@Override
					public void onSuccess(ProductCommentsResponse t) {
						listener.onGetProductComments(t.getData());
					}

					@Override
					public void onHttpException(HttpResponseException e) {
						listener.onGetProductCommentsFail(e);
					}

					@Override
					public void onBusinessException(BusinessException e) {
						listener.onGetProductCommentsFail(e);
					}

					@Override
					public void onOtherException(Throwable throwable) {
						ResponseException re = new ResponseException(throwable);
						re.setResultMsg("获取评论发生异常，请稍后重试。");
						listener.onGetProductCommentsFail(re);
					}

					@Override
					public void onFinish() {

					}
				});
	}

	public static interface OnGetProductCommentsListener {
		void onGetProductComments(ProductCommentsInfo info);

		void onGetProductCommentsFail(final ResponseException e);
	}
}
