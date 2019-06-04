package com.huihu.commonlib.utils;


import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 周凯
 * @date 2015年7月16日 上午10:36:37
 * @description 常用的一些正则表达式校验
 */
public class ValidateTool {

    private static ValidateTool vt = null;


    public static ValidateTool getInstance() {
        if (vt == null) {
            vt = new ValidateTool();
        }
        return vt;
    }

    private ValidateTool() {
    }

    /**
     * 检查 email输入是否正确
     * 正确的书写格 式为 username@domain
     *
     * @param value
     * @return
     */
    public boolean checkEmail(String value, int length) {
        return value
                .matches("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*")
                && value.length() <= length;
    }

    /**
     * 检查手机输入 是否正确
     *
     * @param value
     * @return
     */
    public boolean checkMobile(String value) {
        //13\d{9}|14[57]\d{8}|15[012356789]\d{8}|18[012356789]\d{8}|17[012356789]\d{8}
        //^1[3,4,5,7,8]\d{9}$
        Pattern p = Pattern.compile("^(86){0,1}1\\d{10}$");
        if (value == null) return false;
        Matcher m = p.matcher(value);
        boolean b = m.matches();
        return m.matches();
    }

    /**
     * 检查国家手机输入 是否正确
     *
     * @param value regioncode + phonenumber
     * @return
     */
    public boolean checkContryMobile(String value, String reguler) {
        //^(00){0,1}(852){1}0{0,1}[1,5,6,9](?:\d{7}|\d{8}|\d{12})$"  -(00){0,1}(852)为区号
        LogUtil.e("fxhttp", "电话号码```" + value);
        LogUtil.e("fxhttp", "规则```" + reguler);
        Pattern p = Pattern.compile(reguler);
        if (value == null) return false;
        Matcher m = p.matcher(value);
        boolean b = m.matches();
        return m.matches();
    }

    /**
     * 验证密码是6-20位的混合组合，且不能单独输入一种
     *
     * @param value
     * @return
     */
    public boolean checkpwdword(String value) {

        //[A-Za-z0-9]{6,20}
        //^[A-Za-z]{1}[A-Za-z0-9]{5,20}
        //
        //
        Pattern p = Pattern.compile("^(?![\\d]+$)(?![a-zA-Z]+$)(?![^\\da-zA-Z]+$).{6,20}$");
        Matcher m = p.matcher(value);
        return m.matches();
    }

    /**
     * 验证密码是4-25位的混合组合，且不能单独输入一种
     * {4,26}含头不含尾
     *
     * @param value
     * @return
     */
    public boolean checkpwdword1(String value) {

        //[A-Za-z0-9]{6,20}
        //^[A-Za-z]{1}[A-Za-z0-9]{5,20}
        //
        //
        Pattern p = Pattern.compile("^(?![\\d]+$)(?![a-zA-Z]+$)(?![^\\da-zA-Z]+$).{4,26}$");
        Matcher m = p.matcher(value);
        return m.matches();
    }

    /**
     * 检测是否输入中文
     *
     * @param value
     * @return
     */
    public boolean checkZhongWen(String value) {
        Pattern p = Pattern.compile("[\\u4e00-\\u9fa5]");
        Matcher m = p.matcher(value);
        boolean ff = m.matches();
        return m.matches();
    }

    /**
     * 验证字符里面是否含有字母
     *
     * @param value
     * @return
     */
    public boolean checkHaveLetter(String value) {
        Pattern p = Pattern.compile(".*[a-zA-Z]+.*");
        Matcher m = p.matcher(value);
        return m.matches();
    }

    /**
     * 检查中文名输 入是否正确
     *
     * @param value
     * @return
     */
    public boolean checkChineseName(String value) {
        return value.matches("^([\u4E00-\uFA29]|[\uE7C7-\uE7F3]){2,10}$");
    }

    /**
     * 检查中文名输 入是否正确
     *
     * @param value
     * @return
     */
    //^[A-Za-z]{1}[A-Za-z0-9]{1,12}
    //[\u4e00-\u9fa5,a-z,A-Z][\u4e00-\u9fa5,a-z,A-Z,0-9]{0,9}
    public boolean checkUserName(String value) {
        return value.matches("[\u4e00-\u9fa5,a-z,A-Z][\u4e00-\u9fa5,a-z,A-Z,0-9]{0,9}");
    }

    public boolean checkgrounpName(String value) {
        return value.matches("^[a-zA-Z\\u4e00-\\u9fa5][a-zA-Z0-9\\u4e00-\\u9fa5]+");
    }

    /**
     * 检查URL是 否合法
     *
     * @param value
     * @return
     */
    public boolean checkURL(String value) {
        return value.matches("[a-zA-z]+://[^\\s]*");
    }

    /**
     * 检查IP是否 合法
     *
     * @param value
     * @return
     */
    public boolean checkIP(String value) {
        return value.matches("\\d{1,3}+\\.\\d{1,3}+\\.\\d{1,3}+\\.\\d{1,3}");
    }

    /**
     * 检查QQ是否 合法，必须是数字，且首位不能为0，最长15位
     *
     * @param value
     * @return
     */

    public boolean checkQQ(String value) {
        return value.matches("[1-9][0-9]{4,13}");
    }

    /**
     * 检查邮编是否合法
     *
     * @param value
     * @return
     */
    public boolean checkPostCode(String value) {
        return value.matches("[1-9]\\d{5}(?!\\d)");
    }


    /**
     * 检查输入是否超出规定长度
     * Java教程:http://www.javaweb.cc
     *
     * @param length
     * @param value
     * @return
     */
    public boolean checkLength(String value, int length) {
        return ((value == null || "".equals(value.trim())) ? 0 : value.length()) <= length;
    }

    /**
     * 检查是否为空 字符串,空：true,不空:false
     *
     * @param value
     * @return
     */
    public boolean checkNull(String value) {
        return value == null || "".equals(value.trim()) || "null".equals(value.trim());
    }

    /**
     * 是否包含emoji表情
     */
    public boolean containsEmoji(String source) {
//		int len = source.length();
//		for (int i = 0; i < len; i++) {
//			char hs = source.charAt(i);
//			if (0xd800 <= hs && hs <= 0xdbff) {
//				if (source.length() > 1) {
//					char ls = source.charAt(i+1);
//					int uc = ((hs - 0xd800) * 0x400) + (ls - 0xdc00) + 0x10000;
//					if (0x1d000 <= uc && uc <= 0x1f77f) {
//						return true;
//					}
//				}
//			} else {
//				// non surrogate
//				if (0x2100 <= hs && hs <= 0x27ff && hs != 0x263b) {
//					return true;
//				} else if (0x2B05 <= hs && hs <= 0x2b07) {
//					return true;
//				} else if (0x2934 <= hs && hs <= 0x2935) {
//					return true;
//				} else if (0x3297 <= hs && hs <= 0x3299) {
//					return true;
//				} else if (hs == 0xa9 || hs == 0xae || hs == 0x303d || hs == 0x3030 || hs == 0x2b55 || hs == 0x2b1c || hs == 0x2b1b || hs == 0x2b50|| hs == 0x231a ) {
//					return true;
//				}
//				if (source.length() > 1 && i < source.length() -1) {
//					char ls = source.charAt(i+1);
//					if (ls == 0x20e3) {
//						return true;
//					}
//				}
//			}
//		}
        String reg = "[\\ud800\\udc00-\\udbff\\udfff\\ud800-\\udfff]";
        Matcher matcher = Pattern.compile(reg).matcher(source);
        return matcher.find();
    }

    /**
     * 去掉文案中所有空格
     *
     * @param str
     * @return
     */
    public String toTrimALL(String str) {
        return str.trim();
    }

    /**
     * 判断字符串是否为数字或中文或字母
     * *各种字符的unicode编码的范围：
     * 汉字：[0x4e00,0x9fa5]（或十进制[19968,40869]）
     * 数字：[0x30,0x39]（或十进制[48, 57]）
     * 小写字母：[0x61,0x7a]（或十进制[97, 122]）
     * 大写字母：[0x41,0x5a]（或十进制[65, 90]）
     * 常用字符
     */
    public static boolean isLetterDigitOrChinese(String str) {
        String regex = "^[a-z0-9A-Z\u4e00-\u9fa5\\s"
                + ".@~\\-,:\\*\\?!_#/=+﹉&^;%…$()\\<>|•¥\\[\\]\"\"{}–'€，。？！～、：＃；％＊—…＆•＄（）‘’“”『』〔〕｛｝【】￥￡♀‖〖〗《》「」]+$";//其他需要，直接修改正则表达式就好
        return str.matches(regex);
    }

    /**
     * 只匹配符号，如果文字、数字、字母+符号不能匹配
     */
    public static boolean isLetterSingleAndChinese(String str) {
        String regex = "^[^\\w\\s]+$";
        return str.matches(regex);
    }

    /**
     * 非0开头的数字
     */
    public static boolean isStartZero(String str) {
        String regex = "^([^0][0-9]*)$";
        return str.matches(regex);
    }

    public Map<String, String> getParamsByUrl(String url) {
        try {
            if (url.contains("?")) {
                Map<String, String> map = new HashMap<>();
                String result = url.substring(url.indexOf("?") + 1, url.length());
                String[] strs = result.split("&");
                for (String str : strs) {
                    String[] params = str.split("=");
                    map.put(params[0], params[1]);
                }
                return map;
            } else return new HashMap<>();
        } catch (Exception e) {
            return new HashMap<>();
        }
    }

    public String getShortStr(String text, int limit) {
        if (text != null && text.length() > limit && limit > 2) {
            if (ValidateTool.getInstance().containsEmoji(text.substring(limit - 1, limit + 1))) {
                return text.substring(0, limit - 2) + "...";
            } else {
                return text.substring(0, limit - 1) + "...";
            }
        } else {
            return text;
        }
    }

    //判断字符串是否包含中文
    public  boolean isContainChinese(String str) {

        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }

}
