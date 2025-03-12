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
	public HttpServiceProxyFactory httpServiceProxyFactory() {
		WebClient webClient = WebClient.builder().baseUrl("https://api.github.com").build();
		return HttpServiceProxyFactory.builderFor(WebClientAdapter.create(webClient)).build();
	}

	@Bean
	public GithubClient githubClient(HttpServiceProxyFactory factory) {
		return factory.createClient(GithubClient.class);

	}
}
