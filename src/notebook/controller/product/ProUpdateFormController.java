package notebook.controller.product;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notebook.controller.Controller;
import notebook.controller.ModelAndView;
import notebook.domain.Product;
import notebook.exception.NotEnoughParameterException;
import notebook.exception.NotFoundException;
import notebook.service.ProductService;

public class ProUpdateFormController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String serialNum = request.getParameter("serialNum");
		String userId = (String)request.getSession().getAttribute("id");
		if(serialNum == null || serialNum.equals("") || userId ==null || userId.equals("")) {
			throw new NotEnoughParameterException("입력값이 충분하지 않습니다.");
		}
		
		if(!"admin".equals(userId)) {
			throw new NotFoundException("관리자 기능입니다.");
		}
		
		Map<String, Object> map = ProductService.selectBySerialNum(serialNum);
		Product product = (Product)map.get("product");
		
		request.setAttribute("product", product);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("manager_productUpdate.jsp");
		
		return mv;
	}

}
