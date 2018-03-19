package servletserver.filter;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;

@WebFilter(
        urlPatterns = {"/*"},
        initParams = {
                @WebInitParam(name = "ENCODING",value = "UTF-8")
        }
)
public class EncodingRequestFilter implements Filter {

    private String ENCODING;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        ENCODING = filterConfig.getInitParameter("ENCODING");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        req = new EncodingRequestWrapper(req,ENCODING);

        filterChain.doFilter(req,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
