package Security;

import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

// Using the filter to check if the request to a web service endpoint does contain a JWT token and if the provided
// JWT token has been signed with the correct token secret
@Component // AbstractGatewayFilterFactory.
// This means it will execute for every HTTP request passing through your gateway.


public class AuthorizationFilter extends AbstractGatewayFilterFactory<AuthorizationFilter.Config> {

    @Override
    public GatewayFilter apply(Config config) {

  //exchange is HTTP request and response and chain is the filer
        return (exchange, chain) -> {

           ServerHttpRequest request = exchange.getRequest();
            if (request.getHeaders().containsKey("Authorization")){
                return OneError(exchange, " No Authorization header", HttpStatus.UNAUTHORIZED);
            }
// if the filter finds the header it's looking for, it doesn't need to do anything fancy—it just lets the request proceed as normal.
            return chain.filter(exchange);
        };
    }
//creates an error message to send back . Here's what it does:
//Mono<Void> indicates a completion signal without a specific result. It's used to represent that an asynchronous operation has completed
    private Mono<Void> OneError(ServerWebExchange exchange, String s, HttpStatus httpStatus) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);

        return response.setComplete();
    }

    public static class Config {

    }


}
