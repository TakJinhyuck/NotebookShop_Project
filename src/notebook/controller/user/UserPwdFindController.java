package notebook.controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notebook.controller.Controller;
import notebook.controller.ModelAndView;
import notebook.domain.Users;
import notebook.exception.NotEnoughParameterException;
import notebook.service.UserService;

/**
 * ����ȣ�� �亯���� ��й�ȣ ��������
 * @author kosta
 *
 */
public class UserPwdFindController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String phone = request.getParameter("phone");
		String answer = request.getParameter("answer");
		ModelAndView mv = new ModelAndView();
		
		try {
			if(phone == null || phone.equals("") || answer == null || answer.equals("")) {
				throw new NotEnoughParameterException("�Է°��� ������� �ʽ��ϴ�.");
			}
			Users user = UserService.selectByPhoneAns(phone, answer);
			String pwd = user.getPwd();
			request.setAttribute("pwd", pwd);
			mv.setViewName("findByPasswordSuccess.jsp");
		}catch (Exception e) {
			mv.setViewName("findFail.jsp");
			mv.setRedirect(true);
		}
		return mv;
	}

}
