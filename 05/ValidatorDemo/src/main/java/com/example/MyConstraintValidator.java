package com.example.demo;



import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MyConstraintValidator implements ConstraintValidator<MyCustomConstraint, String> {
    // String 为校验的类型
    @Override
    public void initialize(MyCustomConstraint myConstraint) {
        // 在启动时执行
    }

    //自定义校验逻辑
    @Override
    public boolean isValid(String s,
                           ConstraintValidatorContext validatorContext) {
        return s.equals("北京") || s.equals("上海");
    }
}
