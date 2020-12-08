  
////////////////////////////////////////////////////////////////////
// STEFANO RIZZO 1193464
////////////////////////////////////////////////////////////////////

package it.unipd.tos.model;

import java.time.LocalDate;
import java.time.Period;

public class User {
    String name;
    String surname;
    LocalDate birthDate;

    public User(String userName, String userSurname, 
    LocalDate userBirthDate) {
        /*if (userName == null || userName.equals("")) {
            throw new IllegalArgumentException("No name has been" 
            +" declared for the user");
        }
        if (userSurname == null || userName.equals("")) {
            throw new IllegalArgumentException("No surname has been"
            + " declared for the user");
        } 
        if ((userBirthDate == null) || (Period.between(
            userBirthDate,LocalDate.now()).isNegative())) {
            throw new IllegalArgumentException("No valid data has"
             + "been entered");
        }*/
        name = userName;
        surname = userSurname;
        birthDate = userBirthDate;
    }

    public String getUserName() {
        return name+" "+surname;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public boolean isUnderage() {
        return Period.between(birthDate, LocalDate.now())
        .getYears()<18;
    }

}
