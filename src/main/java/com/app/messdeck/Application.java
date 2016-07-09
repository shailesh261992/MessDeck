package com.app.messdeck;

import org.hibernate.hql.internal.ast.tree.MapValueNode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.app.messdeck.configuration.MessDeckConfiguration;

@SpringBootApplication
@Import(MessDeckConfiguration.class)
@EnableJpaRepositories
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
        
	}

}
