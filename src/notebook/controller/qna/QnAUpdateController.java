
package notebook.controller.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notebook.controller.Controller;
import notebook.controller.ModelAndView;
import notebook.domain.BoardQnA;
import notebook.exception.NotEnoughParameterException;
import notebook.service.QnAService;

public class QnAUpdateController implements Controller {

	/**
	 * �Խù� �����ϱ� _ ���� ���� ��й�ȣ ����
	 */
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//subject, content, password
				String subject = request.getParameter("subject");
				String content = request.getParameter("content");
				String password = request.getParameter("password");
				String qnaNo = request.getParameter("qnaNo");
				String userId = (String)request.getSession().getAttribute("id");
				if(subject == null || subject.equals("") || content == null || content.equals("")||
						qnaNo == null || qnaNo.equals("") || userId==null || userId.equals("")) {
					throw new NotEnoughParameterException("�Է°��� ������� �ʽ��ϴ�.");
				}
				BoardQnA board = new BoardQnA(Integer.parseInt(qnaNo) ,userId, password);
				board.setSubject(subject);
				board.setContent(content);
				
				QnAService.update(board);//������Ʈ
				ModelAndView mv = new ModelAndView();
				//�Խù� �󼼷�
				mv.setRedirect(true);
				mv.setViewName("note?command=qnaAll");
				return mv;
	}

}

