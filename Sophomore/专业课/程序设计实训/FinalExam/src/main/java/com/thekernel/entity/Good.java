package com.thekernel.entity;

public class Good {

	private int id; // ��Ʒ���
	private String name; // ��Ʒ����
	private double price; // ��Ʒ�۸�
	private String type; // ��Ʒ���
	private boolean is_delete; // �Ƿ�ɾ��

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isIs_delete() {
		return is_delete;
	}

	public void setIs_delete(boolean is_delete) {
		this.is_delete = is_delete;
	}
	
	public Good() {
		
	}

	public Good(int id, String name, double price, String type) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.type = type;
	}

	@Override
	public String toString() {
		return "Good [id=" + id + ", name=" + name + ", price=" + price + ", type=" + type + ", is_delete=" + is_delete
				+ "]";
	}

}
