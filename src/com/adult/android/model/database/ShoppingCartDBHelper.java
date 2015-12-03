package com.adult.android.model.database;

import java.sql.SQLException;
import java.util.List;

import android.content.Context;

import com.adult.android.entity.ShoppingCart;
import com.j256.ormlite.dao.Dao;

public class ShoppingCartDBHelper<T extends BaseDB> extends BaseDB {
	private Dao<ShoppingCart, String> dao;

	public ShoppingCartDBHelper(Context context) {
		super(context);
		try {
			dao = getHelper().getShoppingCartDao();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int createOrUpdateAdList(List<ShoppingCart> list) {
		int count = 0;
		for (ShoppingCart entity : list) {
			if (createOrUpdateAd(entity)) {
				count++;
			}
		}
		return count;
	}

	public boolean createOrUpdateAd(ShoppingCart entity) {
		if (entity == null)
			return false;
		try {
			final Dao.CreateOrUpdateStatus status = dao.createOrUpdate(entity);
			if (status.isCreated() || status.isUpdated()) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public List<ShoppingCart> queryForAll() {
		try {
			List<ShoppingCart> list = dao.queryForAll();
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ShoppingCart queryForId(String id) {
		try {
			ShoppingCart info = dao.queryForId(id);
			return info;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean remove(String id) {
		try {
			return dao.deleteById(id) > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
