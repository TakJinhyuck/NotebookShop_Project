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
 * ���� �ڵ������� ���̵� ã�� -> ���� ��������
 * @author ���ȣ
 *
 */
public class UserIdQuestionFindController implements Controller{
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		ModelAndView mv = new ModelAndView();
		try {
			if(phone == null||phone.equals("") || name == null || name.equals("")) {
				throw new NotEnoughParameterException("�Է°��� ������� �ʽ��ϴ�.");
			}
			Users user = UserService.selectByPhoneName(phone, name);
			if(user.getState() == 2) {
				throw new NotFoundException("������ Ȯ���� �� �����ϴ�.");
			}else {
				String question = user.getQuestion().getQuestion();
				//�ڵ��� ��ȣ�� �亯���� ã�ƿ���
				request.setAttribute("question", question);
				request.setAttribute("phone", phone);
				mv.setViewName("findById2.jsp");
			}
		}catch (Exception e) {
			mv.setViewName("findFail.jsp");
			mv.setRedirect(true);
		}
		
		return mv;
	}
}
