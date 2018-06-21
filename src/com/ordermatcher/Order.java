package com.ordermatcher;

public class Order implements Comparable<Order> {

	private int volume;
	private int price;
	private String orderType;

	public Order(int volume, int price, String orderType) {
		this.volume = volume;
		this.price = price;
		this.orderType = orderType;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
// Object Sorting
	@Override
	public int compareTo(Order o) {
		return this.price - o.price;
	}

}
