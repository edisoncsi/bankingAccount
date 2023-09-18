package com.ec.banking.account.query.infrastructure.consumers;

import com.ec.banking.account.common.events.AccountClosedEvent;
import com.ec.banking.account.common.events.AccountOpenedEvent;
import com.ec.banking.account.common.events.FundsDepositedEvent;
import com.ec.banking.account.common.events.FundsWithdrawnEvent;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;

/**
 * @author edisoncsi on 15/9/23
 * @project banking-account
 */
public interface EventConsumer {
    void consume(@Payload AccountOpenedEvent event, Acknowledgment ack);
    void consume(@Payload FundsDepositedEvent event, Acknowledgment ack);
    void consume(@Payload FundsWithdrawnEvent event, Acknowledgment ack);
    void consume(@Payload AccountClosedEvent event, Acknowledgment ack);
}
