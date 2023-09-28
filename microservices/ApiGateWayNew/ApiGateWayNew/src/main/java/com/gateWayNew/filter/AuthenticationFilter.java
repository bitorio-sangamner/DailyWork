package com.gateWayNew.filter;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.gateWayNew.security.JwtTokenValidation;


@Component
public class AuthenticationFilter  extends AbstractGatewayFilterFactory {

    @Autowired
    private RouteValidator routeValidator;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private JwtTokenValidation JwtTokenValidation;



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

               System.out.println("inside if");

               if(!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION))
               {
                  throw new RuntimeException("missing authorization header");
               }

               String authheader=exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);



               System.out.println("Header : "+authheader);

               if(authheader!=null && authheader.startsWith("Bearer"))
               {
                   token=authheader.substring(7);
                   System.out.println("token :"+token);
               }

               try
               {
                 //String msg=restTemplate.getForObject("http://IDENTITY-SERVICE/authanticate/validate/"+token,String.class);
                  //System.out.println(msg);

                   boolean result=JwtTokenValidation.validateToken(token);
//                   String userName=jwtHelperClass.getUsernameFromToken(token);
//
//                   UserDetails userDetails=restTemplate.getForObject("http://IDENTITY-SERVICE/getUserDatails/"+token, UserDetails.class);
//                   jwtHelperClass.validateToken(token,userDetails);

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
