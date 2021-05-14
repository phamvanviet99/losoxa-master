package com.phamvanviet.losoxa.model.request.mail;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Validated
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MailGivePasswordRequest {
    @Email(message = "Hãy nhập đúng định dạng email")
    @Size(min = 3, max = 100, message = "Hãy nhập đúng email")
    private String email;
}
