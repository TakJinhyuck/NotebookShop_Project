package notebook.dao;

import java.sql.SQLException;
import java.util.List;

import notebook.domain.OrderInfo;
import notebook.exception.CannotModifyException;
import notebook.exception.NotFoundException;

public interface OrderInfoDao {
	/**
	 * ��ȣ�� ���ų��� �˻�(���� �󼼺���)
	 * @param orderNo
	 * @return
	 */
	public OrderInfo selectByNo(int orderNo) throws SQLException;
	
	/**
	 * ������ ���ų��� ����(���ų�����)
	 * @param userId
	 * @return
	 */
	public List<OrderInfo> selectById(String userId) throws SQLException;
	
	/**
	 * ��ü ���ų���(������)
	 * @return
	 */
	public List<OrderInfo> selectAll() throws SQLException;
	
	/**
	 * ��ۻ��� ����
	 * @param info
	 * @return
	 */
	public int updateDelivery(int orderNo, int deliveryState) throws SQLException;
	
	/**
	 * ȯ�� ��û
	 * @param orderNo
	 * @param request  true�� ȯ�ҿ�û, false�� ȯ�����
	 * @return
	 * @throws SQLException
	 */
	public int updateRefundRequest(int orderNo, boolean request, String userId) throws SQLException;
	
	/**
	 * ����� ����
	 * @param orderNo
	 * @param addrDelivery
	 * @return
	 * @throws SQLException
	 */
	public int updateAddr(int orderNo, String addrDelivery, String userId) throws SQLException;
	
	/**
	 * ȯ��(������)
	 * @param orderNo
	 * @return
	 */
	public void delete(int orderNo) throws SQLException, NotFoundException, CannotModifyException;
	
	/**
	 * ���Ž�
	 * @param info
	 * @return
	 */
	public void insert(OrderInfo info) throws SQLException, NotFoundException, CannotModifyException;
}
