package com.example.dbservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Employee {

	@Id
	private Integer id;

	private String DISP_NAME;
	private String D_TITLE;
	private String JOB_NAME;
	private String D_SEX;
	@Lob
	private byte[] IMAGE;


}
