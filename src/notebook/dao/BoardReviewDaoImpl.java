package notebook.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import notebook.domain.BoardReview;
import notebook.exception.CannotModifyException;
import notebook.exception.NotFoundException;
import notebook.util.DbUtil;

public class BoardReviewDaoImpl implements BoardReviewDao {
	@Override
	public List<BoardReview> selectBySerialNum(String serialNum) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from board_review where serialnum=? order by review_no";
		List<BoardReview> list = new ArrayList<BoardReview>();
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, serialNum);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				int reviewNo = rs.getInt("review_no");
				String userId = rs.getString("user_id");
				String imgName = rs.getString("img_name");
				String createDate = rs.getString("create_date");
				String content = rs.getString("content");
				int grade = rs.getInt("grade");
				
				list.add(new BoardReview(reviewNo, userId, imgName, createDate, content, serialNum, grade));
			}
		}finally {
			DbUtil.dbClose(con, ps, rs);
		}
		return list;
	}

	@Override
	public BoardReview selectByNo(int reviewNo) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from board_review where review_no=?";
		BoardReview review = null;
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, reviewNo);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				String serialNum = rs.getString("serialNum");
				String userId = rs.getString("user_id");
				String imgName = rs.getString("img_name");
				String createDate = rs.getString("create_date");
				String content = rs.getString("content");
				int grade = rs.getInt("grade");
				
				review = new BoardReview(reviewNo, userId, imgName, createDate, content, serialNum, grade);
			}
		}finally {
			DbUtil.dbClose(con, ps, rs);
		}
		return review;
	}
	
	
	@Override
	public int update(BoardReview review) throws SQLException, CannotModifyException, NotFoundException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		int result = 0;
		try {
			con = DbUtil.getConnection();
			con.setAutoCommit(false);

			//���� �Ű�� ���� ��������
			String sql = "SELECT grade FROM board_review WHERE review_no = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, review.getReviewNo());
			rs = ps.executeQuery();
			
			int myGrade = 0;
			if(rs.next()) {
				myGrade = rs.getInt(1);
			}
			rs.close();
			ps.close();
			
			//�ı� ���� ����
			sql = "UPDATE board_review SET create_date=sysdate, content=?, grade=? WHERE review_no = ? AND user_id = ?";
			
			ps = con.prepareStatement(sql);
			ps.setString(1, review.getContent());
			ps.setInt(2, review.getGrade());
			ps.setInt(3, review.getReviewNo());
			ps.setString(4, review.getUserId());
			if(ps.executeUpdate() ==0) {
				con.rollback();
				throw new CannotModifyException("�ı⸦ ������ �� �����ϴ�.");
			}
			ps.close();
			
			
			//��ǰ�� �ı���� ������ ���� ������ ���Ѵ�.
			sql = "select grade from board_review where serialnum=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, review.getSerialNum());
			rs = ps.executeQuery();
			
			int sum = 0;
			int reviewcnt = 0;
			while(rs.next()) {
				sum += rs.getInt("grade");
				reviewcnt++;
			}
			if(reviewcnt == 0) {
				con.rollback();
				throw new NotFoundException("��ǰ �ı���� ã�� �� �����ϴ�.");
			}
			rs.close();
			ps.close();
			
			//��ǰ ���� �ٽ� ���
			sql = "UPDATE product SET grade = ? WHERE serialnum = ?";
			ps = con.prepareStatement(sql);
			ps.setDouble(1, (double)(sum - myGrade + review.getGrade()) / reviewcnt);
			ps.setString(2, review.getSerialNum());
			
			if(ps.executeUpdate() == 0) {
				con.rollback();
				throw new CannotModifyException("��ǰ ������ ������ �� �����ϴ�.");
			}
			
			con.commit();
		}finally {
			DbUtil.dbClose(con, ps);
		}
		return result;
	}
	
	@Override
	public int delete(int reviewNo, String userId) throws SQLException, NotFoundException, CannotModifyException{
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int result = 0;
		
		try {
			con =DbUtil.getConnection();
			con.setAutoCommit(false);
		
			//��ǰ�� �ı���� ������ ���� ������ ���Ѵ�.
			String sql = "select serialnum, grade from board_review where review_no = ?";
			String serialNum = null ;
			int mygrade = 0;
			
			ps = con.prepareStatement(sql);
			ps.setInt(1, reviewNo);
			rs = ps.executeQuery();
			if(rs.next()) {
				serialNum = rs.getString("serialnum");
				mygrade = rs.getInt("grade");
			}
			if(serialNum == null) {
				con.rollback();
				throw new NotFoundException("�ı� ������ ã�� �� �����ϴ�.");
			}
			ps.close();
			rs.close();
			
			//serialnum�� �����Ѹ����� ������ ã�Ƴ��� reviewcnt�� ��´�.
			//grade�� ��� �հ踦 sum�� ��´�.
			sql = "select grade from board_review where serialnum=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, serialNum);
			rs = ps.executeQuery();
			
			int sum = 0;
			int reviewcnt = 0;
			while(rs.next()) {
				sum += rs.getInt("grade");
				reviewcnt++;
			}
			if(reviewcnt == 0) {
				con.rollback();
				throw new NotFoundException("��ǰ �ı⸦ ã�� �� �����ϴ�.");
			}
			ps.close();
			rs.close();
			
			//�ı� ����
			sql = "DELETE board_review WHERE review_no = ?  AND user_id = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, reviewNo);
			ps.setString(2, userId);
			result = ps.executeUpdate();
			if(result == 0) {
				con.rollback();
				throw new NotFoundException("���� �Ұ��� ");
			}
			ps.close();
			
			//update product grade
			if(reviewcnt > 1) {
				sql = "UPDATE product SET grade = ? WHERE serialnum = ?";
				ps = con.prepareStatement(sql);
				ps.setDouble(1, (double)(sum - mygrade) / (reviewcnt - 1));
				ps.setString(2, serialNum);
				result = ps.executeUpdate();
				if(result == 0) {
					con.rollback();
					throw new CannotModifyException("cannot modify grade");
				}
			}
			con.commit();
		}finally {
			DbUtil.dbClose(con, ps, rs);
		}
		return result;
	}
	
	@Override
	public int insert(BoardReview review) throws SQLException, NotFoundException, CannotModifyException{
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int result = 0;
		try {
			con =DbUtil.getConnection();
			con.setAutoCommit(false);
			
			//���� serialnum�� �����Ѹ����� ������ ã�Ƴ��� reviewcnt�� ��´�.
			//grade�� ��� �հ踦 sum�� ��´�.
			String sql = "select grade from board_review where serialnum=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, review.getSerialNum());
			rs = ps.executeQuery();
			
			int sum = 0;
			int reviewcnt = 0;
				while(rs.next()) {
					sum += rs.getInt("grade");;
					reviewcnt++;
				}
				ps.close();
				rs.close();
			
			//insertReview
			sql = "INSERT INTO board_review(review_no, user_id, serialnum, img_name, content, grade) VALUES(seq_board_review.NEXTVAL, ?, ?, ?, ?, ?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, review.getUserId());
			ps.setString(2, review.getSerialNum());
			ps.setString(3, review.getImgName());
			ps.setString(4, review.getContent());
			ps.setInt(5, review.getGrade());
			result = ps.executeUpdate();
			if(result == 0) {
				con.rollback();
				throw new SQLException("insert fail");
			}
			ps.close();
			
			//update product grade
			sql = "UPDATE product SET grade = ? WHERE serialnum = ?";
			ps = con.prepareStatement(sql);
			double refreshgrade = (double)(review.getGrade() + sum)/(reviewcnt+1);
			ps.setDouble(1, refreshgrade);
			ps.setString(2, review.getSerialNum());
			result = ps.executeUpdate();
			if(result == 0) {
				con.rollback();
				throw new CannotModifyException("cannot modify grade");
			}
			
			con.commit();
		}finally {
			DbUtil.dbClose(con, ps, rs);
			
		}
		
		return result;
	}
	
	@Override
	public List<BoardReview> selectByUserId(String userId) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "select * from board_review where user_id=?";
		List<BoardReview> list = new ArrayList<BoardReview>();
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, userId);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				int reviewNo = rs.getInt("review_no");
				String imgName = rs.getString("img_name");
				String createDate = rs.getString("create_date");
				String content = rs.getString("content");
				String serialNum = rs.getString("serial_num");
				int grade = rs.getInt("grade");
				
				list.add(new BoardReview(reviewNo, userId, imgName, createDate, content, serialNum, grade));
			}

		}finally {
			DbUtil.dbClose(con, ps, rs);
		}

		return list;
	}
}
