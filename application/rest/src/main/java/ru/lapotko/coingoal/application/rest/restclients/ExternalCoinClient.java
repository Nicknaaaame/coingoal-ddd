package ru.lapotko.coingoal.application.rest.restclients;

import ru.lapotko.coingoal.application.rest.dto.CoinDto;

import java.util.List;

public interface ExternalCoinClient {
    List<CoinDto> getCoins();
}
