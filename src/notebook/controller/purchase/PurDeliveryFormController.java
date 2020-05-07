package notebook.controller.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notebook.controller.Controller;
import notebook.controller.ModelAndView;
import notebook.domain.OrderInfo;
import notebook.exception.NotEnoughParameterException;
import notebook.service.PurchaseService;

/**
 * ����� �����ϱ� ��
 * @author kosta
 *
 */
public class PurDeliveryFormController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String orderNo = request.getParameter("orderNo");
		String userId = (String)request.getSession().getAttribute("id");
		if(orderNo == null || orderNo.equals("") || userId == null || userId.equals("")) {
			throw new NotEnoughParameterException("�Է°��� ������� �ʽ��ϴ�.");
		}
		OrderInfo info = PurchaseService.selectByNo(Integer.parseInt(orderNo), userId);
		request.setAttribute("info", info);
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("deliveryUpdate.jsp");
		return mv;
	}

}
