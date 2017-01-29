package com.cowlib.jpa.model;

import java.util.List;

import lombok.Data;

@Data
public class Book {

	BookMeta bookMeta;
	List<CirculationRecord> circulationRecordList;
}
