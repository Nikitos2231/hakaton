package com.alibou.security.rest.controller;

import com.alibou.security.rest.dto.request.AuthenticationRequest;
import com.alibou.security.rest.dto.request.RefreshTokenRequest;
import com.alibou.security.rest.dto.request.RegisterRequest;
import com.alibou.security.rest.dto.response.AuthenticationResponse;
import com.alibou.security.rest.dto.response.ResponseDto;
import com.alibou.security.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/authenticate")
    public ResponseDto<AuthenticationResponse> authenticate(@RequestBody @Valid AuthenticationRequest request) {
        return ResponseDto.ok(authenticationService.authenticate(request));
    }

    @PostMapping("/refresh")
    public ResponseDto<AuthenticationResponse> refreshToken(@RequestBody @Valid RefreshTokenRequest request) {
        return ResponseDto.ok(authenticationService.refreshToken(request));
    }

    @PostMapping("/register")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseDto<AuthenticationResponse> register(@RequestBody @Valid RegisterRequest request) {
        return ResponseDto.ok(authenticationService.register(request));
    }

}
