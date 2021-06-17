package ru.geekbrains.AMorozov.market;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:secured.properties")
public class MarketApplication {
	// Домашнее задание:
	// 1. Добавить к магазину возможность выгрузки всех товаров,
	// и отдельных товаров по id через SOAP сервис

	public static void main(String[] args) {
		SpringApplication.run(MarketApplication.class, args);
	}
}
