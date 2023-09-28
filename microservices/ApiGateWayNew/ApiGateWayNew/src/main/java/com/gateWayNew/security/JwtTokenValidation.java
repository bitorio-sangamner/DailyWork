package com.gateWayNew.security;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenValidation {

    private String secret = "afafasfafafasfasfasfafacasdasfasxASFACASDFACASDFASFASFDAFASFASDAADSCSDFADCVSGCFVADXCcadwavfsfarvf";

    public boolean validateToken(String token) {
            try {
                Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
                return true;
            } catch (JwtException | IllegalArgumentException e) {
                return false;
            }
        }
}
