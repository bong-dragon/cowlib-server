package com.cowlib.model;

import lombok.Data;

@Data
public class User {
	private int id;
	private String facebookId;
	private String profile;
	private String name;
	private boolean isDeleted;
}
