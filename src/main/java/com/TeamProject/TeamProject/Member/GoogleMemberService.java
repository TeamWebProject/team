package com.TeamProject.TeamProject.Member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoogleMemberService {
        private final GoogleMemberRepository googleMemberRepository;

        @Autowired
        public GoogleMemberService(GoogleMemberRepository googleMemberRepository) {
          this.googleMemberRepository = googleMemberRepository;
        }

        public GoogleMember saveOrUpdateGoogleMember(String googleId, String email, String nickname) {
          // 구글 로그인 정보를 저장 또는 업데이트
          GoogleMember googleMember = googleMemberRepository.findById(googleId)
                  .orElse(new GoogleMember());

          googleMember.setGoogleId(googleId);
          googleMember.setEmail(email);
          googleMember.setNickname(nickname);

          return googleMemberRepository.save(googleMember);
        }
}
