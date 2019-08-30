package app.validation;

import app.model.Message;

public interface Validator {
    String validate(Message msg);
}
