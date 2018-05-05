package com.edu.system.validators.code;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.UUID;

import org.apache.commons.io.FileUtils;

import com.edu.system.validators.Validator;
import com.edu.system.validators.ValidatorException;
import com.edu.system.validators.code.vo.CodeValidateBody;
import com.edu.system.validators.vo.ValidatorResult;
import com.edu.system.vo.Test;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CodeValidator implements Validator {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public ValidatorResult validate(String body, Test test) throws ValidatorException {
        String folderName = UUID.randomUUID().toString();
        try {
            CodeValidateBody validateBody = objectMapper.readValue(body, CodeValidateBody.class);
            String response = executeCode(validateBody.getCode(), folderName).replace("\n", "");
            switch (test.getCodeType()) {
                case SOUT:
                    return new ValidatorResult(response, response.equals(test.getCondition()));
                case FILE:
                    String content = FileUtils.readFileToString(new File(folderName + "/" + test.getFileToWrite()));
                    return new ValidatorResult(content, content.equals(test.getCondition()));
                default:
                    throw  new ValidatorException("Internal server error", true);
            }
        } catch (IOException e) {
            throw new ValidatorException("Internal server error", true);
        }
    }

    private String executeCode(String code, String folderName) throws ValidatorException {
        try {
            File file = new File(folderName + "/Main.java");
            FileUtils.writeStringToFile(file, code);
            Process process = Runtime.getRuntime().exec("javac " + folderName + "/Main.java");
            process.waitFor();
            String resultOfCompiling = readFromProcess(process);
            if (!resultOfCompiling.isEmpty()) {
                throw new ValidatorException(resultOfCompiling, false);
            }
            process = Runtime.getRuntime().exec("java Main", null, new File(folderName));
            process.waitFor();
            return readFromProcess(process);
        } catch (IOException | InterruptedException e) {
            throw new ValidatorException("Internal server error", true);
        }
    }

    private String readFromProcess(Process process) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line).append("\n");
        }
        reader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line).append("\n");
        }
        return stringBuilder.toString();
    }
}