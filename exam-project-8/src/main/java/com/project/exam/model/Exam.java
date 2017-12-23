package com.project.exam.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name="exam")
public class Exam {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int exam_id;
	
	@ManyToOne
	@JoinColumn(name="exam_type_id")
	private Exam_type examtype;
	
	@ManyToOne
	@JoinColumn(name="subject_id")
	private Subjects subject;
	
	private String exam_date;
	private int full_marks;
	private int pass_marks;
	private int status;
	private String time_from;
	private String time_to;



	public Exam_type getExamtype() {
		return examtype;
	}

	public void setExamtype(Exam_type examtype) {
		this.examtype = examtype;
	}

	public Subjects getSubject() {
		return subject;
	}

	public void setSubject(Subjects subject) {
		this.subject = subject;
	}




	public int getExam_id() {
		return exam_id;
	}

	public void setExam_id(int exam_id) {
		this.exam_id = exam_id;
	}

	public String getExam_date() {
		return exam_date;
	}

	public void setExam_date(String exam_date) {
		this.exam_date = exam_date;
	}

	public int getFull_marks() {
		return full_marks;
	}

	public void setFull_marks(int full_marks) {
		this.full_marks = full_marks;
	}

	public int getPass_marks() {
		return pass_marks;
	}

	public void setPass_marks(int pass_marks) {
		this.pass_marks = pass_marks;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getTime_from() {
		return time_from;
	}

	public void setTime_from(String time_from) {
		this.time_from = time_from;
	}

	public String getTime_to() {
		return time_to;
	}

	public void setTime_to(String time_to) {
		this.time_to = time_to;
	}

	

	public Exam() {
		
	}

	@Override
	public String toString() {
		return "Exam [exam_id=" + exam_id + ", exam_date=" + exam_date + ", full_marks=" + full_marks + ", pass_marks="
				+ pass_marks + ", status=" + status + ", time_from=" + time_from + ", time_to=" + time_to + "]";
	}
}
