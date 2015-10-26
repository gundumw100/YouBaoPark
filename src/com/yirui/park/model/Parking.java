package com.yirui.park.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;

/**
 * 停车场
 * @author Administrator
 *
 */
public class Parking implements Parcelable {

	// "id": "3712",
	// "name": "上海浦东香格里拉大酒店地下车库",
	// "address": "富城路33号",
	// "longitude": "121.499691",
	// "latitude": "31.235894",
	// "free": "0",
	// "total": "134",
	// "price": "0.0元/小时",
	// "description": "不对外只对住宿提供",
	// "image": "upload/cppark/20140111131433967c03.jpg",
	// "bookable": "0",
	// "phone": "18988888888",
	// "has_discount"："1"
	private String id;
	private String name;
	private String address;
	private String longitude;
	private String latitude;
	private String free;
	private String total;
	private String price;
	private String description;
	private String image;
	private String bookable;
	private String phone;
	private String has_discount;
	private Policy policy;
	private String distance;
	private boolean isOpen;//是否打开下滑菜单条

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	public Policy getPolicy() {
		return policy;
	}

	public void setPolicy(Policy policy) {
		this.policy = policy;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getFree() {
		return free;
	}

	public void setFree(String free) {
		this.free = free;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getBookable() {
		return bookable;
	}

	public void setBookable(String bookable) {
		this.bookable = bookable;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getHas_discount() {
		return has_discount;
	}

	public void setHas_discount(String has_discount) {
		this.has_discount = has_discount;
	}

	public boolean isOpen() {
		return isOpen;
	}

	public void setOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}

	public int describeContents() {
		return 0;
	}

	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(id);
		dest.writeString(name);
		dest.writeString(address);
		dest.writeString(longitude);
		dest.writeString(latitude);
		dest.writeString(free);
		dest.writeString(total);
		dest.writeString(price);
		dest.writeString(description);
		dest.writeString(image);
		dest.writeString(bookable);
		dest.writeString(phone);
		dest.writeString(has_discount);
		dest.writeString(distance);
	}

	public static final Parcelable.Creator<Parking> CREATOR = new Creator<Parking>() {
		public Parking createFromParcel(Parcel source) {
			Parking instance = new Parking();
			instance.id = source.readString();
			instance.name = source.readString();
			instance.address = source.readString();
			instance.longitude = source.readString();
			instance.latitude = source.readString();
			instance.free = source.readString();
			instance.total = source.readString();
			instance.price = source.readString();
			instance.description = source.readString();
			instance.image = source.readString();
			instance.bookable = source.readString();
			instance.phone = source.readString();
			instance.has_discount = source.readString();
			instance.distance = source.readString();
			return instance;
		}

		public Parking[] newArray(int size) {
			return new Parking[size];
		}
	};

}
