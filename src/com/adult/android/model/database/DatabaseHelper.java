/*
 * Created by Jing YuChuan on 14-8-1 上午11:24.
 * Copyright (c) 2014 RmbCash. All rights reserved.
 */

package com.adult.android.model.database;

import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.adult.android.entity.ShoppingCart;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

/**
 * Database helper class used to manage the creation and upgrading of database.
 * This class also usually provides the DAOs used by the other classes.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
	// name of the database file for application -- change to something
	// appropriate for your app
	private static final String DATABASE_NAME = "ccigmall.db";
	// any time you make changes to your database objects, you may have to
	// increase the database version
	private static final int DATABASE_VERSION = 1;

	// the DAO object we use to access the Data table
	private Dao<ShoppingCart, String> ShoppingCartDao = null;

	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	/**
	 * This is called when the database is first created. Usually you should
	 * call createTable statements here to create the tables that will store
	 * your data.
	 */
	@Override
	public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
		try {
			TableUtils.createTable(connectionSource, ShoppingCart.class);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * This is called when your application is upgraded and it has a higher
	 * version number. This allows you to adjust the various data to match the
	 * new version number.
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource,
			int oldVersion, int newVersion) {
		// try {
		// TableUtils.dropTable(connectionSource, Advertisement.class, true);
		// // after we drop the old databases, we create the new ones
		// onCreate(db, connectionSource);
		// } catch (SQLException e) {
		// Log.e(DatabaseHelper.class.getName(), "Can't drop databases", e);
		// throw new RuntimeException(e);
		// }
	}

	public Dao<ShoppingCart, String> getShoppingCartDao() throws SQLException {
		if (ShoppingCartDao == null) {
			ShoppingCartDao = getDao(ShoppingCart.class);
		}
		return ShoppingCartDao;
	}

	/**
	 * Close the database connections and clear any cached DAOs.
	 */
	@Override
	public void close() {
		super.close();
		ShoppingCartDao = null;
	}
}
