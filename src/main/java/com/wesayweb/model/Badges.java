package com.wesayweb.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity

@Table(name = "badge_master")
@EntityListeners(AuditingEntityListener.class)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Badges implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter
	@Setter
	private Long badgeid;

	@Getter
	@Setter
	private String badgename;

	@Getter
	@Setter
	private String badgedescription;

	@Getter
	@Setter
	private String badgeicon; 
 
	@Getter
	@Setter
	@Column(name = "badgeisactive", nullable = false, columnDefinition = "int default 1")
	private int badgeisactive;
	
	@Getter
	final Date addeddate = new Date();

	@Getter
	@Setter
	@ManyToOne
	private BadgeCategoryMaster catagory;
	//commented for below reason
	//We can use unique badgeId while adding badges to a user, rather then using the same id for all the user
	/*@Getter
	final String badgeuniqueid = WesayStringUtil.generateRandomNumber();*/
	
}