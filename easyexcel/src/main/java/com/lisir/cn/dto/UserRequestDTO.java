package com.lisir.cn.dto;

import com.lisir.cn.group.Create;
import com.lisir.cn.group.Update;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

/**
 * @Auther: liSir
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO {

    @Null(message = "新增用户,id应为空", groups = {Create.class})
    @NotNull(message = "更新用户, id不能为空", groups = {Update.class})
    private Integer id;

    @NotBlank(message = "用户名不能为空")
    @Size(max = 50, message = "用户名长度不能大于50")
    private String name;

    @NotBlank(message = "密码不能为空")
    @Size(max = 50, min = 4, message = "密码长度4~50为之间")
    private String password;

    @NotNull(message = "年龄不能为空")
    @Size(max = 11, message = "年龄长度不能大于11")
    private Integer age;
}
