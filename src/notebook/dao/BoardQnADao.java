package notebook.dao;

import java.sql.SQLException;
import java.util.List;

import notebook.domain.BoardQnA;

public interface BoardQnADao {
	/**
	 * �Խù� �ϳ� Ŭ���ؼ� �� ��
	 * @param qnaNo
	 * @return
	 */
	public BoardQnA selectByNo(int qnaNo) throws SQLException;
	
	/**
	 * ��ǰ �󼼺���� qna������ ��
	 * @param serialNum
	 * @return
	 */
	public List<BoardQnA> selectBySerialNum(String serialNum) throws SQLException;
	
	/**
	 * qna�Խ���
	 * @return
	 */
	public List<BoardQnA> selectAll() throws SQLException;
	
	/**
	 * �Խù� ����
	 * @param board
	 * @return
	 */
	public int update(BoardQnA board) throws SQLException;
	
	/**
	 * �Խù� ���
	 * @param board
	 * @return
	 */
	public int insert(BoardQnA board) throws SQLException;
	
	/**
	 * �Խù� ����
	 * @param qnaNo
	 * @return
	 */
	public int delete(int qnaNo, String userId) throws SQLException;
	
	/**
	 * ��ȸ�� ����
	 * @param qnaNo
	 * @return
	 */
	public int increamentView(int qnaNo) throws SQLException;
	
	
	/**
	 * ������ �Խù� ����
	 * @param qnaNo
	 * @return
	 * @throws SQLException
	 */
	public int admindelete(int qnaNo) throws SQLException;

}
