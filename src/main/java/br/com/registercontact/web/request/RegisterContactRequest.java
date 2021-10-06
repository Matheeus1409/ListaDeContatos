package br.com.registercontact.web.request;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class RegisterContactRequest {

    private Long id;

    private String nameContact;

    private String numberTelephone;

}
