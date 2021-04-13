package error;

import javax.management.InvalidAttributeValueException;

public class NoSuchRoleException extends InvalidAttributeValueException {
    public NoSuchRoleException(String role) {
        super("No such role: " + role);
    }
}
