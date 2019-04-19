package org.exam.config;

import org.exam.common.IdGen.UKeyTypeEnum;
import org.exam.common.IdGen.UKeyWorker;
import org.exam.common.IdGen.UKeyWorkerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author heshiyuan
 */
@Configuration
public class IdKeyWorkerConfiguration {
    /**
     * 用户的userId
     * @return
     */
    @Bean
    UKeyWorker userIdWorker() {
        return UKeyWorkerFactory.createUKeyWorker(UKeyTypeEnum.USER_ID);
    }
    @Bean
    UKeyWorker menuIdWorker() {
        return UKeyWorkerFactory.createUKeyWorker(UKeyTypeEnum.MENU_ID);
    }
    @Bean
    UKeyWorker dicTypeIdWorker() {
        return UKeyWorkerFactory.createUKeyWorker(UKeyTypeEnum.DIC_TYPE_ID);
    }

    @Bean
    UKeyWorker dicIdWorker() {
        return UKeyWorkerFactory.createUKeyWorker(UKeyTypeEnum.DIC_ID);
    }
    @Bean
    UKeyWorker academyIdWorker() {
        return UKeyWorkerFactory.createUKeyWorker(UKeyTypeEnum.ACADEMY_ID);
    }

    @Bean
    UKeyWorker majorIdWorker() {
        return UKeyWorkerFactory.createUKeyWorker(UKeyTypeEnum.MAJOR_ID);
    }

    @Bean
    UKeyWorker clazzIdWorker() {
        return UKeyWorkerFactory.createUKeyWorker(UKeyTypeEnum.CLAZZ_ID);
    }
}
