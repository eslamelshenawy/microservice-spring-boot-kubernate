package vmware.services.gateway.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;


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
