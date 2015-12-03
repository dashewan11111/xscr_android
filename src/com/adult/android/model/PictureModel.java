package com.adult.android.model;

import android.text.TextUtils;

import com.adult.android.model.constants.ServiceUrlConstants;

/**
 * Created by RoyJing on 15/6/15.
 */
public class PictureModel {
	public static enum PictureFormat {
		/**
		 * /p0/ 720x720 /p1/ 360x360 /p2/ 340x340尺寸图 是否保留？
		 * 
		 * /h0/ 为原图
		 * 
		 * /m0/ 先切720x720后打水印 /m1/ 先切720*720然后水印（即/m0/），再切360*360 /m2/
		 * 先切640x520后打水印 是否保留？
		 */
		P0(""), P1("p1/"),
		// P2("p2/"),
		H0("h0/"), M0("m0/"), M1("m1/");
		// M2("m2/");
		private String format;

		PictureFormat(String format) {
			this.format = format;
		}

		public String getFormat() {
			return format;
		}
	}

	public static enum DisplayModule {
		ProductDetail {
			@Override
			public PictureFormat getPictureFormat(String type) {
				return PictureFormat.P0;
			}
		},
		HomePageDiaplay {
			@Override
			public PictureFormat getPictureFormat(String type) {
				return PictureFormat.P0;
			}
		},
		ProductSpecificationTag {
			@Override
			public PictureFormat getPictureFormat(String type) {
				return PictureFormat.P1;
			}
		},
		ProductDetailLarge {
			@Override
			public PictureFormat getPictureFormat(String type) {
				return PictureFormat.P0;
			}
		},
		ProductList {
			@Override
			public PictureFormat getPictureFormat(String type) {
				return PictureFormat.P0;
			}
		},
		ShoppingCart {
			@Override
			public PictureFormat getPictureFormat(String type) {
				return PictureFormat.P1;
			}
		},
		OrderSubmit {
			@Override
			public PictureFormat getPictureFormat(String type) {
				return PictureFormat.P1;
			}
		},
		OrderList {
			@Override
			public PictureFormat getPictureFormat(String type) {
				return PictureFormat.P1;
			}
		},
		OrderDetailPackage {
			@Override
			public PictureFormat getPictureFormat(String type) {
				return PictureFormat.P1;
			}
		},
		OrderDetailProduct {
			@Override
			public PictureFormat getPictureFormat(String type) {
				return PictureFormat.P1;
			}
		},
		Comments {
			@Override
			public PictureFormat getPictureFormat(String type) {
				return PictureFormat.P1;
			}
		},
		CategoryIcon {
			@Override
			public PictureFormat getPictureFormat(String type) {
				return PictureFormat.P1;
			}
		},
		CountryDisplay {
			@Override
			public PictureFormat getPictureFormat(String type) {
				return PictureFormat.P0;
			}
		},
		CountryIcon {
			@Override
			public PictureFormat getPictureFormat(String type) {
				return PictureFormat.P1;
			}
		},
		SharePicture {
			@Override
			public PictureFormat getPictureFormat(String type) {
				return PictureFormat.P1;
			}
		};
		public abstract PictureFormat getPictureFormat(String type);

		public String urlWithHost(String url, String type) {
			return urlWithHostAndFormat(url, getPictureFormat(type));
		}
	}

	public static String urlWithHostAndFormat(String url, PictureFormat format) {
		String result = "";
		if (TextUtils.isEmpty(url)) {
			result = url;
		} else if (!url.startsWith("http:")) {
			boolean startWithFormat = false;
			for (PictureFormat f : PictureFormat.values()) {
				if (url.startsWith(f.format)) {
					startWithFormat = true;
					break;
				}
			}
			if (startWithFormat || format == null) {
				if (url.startsWith("/")) {
					result = ServiceUrlConstants.getImageHost()
							+ url.substring(1, url.length());
				} else {
					result = ServiceUrlConstants.getImageHost() + url;
				}
			} else {
				if (url.startsWith("/")) {
					result = ServiceUrlConstants.getImageHost() + format.format
							+ url.substring(1, url.length());
				} else {
					result = ServiceUrlConstants.getImageHost() + format.format
							+ url;
				}
			}
		} else {
			result = url;
		}
		return result;
	}
}
