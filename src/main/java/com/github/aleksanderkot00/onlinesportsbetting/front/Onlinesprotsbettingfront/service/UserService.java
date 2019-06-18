package com.github.aleksanderkot00.onlinesportsbetting.front.Onlinesprotsbettingfront.service;

import com.github.aleksanderkot00.onlinesportsbetting.front.Onlinesprotsbettingfront.exception.EmailNotFoundException;
import com.github.aleksanderkot00.onlinesportsbetting.front.Onlinesprotsbettingfront.client.UserClient;
import com.github.aleksanderkot00.onlinesportsbetting.front.Onlinesprotsbettingfront.domain.dto.UserDetailsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    private final UserClient userClient = new UserClient();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<UserDetailsDto> users = userClient.getUsersDetails();
        UserDetailsDto user = users.stream()
                .filter(u -> u.getEmail().equals(username))
                .findAny().orElseThrow(EmailNotFoundException::new);
        List<GrantedAuthority> authorities = new ArrayList<>();
        for(String role: user.getRoles()) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
        }
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getEncryptedPassword(), user.isActive(),
                true, true, true, authorities);
    }
}
