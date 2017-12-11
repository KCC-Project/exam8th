package com.project.exam.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Exam {

	private int exam_id;
	private int exam_type_id;
	private int subject_id;
	private String exam_date;
	private int full_marks;
	private int pass_marks;
	private int status;
	private String time_from;
	private String time_to;

	
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

	public int getExam_type_id() {
		return exam_type_id;
	}

	public void setExam_type_id(int exam_type_id) {
		this.exam_type_id = exam_type_id;
	}

	public int getSubject_id() {
		return subject_id;
	}

	public void setSubject_id(int subject_id) {
		this.subject_id = subject_id;
	}

	public Exam(int exam_id, String exam_date, int full_marks, int pass_marks, int status, String time_from,
			String time_to, int exam_type_id, int subject_id) {
		super();
		this.exam_id = exam_id;
		this.exam_date = exam_date;
		this.full_marks = full_marks;
		this.pass_marks = pass_marks;
		this.status = status;
		this.time_from = time_from;
		this.time_to = time_to;
		this.exam_type_id = exam_type_id;
		this.subject_id = subject_id;
	}

	public Exam() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	@Override
	public String toString() {
		return "Exam [exam_id=" + exam_id + ", exam_date=" + exam_date + ", full_marks=" + full_marks + ", pass_marks="
				+ pass_marks + ", status=" + status + ", time_from=" + time_from + ", time_to=" + time_to
				+ ", exam_type_id=" + exam_type_id + ", subject_id=" + subject_id + "]";
	}

}
