package com.phamvanviet.losoxa.model.request.profile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Validated
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileRequest {

    @Size(min = 6, max = 50, message = "Họ tên phải từ 6 ký tự")
    private String fullName;

    @Size(min = 10, max = 12, message = "Số điện thoại không hợp lệ")
    private String phone;

    @Size(min = 2, max = 100, message = "Must be between 2 and 100 characters")
    private String address;

    @Pattern(regexp="(^$|.{6,30})", message = "Mật khẩu phải từ 6 đến 30 ký tự")
//    @Size(min = 6, max = 30, message = "Mật khẩu phải từ 6 đến 30 ký tự")
    private String currentPassword;

    @Pattern(regexp="(^$|.{6,30})", message = "Mật khẩu phải từ 6 đến 30 ký tự")
//    @Size(min = 6, max = 30, message = "Mật khẩu phải từ 6 đến 30 ký tự")
    private String newPassword;

    @Pattern(regexp="(^$|.{6,30})", message = "Mật khẩu phải từ 6 đến 30 ký tự")
//    @Size(min = 6, max = 30, message = "Mật khẩu phải từ 6 đến 30 ký tự")
    private String confirmPassword;
}
