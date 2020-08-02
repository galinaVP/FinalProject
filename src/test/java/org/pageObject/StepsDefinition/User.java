package org.pageObject.StepsDefinition;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class User {
    String firstName;
    String lastName;
    String email;
    String password;
    String confirmPassword;
}
