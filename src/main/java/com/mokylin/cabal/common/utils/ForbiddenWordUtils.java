
package com.mokylin.cabal.common.utils;

import com.google.common.collect.Lists;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 作者: 稻草鸟人
 * 日期: 2015年2月4日9:36:43
 * 项目: admin-tools
 */
public class ForbiddenWordUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(ForbiddenWordUtils.class);

    /**
     * 默认的遮罩文字
     */
    private static final String DEFAULT_MASK = "***";

    /**
     * 屏蔽关键词
     */
    private static List<Pattern> forbiddenWords;


    /**
     * 替换input中的屏蔽关键词为默认的掩码
     *
     * @param input
     * @return
     */
    public static String replace(String input) {
        return replace(input, DEFAULT_MASK);
    }

    /**
     * 将屏蔽关键词 替换为 mask
     *
     * @param input
     * @param mask
     * @return
     */
    public static String replace(String input, String mask) {
        for (int i = 0, l = forbiddenWords.size(); i < l; i++) {
            Pattern forbiddenWordPattern = forbiddenWords.get(i);
            input = forbiddenWordPattern.matcher(input).replaceAll(mask);
        }
        return input;
    }


    /**
     * 是否包含屏蔽关键词
     *
     * @param input
     * @return
     */
    public static boolean containsForbiddenWord(String input) {
        for (int i = 0, l = forbiddenWords.size(); i < l; i++) {
            Pattern forbiddenWordPattern = forbiddenWords.get(i);
            if (forbiddenWordPattern.matcher(input).find()) {
                return true;
            }
        }
        return false;
    }


    static {
        InputStream is = null;
        try {
            String fileName = "forbidden.txt";
            is = ForbiddenWordUtils.class.getResourceAsStream(fileName);
            byte[] fileCBytes;
            fileCBytes = IOUtils.toByteArray(is);
            ForbiddenWordUtils.loadForbiddenWords(fileCBytes);
        } catch (IOException e) {
            LOGGER.error("read forbidden file failed", e);
        } finally {
            IOUtils.closeQuietly(is);
        }

    }


    private static void loadForbiddenWords(byte[] fileCBytes) throws IOException {
        Reader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(fileCBytes), "UTF-8"));
            List<String> forbiddenWordsStrList = IOUtils.readLines(reader);
            forbiddenWords = Lists.newArrayList();
            for (int i = forbiddenWordsStrList.size() - 1; i >= 0; i--) {
                String forbiddenWord = forbiddenWordsStrList.get(i).trim();
                if (forbiddenWord.length() == 0 || forbiddenWord.startsWith("#")) {
                    continue;
                } else {
                    forbiddenWords.add(Pattern.compile(forbiddenWord));
                }
            }
        } catch (Exception e) {
            LOGGER.error("load forbidden words failed", e);
        } finally {
            IOUtils.closeQuietly(reader);
        }
    }

    public static void main(String[] args) {
        System.out.println(replace("天安门"));
        System.out.println(replace("你好"));
    }

}
