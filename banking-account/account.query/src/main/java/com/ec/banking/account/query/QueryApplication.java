package com.ec.banking.account.query;

import com.ec.banking.account.query.api.queries.*;
import com.ec.banking.cqrs.core.infrastructure.QueryDispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class QueryApplication {

	@Autowired
	private QueryDispatcher queryDispatcher;

	@Autowired
	private QueryHandler queryHandler;

	public static void main(String[] args) {
		SpringApplication.run(QueryApplication.class, args);
	}

	@PostConstruct
	public void registerHandlers(){
		queryDispatcher.registerHandler(FindAccountByHolderQuery.class,queryHandler::handle);
		queryDispatcher.registerHandler(FindAccountByIdQuery.class,queryHandler::handle);
		queryDispatcher.registerHandler(FindAccountWithBalanceQuery.class,queryHandler::handle);
		queryDispatcher.registerHandler(FindAllAccountsQuery.class,queryHandler::handle);
	}

}
