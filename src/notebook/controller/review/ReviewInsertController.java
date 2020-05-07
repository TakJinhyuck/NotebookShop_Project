 package notebook.controller.review;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notebook.controller.Controller;
import notebook.controller.ModelAndView;
import notebook.domain.BoardReview;
import notebook.exception.NotEnoughParameterException;
import notebook.service.ReviewService;

public class ReviewInsertController implements Controller {
	
	
	
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String userId = (String)request.getSession().getAttribute("id");
		String content = request.getParameter("content");
		String serialNum = request.getParameter("serialNum");
		String grade = request.getParameter("grade");
		
		if(userId == null || userId.equals("") || serialNum == null || serialNum.equals("")
				|| grade==null || grade.equals("") ) {
			
			
			throw new NotEnoughParameterException("�Է°��� ������� �ʽ��ϴ�.");
		}

		BoardReview review = new BoardReview(userId, null, content, serialNum, Integer.parseInt(grade) );
		
		
		ReviewService.insert(review);
		ModelAndView mv = new ModelAndView(true, "note?command=proDetail&serialNum="+serialNum);
		return mv;
	}
}
		
	


