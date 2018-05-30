package com.edu.system.validators.formula;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import com.edu.system.validators.Validator;
import com.edu.system.validators.ValidatorException;
import com.edu.system.validators.vo.ValidatorResult;
import com.edu.system.vo.Test;

import javafx.util.Pair;

public class FormulaValidator implements Validator {
    @Override
    public ValidatorResult validate(String body, Test test) throws ValidatorException {
        String condition = test.getCondition();
        String[] params = condition.split(",");
        List<Pair<String, Double>> values = Arrays
                .stream(params)
                .filter(param -> !param.contains("[F]="))
                .map(param -> {
                    String[] prm = param.split("=");
                    return new Pair<>(prm[0].trim().replace("[","").replace("]", ""), Double.valueOf(prm[1].trim()));
                }).collect(Collectors.toList());

        String formula = Arrays
                .stream(params)
                .filter(param -> param.contains("[F]="))
                .map(param -> {
                    String[] prm = param.split("=");
                    return prm[1].trim();
                }).findFirst().get();

        for (Pair<String, Double> value : values) {
            formula = formula.replaceAll(value.getKey(), value.getValue().toString());
        }
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");
        try {
            return new ValidatorResult(null, Double.valueOf(body).equals(engine.eval(formula)));
        } catch (Exception e) {
            throw new ValidatorException(e.getMessage(), true);
        }
    }
}
