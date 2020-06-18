package com.brycen.hrm.serivce.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.brycen.hrm.config.JwtTokenProvider;
import com.brycen.hrm.model.UserEntity;
import com.brycen.hrm.model.security.CurrentUser;
import com.brycen.hrm.repository.UserRepository;
import com.brycen.hrm.request.UserRequest;
import com.brycen.hrm.response.Response;
import com.brycen.hrm.response.object.UserResponse;
import com.brycen.hrm.service.UserService;
import com.brycen.hrm.status.BaseConvert;
import com.brycen.hrm.status.Code;
import com.brycen.hrm.status.Message;

@Service
public class UserImpl implements UserService{
    
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private BaseConvert baseConvert;
    
    @Autowired
    private UserRepository userRepository;
    
    /* (non-Javadoc)
     * @see com.brycen.hrm.service.UserService#login(com.brycen.hrm.request.UserRequest)
     * 
     * Nếu có kết quả thì tạo jwt chèn vào trong data của response và return
     * Ngược lại sẽ trả về lỗi 100
     */
    @Override
    public Response login(UserRequest userRequest) {
       Response response = new Response();
       try {
           String jwt = "";
           
           // Kiếm tra có tồn tại username không?
           Authentication authentication = 
                   authenticationManager.authenticate(
                           new UsernamePasswordAuthenticationToken(userRequest.getUsername(), userRequest.getPassword()));
           
           // Lấy UserEntity convert to UserResponse
           UserEntity userEntity = userRepository.findByUsername(userRequest.getUsername());
           UserResponse userResponse = baseConvert.userToResponse(userEntity);
           
           // Nếu có thì sẽ tạo jwt
           jwt = jwtTokenProvider.generateToken((CurrentUser) authentication.getPrincipal(), userResponse);
           
           response.setData(jwt);          
        } catch (Exception e) {
            response.setCode(Code.login_fail);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @Override
    public Response getUser(String username) {
        Response response = new Response();
        try {
            UserEntity userEntity = userRepository.findByUsername(username);
            
           // Kiểm tra user có tồn tại 
            if(userEntity == null) {
                response.setCode(Code.user_not_found);
                response.setMessage(Message.user_not_found);
                return response;
            }
            
            // Chuyển đổi sang object response để return
            UserResponse userResponse = baseConvert.userToResponse(userEntity);
            
            // Set to data object response
            response.setData(userResponse);
            
        }catch (Exception e) {
            response.setCode(Code.unknown);
            response.setMessage(e.getMessage());
        }
        
        return response;
    }
    
    

}
