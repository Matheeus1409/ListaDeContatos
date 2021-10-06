package br.com.registercontact.web.response;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class SelectContactResponse {

    private Long id;

    private String nameContact;

    private String numberTelephone;
}
