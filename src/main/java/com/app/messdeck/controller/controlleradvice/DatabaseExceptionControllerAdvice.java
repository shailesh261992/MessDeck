package com.app.messdeck.controller.controlleradvice;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.app.messdeck.businessException.CustomerNotExistsException;
import com.app.messdeck.businessException.MessDeckServiceInfoNotExistException;
import com.app.messdeck.businessException.ValidationException;
import com.app.messdeck.businessException.VendorNotExistException;
import com.app.messdeck.controller.exceptions.ErrorInfo;
import com.app.messdeck.model.dto.ValidationErrrorInfo;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

@ControllerAdvice
public class DatabaseExceptionControllerAdvice {
	private static Logger logger = Logger.getLogger(DatabaseExceptionControllerAdvice.class);

	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler({ DataIntegrityViolationException.class, })
	public @ResponseBody ErrorInfo handleDatabseExceptions(HttpServletRequest req,
			DataIntegrityViolationException exception) {
		logger.debug(exception.getMessage(), exception);
		// exception.printStackTrace();
		if (exception.getMostSpecificCause() instanceof MySQLIntegrityConstraintViolationException) {
			return new ErrorInfo(req.getRequestURL().toString(),
					((MySQLIntegrityConstraintViolationException) exception.getMostSpecificCause())
							.getLocalizedMessage());

		}

		return new ErrorInfo(req.getRequestURL().toString(), exception.getMessage());

	}

	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler({ VendorNotExistException.class })
	public @ResponseBody ErrorInfo handleVendorNotExistException(HttpServletRequest req,
			VendorNotExistException exception) {

		logger.debug(exception.getMessage(), exception);
		return new ErrorInfo(req.getRequestURL().toString(), exception.getMessage());

	}

	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler({ CustomerNotExistsException.class })
	public @ResponseBody ErrorInfo handleCustomerNotExistsException(HttpServletRequest req,
			CustomerNotExistsException exception) {
		logger.debug(exception.getMessage(), exception);
		return new ErrorInfo(req.getRequestURL().toString(), exception.getMessage());

	}

	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler({ MessDeckServiceInfoNotExistException.class })
	public @ResponseBody ErrorInfo handleMessDeckServiceInfoNotExistException(HttpServletRequest req,
			MessDeckServiceInfoNotExistException exception) {
		logger.debug(exception.getMessage(), exception);
		return new ErrorInfo(req.getRequestURL().toString(), exception.getMessage());

	}

	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler({ ValidationException.class, })
	public @ResponseBody List<ValidationErrrorInfo> handleValidationExceptions(HttpServletRequest req,
			ValidationException exception) {
		// exception.printStackTrace();
		logger.debug(exception.getMessage(), exception);
		return exception.getViolationList();

	}

	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler({ HttpMessageNotReadableException.class, })
	public @ResponseBody ErrorInfo handleHttpMessageNotReadableException(HttpServletRequest req,
			HttpMessageNotReadableException exception) {
		logger.debug("Unable to convert Json Into DTO", exception);

		return new ErrorInfo(req.getRequestURL().toString(), exception.getMessage());

	}

	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler({ RuntimeException.class, })
	public @ResponseBody ErrorInfo handleRunTimeExceptions(HttpServletRequest req, RuntimeException exception) {
		logger.debug(exception.getMessage(), exception);
		return new ErrorInfo(req.getRequestURL().toString(), exception.getMessage());

	}
}
