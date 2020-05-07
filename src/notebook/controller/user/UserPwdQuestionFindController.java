package notebook.controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notebook.controller.Controller;
import notebook.controller.ModelAndView;
import notebook.domain.Users;
import notebook.exception.NotEnoughParameterException;
import notebook.exception.NotFoundException;
import notebook.service.UserService;

/**
 * ����ȣ�� ���̵�� ���� ��������
 * @author ���ȣ
 *
 */
public class UserPwdQuestionFindController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String name = request.getParameter("name");
		String userId = request.getParameter("userId");
		String phone = request.getParameter("phone");
		ModelAndView mv = new ModelAndView();
		
		try {
			if(userId == null || userId.equals("") || phone == null || phone.equals("") || name ==null ||name.equals("")) {
				throw new NotEnoughParameterException("�Է°��� ������� �ʽ��ϴ�.");
			}
			Users user = UserService.selectByIdPhoneName(userId, phone, name);
			if(user.getState() == 2) {
				throw new NotFoundException("������ Ȯ���� �� �����ϴ�.");
			}else {
				String question = user.getQuestion().getQuestion();
				request.setAttribute("phone", phone);
				request.setAttribute("question", question);
				mv.setViewName("findByPassword2.jsp");
			}
		}catch (Exception e) {
			mv.setViewName("findFail.jsp");
			mv.setRedirect(true);
		}
		return mv;
	}

}
