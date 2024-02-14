package Security;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

// Using the filter to check if the request to a web service endpoint does contain a JWT token and if the provided
// JWT token has been signed with the correct token secret
@Component
public class AuthorizationFilter extends AbstractGatewayFilterFactory<AuthorizationFilter.Config> {

    @Override
    public GatewayFilter apply(Config config) {

        return (exchange, chain) -> {

           ServerHttpRequest request = exchange.getRequest();
            if (request.getHeaders().containsKey("Authorization")){
                return OneError(exchange, " No Authorization header", HttpStatus.UNAUTHORIZED);
            }
            return chain.filter(exchange);
        };
    }

    public static class Config {

    }
}
