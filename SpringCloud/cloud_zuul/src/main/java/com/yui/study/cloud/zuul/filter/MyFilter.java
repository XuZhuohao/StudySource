package com.yui.study.cloud.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * zuul自定义filter
 *
 * @author XuZhuohao
 * @date 2018/8/27
 */
@Component
public class MyFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return FilterConstants.ROUTE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String p = request.getParameter("p");
        String url = request.getRequestURI();
        System.out.println(url);
        if (!"1".equals(p)) {
            ctx.put(FilterConstants.SERVICE_ID_KEY, "service-client");
        } else {
            ctx.put(FilterConstants.SERVICE_ID_KEY, "service-ribbon");
        }
        return null;
    }
}
