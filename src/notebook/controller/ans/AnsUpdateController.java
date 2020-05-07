package notebook.controller.ans;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notebook.controller.Controller;
import notebook.controller.ModelAndView;
import notebook.domain.BoardAnswer;
import notebook.exception.NotEnoughParameterException;
import notebook.exception.NotFoundException;
import notebook.service.AnswerService;

public class AnsUpdateController implements Controller {
	
	/**
	 * �Խù��亯 �����ϱ�_content
	 */
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String content = request.getParameter("content");//������ ����
		String ansNo = request.getParameter("ansNo");//������ �亯�Խù� ��ȣ
		String qnaNo = request.getParameter("qnaNo");
		String userId = (String)request.getSession().getAttribute("id");
		if(content == null || content.equals("") || ansNo == null || ansNo.equals("") || qnaNo == null || qnaNo.equals("") || userId == null || userId.equals("")) {
			throw new NotEnoughParameterException("�Է°��� ������� �ʽ��ϴ�.");
		}

		if(userId.equals("admin")) {
			throw new NotFoundException("�����ڸ� �����մϴ�.");
		}
		BoardAnswer ans = new BoardAnswer();
		ans.setAnsNo(Integer.parseInt(ansNo));
		ans.setContent(content);
		AnswerService.update(ans);//�亯 ����
		
		ModelAndView mv = new ModelAndView();
		//�Խù� �󼼺����
		mv.setViewName("");
		return mv;
	}

}


