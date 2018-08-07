package com.wesayweb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.wesayweb.model.ContactList;

@Repository
public interface ContactCustomRepository {
	
	@Query(value = "SELECT "
			+ "     '' as firstname,'' as lastname, "
			+ "     a.fullname,a.isinviationsent, a.sourceuserid,a.syncdate,"
			+ "		CASE WHEN b.id IS NULL THEN a.contactid ELSE b.id END   as contactid, "
			+ "     a.contactid, " + 
		    "		a.emailaddress, " + 
		    "		a.countrycode, " + 
		    "		a.mobilenumber, " + 
		    "       CASE WHEN B.isactive = 1 and B.emailaddress IS NOT NULL " + 
		    "       AND B.countrycode IS NOT NULL AND B.mobilenumber IS NOT NULL " + 
		    "       THEN 1 ELSE 0 END AS isregistredinwesay " + 
		    "		FROM user_contact_list a  " + 
		    "       JOIN user_master B ON " + 
		    "		TRIM(LOWER(a.mobilenumber)) = TRIM(LOWER(B.mobilenumber)) " + 
		    "		WHERE sourceuserid = :sourceuserid and a.isinviationsent = 0 and   a.sourceuserid != CASE WHEN b.id IS NULL THEN a.contactid ELSE b.id END",
		    	   nativeQuery = true, name = "getMyContactList")
				public List<ContactList> getMyContactList(@Param("sourceuserid") long sourceuserid);
				
				public boolean saveMyContact(ContactList contact);
	
				public boolean isAlreadyAddedInApplication(String emailid, String mobilenum);
				
				public boolean getByMobilenumber(String mobilenumber, String countryCode);
				
				public ContactList getRequestedContactDetails(String mobileNumber, String countryCode , Long loggedinUserId);
	
}