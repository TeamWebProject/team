package com.TeamProject.TeamProject;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Length;

@Getter
@Setter
@Entity
public class Restaurant {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;//게시글번호 일단 기본키로만 설정후 추후에 수정가능

  @Column(columnDefinition = "TEXT")
  private String titel;//맛집제목

  @Column(columnDefinition = "Text")
  private String category;//맛집설명내용

  private String address;// 맛집주소

  @Column(columnDefinition = "TEXT")
  private String roadAddress; //지도주소

  @Column(columnDefinition = "TEXT")
  private String businessHours;//영업시간







}
