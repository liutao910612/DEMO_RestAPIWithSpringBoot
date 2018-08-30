package com.liutao.util;

import com.liutao.enums.SelfHttpStatus;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.regex.Pattern;

/**
 * 通用工具类
 *
 * @author: LIUTAO
 * @Date: Created in 2018/8/30  13:52
 * @Modified By:
 */
public class CommonUtil {

    /**
     * 判断一个数是否是整数
     * @param str
     * @return
     */
    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

    /**
     * 获取response
     * @return
     */
    public static HttpServletResponse getResponse(){
        HttpServletResponse response = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse();
        return response;
    }

    /**
     * 获取request
     * @return
     */
    public static HttpServletRequest getRequest(){
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        return request;
    }

    /**
     * 处理错误响应
     * @param selfHttpStatus
     */
    public static void buildErrorResponse(SelfHttpStatus selfHttpStatus) {
        HttpServletResponse response = getResponse();
        response.setStatus(selfHttpStatus.getCode());
        response.addHeader("Error-Message",selfHttpStatus.getMessage());
    }
}
