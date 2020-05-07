package notebook.service;

import java.sql.SQLException;
import java.util.List;

import notebook.dao.BoardAnswerDao;
import notebook.dao.BoardAnswerDaoImpl;
import notebook.domain.BoardAnswer;
import notebook.exception.CannotModifyException;
import notebook.exception.NotFoundException;

public class AnswerService {
	private static BoardAnswerDao ans = new BoardAnswerDaoImpl();
	
	public static void delete(int ansNo, int qnaNo) throws SQLException, NotFoundException, CannotModifyException{
		int result = ans.delete(ansNo, qnaNo);
	}
	
	/**
	 * �亯���_�亯 ���� 1�� ����(0:�̴亯, 1:�亯�Ϸ�)
	 * @param answer
	 * @throws SQLException
	 * @throws CannotModifyException
	 */
	public static void insert(BoardAnswer answer) throws SQLException, CannotModifyException{
		int result = ans.insert(answer);
	}
	
	/**
	 * �亯 ����
	 * @param answer
	 * @throws SQLException
	 * @throws CannotModifyException
	 */
	public static void update(BoardAnswer answer) throws SQLException, CannotModifyException{
		int result = ans.update(answer);
		if(result == 0) {
			throw new CannotModifyException("�������� �ʾҽ��ϴ�.");
		}
	}
	
	public static BoardAnswer selectByNo(int ansNo) throws SQLException, NotFoundException{
		BoardAnswer answer = ans.selectByNo(ansNo);
		if(answer == null) {
			throw new NotFoundException("�亯�� ã�� �� �����ϴ�.");
		}
		return answer;
	}
	
	public static List<BoardAnswer> selectByQnaNo(int qnaNo) throws SQLException{
		List<BoardAnswer> list = ans.selectByQnaNo(qnaNo);
		return list;
	}
}
