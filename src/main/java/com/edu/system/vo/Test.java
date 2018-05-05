package com.edu.system.vo;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.edu.system.vo.types.CodeType;
import com.edu.system.vo.types.TestType;

import lombok.Data;

@Entity
@Table(name = "tests")
@Data
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String body;
    private String condition;
    private String fileToWrite;

    @ElementCollection(targetClass = String.class)
    private List<String> variants;

    @ManyToOne
    private Test test;

    @Enumerated(EnumType.STRING)
    private TestType testType;

    @Enumerated(EnumType.STRING)
    private CodeType codeType;
}
