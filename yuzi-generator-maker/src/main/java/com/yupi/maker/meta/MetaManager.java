package com.yupi.maker.meta;

import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.json.JSONUtil;

/**
 * 单例模式：只返回一个实例
 */
public class MetaManager {
    //volatile 确保多线程环境下变量的可见性，防止指令重排序问题
    private static volatile Meta meta;

    /** DCL 双重检查锁定
     * 实现原理：1.先判断meta是否被初始化，是，直接拿值
     *          2.否，需要初始化
     *           3.为避免多线程重复初始化，加锁
     *             4.当a线程拿到锁 初始化完meta，为了避免后续线程重复初始化，判断meta是否已完成初始化
     * @return
     */
    public static Meta getMetaObject(){
        if (meta == null){ //第一次检查 无锁提高性能
            synchronized (MetaManager.class){ //加锁
                if (meta == null){ //第二次检查 避免重复初始化
                    meta = initMeta();
                }
            }
        }
        return meta;
    }

    private static Meta initMeta(){
        String metaJson = ResourceUtil.readUtf8Str("meta.json");
        Meta newMeta = JSONUtil.toBean(metaJson, Meta.class);
        //todo 校验配置文件 处理默认值
        return newMeta;
    }
}
