package notebook.dao;

import java.sql.SQLException;
import java.util.List;

import notebook.domain.BoardAnswer;
import notebook.exception.CannotModifyException;
import notebook.exception.NotFoundException;

public interface BoardAnswerDao {
	/**
	 * �亯 ����
	 * @param answer
	 * @return
	 */
	public int update(BoardAnswer answer) throws SQLException;
	
	/**
	 * �亯 ����
	 * @param ansNo
	 * @return
	 */
	public int delete(int ansNo, int qnaNo) throws SQLException, NotFoundException, CannotModifyException;
	
	/**
	 * �亯 ���
	 * @param answer
	 * @return
	 */
	public int insert(BoardAnswer answer) throws SQLException, CannotModifyException;
	
	/**
	 * �Խù� �亯 ��������
	 */
	public List<BoardAnswer> selectByQnaNo(int qnaNo) throws SQLException;
	
	/**
	 * �亯 ���� ������ �̵��� �� ���
	 * @param ansNo
	 * @return
	 * @throws SQLException
	 */
	public BoardAnswer selectByNo(int ansNo) throws SQLException;
}
