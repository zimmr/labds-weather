package model.requests.validation;

public class EmailValidator {
    
    protected boolean validateEmail(String email) {

        if (email.contains(" ") || !email.contains("@"))
            return false;

        var splitEmail = email.split("@");
        if (splitEmail.length < 2)
            return false;

        if (splitEmail.length >= 2)
        {
            if (!splitEmail[1].contains("."))
                return false;

            var splitDomain = splitEmail[1].split("\\.");
            if (splitDomain.length < 2)
                return false;

            if (splitDomain.length >= 2 && !splitDomain[1].equals("org") && !splitDomain[1].equals("com") && !splitDomain[1].equals("edu"))
                return false;
        }

        return true;
    }
}
