package com.alibou.security.service;

import com.alibou.security.rest.dto.request.AuthenticationRequest;
import com.alibou.security.rest.dto.request.RefreshTokenRequest;
import com.alibou.security.rest.dto.request.RegisterRequest;
import com.alibou.security.rest.dto.response.AuthenticationResponse;

public interface AuthenticationService {
    AuthenticationResponse refreshToken(RefreshTokenRequest request);

    AuthenticationResponse authenticate(AuthenticationRequest request);

    AuthenticationResponse register(RegisterRequest request);
}
