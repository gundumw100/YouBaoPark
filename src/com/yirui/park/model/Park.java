package com.yirui.park.model;

/**
 * 停车行为
 * @author Administrator
 *
 */
public class Park {

	private String in_gate_name;
	private String in_time;
	private String status;
	private String should_leave_time;
	private String park_no;
	private String park_id;
	private String parking_id;
	private String parking_name;
	private String payed_price;
	private Parking parking;
	
	public String getParking_name() {
		return parking_name;
	}
	public void setParking_name(String parking_name) {
		this.parking_name = parking_name;
	}
	public String getPayed_price() {
		return payed_price;
	}
	public void setPayed_price(String payed_price) {
		this.payed_price = payed_price;
	}
	public String getIn_gate_name() {
		return in_gate_name;
	}
	public void setIn_gate_name(String in_gate_name) {
		this.in_gate_name = in_gate_name;
	}
	public String getIn_time() {
		return in_time;
	}
	public void setIn_time(String in_time) {
		this.in_time = in_time;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getShould_leave_time() {
		return should_leave_time;
	}
	public void setShould_leave_time(String should_leave_time) {
		this.should_leave_time = should_leave_time;
	}
	public String getPark_no() {
		return park_no;
	}
	public void setPark_no(String park_no) {
		this.park_no = park_no;
	}
	public String getPark_id() {
		return park_id;
	}
	public void setPark_id(String park_id) {
		this.park_id = park_id;
	}
	public String getParking_id() {
		return parking_id;
	}
	public void setParking_id(String parking_id) {
		this.parking_id = parking_id;
	}
	public Parking getParking() {
		return parking;
	}
	public void setParking(Parking parking) {
		this.parking = parking;
	}
	
}
