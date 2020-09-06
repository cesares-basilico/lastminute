package it.lastminute.sales.business.login;

import it.lastminute.sales.commonmodels.LoginRequest;
import it.lastminute.sales.commonmodels.UserDto;

public interface LoginBd {

    UserDto login(final LoginRequest request);

}
