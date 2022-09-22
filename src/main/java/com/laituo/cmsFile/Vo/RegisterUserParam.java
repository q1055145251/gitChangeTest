package com.laituo.cmsFile.Vo;


import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class RegisterUserParam {
    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9_-]{4,16}$",message = "用户名不规范(4到16位（字母，数字，下划线，减号)")
    private String uid;
    @NotNull
    @Pattern(regexp = "^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\\d{8}$",message = "请输入正确的手机号")
    private String phone;

    @NotNull
    private String name;

    private String info;
    @NotNull
    @Pattern(regexp = "^[a-zA-Z]\\w{5,17}$",message = "密码不规范(以字母开头，长度在6~18之间，只能包含字母、数字和下划线)")
    private String password;
}
