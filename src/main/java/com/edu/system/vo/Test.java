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
import javax.persistence.Table;

import com.edu.system.vo.types.CodeType;
import com.edu.system.vo.types.TestType;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "tests")
@Data
public class Test extends AbstractCadr{

    private String condition;
    private String fileToWrite;

    @ElementCollection(targetClass = String.class)
    private List<String> variants;

    @Enumerated(EnumType.STRING)
    private TestType testType;

    @Enumerated(EnumType.STRING)
    private CodeType codeType;
}
