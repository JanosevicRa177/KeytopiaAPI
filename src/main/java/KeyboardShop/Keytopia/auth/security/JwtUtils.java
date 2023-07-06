package KeyboardShop.Keytopia.auth.security;

import io.github.cdimascio.dotenv.Dotenv;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Component
public class JwtUtils {
	private final String authSecret = Dotenv.load().get("AUTH_SECRET");
	private final String activationSecret = Dotenv.load().get("ACTIVATION_SECRET");


	public String generateAccessToken(String subject) {
		int accessTokenExpirationMs = 1000 * 60 * 60 * 2; //2h
		return Jwts.builder()
				.setSubject(subject)
				.setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + accessTokenExpirationMs))
				.signWith(SignatureAlgorithm.HS512, authSecret)
				.compact();
	}
	public String generateRegisterToken(String email) {
		Date dt = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(dt);
		c.add(Calendar.DATE, 7);
		dt = c.getTime();
		return Jwts.builder()
				.setSubject(email)
				.setIssuedAt(new Date())
				.setExpiration(dt)
				.signWith(SignatureAlgorithm.HS512, activationSecret)
				.compact();
	}

	public String generateToken(String email, int expirationMs,String secret) {
		return Jwts.builder()
			.setSubject(email)
			.setIssuedAt(new Date())
			.setExpiration(new Date((new Date()).getTime() + expirationMs))
			.signWith(SignatureAlgorithm.HS512, secret)
			.compact();
	}

	public String getEmailFromAuthToken(String token) {
		return Jwts.parser().setSigningKey(authSecret).parseClaimsJws(token).getBody().getSubject();
	}
	public Claims getClaimsFromActivationToken(String token) {
		return Jwts.parser().setSigningKey(activationSecret).parseClaimsJws(token).getBody();
	}

	public boolean validateAuthToken(final String authToken) {
		return validateToken(authToken, authSecret);
	}
	public boolean validateActivationToken(final String activationToken) {
		return validateToken(activationToken, activationSecret);
	}

	public boolean validateToken(final String authToken, String secret) {
		try {
			Jwts.parser().setSigningKey(secret).parseClaimsJws(authToken);
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
