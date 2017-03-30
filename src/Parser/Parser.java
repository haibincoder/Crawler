package Parser;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    //按空格分割字符串
    public static ArrayList<String> ParserBlank(String input){
        ArrayList<String> arrayList = new ArrayList<String>();
        String [] result = input.split("\\s+");
        for(String string : result){
            if(!isNumeric(string)){
                arrayList.add(string);
            }
        }
        return arrayList;
    }
    //按换行符分割字符串
    public static ArrayList<String> ParserLine(String input){
        ArrayList<String> arrayList = new ArrayList<String>();
        String [] result = input.split("\n");
        for(String string : result){
            if(string.length() > 1){
                arrayList.add(string);
            }
        }
        return arrayList;
    }
    //判断是否为数字
    public static boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
    }

}
