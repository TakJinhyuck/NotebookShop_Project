package notebook.controller.cart;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notebook.controller.Controller;
import notebook.controller.ModelAndView;
import notebook.exception.NotEnoughParameterException;
import notebook.service.CartService;

/**
 * ��ٱ��Ͽ��� �ϳ� ����
 * @author kosta
 *
 */
public class CartDeleteOneController implements Controller{
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String userId = (String)request.getSession().getAttribute("id");
		String serialNum = request.getParameter("serialNum");
		if(userId == null || userId.equals("") || serialNum == null || serialNum.equals("")) {
			throw new NotEnoughParameterException("�Է°��� ������� �ʽ��ϴ�.");
		}
		
		CartService.deleteOne(userId, serialNum);
		ModelAndView mv = new ModelAndView(true, "��ٱ�����������");
		return mv;
	}
}
