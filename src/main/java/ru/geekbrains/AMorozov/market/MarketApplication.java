package ru.geekbrains.AMorozov.market;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:secured.properties")
public class MarketApplication {
    /*
        Домашнее задание:
        1. Отдельная страница для просмотра информации о товаре
        2. Комментарии клиентов к товарам
        3. * Комментарии пользователь может оставлять только если он покупал этот товар
     */

	public static void main(String[] args) {
		SpringApplication.run(MarketApplication.class, args);
	}
}
