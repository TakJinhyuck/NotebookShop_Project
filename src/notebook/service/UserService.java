package notebook.service;

import java.sql.SQLException;
import java.util.List;

import notebook.domain.Users;
import notebook.exception.DuplicateException;
import notebook.exception.NotFoundException;
import notebook.exception.PasswordException;
import notebook.dao.UserDao;
import notebook.dao.UserDaoImpl;

public class UserService {
	private static UserDao userDao = new UserDaoImpl();
	
	public static List<Users> selectAll() throws SQLException{
		List<Users> list = userDao.selectAll();
		return list;
	}
	
	public static void withdrawMember(String userId, String password) throws SQLException, PasswordException {
		int result = userDao.withdrawMember(userId, password);
		if(result == 0) {
			throw new PasswordException("��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
		}
	}
	
	public static void insert(Users user) throws DuplicateException, SQLException {
		Users u = userDao.selectById(user.getUserId());
		if(u != null) {
			throw new DuplicateException("����� �� ���� ���̵� �Դϴ�.");
		}
		
		u = userDao.selectByPhone(user.getPhone());
		if(u != null) {
			throw new DuplicateException("�̹� ��ϵ� ��ȭ��ȣ �Դϴ�.");
		}
		if(userDao.insert(user) == 0) {
			throw new SQLException("��ϵ��� �ʾҽ��ϴ�.");
		}
	}
	
	public static void updateStatus(Users user) throws SQLException {
		if(userDao.updateStatus(user) == 0) {
			throw new SQLException("�������� �ʾҽ��ϴ�.");
		}
	}
	
	public static Users selectById(String userId) throws SQLException, NotFoundException{
		Users user = userDao.selectById(userId);
		if(user == null) {
			throw new NotFoundException("ȸ�� ������ �����ϴ�.");
		}
		return user;
	}
	
	public static Users selectByPhone(String phone) throws SQLException, NotFoundException{
		Users user = userDao.selectByPhone(phone);
		if(user == null) {
			throw new NotFoundException("ã���� �ϴ� ȸ�� ������ �����ϴ�.");
		}
		return user;
	}
	
	public static Users selectByPhoneName(String phone, String name) throws SQLException, NotFoundException{
		Users user = userDao.selectByPhone(phone);
		if(!user.getName().equals(name)) {
			throw new NotFoundException("ã���� �ϴ� ȸ�� ������ �����ϴ�.");
		}
		return user;
	}
	
	public static Users selectByIdPhoneName(String userId, String phone, String name) throws SQLException, NotFoundException{
		Users user = userDao.selectByIdPhone(userId, phone);
		if(!user.getName().equals(name)) {
			throw new NotFoundException("ã���� �ϴ� ȸ�� ������ �����ϴ�.");
		}
		return user;
	}
	
	public static Users selectByPhoneAns(String phone, String answer) throws SQLException, NotFoundException{
		Users user = userDao.selectByPhone(phone);
		if(!user.getAnswer().equals(answer)) {
			throw new NotFoundException("��ġ���� �ʴ� �亯�Դϴ�.");
		}
		return user;
	}
}
