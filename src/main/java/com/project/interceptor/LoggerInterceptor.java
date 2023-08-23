package com.project.interceptor;


import io.micrometer.observation.annotation.Observed;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HttpServletBean;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
public class LoggerInterceptor implements HandlerInterceptor {

    // 메서드에 매핑된 특정 URI가 호출되었을 때 실행되는 메서드, 컨트롤러 접근 직전에 실행됨.
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        log.debug("===============================================");
        log.debug("==================== BEGIN ====================");
        log.debug("Request URI ===>" + request.getRequestURI());
        return HandlerInterceptor.super.preHandle(request,response,handler);
    }

    // 컨트롤러를 접근 한 후 화면에 결과를 전달하기 전에 실행되는 메서드
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception{
        log.debug("==================== END ======================");
        log.debug("===============================================");
        HandlerInterceptor.super.postHandle(request,response,handler,modelAndView);
    }
}
