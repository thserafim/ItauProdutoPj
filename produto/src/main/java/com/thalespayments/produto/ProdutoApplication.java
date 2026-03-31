package com.thalespayments.produto;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProdutoApplication {

	public static void main(String[] args) {
		configureDatasourceFromRenderEnvironment();
		SpringApplication.run(ProdutoApplication.class, args);
	}

	private static void configureDatasourceFromRenderEnvironment() {
		String springDatasourceUrl = readEnv("SPRING_DATASOURCE_URL");
		if (!isBlank(springDatasourceUrl)) {
			return;
		}

		String jdbcDatabaseUrl = readEnv("JDBC_DATABASE_URL");
		if (!isBlank(jdbcDatabaseUrl) && jdbcDatabaseUrl.toLowerCase().startsWith("jdbc:")) {
			System.setProperty("spring.datasource.url", jdbcDatabaseUrl);
			setSystemPropertyIfMissing("spring.datasource.username", readEnv("JDBC_DATABASE_USERNAME"));
			setSystemPropertyIfMissing("spring.datasource.password", readEnv("JDBC_DATABASE_PASSWORD"));
			return;
		}

		String renderDatabaseUrl = readEnv("DATABASE_URL");
		if (isBlank(renderDatabaseUrl)) {
			return;
		}

		ParsedDatabaseUrl parsed = parseRenderDatabaseUrl(renderDatabaseUrl);
		if (parsed != null && !isBlank(parsed.jdbcUrl)) {
			System.setProperty("spring.datasource.url", parsed.jdbcUrl);
			setSystemPropertyIfMissing("spring.datasource.username", parsed.username);
			setSystemPropertyIfMissing("spring.datasource.password", parsed.password);
		}
	}

	private static ParsedDatabaseUrl parseRenderDatabaseUrl(String databaseUrl) {
		try {
			URI uri = new URI(databaseUrl);
			String scheme = uri.getScheme();
			if (isBlank(scheme) || (!"postgres".equalsIgnoreCase(scheme) && !"postgresql".equalsIgnoreCase(scheme))) {
				return null;
			}

			String host = uri.getHost();
			int port = uri.getPort() > 0 ? uri.getPort() : 5432;
			String databaseName = uri.getPath() != null ? uri.getPath().replaceFirst("^/", "") : "";
			if (isBlank(host) || isBlank(databaseName)) {
				return null;
			}

			String query = uri.getQuery();
			String jdbcUrl = "jdbc:postgresql://" + host + ":" + port + "/" + databaseName;
			if (!isBlank(query)) {
				jdbcUrl = jdbcUrl + "?" + query;
			}

			String username = null;
			String password = null;
			String userInfo = uri.getUserInfo();
			if (!isBlank(userInfo)) {
				String[] parts = userInfo.split(":", 2);
				if (parts.length > 0) {
					username = decode(parts[0]);
				}
				if (parts.length > 1) {
					password = decode(parts[1]);
				}
			}

			return new ParsedDatabaseUrl(jdbcUrl, username, password);
		} catch (URISyntaxException ex) {
			return null;
		}
	}

	private static void setSystemPropertyIfMissing(String property, String value) {
		if (isBlank(value) || !isBlank(System.getProperty(property))) {
			return;
		}
		System.setProperty(property, value);
	}

	private static String readEnv(String key) {
		String value = System.getenv(key);
		return isBlank(value) ? null : value;
	}

	private static String decode(String value) {
		return URLDecoder.decode(value, StandardCharsets.UTF_8);
	}

	private static boolean isBlank(String value) {
		return value == null || value.isBlank();
	}

	private record ParsedDatabaseUrl(String jdbcUrl, String username, String password) {
	}
}
