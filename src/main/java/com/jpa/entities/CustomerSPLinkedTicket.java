package com.jpa.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="pm_cust_sp_ticketmapping")
@NamedQuery(name="CustomerSPLinkedTicket.findAll", query="SELECT p FROM CustomerSPLinkedTicket p")
public class CustomerSPLinkedTicket implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5922625049788660635L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id", unique=true, nullable=false)
	private Long id;
	
	@Column(name="cust_ticket_id")
	private Long custTicketId;
	
	@Column(name="customer_ticket_no")
	private String custTicketNo;
	
	@Column(name="spticket_no")
	private String spTicketNo;
	
	@Column(name="closed_flag")
	private String closedFlag;
	
	@Column(name="closed_time")
	private Date closedTime;
	
	@Column(name="created_on")
	private Date createdOn = new Date();
	
	@Column(name="created_by")
	private String createdBy;

	public CustomerSPLinkedTicket() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCustTicketId() {
		return custTicketId;
	}

	public void setCustTicketId(Long custTicketId) {
		this.custTicketId = custTicketId;
	}

	public String getCustTicketNo() {
		return custTicketNo;
	}

	public void setCustTicketNo(String custTicketNo) {
		this.custTicketNo = custTicketNo;
	}

	public String getSpTicketNo() {
		return spTicketNo;
	}

	public void setSpTicketNo(String spTicketNo) {
		this.spTicketNo = spTicketNo;
	}

	public String getClosedFlag() {
		return closedFlag;
	}

	public void setClosedFlag(String closedFlag) {
		this.closedFlag = closedFlag;
	}

	public Date getClosedTime() {
		return closedTime;
	}

	public void setClosedTime(Date closedTime) {
		this.closedTime = closedTime;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	
	
}
