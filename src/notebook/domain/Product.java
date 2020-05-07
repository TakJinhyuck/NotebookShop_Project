package notebook.domain;

import java.util.List;

public class Product {
	private String serialNum;//	�Ϸù�ȣ
	private String modelName;//	�𵨸�
	private String company;//	ȸ��
	private int price;//	����
	private int ram;//		��
	private String cpu;//	cpu
	private int noteSize;//	ȭ��ũ��
	private double noteWeight;//	����
	private String launchDate;//	�����
	private int stock;//	���
	private double grade;//	����
	private String descriptionImgName;//	��ǰ�󼼻���
	private String imgName;
	public Product() {
		super();
	}
	
	
	public Product(String modelName, String company, int price, int ram, String cpu, int noteSize, double noteWeight,
			String launchDate, String descriptionImgName, String imgName) {
		super();
		this.modelName = modelName;
		this.company = company;
		this.price = price;
		this.ram = ram;
		this.cpu = cpu;
		this.noteSize = noteSize;
		this.noteWeight = noteWeight;
		this.launchDate = launchDate;
		this.descriptionImgName = descriptionImgName;
		this.imgName = imgName;
	}

	


	public Product(String modelName, String company, int price, int ram, String cpu, int noteSize, double noteWeight,
			String launchDate) {
		super();
		this.modelName = modelName;
		this.company = company;
		this.price = price;
		this.ram = ram;
		this.cpu = cpu;
		this.noteSize = noteSize;
		this.noteWeight = noteWeight;
		this.launchDate = launchDate;
	}


	public Product(String serialNum, String modelName, String company, int price, int ram, String cpu, int noteSize,
			double noteWeight, int stock, String descriptionImgName, String imgName) {
		super();
		this.serialNum = serialNum;
		this.modelName = modelName;
		this.company = company;
		this.price = price;
		this.ram = ram;
		this.cpu = cpu;
		this.noteSize = noteSize;
		this.noteWeight = noteWeight;
		this.stock = stock;
		this.descriptionImgName = descriptionImgName;
		this.imgName = imgName;
	}


	public Product(String serialNum, String modelName, String company, int price, int ram, String cpu, int noteSize,
			double noteWeight, String launchDate, int stock, double grade, String descriptionImgName, String imgName) {
		super();
		this.serialNum = serialNum;
		this.modelName = modelName;
		this.company = company;
		this.price = price;
		this.ram = ram;
		this.cpu = cpu;
		this.noteSize = noteSize;
		this.noteWeight = noteWeight;
		this.launchDate = launchDate;
		this.stock = stock;
		this.grade = grade;
		this.descriptionImgName = descriptionImgName;
		this.imgName = imgName;
	}
	public String getSerialNum() {
		return serialNum;
	}
	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getRam() {
		return ram;
	}
	public void setRam(int ram) {
		this.ram = ram;
	}
	public String getCpu() {
		return cpu;
	}
	public void setCpu(String cpu) {
		this.cpu = cpu;
	}
	public int getNoteSize() {
		return noteSize;
	}
	public void setNoteSize(int noteSize) {
		this.noteSize = noteSize;
	}
	public double getNoteWeight() {
		return noteWeight;
	}
	public void setNoteWeight(double noteWeight) {
		this.noteWeight = noteWeight;
	}
	public String getLaunchDate() {
		return launchDate;
	}
	public void setLaunchDate(String launchDate) {
		this.launchDate = launchDate;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public double getGrade() {
		return grade;
	}
	public void setGrade(double grade) {
		this.grade = grade;
	}
	public String getDescriptionImgName() {
		return descriptionImgName;
	}
	public void setDescriptionImgName(String descriptionImgName) {
		this.descriptionImgName = descriptionImgName;
	}
	public String getImgName() {
		return imgName;
	}
	public void setImgName(String imgName) {
		this.imgName = imgName;
	}
	
	 
	
}
