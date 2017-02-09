package io.khasang.archivarius.config;

import io.khasang.archivarius.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;

@Configuration
@PropertySource(value = {"classpath:auth.properties"})
@PropertySource(value = {"classpath:hibernate.properties"})
public class AppConfig {
    @Autowired
    Environment environment;
    @Autowired
    UserRepository userRepository;

    @Bean
    public UserDetailsService userDetailsService() {
        JdbcDaoImpl jdbcImpl = new JdbcDaoImpl();
        jdbcImpl.setDataSource(dataSource());
        jdbcImpl.setUsersByUsernameQuery(environment.getRequiredProperty("usersByQuery"));
        jdbcImpl.setAuthoritiesByUsernameQuery(environment.getRequiredProperty("rolesByQuery"));
        return jdbcImpl;
    }

    @Bean
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getProperty("jdbc.postgresql.driverClass"));
        dataSource.setUrl(environment.getProperty("jdbc.postgresql.url"));
        dataSource.setUsername(environment.getProperty("jdbc.postgresql.username"));
        dataSource.setPassword(environment.getProperty("jdbc.postgresql.password"));
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource());
        return jdbcTemplate;
    }

    @EventListener(ContextRefreshedEvent.class)
    public void contextRefreshedEvent() {
        if (userRepository.findAll().isEmpty()) {
            JdbcTemplate jdbcTemplate = jdbcTemplate();
            jdbcTemplate.execute("INSERT INTO worker(id, companyworker, lastname) VALUES (1, TRUE, 'admin')");
            jdbcTemplate.execute("INSERT INTO users(id, login, password, worker_id) VALUES (1, 'admin', '$2a$10$FB3WSArYXyhy94xELFE9f.45/nHD6lnww5PGI0MCMikWQUDofHKuW', 1)");
            jdbcTemplate.execute("INSERT INTO roles(id, role_name) VALUES (1, 'ROLE_ADMIN'), (2, 'ROLE_USER'), (3, 'ROLE_DOCUMENTOVED')");
            jdbcTemplate.execute("INSERT INTO users_roles(users_id, roles_id) VALUES (1, 1)");
        }
    }
}
