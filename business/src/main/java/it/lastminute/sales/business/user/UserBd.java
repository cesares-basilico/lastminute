package it.lastminute.sales.business.user;

import it.lastminute.sales.commonmodels.InsertUserRequest;
import it.lastminute.sales.commonmodels.InsertUserResponse;

public interface UserBd {

    InsertUserResponse insertUser(final InsertUserRequest request);

}
