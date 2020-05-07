package notebook.controller.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notebook.controller.Controller;
import notebook.controller.ModelAndView;
import notebook.exception.NotEnoughParameterException;
import notebook.exception.NotFoundException;
import notebook.service.PurchaseService;

/**
 * ȯ�ұ��
 * @author ���ȣ
 *
 */
public class PurRefundController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String orderNo = request.getParameter("orderNo");
		String userId = (String)request.getSession().getAttribute("id");
		
		if(orderNo == null || orderNo.equals("") || userId == null || userId.equals("")) {
			throw new NotEnoughParameterException("�Է°��� ������� �ʽ��ϴ�.");
		}
		if(!"admin".equals(userId)) {
			throw new NotFoundException("�����ڸ� �����մϴ�.");
		}
		
		PurchaseService.refundOrder(Integer.parseInt(orderNo));
		ModelAndView mv = new ModelAndView(false, "note?command=purAll");
		return mv;
	}

}
