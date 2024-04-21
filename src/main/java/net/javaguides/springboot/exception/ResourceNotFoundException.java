package net.javaguides.springboot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.logging.Level;
import java.util.logging.Logger;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{
	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;
	private String resourceName;
	private String fieldName;
	private Object fieldValue;
	
	public ResourceNotFoundException(Class<?> clazz,String resourceName, String fieldName, Object fieldValue) {
		String message =String.format("%s record not found with %s : '%s'", resourceName, fieldName, fieldValue);
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
		LoggerUtil.logError(clazz,message,null);
	}
	
	public String getResourceName() {
		return resourceName;
	}
	
	public String getFieldName() {
		return fieldName;
	}
	
	public Object getFieldValue() {
		return fieldValue;
	}
	
}
