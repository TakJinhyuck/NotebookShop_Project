package notebook.controller.purchase;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notebook.controller.Controller;
import notebook.controller.ModelAndView;
import notebook.domain.OrderInfo;
import notebook.service.PurchaseService;

/**
 * ���ų��� ��ü ��ȸ
 * @author ���ȣ
 *
 */
public class PurSelectAllController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<OrderInfo> list = PurchaseService.selectAll();
		request.setAttribute("list", list);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("manager_order.jsp");
		return mv;
	}

}
