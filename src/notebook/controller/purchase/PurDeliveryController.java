package notebook.controller.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notebook.controller.Controller;
import notebook.controller.ModelAndView;
import notebook.exception.NotEnoughParameterException;
import notebook.service.PurchaseService;

/**
 * ��ǰ ������� ����
 * @author ���ȣ
 *
 */
public class PurDeliveryController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String orderNo = request.getParameter("orderNo");
		String userId = (String)request.getSession().getAttribute("id");
		String addrDelivery = request.getParameter("deliveryAddr");
		
		if(orderNo == null || orderNo.equals("") || addrDelivery == null || addrDelivery.equals("") || userId == null || userId.equals("")) {
			throw new NotEnoughParameterException("�Է°��� ������� �ʽ��ϴ�.");
		}
		
		PurchaseService.updateAddr(Integer.parseInt(orderNo), addrDelivery, userId);
		ModelAndView mv = new ModelAndView(false, "note?command=purUser");
		return mv;
	}

}
