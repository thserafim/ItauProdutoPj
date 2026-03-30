package com.itaupj.produto.exception;

import java.time.LocalDateTime;

public class ApiErrorResponse {

    private final int status;
    private final String mensagem;
    private final LocalDateTime timestamp;

    public ApiErrorResponse(int status, String mensagem){
        this.status = status;
        this.mensagem = mensagem;
        this.timestamp = LocalDateTime.now();
    }

    public int getStatus(){
        return status;
    }

    public String getMensagem(){
        return mensagem;
    }

    public LocalDateTime getTimestamp(){
        return timestamp;
    }


}
