package ru.lapotko.coingoal.core.exception;

public class CoinNotFoundException extends DomainNotFoundException {
    public CoinNotFoundException(Long id) {
        super("Coin with id [%s] not found".formatted(id));
    }
}
