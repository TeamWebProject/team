package com.TeamProject.TeamProject;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class RestaurantReview {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(columnDefinition = "TEXT")
  private String title; //후기 제목

  private int rating;//후기점수
  
  @Column(columnDefinition = "TEXT")
  private String content;//후기내용

  private String imageUrl; // 이미지 URL을 저장할 필드

  private byte[] image; // 이미지를 byte 배열로 저장할 필드

  private String voter;// 추천및 공감
}
