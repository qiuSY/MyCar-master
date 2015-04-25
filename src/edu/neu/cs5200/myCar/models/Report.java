package edu.neu.cs5200.myCar.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import org.codehaus.jackson.annotate.JsonBackReference;

@Entity
public class Report {
	@Id
	@SequenceGenerator( name = "mySeq", sequenceName = "MY_SEQ", allocationSize = 1, initialValue = 1 )
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator="mySeq")
	private Integer ReportId;
	private String Reason;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonBackReference
	@JoinColumn(name="userid")
	private User user;
	
	public Integer getReportId() {
		return ReportId;
	}
	public void setReportId(Integer reportId) {
		ReportId = reportId;
	}
	public String getReason() {
		return Reason;
	}
	public void setReason(String reason) {
		Reason = reason;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Report(Integer reportId, String reason, User user) {
		super();
		ReportId = reportId;
		Reason = reason;
		this.user = user;
	}
	public Report() {
		super();
	}
	
	
}
