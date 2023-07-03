package KeyboardShop.Keytopia.auth.security;

import io.github.cdimascio.dotenv.Dotenv;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {
	private final String jwtSecret = Dotenv.load().get("JWT_SECRET");;


	public String generateAccessToken(String subject) {
		int accessTokenExpirationMs = 1000 * 60 * 60 * 2; //2h
		return generateToken(subject, accessTokenExpirationMs);
	}

	public String generateToken(String email, int expirationMs) {
		return Jwts.builder()
			.setSubject(email)
			.setIssuedAt(new Date())
			.setExpiration(new Date((new Date()).getTime() + expirationMs))
			.signWith(SignatureAlgorithm.HS512, jwtSecret)
			.compact();
	}

	public String getEmailFromJwtToken(String token) {
		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
	}

	public boolean validateJwtToken(final String authToken) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
			return true;
		} catch (final MalformedJwtException e) {
			System.out.println("Invalid JWT token: " + e.getMessage());
		} catch (final ExpiredJwtException e) {
			System.out.println("Token expired " + e.getMessage());
		} catch (final UnsupportedJwtException e) {
			System.out.println("Unsupported token " + e.getMessage());
		} catch (final IllegalArgumentException e) {
			System.out.println("JWT claims string is empty: " + e.getMessage());
		}
		return false;
	}
}
