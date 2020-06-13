package validation;

import java.util.regex.Pattern;

public class userInfoValidation {
	//check phone number is only numbers
	public boolean isNumeric(String strNum) {
	    try {
	    	int number = Integer.parseInt(strNum);
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	    return true;
	}
	// check mail format
	public boolean validEmail(String email) 
    { 
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+ 
                            "[a-zA-Z0-9_+&*-]+)*@" + 
                            "(?:[a-zA-Z0-9-]+\\.)+[a-z" + 
                            "A-Z]{2,7}$"; 
                              
        Pattern pat = Pattern.compile(emailRegex); 
        if (email == null) 
            return false; 
        return pat.matcher(email).matches(); 
    }
	// customize password format
	public static final int PASSWORDLENGTH = 8;
    public boolean validPassword(String password) {

        if (password.length() < PASSWORDLENGTH) return false;

        int charCapitalCount = 0;
        int charSmallCount = 0;
        int numCount = 0;
        for (int i = 0; i < password.length(); i++) {

            char ch = password.charAt(i);
            if (is_Numeric(ch)) numCount++;
            else if (is_Letter(ch) && Character.isUpperCase(ch)) charCapitalCount++;
            else if (is_Letter(ch) && Character.isLowerCase(ch)) charSmallCount++;
            else return false;
        }   return (charCapitalCount >= 1 && charSmallCount >= 1 && numCount >= 2);
    }
    public static boolean is_Letter(char ch) {
        ch = Character.toUpperCase(ch);
        return (ch >= 'A' && ch <= 'Z');
    }
   
    public static boolean is_Numeric(char ch) {
        return (ch >= '0' && ch <= '9');
    }
	

}
