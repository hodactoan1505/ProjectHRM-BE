package com.brycen.hrm.service;

import com.brycen.hrm.request.UserRequest;
import com.brycen.hrm.response.Response;

public interface UserService {
    Response login(UserRequest userRequest);
    Response getUser(String username);
}
