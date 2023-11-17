package com.TeamProject.TeamProject.Member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class GoogleMemberOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

  private final GoogleMemberService googleMemberService;

  @Autowired
  public GoogleMemberOAuth2UserService(GoogleMemberService googleMemberService) {
    this.googleMemberService = googleMemberService;
  }

  @Override
  public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
    System.out.println("asdasd");
    OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
    OAuth2User oAuth2User = delegate.loadUser(userRequest);

    System.out.println("asdasd");

    // Extract relevant information from oAuth2User
    String googleId = oAuth2User.getAttribute("sub");
    String email = oAuth2User.getAttribute("email");
    String nickname = oAuth2User.getAttribute("name");

    // Save or update GoogleMember information in the database
    googleMemberService.saveOrUpdateGoogleMember(googleId, email, nickname);

    return oAuth2User;
  }
}
