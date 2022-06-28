package com.kosta.myapp.vo;

//JavaBeans 조건 : �??��?�� ?��근�??��?���? private, default?��?��?��?��?��, getter/setter?��?�� 
//VO(Value Object)
//DTO(Data Transfer Object)
public class Car {

	private String model;
	private int price;
	private String color;
	
	public Car() {
		System.out.println("default?��?��?���? ?��?��?��?�� Car�? 만든?��.");
	}
	
	public Car(String model, int price, String color) {
		super();
		this.model = model;
		this.price = price;
		this.color = color;
		System.out.println("argument3개 Car생성.");
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
		System.out.println("setModel:" + model);
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
		System.out.println("setPrice:" + price);
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
		System.out.println("setColor:" + color);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Car [model=").append(model).append(", price=").append(price).append(", color=").append(color)
				.append("]");
		return builder.toString();
	}
	
	
}
