package by.iba.security.config;

import by.iba.security.CustomUserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;


@Configuration
@EnableAuthorizationServer
@AllArgsConstructor
public class OAuth2AuthorizationConfig extends AuthorizationServerConfigurerAdapter {

    private final TokenStore tokenStore = new InMemoryTokenStore();
    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService userDetailsService;
    private final Environment env;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("browser")
                .authorizedGrantTypes("refresh_token", "password")
                .scopes("ui")
                .and()
                .withClient("account-service")
                .secret(env.getProperty("ACCOUNT_SERVICE_PASSWORD"))
                .authorizedGrantTypes("client_credentials", "refresh_token")
                .scopes("server")
                .and()
                .withClient("notification-service")
                .authorizedGrantTypes("client_credentials", "refresh_token")
                .secret(env.getProperty("NOTIFICATION_SERVICE_PASSWORD"))
                .scopes("server")
                .and()
                .withClient("transport-exchange")
                .authorizedGrantTypes("client_credentials", "refresh_token")
                .secret(env.getProperty("TRANSPORT_EXCHANGE_SERVICE_PASSWORD"))
                .scopes("server")
        ;
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints
                .tokenStore(tokenStore)
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
        oauthServer
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()")
                .allowFormAuthenticationForClients()
                .passwordEncoder(NoOpPasswordEncoder.getInstance());//TODO
        //?????? ?????????????? ?? ?????????????? @ilya ?????????????????????? ???????????????????? ?????????? ?????? ???? ?????????????? ?? ?????? ???????????? ???? ???????????????? ?????????? ??????????????????
        //.passwordEncoder(AESEncoder.builder().build());
    }

}
