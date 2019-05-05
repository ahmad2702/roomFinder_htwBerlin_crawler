package com.sadullaev.htw.ai.bachelor.lsfCrawler.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "events")
public class Event {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
	
	@Column(name = "date")
    private Date date;
	
	@Column(name = "begin")
    private double begin;
	
	@Column(name = "end")
    private double end;
	
	@Column(name = "lsf_nr")
    private int lsfNr;
	
	@Column(name = "name")
    private String name;
	
	@Column(name = "lsf_id")
    private int lsfId;
	
	@Column(name = "building")
    private String building;
	
	@Column(name = "room")
    private String room;
	
	@Column(name = "lecturer")
    private String lecturer;
	
	public Event() {
		
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getBegin() {
		return begin;
	}

	public void setBegin(double begin) {
		this.begin = begin;
	}

	public double getEnd() {
		return end;
	}

	public void setEnd(double end) {
		this.end = end;
	}

	public int getLsfNr() {
		return lsfNr;
	}
	
	public void setLsfNr(String lsfNr) {
		if(!lsfNr.equals("")){
			this.lsfNr = Integer.parseInt(lsfNr);
		}
	}

	public void setLsfNr(int lsfNr) {
		this.lsfNr = lsfNr;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLsfId() {
		return lsfId;
	}

	public void setLsfId(int lsfId) {
		this.lsfId = lsfId;
	}

	public String getBuilding() {
		return building;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public String getLecturer() {
		return lecturer;
	}

	public void setLecturer(String lecturer) {
		this.lecturer = lecturer;
	}

	@Override
	public String toString() {
		return "Event [date=" + date + ", begin=" + begin + ", end=" + end + ", lsfNr=" + lsfNr + ", name=" + name
				+ ", lsfId=" + lsfId + ", building=" + building + ", room=" + room + ", lecturer=" + lecturer + "]";
	}
	
	
	
	
	
	
	
}
