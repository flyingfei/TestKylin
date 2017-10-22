package com.whf.kylin.po;

public class Segment {
	private String uuid;
	private String name;
	private String status;
	private Long input_records;
	
	private String date_range_start;
	private String date_range_end;
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Long getInput_records() {
		return input_records;
	}
	public void setInput_records(Long input_records) {
		this.input_records = input_records;
	}
	public String getDate_range_start() {
		return date_range_start;
	}
	public void setDate_range_start(String date_range_start) {
		this.date_range_start = date_range_start;
	}
	public String getDate_range_end() {
		return date_range_end;
	}
	public void setDate_range_end(String date_range_end) {
		this.date_range_end = date_range_end;
	}
	@Override
	public String toString() {
		return "Segment [uuid=" + uuid + ", name=" + name + ", status=" + status + ", input_records=" + input_records
				+ ", date_range_start=" + date_range_start + ", date_range_end=" + date_range_end + "]";
	}
	
	
}
