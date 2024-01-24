package socialnetwork.domain.validators;

import socialnetwork.domain.Utilizator;
import socialnetwork.service.UtilizatorService;

public class UtilizatorValidator implements Validator<Utilizator> {
    @Override
    public void validate(Utilizator entity) throws ValidationException {
        //TODO: implement method validate
        System.out.println(entity);

        if(Character.isUpperCase(entity.getFirstName().charAt(0))==false)
            throw new ValidationException("FirstName must start with uppercase");

        if(entity.getFirstName().equals(""))
            throw new ValidationException("Firstname should contain something");

        if(entity.getFirstName().trim().equals(""))
            throw new ValidationException("Firstname should contain something");

        if(Character.isUpperCase(entity.getLastName().charAt(0))==false)
            throw new ValidationException("LastName must start with uppercase");

        if(entity.getLastName().equals(""))
            throw new ValidationException("Lastname should contain something");

        if(entity.getLastName().trim().equals(""))
            throw new ValidationException("Lastname should contain something");
    }
}
