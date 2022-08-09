package Utility;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

	public static boolean validateName(String str) {
		boolean result = true;
		for (int i = 0; i < str.length(); i++) {
			if (Character.isDigit(str.charAt(i))) {
				result = false;
				break;
			}
		}
		return result;
	}

	public static boolean validatePhoneNum(String str) {
		boolean result = true;
		try {
			Integer.parseInt(str);
			if ((str.charAt(0) != '9') && (str.charAt(0) != '6')
					&& (str.charAt(0) != '8'))
				result = false;
			else if (str.length() != 8)
				result = false;
		} catch (NumberFormatException e) {
			result = false;
		}
		return result;
	}
	
	public static boolean validateEmail(String str){
		Pattern p = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");
		Matcher m = p.matcher(str);
		if(m.find() && m.group().equals(str)){
			return true;
		} else {
			return false;
		}
	}

}
