package com.br.santander.exception;

import com.br.santander.util.MessageUtil;

public class NotFoundException extends RuntimeException {
    public NotFoundException(){
        super(MessageUtil.NO_RECORDS_FOUND);
    }
}
