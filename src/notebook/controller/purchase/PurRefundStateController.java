package notebook.controller.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notebook.controller.Controller;
import notebook.controller.ModelAndView;
import notebook.exception.NotEnoughParameterException;
import notebook.service.PurchaseService;

/**
 * ȯ�� ��û�ϱ�
 * @author ���ȣ
 *
 */
public class PurRefundStateController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String orderNo = request.getParameter("orderNo");
		String req = request.getParameter("request");
		String userId = (String)request.getSession().getAttribute("id");
		
		if(orderNo == null|| orderNo.equals("")) {
			throw new NotEnoughParameterException("�Է°��� ������� �ʽ��ϴ�.");
		}
		
		//true�� ȯ�ҿ�û
		boolean refund = req == null || req.equals("") ? false : true;
		PurchaseService.refundState(Integer.parseInt(orderNo), refund, userId);
		ModelAndView mv = new ModelAndView(false, "note?command=purUser");
		
		return mv;
	}

}
