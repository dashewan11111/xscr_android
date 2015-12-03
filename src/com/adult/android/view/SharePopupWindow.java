package com.adult.android.view;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.adult.android.R;
import com.adult.android.entity.ShareInfo;
import com.adult.android.model.share.ShareByQQ;
import com.adult.android.model.share.ShareByQZone;
import com.adult.android.model.share.ShareByWeChat;
import com.adult.android.utils.ImageUtil;

/**
 * Created by huangchao on 2015/6/10.
 */
public class SharePopupWindow extends PopupWindow {
	private View popView;
	private LinearLayout product_detail_trans_layout;
	private Object[] args;
	private Activity context;
	// private ShareBySina shareBySina;
	private String url, firstStr, twoStr, threeStr, fourStr;
	private Boolean isWiboInit = true;

	public SharePopupWindow(Activity context,
			LinearLayout product_detail_trans_layout, Object... args) {
		super();
		this.context = context;
		this.product_detail_trans_layout = product_detail_trans_layout;
		this.args = args;
		setFocusable(true);
		setOutsideTouchable(true);
		setBackgroundDrawable(new ColorDrawable(0));
		// 大于等于5.0以上版本暂不支持新浪微博分享功能
//		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT_WATCH) {
//			isWiboInit = false;
//		} else {
//			// shareBySina = new ShareBySina(context);
//		}
		// 窗口进入和退出的效果
		setAnimationStyle(R.style.win_ani_top_bottom);
		setShareView(R.layout.share_pw);
	}

	/**
	 * 为window添加布局文件
	 * 
	 * @param layoutResId
	 */
	private void setShareView(int layoutResId) {
		popView = LayoutInflater.from(context).inflate(layoutResId, null);
		setContentView(popView);

		setWidth(ImageUtil.getScreenWidth(context));
		setHeight(ImageUtil.getScreenHeight(context) * 4 / 9);

		RelativeLayout.LayoutParams transLayoutParams = (RelativeLayout.LayoutParams) product_detail_trans_layout
				.getLayoutParams();
		transLayoutParams.width = ImageUtil.getScreenWidth(context);
		transLayoutParams.height = ImageUtil.getScreenHeight(context);
		product_detail_trans_layout.setLayoutParams(transLayoutParams);

		// 把整个布局的高度设置的跟popupWindow的高度一样
		LinearLayout share_pw_layout = (LinearLayout) popView
				.findViewById(R.id.share_pw_layout);
		LinearLayout.LayoutParams lLayoutParams = (LinearLayout.LayoutParams) share_pw_layout
				.getLayoutParams();
		lLayoutParams.height = ImageUtil.getScreenHeight(context) * 4 / 9;
		share_pw_layout.setLayoutParams(lLayoutParams);

		SortGridView share_pw_gv = (SortGridView) popView
				.findViewById(R.id.share_pw_gv);
		share_pw_gv.setAdapter(new ShareGvAdapter(context, sharePlatforms()));
		share_pw_gv
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						if (args == null) {
							dismiss();
							return;
						}
						// 活动页url分享http://api.ccigmall.com/view/activity/get/29

						if (args.length == 1) {
							url = replaceHost(args[0]);
							switch (position) {
							case 0:
								// 微信好友分享
								ShareByWeChat shareByWeChat = ShareByWeChat
										.getWeChatInstance();
								shareByWeChat.weChatActShare(context, url,
										false);
								break;
							case 1:
								// 微信朋友圈分享
								ShareByWeChat shareByWeChatMom = ShareByWeChat
										.getWeChatInstance();
								shareByWeChatMom.weChatActShare(context, url,
										true);
								break;
							case 2:
								// QQ分享给好友
								ShareByQQ shareByQQ = ShareByQQ.getQQInstance();
								shareByQQ.qqActShare(context, url);

								break;
							case 3:

								// 分享QQ空间
								ShareByQZone shareByQZone = ShareByQZone
										.getQZoneInstance();
								shareByQZone.qZoneActShare(context, url);
								break;
							case 4:

								// 微博分享
								// if (!shareBySina.isWeiboAppInstalled()) {
								// ToastUtil.showToastShort(context,
								// R.string.sina_weibo_uninstall);
								// } else if
								// (!shareBySina.isWeiboAppSupportAPI()) {
								// ToastUtil
								// .showToastShort(
								// context,
								// R.string.sina_weibo_not_support_share);
								// } else {
								// if (shareBySina.isAuthorised()) {
								// shareBySina.sinaActShare(context, url,
								// shareBySina
								// .readSinaAccessToken());
								// } else {
								// shareBySina.actAuthWeibo(context, url);
								// }
								// }
								break;
							}
							// 商品详情分享
						} else if (args.length == 4) {
							firstStr = replaceHost(args[0]);
							twoStr = replaceHost(args[1]);
							threeStr = replaceHost(args[2]);
							fourStr = replaceHost(args[3]);
							switch (position) {
							case 0:
								// 微信好友分享
								ShareByWeChat shareByWeChat = ShareByWeChat
										.getWeChatInstance();
								shareByWeChat.weChatShare(context, firstStr,
										twoStr, threeStr, fourStr, false);
								break;
							case 1:
								// 微信朋友圈分享
								ShareByWeChat shareByWeChatMom = ShareByWeChat
										.getWeChatInstance();
								shareByWeChatMom.weChatShare(context, firstStr,
										twoStr, threeStr, fourStr, true);
								break;
							case 2:
								// QQ分享给好友
								ShareByQQ shareByQQ = ShareByQQ.getQQInstance();
								shareByQQ.qqShare(context, firstStr, twoStr,
										threeStr, fourStr);
								break;
							case 3:
								// 分享QQ空间
								ShareByQZone shareByQZone = ShareByQZone
										.getQZoneInstance();
								shareByQZone.qZoneShare(context, firstStr,
										twoStr, threeStr, fourStr);
								break;
							case 4:
								// 微博分享
								// if (shareBySina.isWeiboAppInstalled()) {
								// if (shareBySina.isAuthorised()) {
								// shareBySina.sinaShare(context,
								// firstStr, twoStr, threeStr,
								// fourStr, shareBySina
								// .readSinaAccessToken());
								// } else {
								// shareBySina.detailsAuthWeibo(context,
								// firstStr, twoStr, threeStr,
								// fourStr);
								// }
								// } else {
								// ToastUtil.showToastShort(context,
								// R.string.sina_weibo_uninstall);
								// }

								break;
							}
						}
						dismiss();
					}
				});

		LinearLayout share_pw_cacel_layout = (LinearLayout) popView
				.findViewById(R.id.share_pw_cacel_layout);
		share_pw_cacel_layout.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				dismiss();
			}
		});

	}

	private String replaceHost(Object arg) {
		String url = "";
		if (arg != null && arg instanceof String) {
			url = ((String) arg);
			if (url.contains("api.ccigmall.com")) {
				url = url.replace("api.ccigmall.com", "m.91xsj.com");
			} else if (url.contains("kj.ccigmall.com")) {
				url = url.replace("kj.ccigmall.com", "m.91xsj.com");
			}
		}
		return url;
	}

	public void show() {
		product_detail_trans_layout.setVisibility(View.VISIBLE);
		// 位置可以按要求定义
		showAtLocation(popView, Gravity.NO_GRAVITY, 0,
				ImageUtil.getScreenHeight(context) * 5 / 9);
	}

	@Override
	public void dismiss() {
		super.dismiss();
		product_detail_trans_layout.setVisibility(View.GONE);
	}

	public void handleWeiboResponse(Intent intent) {
		if (isWiboInit) {
			// shareBySina.handleWeiboResponse(intent);
		}
	}

	public void authorizeCallBack(int requestCode, int resultCode, Intent data) {
		if (isWiboInit) {
			// shareBySina.authorizeCallBack(requestCode, resultCode, data);
		}
	}

	class ShareGvAdapter extends BaseAdapter {

		private Context context;

		private List<ShareInfo> list;

		public ShareGvAdapter(Context context, List<ShareInfo> list) {
			this.context = context;
			this.list = list;
		}

		@Override
		public int getCount() {
			if (list != null && list.size() > 0) {
				return list.size();
			} else {
				return 0;
			}

		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder vh = null;
			if (convertView == null) {
				vh = new ViewHolder();
				convertView = LayoutInflater.from(context).inflate(
						R.layout.share_gv_item, null);
				vh.share_gv_item_drawable = (ImageView) convertView
						.findViewById(R.id.share_gv_item_drawable);
				vh.share_gv_item_name = (TextView) convertView
						.findViewById(R.id.share_gv_item_name);
				convertView.setTag(vh);
			} else {
				vh = (ViewHolder) convertView.getTag();
			}

			vh.share_gv_item_name.setText(list.get(position).getShareName());

			vh.share_gv_item_drawable.setImageResource(list.get(position)
					.getShareRes());

			return convertView;
		}

		class ViewHolder {
			ImageView share_gv_item_drawable;

			TextView share_gv_item_name;
		}
	}

	/**
	 * 分享的平台
	 * 
	 * @return
	 */
	private List<ShareInfo> sharePlatforms() {
		List<ShareInfo> shareInfos = new ArrayList<ShareInfo>();
		ShareInfo weChatShare = new ShareInfo();
		weChatShare.setShareName(R.string.share_wechat_platform);
		weChatShare.setShareRes(R.drawable.ic_share_wechat);
		shareInfos.add(weChatShare);

		ShareInfo momentsShare = new ShareInfo();
		momentsShare.setShareName(R.string.share_wechat_moments_platform);
		momentsShare.setShareRes(R.drawable.ic_share_moments);
		shareInfos.add(momentsShare);

		ShareInfo qqShare = new ShareInfo();
		qqShare.setShareName(R.string.share_qq_platform);
		qqShare.setShareRes(R.drawable.ic_share_qq);
		shareInfos.add(qqShare);
		ShareInfo qzoneShare = new ShareInfo();
		qzoneShare.setShareName(R.string.share_qzone_platform);
		qzoneShare.setShareRes(R.drawable.ic_share_qzone);
		shareInfos.add(qzoneShare);

		if (isWiboInit) {
			ShareInfo sinaShare = new ShareInfo();
			sinaShare.setShareName(R.string.share_sina_platform);
			sinaShare.setShareRes(R.drawable.ic_share_weibo);
			shareInfos.add(sinaShare);
		}

		return shareInfos;
	}
}
