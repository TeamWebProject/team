package com.TeamProject.TeamProject;

import com.TeamProject.TeamProject.Member.GoogleMemberOAuth2UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.io.IOException;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
          @Autowired
          private GoogleMemberOAuth2UserService googleMemberOAuth2UserService;


          @Bean
          SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            http
                    .authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
                            .requestMatchers(new AntPathRequestMatcher("/**")).permitAll())
                    .headers((headers) -> headers
                            .addHeaderWriter(new XFrameOptionsHeaderWriter(
                                    XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN)))
                    .formLogin((formLogin) -> formLogin
                            .loginPage("/member/login")
                            .defaultSuccessUrl("/"))
                    .logout((logout) -> logout
                            .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
                            .logoutSuccessUrl("/member/logout")
                            .invalidateHttpSession(true)
                            .addLogoutHandler((request, response, authentication) -> {
                              // 추가적으로 필요한 로그아웃 처리를 여기에 추가

                              if (authentication != null) {
                                // 현재 로그인한 사용자의 Principal을 가져옴
                                Object principal = authentication.getPrincipal();

                                // 구글 소셜 로그인 사용자의 경우 로그아웃 페이지로 이동
                                if (principal instanceof OAuth2User) {
                                  String googleLogoutUrl = "https://www.google.com/accounts/Logout?continue=https://appengine.google.com/_ah/logout?continue=" + request.getRequestURL();
                                  try {
                                    response.sendRedirect(googleLogoutUrl);
                                    return;
                                  } catch (IOException e) {
                                    throw new RuntimeException(e);
                                  }
                                }
                              }

                              // 일반 사용자의 경우 홈페이지 또는 list 페이지로 이동
                              try {
                                response.sendRedirect("/restaurant/list");
                              } catch (IOException e) {
                                throw new RuntimeException(e);
                              }
                            })
                    )
                    .oauth2Login((oauth2Login) -> oauth2Login
                            .loginPage("/member/login")
                            .userInfoEndpoint(userInfoEndpoint ->
                                    userInfoEndpoint
                                            .userService(googleMemberOAuth2UserService))
                    );

            return http.build();
          }

          @Bean
          PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
          }

          @Bean
          AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
            return authenticationConfiguration.getAuthenticationManager();
          }


}
