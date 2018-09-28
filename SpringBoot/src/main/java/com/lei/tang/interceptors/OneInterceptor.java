package com.lei.tang.interceptors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author tanglei
 * @date 18/9/28
 */
@Slf4j
public class OneInterceptor extends HandlerInterceptorAdapter {

    //该方法将在请求处理之前进行调用,返回false请求结束
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("OneInterceptor -> preHandle");
        return super.preHandle(request, response, handler);
    }

    //在当前请求进行处理之后，也就是Controller 方法调用之后执行，但是它会在DispatcherServlet 进行视图返回渲染之前被调用，所以我们可以在这个方法中对Controller 处理之后的ModelAndView 对象进行操作
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        log.info("OneInterceptor -> postHandle");
        super.postHandle(request, response, handler, modelAndView);
    }

    //该方法将在整个请求结束之后，也就是在DispatcherServlet 渲染了对应的视图之后执行。这个方法的主要作用是用于进行资源清理工作的
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                Exception ex) throws Exception {
        log.info("OneInterceptor -> afterCompletion");
        super.afterCompletion(request, response, handler, ex);
    }

    //afterConcurrentHandlingStarted是返回异步结果时调用(此时异步结果里不需要有数据), 而postHandle必须是返回的结果执行完, 异步结果中有数据了才调用
    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response,
                                               Object handler) throws Exception {
        log.info("OneInterceptor -> afterConcurrentHandlingStarted");
        super.afterConcurrentHandlingStarted(request, response, handler);
    }
}
