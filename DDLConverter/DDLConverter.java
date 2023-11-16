import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author : Yihan Zhang
 * @date : 2023/11/16 11:02
 */
public class DDLConverter {

    private static String convertDDLToJson(String ddlScript) {
        String prefix = "?szods";
        String postfix = "?szode";

        Pattern pattern = Pattern.compile("`([^`]+)`");
        Matcher matcher = pattern.matcher(ddlScript);

        StringBuilder jsonResult = new StringBuilder();

        while (matcher.find()) {
            String columnName = matcher.group(1);
            String camelCaseName = toCamelCase(columnName);
            String jsonField = String.format("  \"%s\": \"%s_%s_%s\",\n", columnName, prefix, camelCaseName, postfix);
            jsonResult.append(jsonField);
        }

        // Remove the trailing comma and add the closing brace
        jsonResult.deleteCharAt(jsonResult.lastIndexOf(","));

        return jsonResult.toString();
    }

    private static String toCamelCase(String columnName) {
        StringBuilder camelCase = new StringBuilder();
        boolean capitalizeNext = false;

        for (char c : columnName.toCharArray()) {
            if (c == '_') {
                capitalizeNext = true;
            } else {
                if (capitalizeNext) {
                    camelCase.append(Character.toUpperCase(c));
                    capitalizeNext = false;
                } else {
                    camelCase.append(Character.toLowerCase(c));
                }
            }
        }

        return camelCase.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("输入建表语句(从第一个属性开始复制到最后一个属性):");
        StringBuilder ddlScriptBuilder = new StringBuilder();
        String line;
        while (!(line = scanner.nextLine()).isEmpty()) {
            ddlScriptBuilder.append(line).append("\n");
        }
        String ddlScript = ddlScriptBuilder.toString().trim();
        String jsonResult = convertDDLToJson(ddlScript);
        System.out.println("\n转换后的 JSON:\n" + jsonResult);
        scanner.close();
    }
}
