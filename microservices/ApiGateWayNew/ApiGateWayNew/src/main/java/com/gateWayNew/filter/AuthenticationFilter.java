package com.gateWayNew.filter;

import com.gateWayNew.util.JwtHelperClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class AuthenticationFilter  extends AbstractGatewayFilterFactory {

    @Autowired
    private RouteValidator routeValidator;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private JwtHelperClass jwtHelperClass;

    public AuthenticationFilter()
    {
        super(Config.class);
    }


    @Override
    public GatewayFilter apply(Object config) {

       return ((exchange, chain) ->{

           if(routeValidator.isSecured.test(exchange.getRequest()))
           {
               String token="";
               //header contains token or not

               if(!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION))
               {
                  throw new RuntimeException("missing authorization header");
               }

               String authheader=exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);

               if(authheader!=null && authheader.startsWith("Bearer"))
               {
                   token=authheader.substring(7);
               }

               try
               {
                 //String msg=restTemplate.getForObject("http://IDENTITY-SERVICE/validate?token"+authheader,String.class);
                   String userName=jwtHelperClass.getUsernameFromToken(token);

                   UserDetails userDetails=restTemplate.getForObject("http://IDENTITY-SERVICE/getUserDatails/"+token, UserDetails.class);
                   jwtHelperClass.validateToken(token,userDetails);

               }

               catch(Exception e)
               {
                  throw new RuntimeException(("Unauthorized access to application"));
               }
           }
           return chain.filter(exchange);
       });
    }

    public static class Config
    {

    }


}
