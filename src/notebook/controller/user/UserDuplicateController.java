package notebook.controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import notebook.controller.Controller;
import notebook.controller.ModelAndView;
import notebook.exception.NotEnoughParameterException;
import notebook.service.UserService;

/**
 * ���̵� �ߺ� üũ(ajax���)
 * @author ���ȣ
 *
 */
public class UserDuplicateController implements Controller {
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String userId = request.getParameter("userId");
		JSONObject jsonObj = new JSONObject();
		request.setAttribute("jsonObj", jsonObj);
		
		if(userId == null || userId.equals("")) {
			jsonObj.put("status",2);	//�Է�x
			throw new NotEnoughParameterException("�Է°��� ������� �ʽ��ϴ�.");
		}
		
		try {
			UserService.selectById(userId);
			jsonObj.put("status", 1);	//�ߺ�
		}catch (Exception e) {
			jsonObj.put("status", 0);	//�ߺ��ƴ�
		}
		
		return null;
	}
}
