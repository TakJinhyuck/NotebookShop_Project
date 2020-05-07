package notebook.controller.cart;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notebook.controller.Controller;
import notebook.controller.ModelAndView;
import notebook.domain.CartList;
import notebook.domain.Users;
//github.com/KimMinhoJA/notebookShopping
import notebook.service.CartService;
import notebook.service.UserService;

/**
 * �� ��ٱ��� ����
 * @author kosta
 *
 */
public class CartShowMyCartController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String userId = (String)request.getSession().getAttribute("id");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("cart.jsp");
		
		if(userId == null || userId.equals("")) {
			mv.setRedirect(true);
			return mv;
		}
		Users user = UserService.selectById(userId);
		List<CartList> list = CartService.showMyCart(userId);
		request.setAttribute("list", list);
		request.setAttribute("addr", user.getAddr());
		return mv;
	}
}
