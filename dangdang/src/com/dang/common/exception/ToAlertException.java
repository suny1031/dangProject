package com.dang.common.exception;

import com.dang.common.code.ErrorCode;

public class ToAlertException extends CustomException {

	private static final long serialVersionUID = -3007270838782253360L;

	public ToAlertException(ErrorCode error) {
		super(error);
	}

	public ToAlertException(ErrorCode error, Exception e) {
		super(error, e);

	}

}
