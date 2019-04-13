package com.jihuayu.screw.util;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface NameUtil {
    /**
     * Building the registry name, liking [hello, world] to hello_world
     *
     * 将字符串数组用下划线拼合，例如：[hello, world] 转换成 hello_world
     *
     * @param params all parts of name
     * @return built name
     */
    static String buildRegistryName(String... params) {
        StringBuilder stringBuilder = new StringBuilder(params[0].toLowerCase());

        for (int i = 1; i < params.length; i++) {
            stringBuilder.append('_');
            stringBuilder.append(params[i].toLowerCase());
        }

        return stringBuilder.toString();
    }

    /**
     * Building the unlocalized name, liking [hello, world] to helloWorld
     *
     * 将字符串数组转换成小驼峰写法的字符串，例如：[hello, world] 转换成 helloWorld
     *
     * @param params all parts of name
     * @return built name
     *
     * @deprecated Using {@code buildTranslationKey} instead
     */
    @Deprecated
    static String buildUnlocalizedName(String... params) {
        return buildTranslationKey(params);
    }

    /**
     * Building the translation key, liking [hello, world] to helloWorld
     *
     * 将字符串数组转换成小驼峰写法的字符串，例如：[hello, world] 转换成 helloWorld
     *
     * @param params all parts of name
     * @return built name
     * @since 1.1.0
     */
    static String buildTranslationKey(String... params) {
        StringBuilder stringBuilder = new StringBuilder(params[0].toLowerCase());

        for (int i = 1; i < params.length; i++) {
            stringBuilder.append(Character.toUpperCase(params[i].charAt(0)));
            stringBuilder.append(params[i].substring(1).toLowerCase());
        }

        return stringBuilder.toString();
    }

    /**
     * apart name like "helloWorld", "hello_world", "HELLO_WORLD" to ["hello", "world"]
     *
     * 拆分类似小驼峰命名法或下划线命名法的字符串的每个单词拆分，并转换成小写，例如
     * "helloWorld"、"hello_world"、"HELLO_WORLD" 转换成 ["hello", "world"]
     */
    static String[] apart(String name) {
        // FIXED: HELLO -> H_E_L_L_O
        if (name.toUpperCase().equals(name)) {
            return apartUnderline(name);
        }

        return name.contains("_") ? apartUnderline(name) : apartHump(name);
    }

    /**
     * apart hump to array, such as "helloWorld" 2 ["hello", "world"]
     *
     * 拆分小驼峰命名法的字符串，例如："helloWorld" 转换成 ["hello", "world"]
     */
    static String[] apartHump(String name) {
        return humpToUnderline(name).split("_");
    }

    /**
     * apart name like "hello_world", "HELLO_WORLD" (with underline separated) to ["hello", "world"]
     *
     * 拆分下划线命名法的字符串为数组，例如："hello_world", "HELLO_WORLD" 转换成 ["hello", "world"]
     */
    static String[] apartUnderline(String name) {
        return name.toLowerCase().split("_");
    }

    /**
     * the pattern to check upper case letter
     */
    Pattern upperCasePattern = Pattern.compile("[A-Z]");

    /**
     * convert hump to underline format, such as "helloWorld" 2 "hello_world"
     *
     * 转换小驼峰命名法的字符串为下划线命名法，例如："helloWorld" 转换成 "hello_world"
     */
    static String humpToUnderline(String str) {
        Matcher matcher = upperCasePattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        String underline = sb.toString();
        if (underline.charAt(0) == '_') {
            return underline.substring(1);
        }
        return underline;
    }
    /**
     *
     *
     * 把带"|"的字符分开并全部重组。
     */
    static void getAllName(String[] name, List<String[]> list){
        getAllName(name,0, list);
    }
    static void getAllName(String[] name, int pos, List<String[]> list){
        if(pos>=name.length) {
            list.add(name);
            return;
        }
        String[] sName = name[pos].split("\\|");
        String old = name[pos];
        for(String i :sName){
            name[pos]=i;
            getAllName(name,pos+1,list);
        }
        name[pos]=old;
    }
}
