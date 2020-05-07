package notebook.dao;

import java.sql.SQLException;
import java.util.List;

import notebook.domain.OrderList;

public interface OrderListDao {
	/**
	 * ��ǰ�� ���ų��� ����
	 * @param serialNum
	 * @return
	 */
	public List<OrderList> selectBySerialNum(String serialNum) throws SQLException;
	
	/**
	 * ���Ž�
	 * @param list
	 * @return
	 */
	public int insert(OrderList list) throws SQLException;
}
