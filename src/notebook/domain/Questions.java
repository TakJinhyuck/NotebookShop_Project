package notebook.domain;

public class Questions {
	private int queNo; //������ȣ
	private String question; //��������
	
	public Questions() {
		super();
	}
	public Questions(int queNo, String question) {
		super();
		this.queNo = queNo;
		this.question = question;
	}
	public int getQueNo() {
		return queNo;
	}
	public void setQueNo(int queNo) {
		this.queNo = queNo;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	
	
}
