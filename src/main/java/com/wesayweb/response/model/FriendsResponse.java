package com.wesayweb.response.model;

import java.io.Serializable;
import java.util.Date;

import com.wesayweb.model.UserSettingsCategoryMapping;
import com.wesayweb.model.UserSettingsCategoryMapping.UserSettingsCategoryMappingBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FriendsResponse implements Serializable {

	@Getter
	@Setter
	private Long friendsid;

	@Getter
	@Setter
	private Long id;
	
	@Getter
	@Setter
	private String emailaddress;

	@Getter
	@Setter
	private String mobilenumber;

	@Getter
	@Setter
	private String countrycode;

	@Getter
	@Setter
	private String fullname;

	@Getter
	@Setter
	private String addeddate;

	@Getter
	@Setter
	private String invitationacceptdate;

	@Getter
	@Setter
	private int accept_status;
	
	@Getter
	@Setter
	private String requestuniueid;
	

}
