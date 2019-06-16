package my.tset.javaweb3.component.filter;

import my.tset.javaweb3.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@WebFilter(urlPatterns = {"/question/*","/answer/*"})
public class AuthorityFilter implements Filter {
    private Set<String> ignoreSet = new HashSet<>();

    @Autowired
    private UserRepository userRepository;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //忽略url
        ignoreSet.add("/login");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        System.out.println(request.getRequestURI());
        if (!ignoreSet.contains(request.getRequestURI())) {
            String token = request.getHeader("token");
            System.out.println(token);
            if (token==null||userRepository.findByToken(token)==null) {
                response.sendError(420, "Error Token");
                return;
            }
        }
        filterChain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}
