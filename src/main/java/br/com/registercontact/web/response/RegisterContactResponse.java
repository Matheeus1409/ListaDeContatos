package br.com.registercontact.web.response;


import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class RegisterContactResponse {

    private Long id;

    private String nameContact;

    private String numberTelephone;


}
