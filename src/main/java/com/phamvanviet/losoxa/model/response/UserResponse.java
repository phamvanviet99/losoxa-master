package com.phamvanviet.losoxa.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse extends BaseResponse{
    private String userName;
    private String email;
    private String fullName;
    private String phone;
    private String address;
    private Boolean status;
    private Integer point;
    private RoleResponse role;
}
