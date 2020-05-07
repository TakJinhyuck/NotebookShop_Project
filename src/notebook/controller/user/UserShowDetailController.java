package notebook.controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notebook.controller.Controller;
import notebook.controller.ModelAndView;
import notebook.domain.Users;
import notebook.exception.NotEnoughParameterException;
import notebook.service.UserService;

/**
 * ���� �󼼺���(����������)
 * @author kosta
 *
 */
public class UserShowDetailController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String userId = (String)request.getSession().getAttribute("id");
		
		if(userId == null || userId.equals("")) {
			throw new NotEnoughParameterException("�Է°��� ������� �ʽ��ϴ�.");
		}
		Users user = UserService.selectById(userId);
		request.setAttribute("user", user);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("myInfo.jsp");
		return mv;
	}
}
