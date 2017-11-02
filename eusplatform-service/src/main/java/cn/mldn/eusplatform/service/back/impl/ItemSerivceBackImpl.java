package cn.mldn.eusplatform.service.back.impl;

import java.sql.SQLException;
import java.util.List;

import cn.mldn.eusplatform.dao.IItemDAO;
import cn.mldn.eusplatform.service.back.IItemServiceBack;
import cn.mldn.eusplatform.vo.Item;
import cn.mldn.util.factory.Factory;

public class ItemSerivceBackImpl implements IItemServiceBack {

	@Override
	/**
	 *列出所有的Item对象
	 */
	public List<Item> list() throws SQLException {
		IItemDAO itemDAO = Factory.getDAOInstance("item.dao");
		return itemDAO.findAll();
	}

}
