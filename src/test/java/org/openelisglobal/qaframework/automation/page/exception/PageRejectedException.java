package org.openelisglobal.qaframework.automation.page.exception;

public class PageRejectedException extends RuntimeException {

	public PageRejectedException(String pageRejectUrl) {
		super(pageRejectUrl);
	}

	public PageRejectedException(String message, Throwable cause) {
		super(message, cause);
	}

	public PageRejectedException(Throwable cause) {
		super(cause);
	}

	public PageRejectedException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
