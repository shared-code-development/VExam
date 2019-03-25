package org.exam.common.IdGen;

/**
 * Created with IntelliJ IDEA.
 * User: changjiang
 * Date: 2019/3/1
 * Time: 15:34
 * To change this template use File | Settings | File Templates.
 */
public class UKeyWorkerFactory {

    /**
     * 生成各种业务标示的主键
     * @param keyTypeEnum
     * @return
     */
    public static UKeyWorker createUKeyWorker(UKeyTypeEnum keyTypeEnum){
        return new UKeyWorker(UKeyTypeEnum.getKeyTypeFlag(keyTypeEnum));
    }
}
