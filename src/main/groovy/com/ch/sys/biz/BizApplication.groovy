package com.ch.sys.biz
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.annotation.EnableScheduling
import com.ch.sys.biz.BizApplication
import com.ch.sys.biz.system.utils.SpringContextUtils
/**
 * 工程启动
 * @author 20112004
 * 2019-07-23
 */
@SpringBootApplication
@EnableAsync
@EnableScheduling
class BizApplication {

	static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(BizApplication, args)
		SpringContextUtils.setApplicationContext(context)
	}

}