package notebook.controller.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notebook.controller.Controller;
import notebook.controller.ModelAndView;
import notebook.domain.BoardQnA;
import notebook.exception.NotEnoughParameterException;
import notebook.exception.NotFoundException;
import notebook.service.QnAService;

public class QnAUpdateFormController implements Controller {
	
	/**
	 * �����ϱ� ��ư ������ ������ �̵�
	 */
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String qnaNo = request.getParameter("qnaNo");
		String userId = (String)request.getSession().getAttribute("id");
		if(qnaNo == null || qnaNo.equals("") || userId == null || userId.equals("")) {
			throw new NotEnoughParameterException("�Է°��� ������� �ʽ��ϴ�.");
		}
		String flag = request.getParameter("flag");
		boolean state = flag == null ? false : true;
		
		BoardQnA board = QnAService.selectByNo(Integer.parseInt(qnaNo), state);
		
		if(!board.getUserId().equals(userId)) {
			throw new NotFoundException("����ڰ� ��ġ���� �ʽ��ϴ�.");
		}
		
		request.setAttribute("board", board);
		ModelAndView mv = new ModelAndView(false, "");//�Խù� �����ϱ�.jsp
		return mv;
		
	}

}
