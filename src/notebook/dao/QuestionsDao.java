package notebook.dao;

import java.sql.SQLException;
import java.util.List;

import notebook.domain.Questions;

public interface QuestionsDao {
	/**
	 * ȸ�����Խ� ȸ������ ����
	 * @return
	 */
	public List<Questions> selectAll() throws SQLException;
}
