package com.example.busreservation.config;

import com.example.busreservation.websocket.ReserveHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.standard.ServletServerContainerFactoryBean;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

@Configuration
public class WebSocketConfiguration implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        webSocketHandlerRegistry
                .addHandler(reserveHandler(), "/reserve")
                .addInterceptors(new HttpSessionHandshakeInterceptor());
    }

    @Bean
    public ReserveHandler reserveHandler() {
        return new ReserveHandler();
    }

    @Bean
    public ServletServerContainerFactoryBean servletServerContainerFactoryBean() {
        ServletServerContainerFactoryBean containerFactoryBean = new ServletServerContainerFactoryBean();
        containerFactoryBean.setMaxTextMessageBufferSize(8192);
        containerFactoryBean.setMaxBinaryMessageBufferSize(8192);

        return containerFactoryBean;
    }
}
