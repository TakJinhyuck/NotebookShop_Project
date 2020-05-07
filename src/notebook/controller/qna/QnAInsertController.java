package notebook.controller.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notebook.controller.Controller;
import notebook.controller.ModelAndView;
import notebook.domain.BoardQnA;
import notebook.domain.Product;
import notebook.exception.NotEnoughParameterException;
import notebook.service.QnAService;

public class QnAInsertController implements Controller {

	/**
	 * qna ��� , ���� ���
	 */
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//user_id, serialnum, subject, content, password
		String userId = (String)request.getSession().getAttribute("id");
		String subject = request.getParameter("subject");
		String serialNum = request.getParameter("serialNum");
		String content = request.getParameter("content");
		String password = request.getParameter("password");	
		
		if(userId == null || userId.equals("") || subject == null || subject.equals("") ||content == null || content.equals("")) {
			throw new NotEnoughParameterException("�Է°��� ������� �ʽ��ϴ�.");
		}
		
		Product product = new Product();
		product.setSerialNum(serialNum);
		BoardQnA board = new BoardQnA(userId, subject, content, password, product);
		QnAService.insert(board);
			
		ModelAndView mv = new ModelAndView();//false�̸� forward true �̸� redirect
		mv.setRedirect(true);
		if(serialNum == null || serialNum.equals("")) {
			mv.setViewName("note?command=qnaAll");
		}else {
			mv.setViewName("note?command=qnaProduct&serialNum="+serialNum);
		}
		return mv;
	}

}

