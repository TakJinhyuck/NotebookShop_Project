package notebook.controller.question;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notebook.controller.Controller;
import notebook.controller.ModelAndView;
import notebook.domain.Questions;
import notebook.service.QuestionsService;

public class QueSelectAllController implements Controller {
	

	/**
	 * ȸ�����Խ� ������ �����ֱ�
	 */
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
			List<Questions> list = QuestionsService.selectAll();
			request.setAttribute("list", list);
			
			ModelAndView mv = new ModelAndView();
			mv.setViewName("register.jsp");
			return mv;
			
	}

}
