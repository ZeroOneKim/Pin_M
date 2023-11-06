package byk.pinM.service.PinService;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Util Class.
 */
@Service
public class MyUtil {

    //Client의 ip 주소를 리턴한다.
    public String getIpAddress() {
        String ip = null;
        HttpServletRequest httpServletRequest = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();

        ip = httpServletRequest.getHeader("X-Forwarded-For");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) ip = httpServletRequest.getHeader("Proxy-Client-IP");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) ip = httpServletRequest.getHeader("WL-Proxy-Client-IP");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) ip = httpServletRequest.getHeader("HTTP_CLIENT_IP");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) ip = httpServletRequest.getHeader("HTTP_X_FORWARDED_FOR");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) ip = httpServletRequest.getHeader("X-Real-IP");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) ip = httpServletRequest.getRemoteAddr();

        return ip;
    }

    //현재시간을 리턴한다.
    public String getTimeNow() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd HHmmss");
        Date date = new Date();
        String now = simpleDateFormat.format(date);

        return now;
    }
    public String getTimeNow(String gbn, String gbn2) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy"+gbn+"MM"+gbn+"dd "+"HH"+gbn2+"mm"+gbn2+"ss");
        Date date = new Date();
        String now = simpleDateFormat.format(date);

        return now;
    }
}
