package com.app.messdeck.controller.exceptions;

public class ErrorInfo {
	public final String url;
	public final String ex;

	public ErrorInfo(String url, String errorInfo) {
		this.url = url;
		this.ex = errorInfo;
	}
}