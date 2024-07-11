package com.nice.order.center.common.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator.Builder;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * Token's encode and decode
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class JwtUtils {

    /**
     * @deprecated 测试用
     */
    @Deprecated
    public static final String SECRET = "JKKLJO^*&FGasd64%hasdHK";

    /**
     * Generate token
     *
     * @param obj
     * @return
     */
    public static String generateJwtToken(Object obj, String secret) {
        Assert.notNull(obj, "The object must be not null");
        Map<String, Object> objKeyValueMap = OtherUtils.convertObjToMap(obj);
//        log.debug(objKeyValueMap.toString());

        // header map
        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("alg", "HS256");
        headerMap.put("typ", "JWT");
//        log.debug(headerMap.toString());

        // payload map
        Map<String, Object> payloadMap = new HashMap<>();
        for (Map.Entry<String, Object> entrySet : objKeyValueMap.entrySet()) {
            if (entrySet.getValue() != null) {
                payloadMap.put(entrySet.getKey(), entrySet.getValue());
            }
        }
//        log.debug(payloadMap.toString());

        // build token
        Builder builder = JWT.create().withHeader(headerMap);
        for (Map.Entry<String, Object> entrySet : payloadMap.entrySet()) {
            // signature
            builder = builder.withClaim(entrySet.getKey(), entrySet.getValue().toString());
        }

        return builder.sign(Algorithm.HMAC256(secret));
    }

    /**
     * Verify token
     *
     * @param token
     * @param obj
     * @param secret
     * @return
     */
    public static boolean verifyToken(String token, Object obj, String secret) {
        DecodedJWT decodedJwt;
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret)).build();
            decodedJwt = verifier.verify(token);
        } catch (Exception e) {
            // Fail to verify token, throw exception
            throw new RuntimeException("fail verify token");
        }
        return JwtUtils.checkFields(decodedJwt.getClaims(), obj);
    }

    private static boolean checkFields(Map<String, Claim> claims, Object obj) {
        Map<String, Object> objKeyValueMap = OtherUtils.convertObjToMap(obj);
        JwtUtils.removeSignField(objKeyValueMap);
        if (claims.size() != objKeyValueMap.size()) {
            return false;
        }
        for (Map.Entry<String, Claim> claimEntry : claims.entrySet()) {
            boolean fieldMatch = false;
            for (Map.Entry<String, Object> objEntry : objKeyValueMap.entrySet()) {
                if (claimEntry.getKey().equals(objEntry.getKey())
                        && claimEntry.getValue().equals(objEntry.getValue())) {
                    fieldMatch = true;
                    break;
                }
            }
            if (!fieldMatch) {
                return false;
            }
        }

        return true;
    }

    private static void removeSignField(Map<String, Object> objMap) {
        for (Map.Entry<String, Object> objEntry : objMap.entrySet()) {
            if (objEntry.getKey().equals("sign")) {
                objMap.remove("sign");
            }
        }
    }

//	// The following is just for reference, not used in project
//	/** Token expired time period: 10 days */
//	public static final int CALENDAR_FIELD = Calendar.DATE;
//	public static final int CALENDAR_INTERVAL = 10;
//
//	/**
//	 * JWT generate Token.<br/>
//	 * JWT structure: header, payload, signature
//	 */
//	public static String createToken(String unsignedString) throws Exception {
//		Assert.notNull(unsignedString, "The string must be not null");
//		Date iatDate = new Date();
//		// expire time
//		Calendar nowTime = Calendar.getInstance();
//		nowTime.add(CALENDAR_FIELD, CALENDAR_INTERVAL);
//		Date expiresDate = nowTime.getTime();
//
//		// header map
//		Map<String, Object> map = new HashMap<>();
//		map.put("alg", "HS256");
//		map.put("typ", "JWT");
//
//		// build token
//		String token = JWT.create().withHeader(map) // header
//				.withClaim("iss", "profile_app").withClaim("aud", "user_app")
//				.withClaim("signedString", unsignedString == null ? null : unsignedString)// payload
//				.withIssuedAt(iatDate) // sign time
//				.withExpiresAt(expiresDate) // expire time
//				.sign(Algorithm.HMAC256(SECRET)); // signature
//		return token;
//	}
//
//	/**
//	 * Verify and decode Token
//	 * 
//	 * @param token
//	 * @return
//	 * @throws TokenException
//	 */
//	public static Map<String, Claim> verifyTokenOld(String token) throws TokenException {
//		DecodedJWT decodedJwt = null;
//		try {
//			JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
//			decodedJwt = verifier.verify(token);
//		} catch (Exception e) {
//			// Fail to verify token, throw exception
//			throw new TokenException(ErrorCodeEnum.FAIL_VERIFY_TOKEN.getSelfDefinedCode(),
//					ErrorCodeEnum.FAIL_VERIFY_TOKEN.getMessage(), ErrorCodeEnum.FAIL_VERIFY_TOKEN.getDetail());
//		}
//		return decodedJwt.getClaims();
//	}
//
//	/**
//	 * Get user_id according to Token
//	 * 
//	 * @param token
//	 * @return user_id
//	 * @throws TokenException
//	 */
//	public static Long getAppUID(String token) throws TokenException {
//		Map<String, Claim> claims = verifyTokenOld(token);
//		Claim userIdClaim = claims.get("user_id");
//		if (userIdClaim == null || userIdClaim.asString() == null) {
//			throw new TokenException(ErrorCodeEnum.INVALID_CLAIM.getSelfDefinedCode(),
//					ErrorCodeEnum.INVALID_CLAIM.getMessage(), ErrorCodeEnum.INVALID_CLAIM.getDetail());
//		}
//		return Long.valueOf(userIdClaim.asString());
//	}
}