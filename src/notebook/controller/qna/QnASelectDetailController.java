package notebook.controller.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import notebook.controller.Controller;
import notebook.controller.ModelAndView;
import notebook.domain.BoardQnA;
import notebook.exception.NotFoundException;
import notebook.service.QnAService;

public class QnASelectDetailController implements Controller {

	/**
	 * qna 하나 상세보기_조회수 증가
	 */
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String qnaNo = request.getParameter("qnaNo");
		HttpSession session = request.getSession();
		
		if(qnaNo == null || qnaNo.equals("")) {
			throw new NotFoundException("해당 게시물의 정보가 존재하지 않습니다.");
		}
		String flag = request.getParameter("flag");
		boolean state = (flag == null || flag.equals("")) ? false : true;
		
		BoardQnA board = QnAService.selectByNo(Integer.parseInt(qnaNo), state);
		
		request.setAttribute("board", board);
		
		ModelAndView mv = new ModelAndView(false, "qnaRead.jsp");//게시물 상세보기 폼
		if("admin".equals((String)session.getAttribute("id"))){
			mv.setViewName("qnaReadAdmin.jsp");
		}
		return mv;
	}

}

