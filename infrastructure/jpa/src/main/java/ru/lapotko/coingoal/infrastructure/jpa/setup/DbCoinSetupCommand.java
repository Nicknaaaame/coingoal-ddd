package ru.lapotko.coingoal.infrastructure.jpa.setup;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import ru.lapotko.coingoal.infrastructure.jpa.dto.CoinDto;
import ru.lapotko.coingoal.infrastructure.jpa.repository.CoinJpaRepository;
import ru.lapotko.coingoal.infrastructure.jpa.util.ConvertUtil;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Log4j2
public class DbCoinSetupCommand implements ApplicationRunner {
    private final CoinJpaRepository coinRepository;
    private final ObjectMapper jsonMapper = new ObjectMapper();

    {
        jsonMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Override
    public void run(ApplicationArguments args) {
        try {
            if (coinRepository.count() == 0) {
                log.info("Coin table is empty, try to setup ...");
                List<CoinDto> data = null;
                try {
                    data = getData();
                } catch (IOException e) {
                    log.info("Error while setup: Import file not found");
                    return;
                }
                if (data.isEmpty()) {
                    log.info("Import file is empty");
                }
                log.info("Importing coins, size: [{}]", data.size());
                coinRepository.saveAll(data.parallelStream()
                        .map(ConvertUtil::convertToCoinEntity)
                        .collect(Collectors.toList()));
                log.info("Successfully imported");
            }
        } catch (Exception e) {
            log.warn("Something went wrong while setup db");
            log.debug(e);
        }
    }

    public String getImportFilePath() {
        return  "db_setup/coin_list_5000.json";
    }
    private List<CoinDto> getData() throws IOException {
        String importFile = getImportFilePath();
        try (InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream(importFile)) {
            if (Objects.isNull(resourceAsStream))
                return Collections.emptyList();
            String json = new String(resourceAsStream.readAllBytes());
            return jsonMapper.readValue(json, new TypeReference<>() {
            });
        }
    }
}
