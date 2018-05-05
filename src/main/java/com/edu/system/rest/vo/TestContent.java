package com.edu.system.rest.vo;

import java.util.List;

import com.edu.system.vo.Test;
import com.edu.system.vo.types.CodeType;
import com.edu.system.vo.types.TestType;

import lombok.Data;

@Data
public class TestContent {

    private Long id;
    private String name;
    private String body;
    private String condition;
    private String fileToWrite;
    private List<String> variants;
    private Long test;
    private TestType testType;
    private CodeType codeType;

    public static TestContent from(Test test){
        TestContent testResponse = new TestContent();
        testResponse.setId(test.getId());
        testResponse.setName(test.getName());
        testResponse.setBody(test.getBody());
        testResponse.setCondition(test.getCondition());
        testResponse.setFileToWrite(test.getFileToWrite());
        testResponse.setVariants(test.getVariants());
        testResponse.setTestType(test.getTestType());
        testResponse.setCodeType(test.getCodeType());
        if(test.getTest() != null){
            testResponse.setTest(test.getId());
        }
        return testResponse;
    }
}
