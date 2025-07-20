package dev.redbeans.bootcamp202506eirzarog.springapi;

import dev.redbeans.bootcamp202506eirzarog.springapi.services.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.util.List;

@Configuration // Configuration class to define security settings
public class SecurityConfiguration {


    @Bean // AuthenticationManager bean to manage authentication
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults())
//                .oauth2ResourceServer(auth -> auth.jwt(Customizer.withDefaults()))
                .csrf(csrf -> csrf.disable());

        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }


    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(UserService userService) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

//
//    @Bean
//    public JWKSource<SecurityContext> jwkSource() throws JOSEException {
//        com.nimbusds.jose.jwk.RSAKey rsaJwk = new RSAKeyGenerator(2048)
//                .keyUse(KeyUse.SIGNATURE)
//                .algorithm(JWSAlgorithm.RS256)
//                .keyID("rsa-key-1")
//                .generate();
//
//        JWKSet jwkSet = new JWKSet(rsaJwk);
//        return new ImmutableJWKSet<>(jwkSet);
//    }

//    // 2) Expose a JwtEncoder that will sign tokens with our JWK
//    @Bean
//    public JwtEncoder jwtEncoder(JWKSource<SecurityContext> jwkSource) {
//        return new NimbusJwtEncoder(jwkSource);
//    }

//    @Bean
//    public JwtDecoder jwtDecoder(JWKSource<SecurityContext> jwkSource) throws JOSEException {
//        // 1) select the JWK with your key-ID
//        JWKSelector selector = new JWKSelector(
//                new JWKMatcher.Builder()
//                        .keyID("rsa-key-1")
//                        .keyUse(KeyUse.SIGNATURE)
//                        .build()
//        );
//        // 2) query the source
//        List<JWK> jwks = jwkSource.get(selector, null);
//        if (jwks.isEmpty()) {
//            throw new IllegalStateException("No RSA JWK found with keyID rsa-key-1");
//        }
//        com.nimbusds.jose.jwk.RSAKey rsa = (RSAKey) jwks.get(0);
//
//        // 3) extract the Java PublicKey
//        RSAPublicKey publicKey = rsa.toRSAPublicKey();
//
//        // 4) plug into the Nimbus decoder
//        return NimbusJwtDecoder.withPublicKey(publicKey).build();
//    }



}