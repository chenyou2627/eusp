package cn.mldn.eusplatform.service.back;

import java.sql.SQLException;
import java.util.List;

import cn.mldn.eusplatform.vo.Item;

public interface IItemServiceBack {
	public List<Item> list()throws SQLException;
}
