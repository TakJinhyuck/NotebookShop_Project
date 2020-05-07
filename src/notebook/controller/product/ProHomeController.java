package notebook.controller.product;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notebook.controller.Controller;
import notebook.controller.ModelAndView;
import notebook.domain.Product;
import notebook.service.ProductService;

/**
 * ȭ�� �����ϱ� ���� Controller
 * Ȩ�������� �Ż�ǰ 3��, ����Ʈ ��ǰ 3��, �ƹ� ��ǰ 3�� ��� �Ѹ���
 */
public class ProHomeController implements Controller {
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, List<Product>> map = ProductService.homePage();
		request.setAttribute("map", map);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("index.jsp");
		
		return mv;
	}
}