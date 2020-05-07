package notebook.controller;

/**
 * ��û ����� ���� ������ ������ ��� view�̸��� �̵���Ŀ� ���� ����
 * @author kosta
 *
 */
public class ModelAndView {
	private boolean isRedirect;	//�̵���� ���� (ture�̸� redirect, fasle�̸� forward)
	private String viewName;	//������̸�
	public ModelAndView() {
		super();
	}
	public ModelAndView(boolean isRedirect, String viewName) {
		this.isRedirect = isRedirect;
		this.viewName = viewName;
	}
	public boolean isRedirect() {
		return isRedirect;
	}
	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}
	public String getViewName() {
		return viewName;
	}
	public void setViewName(String viewName) {
		this.viewName = viewName;
	}
	
	
}
