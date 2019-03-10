package org.exam;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author heshiyuan
 */
@SpringBootApplication
@MapperScan("org.exam.mapper")
//@EnableCaching
public class ExamServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExamServerApplication.class, args);
	}
}
