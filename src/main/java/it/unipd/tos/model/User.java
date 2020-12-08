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
        name = userName;
        surname = userSurname;
        birthDate = userBirthDate;
    }

    public User(User u) {
        name=u.name;
        surname=u.surname;
        birthDate=u.birthDate;
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
