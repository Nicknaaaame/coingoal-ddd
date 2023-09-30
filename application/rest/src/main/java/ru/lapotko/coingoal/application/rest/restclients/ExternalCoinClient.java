package ru.lapotko.coingoal.application.rest.restclients;


import ru.lapotko.coingoal.infrastructure.jpa.dto.CoinDto;

import java.util.List;

public interface ExternalCoinClient {
    List<CoinDto> getCoins();
}
