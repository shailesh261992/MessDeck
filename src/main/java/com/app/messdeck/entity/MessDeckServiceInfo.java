package com.app.messdeck.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.app.messdeck.serializer.LocalDateAttributeConverter;
import com.app.messdeck.serializer.LocalTimeAttributeConverter;

@Entity
public class MessDeckServiceInfo extends AbstractEntity {
	@ManyToOne
	@JoinColumn(nullable = false, name = "vendorID")
	private Vendor vendor;

	@Column(nullable = false)
	private ServiceType serviceType;

	@Convert(converter = LocalDateAttributeConverter.class)
	private LocalDate date;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "Service_Meal", joinColumns = @JoinColumn(name = "serviceId"), inverseJoinColumns = @JoinColumn(name = "itemId"))
	private List<Item> meal;

	private double cost;

	@ManyToMany(mappedBy = "subscribedServices")
	private List<Customer> subscribers;

	private Integer capacityOfMembers;

	@Convert(converter = LocalTimeAttributeConverter.class)
	private LocalTime startTime;

	@Convert(converter = LocalTimeAttributeConverter.class)
	private LocalTime endTime;

	public MessDeckServiceInfo() {

	}

	public MessDeckServiceInfo(Vendor vendor, ServiceType serviceType, LocalDate date, List<Item> meal, double cost) {
		super();
		this.vendor = vendor;
		this.serviceType = serviceType;
		this.date = date;
		this.meal = meal;
		this.cost = cost;
	}

	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}

	public ServiceType getServiceType() {
		return serviceType;
	}

	public void setServiceType(ServiceType serviceType) {
		this.serviceType = serviceType;
	}

	public Integer getCapacityOfMembers() {
		return capacityOfMembers;
	}

	public void setCapacityOfMembers(Integer capacityOfMembers) {
		this.capacityOfMembers = capacityOfMembers;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public LocalTime getEndTime() {
		return endTime;
	}

	public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}

	public ServiceType serviceType() {
		return serviceType;
	}

	public void setService(ServiceType serviceType) {
		this.serviceType = serviceType;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public List<Item> getMeal() {
		return meal;
	}

	public void setMeal(List<Item> meal) {
		this.meal = meal;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public List<Customer> getSubscribers() {
		return subscribers;
	}

	public void setSubscribers(List<Customer> subscribers) {
		this.subscribers = subscribers;
	}

	@Override
	public String toString() {
		return "MessDeckService [id=" + id + ", vendor=" + vendor + ", serviceType=" + serviceType + ", date=" + date
				+ ", meal=" + meal + ", cost=" + cost + ", capacityOfMembers=" + capacityOfMembers + ", startTime="
				+ startTime + ", endTime=" + endTime + "]";
	}

}
