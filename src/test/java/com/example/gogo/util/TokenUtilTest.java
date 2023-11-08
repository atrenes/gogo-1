package com.example.gogo.util;

import com.example.gogo.config.CustomPostgreSQLContainer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;


import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class TokenUtilTest extends CustomPostgreSQLContainer {
    @Autowired
    private TokenUtil tokenUtil;
    @Test
    public void generateAccessTokenTest() {
        assertNotNull(tokenUtil.generateAccessToken("admin"));
    }

    @Test
    public void validateTest() {
        String token = tokenUtil.generateAccessToken("admin");
        assertTrue(tokenUtil.validateToken(token));
    }

    @Test
    public void getLoginFromTokenTest() {
        String token = tokenUtil.generateAccessToken("admin");
        assertEquals("admin", tokenUtil.getLoginFromToken(token));
    }
}
