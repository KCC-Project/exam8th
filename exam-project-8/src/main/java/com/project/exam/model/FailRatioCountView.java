package com.project.exam.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Immutable;

@XmlRootElement
@Entity
@Table(name="fail_ratio_count_view ")
@Immutable
public class FailRatioCountView {
	
	@Id
	private long id;
	
	@Column
	private long science_totalstudent;
	
	@Column
	private long management_totalstudent;
	
	@Column
	private long arts_totalstudent;
	
	@Column
	private long law_totalstudent;
	
	@Column
	private long science_fail_totalstudent;
	
	@Column
	private long management_fail_totalstudent;
	
	@Column
	private long arts_fail_totalstudent;
	
	@Column
	private long law_fail_totalstudent;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getScience_totalstudent() {
		return science_totalstudent;
	}

	public void setScience_totalstudent(long science_totalstudent) {
		this.science_totalstudent = science_totalstudent;
	}

	public long getManagement_totalstudent() {
		return management_totalstudent;
	}

	public void setManagement_totalstudent(long management_totalstudent) {
		this.management_totalstudent = management_totalstudent;
	}

	public long getArts_totalstudent() {
		return arts_totalstudent;
	}

	public void setArts_totalstudent(long arts_totalstudent) {
		this.arts_totalstudent = arts_totalstudent;
	}

	public long getLaw_totalstudent() {
		return law_totalstudent;
	}

	public void setLaw_totalstudent(long law_totalstudent) {
		this.law_totalstudent = law_totalstudent;
	}

	public long getScience_fail_totalstudent() {
		return science_fail_totalstudent;
	}

	public void setScience_fail_totalstudent(long science_fail_totalstudent) {
		this.science_fail_totalstudent = science_fail_totalstudent;
	}

	public long getManagement_fail_totalstudent() {
		return management_fail_totalstudent;
	}

	public void setManagement_fail_totalstudent(long management_fail_totalstudent) {
		this.management_fail_totalstudent = management_fail_totalstudent;
	}

	public long getArts_fail_totalstudent() {
		return arts_fail_totalstudent;
	}

	public void setArts_fail_totalstudent(long arts_fail_totalstudent) {
		this.arts_fail_totalstudent = arts_fail_totalstudent;
	}

	public long getLaw_fail_totalstudent() {
		return law_fail_totalstudent;
	}

	public void setLaw_fail_totalstudent(long law_fail_totalstudent) {
		this.law_fail_totalstudent = law_fail_totalstudent;
	}
	
	
}
