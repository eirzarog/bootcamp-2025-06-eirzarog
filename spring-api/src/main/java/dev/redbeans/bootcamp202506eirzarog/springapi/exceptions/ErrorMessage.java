package dev.redbeans.bootcamp202506eirzarog.springapi.exceptions;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMessage {

    private String errorMessage;
    private int errorCode;
}
