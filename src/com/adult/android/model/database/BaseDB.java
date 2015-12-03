package com.adult.android.model.database;

import android.content.Context;

import com.j256.ormlite.android.apptools.OpenHelperManager;

public class BaseDB {
	private Context mContext;
	private DatabaseHelper databaseHelper = null;

	public BaseDB(Context context) {
		mContext = context;
	}

	/**
	 * You'll need this in your class to get the helper from the manager once
	 * per class.
	 */
	protected DatabaseHelper getHelper() {
		if (databaseHelper == null) {
			databaseHelper = OpenHelperManager.getHelper(mContext,
					DatabaseHelper.class);
		}
		return databaseHelper;
	}

	protected void releaseHelper() {
		if (databaseHelper != null) {
			OpenHelperManager.releaseHelper();
			databaseHelper = null;
		}
	}
}
