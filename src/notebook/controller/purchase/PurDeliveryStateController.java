package notebook.controller.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notebook.controller.Controller;
import notebook.controller.ModelAndView;
import notebook.exception.NotEnoughParameterException;
import notebook.exception.NotFoundException;
import notebook.service.PurchaseService;

/**
 * ��ۻ��� ����(������)
 * @author ���ȣ
 *
 */
public class PurDeliveryStateController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String orderNo = request.getParameter("orderNo");
		String deliveryState = request.getParameter("deliveryState");
		String userId = (String)request.getSession().getAttribute("id");
		System.out.println(orderNo);
		System.out.println(deliveryState);
		System.out.println(userId);
		
		if(orderNo == null || orderNo.equals("") || deliveryState == null || deliveryState.equals("") || userId == null || userId.equals("")) {
			throw new NotEnoughParameterException("�Է°��� ������� �ʽ��ϴ�.");
		}
		if(!"admin".equals(userId)) {
			throw new NotFoundException("�����ڸ� �����մϴ�.");
		}
		PurchaseService.deliveryState(Integer.parseInt(orderNo), Integer.parseInt(deliveryState));
		ModelAndView mv = new ModelAndView(false, "note?command=purAll");
		return mv;
	}

}
