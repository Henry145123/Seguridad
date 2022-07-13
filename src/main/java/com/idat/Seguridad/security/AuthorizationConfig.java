package com.idat.Seguridad.security;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;


@Configuration
@EnableAuthorizationServer
public class AuthorizationConfig extends AuthorizationServerConfigurerAdapter{

	@Autowired
	private AuthenticationManager manager;
	
	@Autowired
	private JwtTokenStore store;
	
	@Autowired
	private JwtAccessTokenConverter accessTokenConverter;
	
	
	@Value("spring.seguridad.clientid")
	private String clientid;
	
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		// TODO Auto-generated method stub
		super.configure(security);
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		
		clients.inMemory()
		.withClient("queridoprofesor")
		.secret(new BCryptPasswordEncoder().encode("queridoprofesor"))
								//genera el acces token
		.authorizedGrantTypes("password","authorization_code","refresh_token","implicit")
		.scopes("read","write","trust")//se va a leer escribir el token 
		.accessTokenValiditySeconds(1*60*60)//tiempo de vida del token
		.refreshTokenValiditySeconds(5*60*60);//tiempo de vida del refresh token
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

		TokenEnhancerChain tokenChain = new TokenEnhancerChain();
		tokenChain.setTokenEnhancers(Arrays.asList(new TokenEnhancer() {//agregar campos adicionales al token	
				@Override
				public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
					
					//crear informacion adicional
					Map<String, Object> informacionAdicional = new HashMap<>();
					informacionAdicional.put("suscribete", "canal-youtube-lideratec");
					
					//traer el token por default
					DefaultOAuth2AccessToken token = new DefaultOAuth2AccessToken(accessToken);
					
					//añadir la informacion adicional en el token
					token.setAdditionalInformation(informacionAdicional);
					
					//retornamos el token actualizado
					return token;
				}
			},accessTokenConverter));
		
		endpoints.tokenStore(store)
			.authenticationManager(manager)
			.accessTokenConverter(accessTokenConverter)
			.tokenEnhancer(tokenChain);
	}

	
}
