package notebook.controller.review;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notebook.controller.Controller;
import notebook.controller.ModelAndView;
import notebook.domain.BoardReview;
import notebook.exception.NotEnoughParameterException;
import notebook.service.ReviewService;

/**
 * ���� ������ �̵��ϱ�
 * @author kosta
 *
 */
public class ReviewUpdateFormController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String userId = (String)request.getSession().getAttribute("id");
		String reviewNo = request.getParameter("reviewNo");
		if(userId == null || userId.equals("") || reviewNo == null || reviewNo.equals("")) {
			throw new NotEnoughParameterException("�Է°��� ������� �ʽ��ϴ�.");
		}
		
		BoardReview review = ReviewService.selectByNo(Integer.parseInt(reviewNo));
		request.setAttribute("review", review);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("�ı�� ����?");
		
		return mv;
	}

}
