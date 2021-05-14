package com.phamvanviet.losoxa.model.request.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Set;

@Validated
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateRequest {
    @Size(min = 3, max = 50, message = "Tên đăng nhập từ 3 đến 50 ký tự")
    private String userName;

    @Size(min = 6, max = 50, message = "Họ tên từ 6 đến 50 ký tự")
    private String fullName;

    @Email(message = "Hãy nhập đúng định dạng email")
    @Size(min = 3, max = 100, message = "Hãy nhập đúng email")
    private String email;

    @Size(min = 6, max = 30, message = "Mật khẩu từ 6 đến 30 ký tự")
    private String password;

    @Pattern(regexp="(^$|.{10})", message = "Số điện thoại không tồn tại")
//    @Size(min = 10, max = 10, message = "Số điện thoại không tồn tại")
    private String phone;

    @Pattern(regexp="(^$|.{2,100})", message = "Địa chỉ từ 2 đến 100 ký tự")
//    @Size(min = 2, max = 100, message = "Địa chỉ từ 2 đến 100 ký tự")
    private String address;

    private Long roleId;
}
