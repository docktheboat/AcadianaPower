package com.AcadianaPower.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmailModel {
    private String sender;
    private String message;
    private String service;
    private Integer zipCode;
}
