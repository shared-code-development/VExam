package org.exam.common.IdGen;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: changjiang
 * Date: 2019/3/1
 * Time: 16:26
 * To change this template use File | Settings | File Templates.
 */
@Component
public class IdKeyWorkerConfiguration {

    /**
     * 用户的userid
     *
     * @return
     */
    @Bean
    UKeyWorker userIdWorker() {
        return UKeyWorkerFactory.createUKeyWorker(UKeyTypeEnum.USER_COMMON);
    }

}
