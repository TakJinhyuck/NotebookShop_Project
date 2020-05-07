package notebook.controller.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notebook.controller.Controller;
import notebook.controller.ModelAndView;
import notebook.domain.OrderInfo;
import notebook.exception.CannotModifyException;
import notebook.exception.NotEnoughParameterException;
import notebook.service.PurchaseService;

/**
 * ��ǰ ���Ž�
 * @author ���ȣ
 *
 */
public class PurInsertController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String userId = (String)request.getSession().getAttribute("id");
		String totalPrice = request.getParameter("totalPrice");
		String addrDelivery = request.getParameter("addrDelivery");
		ModelAndView mv = new ModelAndView(true, "note?command=purUser");
		
		if(userId == null || userId.equals("") || totalPrice == null || totalPrice.equals("") || addrDelivery == null || addrDelivery.equals("")) {
			throw new NotEnoughParameterException("�Է°��� ������� �ʽ��ϴ�.");
		}
		OrderInfo info = new OrderInfo();
		info.setUserId(userId);
		info.setTotalPrice(Integer.parseInt(totalPrice));
		info.setAddrDelivery(addrDelivery);
		try {
			PurchaseService.purchaseOrder(info);
		}catch (CannotModifyException e) {
			mv.setViewName("purFail.jsp");
		}
		
		return mv;
	}
}
