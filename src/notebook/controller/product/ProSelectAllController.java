package notebook.controller.product;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notebook.controller.Controller;
import notebook.controller.ModelAndView;
import notebook.domain.Product;
import notebook.service.ProductService;

/**
 * ��ü��ǰ �����ؼ� ����
 * @author ���ȣ
 *
 */
public class ProSelectAllController implements Controller {
   @Override
   public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

      String target = request.getParameter("target");
      
      List<Product> list = ProductService.selectAllSortProduct(target);
      PagingObject pageObj = new PagingObject();
      
      
      request.setAttribute("list", list);
      request.setAttribute("pageObj", pageObj);
      ModelAndView mv = new ModelAndView();
      mv.setViewName("manager_product.jsp");
      return mv;
   }
}