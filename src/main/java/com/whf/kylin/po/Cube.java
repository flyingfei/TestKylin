package com.whf.kylin.po;

import java.util.List;

public class Cube {

	private String uuid;
	private Integer input_records_count;
	private String status ;
	private String name;
	private List<Segment> segments;
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public Integer getInput_records_count() {
		return input_records_count;
	}
	public void setInput_records_count(Integer input_records_count) {
		this.input_records_count = input_records_count;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Segment> getSegments() {
		return segments;
	}
	public void setSegments(List<Segment> segments) {
		this.segments = segments;
	}
	@Override
	public String toString() {
		return "Cube [uuid=" + uuid + ", input_records_count=" + input_records_count + ", status=" + status + ", name="
				+ name + ", segments=" + segments + "]";
	}
	
	
	
	
}
