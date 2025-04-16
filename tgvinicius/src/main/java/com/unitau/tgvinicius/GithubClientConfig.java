package com.unitau.tgvinicius;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import com.unitau.tgvinicius.client.GithubClient;

@Configuration
public class GithubClientConfig {
	
	@Bean
	public HttpServiceProxyFactory httpServiceProxyFactory(WebClient.Builder webClientBuilder) {
		WebClient webClient = webClientBuilder
			.baseUrl("https://api.github.com")
			.defaultHeader("X-GitHub-Api-Version", "2022-11-28")
			.build();

		return HttpServiceProxyFactory
			.builderFor(WebClientAdapter.create(webClient))
			.build();
	}

	@Bean
	public GithubClient githubClient(HttpServiceProxyFactory factory) {
		return factory.createClient(GithubClient.class);
	}

	@Bean
	public WebClient.Builder webClientBuilder() {
		return WebClient.builder().codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(10 * 1024 * 1024) // 10
																														// MB
		);
	}
}
