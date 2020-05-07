package notebook.controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notebook.controller.Controller;
import notebook.controller.ModelAndView;
import notebook.exception.NotEnoughParameterException;
import notebook.service.UserService;

/**
 * ȸ��Ż�� ��� / ��Ȱ��ȭ ���
 * @author kosta
 *
 */
public class UserWithdrawalController implements Controller {

   @Override
   public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
      String adminId = (String)request.getSession().getAttribute("id");
      String userId=request.getParameter("id");
      String password = request.getParameter("password");
      
      ModelAndView mv;
      if((adminId).equals("admin")) {
          mv = new ModelAndView(true, "note?command=userAll");
      }else {
          mv = new ModelAndView(true, "note");
      }

      if(userId == null || userId.equals("") || password == null || password.equals("")) {
         throw new NotEnoughParameterException("�Է°��� ������� �ʽ��ϴ�.");
      }
      
      UserService.withdrawMember(userId, password);
      request.getSession().removeAttribute("id");
      
      return mv;
   }
}