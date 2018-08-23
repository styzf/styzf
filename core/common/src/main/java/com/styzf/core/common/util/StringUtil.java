package com.styzf.core.common.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class StringUtil extends StringUtils {
    private static final String[] hex = { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "0A", "0B", "0C",
            "0D", "0E", "0F", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "1A", "1B", "1C", "1D", "1E",
            "1F", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "2A", "2B", "2C", "2D", "2E", "2F", "30",
            "31", "32", "33", "34", "35", "36", "37", "38", "39", "3A", "3B", "3C", "3D", "3E", "3F", "40", "41", "42",
            "43", "44", "45", "46", "47", "48", "49", "4A", "4B", "4C", "4D", "4E", "4F", "50", "51", "52", "53", "54",
            "55", "56", "57", "58", "59", "5A", "5B", "5C", "5D", "5E", "5F", "60", "61", "62", "63", "64", "65", "66",
            "67", "68", "69", "6A", "6B", "6C", "6D", "6E", "6F", "70", "71", "72", "73", "74", "75", "76", "77", "78",
            "79", "7A", "7B", "7C", "7D", "7E", "7F", "80", "81", "82", "83", "84", "85", "86", "87", "88", "89", "8A",
            "8B", "8C", "8D", "8E", "8F", "90", "91", "92", "93", "94", "95", "96", "97", "98", "99", "9A", "9B", "9C",
            "9D", "9E", "9F", "A0", "A1", "A2", "A3", "A4", "A5", "A6", "A7", "A8", "A9", "AA", "AB", "AC", "AD", "AE",
            "AF", "B0", "B1", "B2", "B3", "B4", "B5", "B6", "B7", "B8", "B9", "BA", "BB", "BC", "BD", "BE", "BF", "C0",
            "C1", "C2", "C3", "C4", "C5", "C6", "C7", "C8", "C9", "CA", "CB", "CC", "CD", "CE", "CF", "D0", "D1", "D2",
            "D3", "D4", "D5", "D6", "D7", "D8", "D9", "DA", "DB", "DC", "DD", "DE", "DF", "E0", "E1", "E2", "E3", "E4",
            "E5", "E6", "E7", "E8", "E9", "EA", "EB", "EC", "ED", "EE", "EF", "F0", "F1", "F2", "F3", "F4", "F5", "F6",
            "F7", "F8", "F9", "FA", "FB", "FC", "FD", "FE", "FF" };
    private static final byte[] val = { 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63,
            63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63,
            63, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 63, 63, 63, 63, 63, 63, 63, 10, 11, 12, 13, 14, 15, 63, 63, 63, 63, 63,
            63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 10, 11, 12, 13, 14, 15,
            63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63,
            63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63,
            63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63,
            63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63,
            63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63,
            63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63 };
    private static char[] HEXCHAR = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

    public static String toHexString(byte[] b) {
        StringBuffer sb = new StringBuffer(b.length * 2);
        for (int i = 0; i < b.length; i++) {
            sb.append(HEXCHAR[((b[i] & 0xF0) >>> 4)]);
            sb.append(HEXCHAR[(b[i] & 0xF)]);
        }
        return sb.toString();
    }

    public static final byte[] toBytes(String s) {
        byte[] bytes = new byte[s.length() / 2];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = ((byte) Integer.parseInt(s.substring(2 * i, 2 * i + 2), 16));
        }

        return bytes;
    }

    public static String escape(String sourceStr) {
        StringBuffer sbuf = new StringBuffer();
        int len = sourceStr.length();
        for (int i = 0; i < len; i++) {
            int ch = sourceStr.charAt(i);
            if ((65 <= ch) && (ch <= 90)) {
                sbuf.append((char) ch);
            } else if ((97 <= ch) && (ch <= 122)) {
                sbuf.append((char) ch);
            } else if ((48 <= ch) && (ch <= 57)) {
                sbuf.append((char) ch);
            } else if ((ch == 45) || (ch == 95) || (ch == 46) || (ch == 33) || (ch == 126) || (ch == 42) || (ch == 39)
                    || (ch == 40) || (ch == 41)) {

                sbuf.append((char) ch);
            } else if (ch <= 127) {
                sbuf.append('%');
                sbuf.append(hex[ch]);
            } else if (ch == 32) {
                sbuf.append("%20");
            } else {
                sbuf.append('%');
                sbuf.append('u');
                sbuf.append(hex[(ch >>> 8)]);
                sbuf.append(hex[(0xFF & ch)]);
            }
        }
        return sbuf.toString();
    }

    public static String unescape(String sourceStr) {
        StringBuffer sbuf = new StringBuffer();
        int i = 0;
        int len = sourceStr.length();
        while (i < len) {
            int ch = sourceStr.charAt(i);
            if ((65 <= ch) && (ch <= 90)) {
                sbuf.append((char) ch);
            } else if ((97 <= ch) && (ch <= 122)) {
                sbuf.append((char) ch);
            } else if ((48 <= ch) && (ch <= 57)) {
                sbuf.append((char) ch);
            } else if ((ch == 45) || (ch == 95) || (ch == 46) || (ch == 33) || (ch == 126) || (ch == 42) || (ch == 39)
                    || (ch == 40) || (ch == 41)) {

                sbuf.append((char) ch);
            } else if (ch == 37) {
                int cint = 0;
                if ('u' != sourceStr.charAt(i + 1)) {
                    cint = cint << 4 | val[sourceStr.charAt(i + 1)];
                    cint = cint << 4 | val[sourceStr.charAt(i + 2)];
                    i += 2;
                } else {
                    cint = cint << 4 | val[sourceStr.charAt(i + 2)];
                    cint = cint << 4 | val[sourceStr.charAt(i + 3)];
                    cint = cint << 4 | val[sourceStr.charAt(i + 4)];
                    cint = cint << 4 | val[sourceStr.charAt(i + 5)];
                    i += 5;
                }
                sbuf.append((char) cint);
            } else {
                sbuf.append((char) ch);
            }
            i++;
        }
        return sbuf.toString();
    }

    public static String XMLEscape(String src) {
        if (src == null)
            return null;
        String rtnVal = src.replaceAll("&", "&amp;");
        rtnVal = rtnVal.replaceAll("\"", "&quot;");
        rtnVal = rtnVal.replaceAll("<", "&lt;");
        rtnVal = rtnVal.replaceAll(">", "&gt;");
        rtnVal = rtnVal.replaceAll("[\\x00-\\x08\\x0b-\\x0c\\x0e-\\x1f]", "");
        return rtnVal;
    }

    public static String getParameter(String query, String param) {
        Pattern p = Pattern.compile("&" + param + "=([^&]*)");
        Matcher m = p.matcher("&" + query);
        if (m.find())
            return m.group(1);
        return null;
    }

    public static Map getParameterMap(String query, String splitStr) {
        Map rtnVal = new HashMap();
        if (isNull(query))
            return rtnVal;
        String[] parameters = query.split("\\s*" + splitStr + "\\s*");
        for (int i = 0; i < parameters.length; i++) {
            int j = parameters[i].indexOf('=');
            if (j > -1)
                rtnVal.put(parameters[i].substring(0, j), new String[] { parameters[i].substring(j + 1) });
        }
        return rtnVal;
    }

    public static String setQueryParameter(String query, String param, String value) {
        String rtnVal = null;
        try {
            String m_query = "&" + query;
            String m_param = "&" + param + "=";
            String m_value = URLEncoder.encode(value, "UTF-8");
            Pattern p = Pattern.compile(m_param + "[^&]*");
            Matcher m = p.matcher(m_query);
            if (m.find()) {
                rtnVal = m.replaceFirst(m_param + m_value);
            } else
                rtnVal = m_query + m_param + m_value;
            rtnVal = rtnVal.substring(1);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return rtnVal;
    }

    public static String replace(String srcText, String fromStr, String toStr) {
        if (srcText == null)
            return null;
        StringBuffer rtnVal = new StringBuffer();
        String rightText = srcText;
        for (int i = rightText.indexOf(fromStr); i > -1; i = rightText.indexOf(fromStr)) {
            rtnVal.append(rightText.substring(0, i));
            rtnVal.append(toStr);
            rightText = rightText.substring(i + fromStr.length());
        }
        rtnVal.append(rightText);
        return rtnVal.toString();
    }

    public static String formatUrl(String url, String urlPrefix) {
        if (!url.startsWith("/")) {
            return url;
        }
        return urlPrefix + url;
    }

    public static String linkString(String leftStr, String linkStr, String rightStr) {
        if (isNull(leftStr))
            return rightStr;
        if (isNull(rightStr))
            return leftStr;
        return leftStr + linkStr + rightStr;
    }

    public static boolean isNull(String str) {
        return (str == null) || (str.trim().length() == 0);
    }

    public static boolean isNotNull(String str) {
        return !isNull(str);
    }

    public static boolean hasLength(String str) {
        return (str != null) && (str.length() > 0);
    }

    public static String getString(String s) {
        return s.equals("null") ? "" : s == null ? "" : s;
    }

    public static String linkPathString(String... paths) {
        if ((null == paths) || (paths.length == 0)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();

        sb.append(paths[0]);

        for (int i = 1; i < paths.length; i++) {
            if ((paths[(i - 1)].endsWith("\\")) || (paths[(i - 1)].endsWith("/"))) {
                if ((paths[i].startsWith("\\")) || (paths[i].startsWith("/"))) {
                    sb.append(paths[i].substring(1));
                } else {
                    sb.append(paths[i]);
                }
            } else if ((paths[i].startsWith("\\")) || (paths[i].startsWith("/"))) {
                sb.append(paths[i]);
            } else {
                sb.append("/").append(paths[i]);
            }
        }

        return sb.toString();
    }

    public static int getIntFromString(String value, int defaultValue) {
        int ret = defaultValue;
        if (isNotNull(value)) {
            try {
                ret = Integer.parseInt(value);
            } catch (NumberFormatException e) {
                ret = defaultValue;
            }
        }
        return ret;
    }

    public static String[] mergeStringArray(String[] ary1, String[] ary2) {
        if (null == ary1)
            return ary2;
        if (null == ary2) {
            return ary1;
        }
        List<String> l1 = new ArrayList(Arrays.asList(ary1));
        List<String> l2 = Arrays.asList(ary2);
        for (String s : l2) {
            if (!l1.contains(s)) {
                l1.add(s);
            }
        }
        String[] strings = new String[l1.size()];
        l1.toArray(strings);
        return strings;
    }

    public static String[] emptyArray2Null(String[] ary1) {
        if ((null == ary1) || (ary1.length == 0)) {
            return null;
        }
        return ary1;
    }

    public static String clearScriptTag(String html) {
        Pattern scriptTag = Pattern.compile("<script[^>]*>.*(?=<\\/script>)<\\/script>");
        Matcher mTag = scriptTag.matcher(html);
        html = mTag.replaceAll("");

        String regx = "(<[^<]*)(on\\w*\\x20*=|javascript:)";
        Pattern pattern = Pattern.compile(regx, 10);

        String ts = html;
        Matcher matcher;
        while ((matcher = pattern.matcher(ts)).find()) {
            ts = matcher.replaceAll("$1_disibledevent=");
        }
        return ts;
    }

    public static String join(String[] array, String separator) {
        if (array == null) {
            return null;
        }
        if (separator == null) {
            separator = "";
        }
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < array.length; i++) {
            if (i > 0) {
                buf.append(separator);
            }
            if (array[i] != null) {
                buf.append(array[i]);
            }
        }
        return buf.toString();
    }

    public static String join(Iterable iterable, String separator) {
        if (iterable == null) {
            return null;
        }
        return join(iterable.iterator(), separator);
    }

    public static String join(Iterator iterator, String separator) {
        if (iterator == null) {
            return null;
        }
        if (!iterator.hasNext()) {
            return "";
        }
        Object first = iterator.next();
        if (!iterator.hasNext()) {
            return first == null ? "" : first.toString();
        }

        StringBuffer buf = new StringBuffer(256);
        if (first != null) {
            buf.append(first);
        }

        while (iterator.hasNext()) {
            if (separator != null) {
                buf.append(separator);
            }
            Object obj = iterator.next();
            if (obj != null) {
                buf.append(obj);
            }
        }
        return buf.toString();
    }

    public static String getExceptionString(Exception e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw, true);
        e.printStackTrace(pw);
        pw.flush();
        sw.flush();
        return sw.toString();
    }

    public static String trimPrefix(String toTrim, String trimStr) {
        while (toTrim.startsWith(trimStr)) {
            toTrim = toTrim.substring(trimStr.length());
        }
        return toTrim;
    }

    public static String trimSufffix(String toTrim, String trimStr) {
        while (toTrim.endsWith(trimStr)) {
            toTrim = toTrim.substring(0, toTrim.length() - trimStr.length());
        }
        return toTrim;
    }

    public static String trim(String toTrim, String trimStr) {
        return trimSufffix(trimPrefix(toTrim, trimStr), trimStr);
    }

    public static void main(String[] args) {
//        String html = "<div asdf onclick='aa'  asdf onfolasdf='a' asdf>  ...  <script type=\"text/javascript\" ...>   ....   document.writeln(\"<h1>在JS代码里也可能会嵌套HTML标签等等，各种符合语法的嵌套</h1>\");     ....  </script>   .<p>ad</p>..   </div> ";
//        System.out.println(clearScriptTag(html));
        String upperCharToUnderLine = upperCharToUnderLine("TestTEST");
        System.out.println(upperCharToUnderLine);
    }
    
    /**
     * 驼峰转下划线
     *  testTest -> test_test
     *  TestTest -> test_test
     *  TESTTest -> test_test
     *  TestTEST -> test_test
     *  
     * @author styzf
     * @date 2018年8月23日 
     * @param param
     * @return
     */
    public static String upperCharToUnderLine(String param) {  
        Pattern p1 = Pattern.compile("[A-Z]{2,}");
        Pattern p2 = Pattern.compile("[A-Z]");
        if(param == null || param.equals("")){  
            return "";  
        }  
        StringBuilder builder=new StringBuilder(param);  
        Matcher mc1 = p1.matcher(param);
        
        int i = 0;
        while (mc1.find()) {
            String lowerCase = mc1.group().toLowerCase();
            if (mc1.end() + i == builder.length()) {
                builder.replace(mc1.start() + i, mc1.end() + i, "_" + lowerCase);  
                i ++ ;
            } else {
                builder.replace(mc1.start() + i, mc1.end() + i, 
                        "_" + lowerCase.substring(0, lowerCase.length() - 1) + 
                        "_" +lowerCase.substring(lowerCase.length() - 1));  
                i += 2;
            }
        }
        
        Matcher mc2 = p2.matcher(builder.toString());
        int j = 0;  
        while (mc2.find()) {
            builder.replace(mc2.start() + j, mc2.end() + j, "_" + mc2.group().toLowerCase());  
            j ++ ;
        }
  
        if('_' == builder.charAt(0)){
            builder.deleteCharAt(0);
        }
        return builder.toString();
    }
}
