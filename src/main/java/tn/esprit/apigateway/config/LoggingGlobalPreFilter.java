package tn.esprit.apigateway.config;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
@Slf4j
@Component
public class LoggingGlobalPreFilter implements GlobalFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange
                                     exchange, GatewayFilterChain chain) {
        log.info("Global Pre Filter executed");
        return chain.filter(exchange);
    }
    @Bean
    public GlobalFilter postGlobalFilter() {
        return (exchange, chain) -> {
            return chain.filter(exchange)
                    .then(Mono.fromRunnable(() -> {
                        log.info("Global Post Filter executed");
                    }));
        }; }
}
