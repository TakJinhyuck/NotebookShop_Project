package notebook.controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import notebook.controller.Controller;
import notebook.controller.ModelAndView;
import notebook.exception.NotEnoughParameterException;
import notebook.service.UserService;

/**
 * ��ȭ��ȣ �ߺ�üũ(ajax���)
 * @author ���ȣ
 *
 */
public class UserDuplicatePhoneController implements Controller{
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String phone = request.getParameter("phone");
		JSONObject jsonObj = new JSONObject();
		request.setAttribute("jsonObj", jsonObj);
		
		if(phone == null || phone.equals("")) {
			jsonObj.put("status", 2);
			throw new NotEnoughParameterException("�Է°��� ������� �ʽ��ϴ�");
		}
		
		try {
			UserService.selectByPhone(phone);
			jsonObj.put("status", 1);
		}catch (Exception e) {
			jsonObj.put("status", 0);
		}
		
		return null;
	}
}
