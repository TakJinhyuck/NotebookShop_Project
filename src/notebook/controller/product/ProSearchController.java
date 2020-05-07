package notebook.controller.product;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notebook.controller.Controller;
import notebook.controller.ModelAndView;
import notebook.domain.Product;
import notebook.exception.NotEnoughParameterException;
import notebook.service.ProductService;

/**
 * ȸ��, �𵨸����� ��ǰ�� �˻��ϱ�
 * @author ���ȣ
 *
 */
public class ProSearchController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String target = request.getParameter("target");
		String search = request.getParameter("search");
		
		if(target == null || target.equals("") || search == null || search.equals("")) {
			throw new NotEnoughParameterException("�Է°��� ������� �ʽ��ϴ�.");
		}
		List<Product> list = ProductService.searchProduct(target, search);
		

		PagingObject pageObj = new PagingObject();
		pageObj.setAllRecord(list.size());
		pageObj.setTotalPage(list.size() / 12 + (list.size() % 12 == 0 ? 0 : 1));
		
		String page = request.getParameter("page");
		if(page == null || page.equals("")) {
			page = "1";
		}
		
		int pageInt = Integer.parseInt(page);
		if(pageInt >= pageObj.getTotalPage()) {
			pageInt = pageObj.getTotalPage();
		}else if(pageInt < 1) {
			pageInt = 1;
		}
		
		list = list.subList((pageInt - 1) * pageObj.getPageRecord(), pageInt * pageObj.getPageRecord());
		request.setAttribute("list", list);
		request.setAttribute("pageObj", pageObj);
		
		
		request.setAttribute("list", list);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("��ǰ �˻� ����");
		return mv;
	}

}
