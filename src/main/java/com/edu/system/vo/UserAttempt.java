package com.edu.system.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "user_attempt")
@Data
public class UserAttempt {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_test")
    private Test test;

    @ManyToOne
    private User user;

    @ManyToOne
    private Article article;

    private Boolean result;

    @Column(name = "count_attempt", nullable = false, columnDefinition = "int default 0")
    private Integer count;

}
