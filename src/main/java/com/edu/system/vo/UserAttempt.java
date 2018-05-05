package com.edu.system.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.edu.system.vo.types.TestType;

import lombok.Data;

@Entity
@Table(name = "user_attempt")
@Data
public class UserAttempt {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Test test;

    @ManyToOne
    private User user;

    @Column(name = "count_attempt", nullable = false, columnDefinition = "int default 0")
    private Integer count;

}
