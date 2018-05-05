package com.edu.system.validators.code;

import org.junit.Assert;
import org.junit.Test;

import com.edu.system.validators.ValidatorException;
import com.edu.system.vo.types.CodeType;

public class CodeValidatorTest {

    String soutJson = "{\"code\":\"public class Main {" +
            "    public static void main(String[] args) {" +
            "        System.out.println(\\\"Hello World!\\\");" +
            "    }" +
            "}\"}";

    String soutJsonMistake = "{\"code\":\"public class Main {" +
            "    public static void main(String[] args) {" +
            "        System.out.prntln(\\\"Hello World!\\\");" +
            "    }" +
            "}\"}";

    String fileJson = "{\"code\":\"import java.io.BufferedWriter;" +
            "import java.io.FileWriter;" +
            "import java.io.IOException;" +
            "public class Main {" +
            "    public static void main(String[] args) throws IOException {" +
            "        String str = \\\"Hello World!\\\";" +
            "        BufferedWriter writer = new BufferedWriter(new FileWriter(\\\"test.txt\\\"));" +
            "        writer.write(str);" +
            "        writer.close();" +
            "    }" +
            "}\"}";

    @Test
    public void testValidateSOUT() throws ValidatorException {
        com.edu.system.vo.Test test = new com.edu.system.vo.Test();
        test.setCodeType(CodeType.SOUT);
        test.setCondition("Hello World!");
        CodeValidator codeValidator = new CodeValidator();
        Assert.assertTrue(codeValidator.validate(soutJson, test).getSuccess());
    }

    @Test
    public void testValidateFILE() throws ValidatorException {
        com.edu.system.vo.Test test = new com.edu.system.vo.Test();
        test.setCodeType(CodeType.FILE);
        test.setCondition("Hello World!");
        test.setFileToWrite("test.txt");
        CodeValidator codeValidator = new CodeValidator();
        Assert.assertTrue(codeValidator.validate(fileJson, test).getSuccess());
    }

    @Test(expected = ValidatorException.class)
    public void testValidateSOUTCompileError() throws ValidatorException {
        com.edu.system.vo.Test test = new com.edu.system.vo.Test();
        test.setCodeType(CodeType.SOUT);
        test.setCondition("Hello World!");
        CodeValidator codeValidator = new CodeValidator();
        codeValidator.validate(soutJsonMistake, test);
    }
}
