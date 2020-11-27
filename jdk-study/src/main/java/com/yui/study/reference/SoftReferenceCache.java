package com.yui.study.reference;

import com.alibaba.fastjson.JSON;
import com.yui.study.example.dto.AccountDto;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

/**
 * 使用软引用构建cache
 *
 * @author XuZhuohao
 * @date 2020/06/01
 */
public class SoftReferenceCache {
    public static void main(String[] args) {
        // -Xms20M -Xmx20M -Xmn10M -verbose:gc -XX:+PrintGCDetails
        int i = 100;
        while(i-- > 0){
            AccountDto accountDto = new AccountDto();
            accountDto.setUsername("name:" + i);
            accountDto.setObj(new byte[1024 * 1024]);
            SoftReferenceCache.add("key-" + i , (byte)1, accountDto);
        }
        System.out.println(JSON.toJSONString(CACHE_MAP));
    }

    /**
     * 缓存层
     */
    private static Map<String, SoftReference<AccountDto>> CACHE_MAP = new HashMap<>(16);
    /**
     * 删除某个缓存
     *
     * @param myAccount myAccount
     *
     * @param type            账号类型
     */
    public static void remove(String myAccount, byte type) {
        CACHE_MAP.remove(getKey(myAccount, type));
    }

    /**
     * 清除所有缓存
     */
    public static void clear() {
        CACHE_MAP.clear();
    }

    /**
     * 添加配置
     *
     * @param myAccount       myAccount
     * @param type            账号类型
     * @param accountDto entity
     */
    public static void add(String myAccount, byte type, AccountDto accountDto) {
        CACHE_MAP.put(getKey(myAccount, type), new SoftReference<>(accountDto));
    }

    /**
     * 批量添加配置
     *
     * @param configMap 配置 map
     */
    public static void add(Map<String, SoftReference<AccountDto>> configMap) {
        CACHE_MAP.putAll(configMap);
    }

    /**
     * 获取配置
     *
     * @param myAccount myAccount
     * @param type      账号类型
     * @return 对应 entity
     */
    public static AccountDto get(String myAccount, byte type) {
        return CACHE_MAP.get(getKey(myAccount, type)).get();
    }

    private static String getKey(String myAccount, byte type){
        return myAccount + "_" + type;
    }

}
