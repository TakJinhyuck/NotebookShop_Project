
package notebook.controller.ans;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import notebook.controller.Controller;
import notebook.controller.ModelAndView;
import notebook.exception.NotEnoughParameterException;
import notebook.exception.NotFoundException;
import notebook.service.AnswerService;

public class AnsDeleteController implements Controller {
	
	/**
	 * �亯 ����
	 */
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String ansNo = request.getParameter("ansNo");//������ �亯 �Խù� ��ȣ
		String qnaNo = request.getParameter("qnaNo");
		String userId = (String)request.getSession().getAttribute("id");
		if(ansNo == null || ansNo.equals("") || qnaNo == null || qnaNo.equals("") || userId==null || userId.equals("")) {
			throw new NotEnoughParameterException("�Է°��� ������� �ʽ��ϴ�.");
		}
		if(!userId.equals("admin")) {
			throw new NotFoundException("�����ڸ� �����մϴ�.");
		}
		AnswerService.delete(Integer.parseInt(ansNo), Integer.parseInt(qnaNo));
		
		ModelAndView mv = new ModelAndView();
		mv.setRedirect(true);
		
		//qnaNo������ �����Ѵ�
		mv.setViewName("note?command=qnaDetail&qnaNo="+qnaNo);
		return mv;
	}

}



