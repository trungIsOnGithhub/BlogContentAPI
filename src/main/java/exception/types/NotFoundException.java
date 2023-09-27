package exception.types;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;

@Getter
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException{
    /**
	 * 
	 */
	private static final long serialVersionUID = 3277603823458226109L;

	private String fieldName;
    private long fieldValue;
    private String resourceName;

    public NotFoundException(String resourceName, String fieldName, long fieldValue) {
        super( String.format("Resource Not Found: %s with %s : '%s'", resourceName, fieldName, fieldValue) );

        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
        this.resourceName = resourceName;
    }
}