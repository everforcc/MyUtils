package cc.advanced.web.webservice.jdkservice;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * 用JDK发部webservice
 */
@WebService
public interface JDKService {

    /**
     * https://www.cnblogs.com/w-essay/p/7357262.html
     */

    @WebMethod
    public String sayHel(String name);

}
