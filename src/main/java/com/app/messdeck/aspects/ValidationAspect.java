package com.app.messdeck.aspects;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.messdeck.businessException.ValidationException;
import com.app.messdeck.model.dto.ValidationErrrorInfo;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import net.sf.oval.context.FieldContext;
import net.sf.oval.context.OValContext;

@Aspect
@Component
public class ValidationAspect {

	@Autowired
	Validator validator;

	@Before("servicePointcut() && annotationPointCutDefination()")
	public void validateArgumentsUsingOVal(JoinPoint jp) throws ValidationException {
		System.out.println("****PointCut is successfully Started");
		Object[] args = jp.getArgs();

		for (Object obj : args) {
			List<ConstraintViolation> lst = validator.validate(obj);
			System.out.println("After Validate Method");
			List<ValidationErrrorInfo> validationErrros = new ArrayList<>();
			for (ConstraintViolation constraintViolation : lst) {
				ValidationErrrorInfo info = new ValidationErrrorInfo();
				info.setErrorMessage(constraintViolation.getMessage());
				OValContext context = constraintViolation.getContext();
				if (context instanceof FieldContext) {
					Field field = ((FieldContext) context).getField();
					info.setFieldName(field.getName());
					// ConstraintViolation[] causes =
					// constraintViolation.getCauses();
					//
					// if (causes != null) {
					// for (ConstraintViolation c : causes) {
					// System.out.println("*** Extra cause" + ((FieldContext)
					// context).getField() + " = "
					// + field.getName());
					// }
					// }

				}
				validationErrros.add(info);

			}
			if (lst != null && lst.size() > 0) {

				System.out.println("Before throwing an Exception");
				throw new ValidationException(validationErrros);
			}

		}
		System.out.println("****PointCut is successfully Ended");

	}

	@Pointcut("@annotation(ValidateWithOval)")
	public void annotationPointCutDefination() {

	}

	@Pointcut("execution(* com.app.messdeck.service.*.*(..))")
	public void servicePointcut() {

	}

}
