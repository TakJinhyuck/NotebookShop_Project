package notebook.controller.product;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import notebook.controller.Controller;
import notebook.controller.ModelAndView;
import notebook.domain.BoardReview;
import notebook.domain.Product;
import notebook.exception.NotEnoughParameterException;
import notebook.service.ProductService;

/**
 * ��ǰ �ϳ� �󼼺���
 * @author ���ȣ
 *
 */
public class ProShowDetailController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String serialNum = request.getParameter("serialNum");
		
		if(serialNum == null || serialNum.equals("")) {
			throw new NotEnoughParameterException("�Է°��� ������� �ʽ��ϴ�.");
		}
		Map<String, Object> map = ProductService.selectBySerialNum(serialNum);
		request.setAttribute("product", (Product)map.get("product"));
		request.setAttribute("review", (List<BoardReview>)map.get("boardReview"));

		JSONObject jsonObj = JSONObject.fromObject(map.get("product"));
		request.setAttribute("jsonObj", jsonObj);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("product.jsp");

		return mv;
	}
}