package com.qa.exceptions;

import javax.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason =" This Team doesn'e exist")
public class TeamNotFoundException extends  EntityNotFoundException {



}
