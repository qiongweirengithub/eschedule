package com.qw.study.scheduleclient;

import com.qw.study.scheduleclient.core.AnnotationParser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(AnnotationParser.class)
public class ScheduleclientApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScheduleclientApplication.class, args);
	}
}
