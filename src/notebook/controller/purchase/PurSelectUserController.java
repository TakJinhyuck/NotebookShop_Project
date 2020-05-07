package notebook.controller.purchase;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notebook.controller.Controller;
import notebook.controller.ModelAndView;
import notebook.domain.OrderInfo;
import notebook.exception.NotEnoughParameterException;
import notebook.service.PurchaseService;

/**
 * ������ ���ų��� ��ü����
 * @author ���ȣ
 *
 */
public class PurSelectUserController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String userId = (String)request.getSession().getAttribute("id");
		
		if(userId == null || userId.equals("")) {
			throw new NotEnoughParameterException("�Է°��� ������� �ʽ��ϴ�.");
		}
		List<OrderInfo> list = PurchaseService.selectById(userId);
		request.setAttribute("list", list);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("orderInfo.jsp");
		response.getWriter().print(list);
		return mv;
	}

}
