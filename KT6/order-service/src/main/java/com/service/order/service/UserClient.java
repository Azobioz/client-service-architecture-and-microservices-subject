package com.service.order.service;

import com.service.order.dto.UserDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserClient {

    private final RestTemplate restTemplate = new RestTemplate();

    public UserDTO getUser(Long id) {
        String url = "http://service-for-user:8081/users/" + id;
        return restTemplate.getForObject(url, UserDTO.class);
    }
}
