import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.Map;

/**
 * 作者: 稻草鸟人
 * 日期: 2014/10/23 13:37
 * 项目: cabal-tools
 */
public class Ognl extends Object {


    public static boolean isBlank(Object o) {
        if (o == null) {
            return true;
        }
        if (o instanceof String) {
            return StringUtils.isBlank((String) o);
        }
        else if (o instanceof Collection) {
            return CollectionUtils.isEmpty((Collection) o);
        }
        else if (o instanceof Map) {
            return MapUtils.isEmpty((Map) o);
        }
        else if (o instanceof Object[]) {
            return ArrayUtils.isEmpty((Object[]) o);
        }
        return false;
    }

    public static boolean isNotBlank(Object o) {
        return !isBlank(o);
    }

    public static boolean isNumber(Object o) {
        if (o == null){
            return false;
        }
        if (o instanceof Number) {
            return true;
        }
        else if (o instanceof String) {
            return StringUtils.isNumeric((String) o);
        }
        return false;
    }

    public static boolean isTrue(Object o){
        return o.equals(true);
    }

    public static boolean isNotTrue(Object o){
        return !isTrue(true);
    }

    public static void main(String[] args) {
        System.out.println(isNotTrue(true));
    }
}
