package error;

import com.sun.jdi.InvalidTypeException;

import javax.management.InvalidAttributeValueException;

public class NoSuchCommandException extends InvalidAttributeValueException {
    public NoSuchCommandException() {
        super("No such command.");
    }
}
