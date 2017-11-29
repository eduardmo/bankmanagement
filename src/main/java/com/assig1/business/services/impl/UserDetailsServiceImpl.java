package com.assig1.business.services.impl;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.assig1.business.domainModel.Employee;
import com.assig1.business.domainModel.Role;
import com.assig1.business.domainModel.UserStatus;
import com.assig1.dataSource.dao.EmployeeDao;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	EmployeeDao emplDao;
	@Override
	@Transactional(readOnly= true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Employee empl = emplDao.getEmployeeByUserName(username);
		if(empl != null){
		String password = empl.getPassword();
		boolean enabled = empl.getStatus().equals(UserStatus.ACTIVE);
		boolean accountNonExpired = empl.getStatus().equals(UserStatus.ACTIVE);
		boolean credentialsNonExpired = empl.getStatus().equals(UserStatus.ACTIVE);
		boolean accountNonLocked = empl.getStatus().equals(UserStatus.ACTIVE);
		Role role = empl.getEmployeeRole();
		Collection<GrantedAuthority> authority = new ArrayList<GrantedAuthority>();
				authority.add(new SimpleGrantedAuthority(role.getRoleName()));
		org.springframework.security.core.userdetails.User securityUser = new
				org.springframework.security.core.userdetails.User(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authority);

		return securityUser;
		}
		else{
			throw new UsernameNotFoundException("User Not Found!");
		}
	}

}
