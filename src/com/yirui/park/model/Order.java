package com.yirui.park.model;

public class Order {

	private Parking parking;
	private String price;
	private String update_time;
	private String end_time;
	private String status_value;
	private String status;
	private String create_time;
	private String start_time;
	private String pay_status_value;
	private String order_no;
	private String pay_status;
	public Parking getParking() {
		return parking;
	}
	public void setParking(Parking parking) {
		this.parking = parking;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	public String getStatus_value() {
		return status_value;
	}
	public void setStatus_value(String status_value) {
		this.status_value = status_value;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	public String getPay_status_value() {
		return pay_status_value;
	}
	public void setPay_status_value(String pay_status_value) {
		this.pay_status_value = pay_status_value;
	}
	public String getOrder_no() {
		return order_no;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}
	public String getPay_status() {
		return pay_status;
	}
	public void setPay_status(String pay_status) {
		this.pay_status = pay_status;
	}
	
}
