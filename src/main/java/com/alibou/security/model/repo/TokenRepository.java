package com.alibou.security.model.repo;

import com.alibou.security.model.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TokenRepository extends JpaRepository<Token, UUID> {

    @Query(value = """
            select t from Token t inner join User u\s
            on t.userID = u.id\s
            where u.id = :id and (t.expired = false or t.revoked = false)\s
            """)
    List<Token> findAllValidTokenByUser(UUID id);

    Token findByUserID(UUID userId);
    List<Token> findAllByUserID(UUID userId);
    Optional<Token> findByToken(String token);
}
