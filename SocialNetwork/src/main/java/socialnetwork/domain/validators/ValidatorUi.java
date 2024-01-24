package socialnetwork.domain.validators;

public class ValidatorUi {

    public void validate_name(String name)
    {
        if(Character.isUpperCase(name.charAt(0))==false)
            throw new ValidationException("Name must start with uppercase");

        if(name.equals(""))
            throw new ValidationException("Name should contain something");

        if(name.trim().equals(""))
            throw new ValidationException("name should contain something");

    }
    public void validate_id(String id)
    {
       if(is_int(id)== false)
           throw new ValidationException("id must be an int");
    }




    public static boolean is_int(String id)
    {
        try{
            int i = Integer.parseInt(id);
        }catch(NumberFormatException nfe){
            return false;
        }
        return true;
    }


}
