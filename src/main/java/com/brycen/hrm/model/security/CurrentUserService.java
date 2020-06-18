package com.brycen.hrm.model.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.brycen.hrm.model.UserEntity;
import com.brycen.hrm.repository.UserRepository;

@Service
public class CurrentUserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    /* (non-Javadoc)
     * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
     * 
     * Get user từ username vào CurrentUser of system
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity entity = userRepository.findByUsername(username);

        if (entity == null) {
            throw new UsernameNotFoundException(username);
        }

        return new CurrentUser(entity);
    }

}
