package com.phamvanviet.losoxa.model.request.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Validated
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterRequest {
    @Size(min = 3, max = 50, message = "Tên đăng nhập từ 3 đến 50 ký tự")
    private String userName;

    @Size(min = 6, max = 50, message = "Họ tên từ 6 đến 50 ký tự")
    private String fullName;

    @Email(message = "Hãy nhập đúng định dạng email")
    @Size(min = 3, max = 100, message = "Hãy nhập đúng email")
    private String email;

    @Size(min = 6, max = 30, message = "Mật khẩu từ 6 đến 30 ký tự")
    private String password;
}
