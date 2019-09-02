package io.github.sanjayrawat1.reactivex.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Sanjay Singh Rawat
 * @since 2019.09.01
 */
@Configuration
@EnableJpaRepositories("io.github.sanjayrawat1.reactivex.repository")
@EnableTransactionManagement
public class DatabaseConfiguration {
}
