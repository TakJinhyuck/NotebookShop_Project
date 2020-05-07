package notebook.controller.ans;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notebook.controller.Controller;
import notebook.controller.ModelAndView;
import notebook.domain.BoardAnswer;
import notebook.exception.NotEnoughParameterException;
import notebook.exception.NotFoundException;
import notebook.service.AnswerService;

public class AnsUpdateFormController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String ansNo = request.getParameter("ansNo");
		String userId = (String)request.getSession().getAttribute("id");
		
		if(ansNo == null || ansNo.equals("") || userId == null || userId.equals("")) {
			throw new NotEnoughParameterException("�Է°��� ������� �ʽ��ϴ�.");
		}

		if(userId.equals("admin")) {
			throw new NotFoundException("�����ڸ� �����մϴ�.");
		}
		
		BoardAnswer answer = AnswerService.selectByNo(Integer.parseInt(ansNo));
		request.setAttribute("answer", answer);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("�亯 ���� ������ �̵�");
		
		return mv;
	}

}
