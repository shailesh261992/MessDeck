package com.app.messdeck.model.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.app.messdeck.customvalidations.IsValidMessDeckServiceDate;
import com.app.messdeck.entity.ServiceType;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;

import net.sf.oval.constraint.Assert;
import net.sf.oval.constraint.CheckWith;
import net.sf.oval.constraint.NotNull;

public class MessDeckServiceInfoDTO extends AbstractDTO {

	@Assert(expr = "_this.vendorId > 0", lang = "js", message = "VendorId can not be less than equal to zero,Only vendors are allowed to publish service")
	private long vendorId;

	@NotNull(message = "Service Type can not be null")
	private ServiceType serviceType;

	@Assert(expr = "_this.capacityOfMembers > 0", lang = "js", message = "Member Capacity must be greater than 0(Zero)")
	private Integer capacityOfMembers;

	@JsonDeserialize(using = LocalTimeDeserializer.class)
	@JsonSerialize(using = LocalTimeSerializer.class)
	@Assert(expr = "_this.startTime < _this.endTime", lang = "js", message = "Service start time must be less than end time")
	private LocalTime startTime;

	@JsonDeserialize(using = LocalTimeDeserializer.class)
	@JsonSerialize(using = LocalTimeSerializer.class)
	private LocalTime endTime;

	@CheckWith(value = IsValidMessDeckServiceDate.class, message = "Date must be greate than eq to current date & must be less than laste date in next month")
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	private LocalDate date;

	private List<ItemDTO> meal;

	@Assert(expr = "_this.cost > 0", lang = "js", message = "Cost must be greater than 0(Zero)")
	private double cost;

	public MessDeckServiceInfoDTO() {

	}

	public MessDeckServiceInfoDTO(long vendorId, ServiceType serviceType, LocalDate date, List<ItemDTO> meal,
			double cost) {
		super();
		this.vendorId = vendorId;
		this.serviceType = serviceType;
		this.date = date;
		this.meal = meal;
		this.cost = cost;
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

	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getVendorId() {
		return vendorId;
	}

	public void setVendor(long vendorId) {
		this.vendorId = vendorId;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public List<ItemDTO> getMeal() {
		return meal;
	}

	public void setMeal(List<ItemDTO> meal) {
		this.meal = meal;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	@Override
	public String toString() {
		return "MessDeckServiceDTO [id=" + id + ", vendor=" + vendorId + ", serviceType=" + serviceType
				+ ", capacityOfMembers=" + capacityOfMembers + ", startTime=" + startTime + ", endTime=" + endTime
				+ ", date=" + date + ", meal=" + meal + ", cost=" + cost + "]";
	}

}
