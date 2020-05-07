package notebook.dao;

import java.sql.SQLException;
import java.util.List;

import notebook.domain.CartList;

public interface CartListDao {
	/**
	 * ȸ���� ��ٱ��� �� ��
	 * @param userId
	 * @return
	 */
	public List<CartList> selectById(String userId) throws SQLException;
	
	/**
	 * ��ٱ��� �߰�
	 * @param list
	 * @return
	 */
	public int insert(CartList list) throws SQLException;
	
	/**
	 * ��ٱ��� ����(�������� ���)
	 * @param list
	 * @return
	 */
	public int update(CartList list) throws SQLException;
	
	/**
	 * ��ٱ��� ����
	 * @param list
	 * @return
	 * @throws SQLException
	 */
	public int delete(String userId) throws SQLException;
	
	/**
	 * ��ٱ��Ͽ� ��ǰ �ִ��� Ȯ��
	 * @param userId
	 * @param serialNum
	 * @return
	 * @throws SQLException
	 */
	public CartList selectByIdSerialNum(String userId, String serialNum) throws SQLException;
	
	/**
	 * ��ٱ��Ͽ� �ϳ� ����
	 */
	public int deleteOne(String userId, String serialNum) throws SQLException;
}
