package academy.kovalevskyi.javadeepdive.week2.day0;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class JsonHelper {

  public static <T> String toJsonString(T target) throws IllegalAccessException {
    if (target == null) {
      return "null";
    }
    if (target instanceof Integer || target instanceof String) {
      return oneValueToJson(target);
    }

    StringBuilder sb = new StringBuilder();
    if (target.getClass().isArray()) {
      if (Array.getLength(target) == 0) {
        return "[]";
      }
      sb.append("[");
      for (int i = 0; i < Array.getLength(target); i++) {
        var test = oneValueToJson(Array.get(target, i));
        sb.append(test).append(",");
      }
      //
      return sb.deleteCharAt(sb.length() - 1).append("]").toString();
    }
    sb.append("{");
    Field[] fields = target.getClass().getDeclaredFields();
    for (Field field : fields) {
      field.setAccessible(true);
      sb.append(oneValueToJson(field.getName())).append(":")
          .append(oneValueToJson(field.get(target))).append(",");
    }
    return sb.deleteCharAt(sb.length() - 1).append("}").toString();
  }

  public static String oneValueToJson(Object obj) {
    if (obj.getClass().equals(String.class)) {
      return String.format("\"%s\"", obj);
    } else {
      return obj.toString();
    }
  }

  public static <T> T fromJsonString(String json, Class<T> cls)
      throws IllegalAccessException, InvocationTargetException,
      InstantiationException, NoSuchMethodException, NoSuchFieldException {
    json = json.trim();
    if (json == null || json.equals("null") || json.equals("")) {
      return null;
    }
    if (json.equals("[]") || json.equals("{}")) {
      if (cls.isArray()) {
        return (T) Array.newInstance(cls.getComponentType(), 0);
      }
      return null;
    }
    T obj = null;
    json = json.trim();
    if (json.contains("[") || json.contains("{")) {
      json = json.substring(1, json.length() - 1);
    }
    String[] values = json.split(",");
    if (cls.isArray()) {
      obj = (T) Array.newInstance(cls.getComponentType(), values.length);
      int i = 0;
      for (String s : values) {
        if (cls.getComponentType().equals(String.class)) {
          s = s.replace("\"", "");
          Array.set(obj, i++, s);
        } else if (cls.getComponentType().equals(Integer.class) || cls.getComponentType()
            .equals(int.class)) {
          Array.set(obj, i++, Integer.parseInt(s));
        }
      }
    } else {
      obj = cls.getDeclaredConstructor().newInstance();
      //Field[] fields = cls.getDeclaredFields();
      for (String s : values) {
        String[] strings = s.replace("\"", "").split(":");
        Field field = cls.getDeclaredField(strings[0].strip());
        field.setAccessible(true);
        if (field.getType().equals(int.class)) {
          field.set(obj, Integer.parseInt(strings[1].strip()));
        } else {
          field.setAccessible(true);
          field.set(obj, strings[1].strip());
        }
      }
    }
    return obj;
  }
}
