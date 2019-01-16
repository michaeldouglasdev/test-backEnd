package br.com.uol.validator;

import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("emailValidator")
public class EmailValidator implements Validator{

	private Pattern pattern;
	  
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                                                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
  
    public EmailValidator() {
        pattern = Pattern.compile(EMAIL_PATTERN);
    }
 
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
    	if(value == null) {
            return;
        }
    	
    	String email = value.toString();
    	
    	if(email.length() > 0)
	    {
	        if(!pattern.matcher(email).matches()) {
	            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "O e-mail informado não é válido!" , null));
	        }
	    }
    }
    
}
