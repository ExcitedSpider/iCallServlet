package servletserver.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class EncodingRequestWrapper extends HttpServletRequestWrapper{

    private  String ENCODING;

    public EncodingRequestWrapper(HttpServletRequest request,String encoding) {
        super(request);
        ENCODING = encoding;
    }

    @Override
    //do encoding change
    public String getParameter(String name) {
        String value = getRequest().getParameter(name);

        if(value!=null){
            try{
                byte[] b = value.getBytes("ISO-8859-1");
                value = new String(b,ENCODING);
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        return value;
    }
}

