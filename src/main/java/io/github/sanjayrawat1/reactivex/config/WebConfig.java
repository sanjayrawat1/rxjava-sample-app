package io.github.sanjayrawat1.reactivex.config;

import io.github.sanjayrawat1.reactivex.config.handler.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author Sanjay Singh Rawat
 * @since 2019.08.31
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> handlers) {
        handlers.add(new CompletableReturnValueHandler());
        handlers.add(new FlowableReturnValueHandler());
        handlers.add(new MaybeReturnValueHandler());
        handlers.add(new ObservableReturnValueHandler());
        handlers.add(new SingleReturnValueHandler());
    }
}
