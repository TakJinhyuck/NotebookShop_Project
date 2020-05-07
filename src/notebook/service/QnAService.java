package notebook.service;

import java.sql.SQLException;
import java.util.List;

import notebook.dao.BoardAnswerDaoImpl;
import notebook.dao.BoardQnADao;
import notebook.dao.BoardQnADaoImpl;
import notebook.domain.BoardAnswer;
import notebook.domain.BoardQnA;
import notebook.domain.Product;
import notebook.exception.CannotModifyException;
import notebook.exception.NotFoundException;

public class QnAService {
	
	private static BoardQnADao bd = new BoardQnADaoImpl();
	
	/**
	 * �Խù� �󼼺���_��ȸ�� ����.
	 * @param qnaNo
	 * @param flag
	 * @return
	 * @throws SQLException
	 * @throws NotFoundException
	 */
	public static BoardQnA selectByNo(int qnaNo, boolean flag) throws SQLException, NotFoundException{
		if(flag) {
			if(bd.increamentView(qnaNo) == 0) {
				throw new SQLException("��ȸ�� ������ ������ �߻��߽��ϴ�.");
			}
		}
		BoardQnA boardQna = bd.selectByNo(qnaNo);
		if(boardQna == null) {
			throw new NotFoundException("�ش��ϴ� �Խù� ������ �ҷ� �� �� �����ϴ�.");
		}
		List<BoardAnswer> list = new BoardAnswerDaoImpl().selectByQnaNo(qnaNo);
		boardQna.setAnswers(list);
		
		return boardQna;
	}
	
	public static List<BoardQnA> selectBySerialNum(String serialNum) throws SQLException {
		List<BoardQnA> list = bd.selectBySerialNum(serialNum);
		
		return list;
		
	}
	
	/**
	 * �Խù� ��ü����
	 * @return
	 * @throws SQLException
	 * @throws NotFoundException 
	 */
	public static List<BoardQnA> selectAll() throws SQLException, NotFoundException {
		List<BoardQnA> list = bd.selectAll();
		return list;
	}
	
	/**
	 * �Խù� �����ϱ�
	 * @throws CannotModifyException 
	 */
	public static void update(BoardQnA board) throws SQLException, CannotModifyException{
		BoardQnADao b = new BoardQnADaoImpl();
		int result = b.update(board);
		if(result == 0) {
			throw new CannotModifyException("������ �Ұ��� �մϴ�.");
		}
	}
	
	/**
	 * �Խù� ����ϱ�(���� ��)
	 */
	public static void insert(BoardQnA board) throws SQLException{
		BoardQnADao b = new BoardQnADaoImpl();
		b.insert(board);
	}
	
	/**
	 * �Խù� ����
	 * @param qnaNo
	 * @throws SQLException
	 * @throws CannotModifyException
	 */
	public static void delete(int qnaNo, String userId) throws SQLException, CannotModifyException {
		int result = bd.delete(qnaNo, userId);
		if(result == 0) {
			throw new CannotModifyException("�������� �ʾҽ��ϴ�.");
		}
	}
	
	/**
	 * ������ �Խù� ����
	 * @param qnaNo
	 * @throws SQLException
	 * @throws CannotModifyException
	 */
	public static void admindelete(int qnaNo) throws SQLException, CannotModifyException{
		int result = bd.admindelete(qnaNo);
		if(result == 0) {
			throw new CannotModifyException("�������� �ʾҽ��ϴ�.");
		}
	}
	
}
