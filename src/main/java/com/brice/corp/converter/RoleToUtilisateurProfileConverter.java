package com.brice.corp.converter;

import com.brice.corp.model.UtilisateurProfile;
import com.brice.corp.service.UtilisateurProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * A converter class used in views to map id's to actual utilisateurProfile objects.
 */
@Component
public class RoleToUtilisateurProfileConverter implements Converter<Object, UtilisateurProfile>{

	@Autowired
    UtilisateurProfileService utilisateurProfileService;

	/**
	 * Gets UtilisateurProfile by Id
	 * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
	 */
	public UtilisateurProfile convert(Object element) {
		Integer id = Integer.parseInt((String)element);
		UtilisateurProfile profile= utilisateurProfileService.findById(id);
		System.out.println("Profile : "+profile);
		return profile;
	}
	
}