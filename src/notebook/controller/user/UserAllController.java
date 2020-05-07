package notebook.controller.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notebook.controller.Controller;
import notebook.controller.ModelAndView;
import notebook.domain.Users;
import notebook.service.UserService;

/**
 * ��� ����� ����
 * @author kosta
 */
public class UserAllController implements Controller {	
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Users> list = UserService.selectAll();
		ModelAndView mv = new ModelAndView();
		request.setAttribute("list", list);
		mv.setViewName("personInfoAll.jsp");
		return mv;
	}
}
