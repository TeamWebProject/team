package com.TeamProject.TeamProject.Controller;

import com.TeamProject.TeamProject.Member.Member;
import com.TeamProject.TeamProject.Member.MemberRepository;
import com.TeamProject.TeamProject.Member.MemberService;
import com.TeamProject.TeamProject.Restaurant.RestaurantService;
import com.TeamProject.TeamProject.Review.Review;
import com.TeamProject.TeamProject.Review.ReviewRepository;
import com.TeamProject.TeamProject.Review.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

@RequestMapping("/review")
@RequiredArgsConstructor
@Controller
public class ReviewController {
    private final RestaurantService restaurantService;
    private final ReviewService reviewService;
    private final MemberService memberService;

    private final MemberRepository memberRepository; // ㅌㅔ스트용. 추후 삭제예정
    private final ReviewRepository reviewRepository; // 테스트용. 추후 삭제예정
    @GetMapping("/detail")
    public String reviewdetail(Model model, Integer reviewid) {

        Member member = new Member();
        member.setMembername("김포도");
        member.setEmail("test@test.com");
        member.setPassword("password");
        member.setCreateDate(LocalDateTime.now());
        this.memberRepository.save(member);

        Review targetReview = new Review();
        targetReview.setAuthor(member);
        targetReview.setRating(4);


//        Review targetReview = reviewService.findById(reviewid);

        model.addAttribute("targetReview" , targetReview);
        return "review_detail";
    }

}
