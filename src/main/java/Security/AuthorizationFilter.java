package Security;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

// Using the filter to check if the request to a web service endpoint does contain a JWT token and if the provided
// JWT token has been signed with the correct token secret
@Component
public class AuthorizationFilter extends AbstractGatewayFilterFactory<AuthorizationFilter.Config> {

    @Override
    public GatewayFilter apply(Config config) {
        return null;
    }

    public static class Config {

    }
}
