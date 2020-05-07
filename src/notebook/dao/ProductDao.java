package notebook.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import notebook.domain.Product;

public interface ProductDao {
	/**
	 * ��ǰ �󼼺���
	 * @param serialNum
	 * @return
	 */
	public Map<String, Object> selectByNum(String serialNum) throws SQLException;

	/**
	 * �������� �˻�
	 * @param minPrice
	 * @param maxPrice
	 * @return
	 */
	public List<Product> selectByPriceRange(int minPrice, int maxPrice) throws SQLException;
	
	/**
	 * �Ż�ǰ 3�� �˻�
	 * @return
	 */
	public List<Product> selectNewProducts() throws SQLException;
	
	/**
	 * ���������� 3�� �˻�
	 * @return
	 */
	public List<Product> selectBestProducts() throws SQLException;
	
	/**
	 * ������ ��ǰ 3�� �˻�
	 * @return
	 */
	public List<Product> selectRendomProducts() throws SQLException;
	
	/**
	 * ��ü��ǰ �����ؼ� �˻� (target�� NULL�̸� ��ȣ�� �ʱⰪ)
	 * @param target
	 * @return
	 */
	public List<Product> selectSortProduct(String target) throws SQLException;

	/**
	 * ȸ��, �𵨸����� ��ǰ �˻�
	 * @param targer
	 * @param search
	 * @return
	 * @throws SQLException
	 */
	public List<Product> searchProduct(String target, String search) throws SQLException;
	/**
	 * ��ǰ ���� ����, ���Ž� ��� ����, ȯ�ҽ� ��� ����, ���� �ű��
	 * ������ ��
	 * @param product
	 * @return
	 */
	public int updateProduct(Product product) throws SQLException;
	
	/**
	 * ��ǰ �߰�
	 * @param product
	 * @return
	 */
	public int insert(Product product) throws SQLException;
	
	/**
	 * ��ǰ ���� �ű��
	 * @param serialNum
	 * @param grade
	 * @return
	 * @throws SQLException
	 */
	public int grantGrade(String serialNum, double grade) throws SQLException;
	
	/**
	 * ��ǰ ��� �߰�, ����
	 * @param serialNum
	 * @param stock
	 * @return
	 * @throws SQLException
	 */
	public int updateStock(String serialNum, int stock) throws SQLException;
	
	/**
	 * ȸ��� �������� ��ǰ�� �˻�
	 * @param company
	 * @return
	 * @throws SQLException
	 */
	public List<Product> selectByCompany(String company) throws SQLException;
}
