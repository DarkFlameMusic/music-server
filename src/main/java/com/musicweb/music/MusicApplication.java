package com.musicweb.music;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.sql.DataSource;

@EnableSwagger2
@SpringBootApplication
@MapperScan(basePackages = "com.musicweb.music.dao")
public class MusicApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(MusicApplication.class, args);
		DataSource dataSource = context.getBean(DataSource.class);

		System.out.println(dataSource.getClass());
	}
}
