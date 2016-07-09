package com.app.messdeck.customvalidations;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;

import org.apache.log4j.Logger;

import net.sf.oval.constraint.CheckWithCheck;

public class IsValidMessDeckServiceDate implements CheckWithCheck.SimpleCheck {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(IsValidMessDeckServiceDate.class);

	@Override
	public boolean isSatisfied(Object validatedObject, Object value) {
		LocalDate date = (LocalDate) value;

		LocalDate currentDate = LocalDate.now();

		YearMonth nextMonth = YearMonth.from(currentDate).plus(1, ChronoUnit.MONTHS);
		int lastDayOfMonth = nextMonth.lengthOfMonth();
		LocalDate lastDateOfNextMonth = nextMonth.atDay(lastDayOfMonth);
		LocalDate thresholdDate = lastDateOfNextMonth;

		logger.debug("Valid Date Range - ");
		logger.debug("Start Date : " + currentDate);
		logger.debug("End date : " + thresholdDate);

		if (date.compareTo(currentDate) < 0 || date.compareTo(thresholdDate) > 0) {
			return false;
		} else {
			return true;
		}

	}

}
