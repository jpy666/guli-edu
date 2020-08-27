package com.bupt.commonutils.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * <p>Title:JWTUtils</p>
 * <p>Description:</P>
 * <p>Company:hhu.edu.cn</p>
 *
 * @Author 北京邮电大学.金培源
 * @Date 2020/7/9 11:59
 * Version 1.0
 */
public class JWTUtils {
    public static final String SUBJECT = "guli-user";

    //秘钥
    public static final String APP_SECRET = "79e7c69681b8270162386e6daa53d1dc";

    //过期时间，毫秒，30分钟
    public static final long EXPIRE = 1000 * 60 * 30;

    /**
     * 生成Jwt令牌
     * @return
     */
    public static String generateJwt(String id,String nickname){

        String token = Jwts.builder()
                .setHeaderParam("typ", "JWT") //令牌类型
                .setHeaderParam("alg", "HS256") //签名算法
                .setSubject(SUBJECT) //令牌主题
                .setIssuedAt(new Date()) //签发时间
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE)) //过期时间
                .claim("id", id)
                .claim("nickname", nickname)
                .signWith(SignatureAlgorithm.HS256, APP_SECRET).compact();

        return token;
    }


    /**
     * 判断token是否存在与有效
     * @param jwtToken
     * @return
     */
    public static boolean checkJwtTToken(String jwtToken) {
        if(StringUtils.isEmpty(jwtToken)) return false;
        try {
            Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 判断token是否存在与有效
     * @param request
     * @return
     */
    public static boolean checkJwtTToken(HttpServletRequest request) {
        try {
            String jwtToken = request.getHeader("token");
            if(StringUtils.isEmpty(jwtToken)) return false;
            Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 根据token获取会员id
     * @param request
     * @return
     */
    public static String getMemberIdByJwtToken(HttpServletRequest request) {
        String jwtToken = request.getHeader("token");
        System.out.println(jwtToken + "=======");
        if(StringUtils.isEmpty(jwtToken)) return null;
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        Claims claims = claimsJws.getBody();
        return (String)claims.get("id");
    }
}
