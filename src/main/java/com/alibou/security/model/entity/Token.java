package com.alibou.security.model.entity;

import com.alibou.security.model.entity.enums.TokenType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Token {
    @Id
    private UUID id;
    private String token;
    @Enumerated(EnumType.STRING)
    private final TokenType tokenType = TokenType.BEARER;
    private boolean revoked;
    private boolean expired;
    @Column(name = "user_id")
    private UUID userID;
}
