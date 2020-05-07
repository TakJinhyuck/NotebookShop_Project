package notebook.controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notebook.controller.Controller;
import notebook.controller.ModelAndView;
import notebook.domain.Users;
import notebook.exception.NotEnoughParameterException;
import notebook.service.UserService;

/**
 * ȸ������ ����
 * @author kosta
 *
 */
public class UserModifyController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String pwd = request.getParameter("pwd");
		String phone = request.getParameter("phone");
		String addr = request.getParameter("addr");
		String userId = (String)request.getSession().getAttribute("id");
		if(pwd == null || pwd.equals("") || phone == null || phone.equals("") || addr == null || addr.equals("") ||userId == null || userId.equals("")) {
			throw new NotEnoughParameterException("�Է°��� ������� �ʽ��ϴ�.");
		}
		Users user = new Users();
		user.setUserId(userId);
		user.setAddr(addr);
		user.setPhone(phone);
		user.setPwd(pwd);
		
		UserService.updateStatus(user);
		ModelAndView mv = new ModelAndView(true, "myPage.jsp");
		return mv;
	}

}
