package com.insonix.qrata.utility;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import net.coobird.thumbnailator.Thumbnails;

import org.apache.commons.lang.StringUtils;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.util.password.ConfigurablePasswordEncryptor;
import org.springframework.security.core.codec.Base64;

import com.ibm.icu.text.NumberFormat;
import com.ibm.icu.text.RuleBasedNumberFormat;

public class Utility {

    /**
     * UUID Generator
     * 
     * @return
     */
    public static String getUUID() {
		UUID uuid = UUID.randomUUID();
		String randomUUIDString = uuid.toString();
		return randomUUIDString;
    }

    /**
     * @author Ramandeep Singh
     * @param pattern
     * @param date
     * @return
     */
    public static String formatDate(String pattern, Date date) {
    	return new SimpleDateFormat(pattern).format(date);
    }

    /**
     * method to encode passed string into base64 encoding
     * 
     * @author Ramandeep Singh
     * @param str
     * @return
     */
    public static String encodeStringBase64(String str) {
		byte[] encodedstr = Base64.encode(str.getBytes());
		return new String(encodedstr);
    }

    /**
     * method to decode passed string into base64 encoding
     * 
     * @author Ramandeep Singh
     * @param str
     * @return
     */
    public static String decodeStringBase64(String str) {
		byte[] decodedBytes = null;
		decodedBytes = Base64.decode(str.getBytes());
		String decodedStr = new String(decodedBytes);
		return decodedStr;
    }

    /**
     * method to find differnce in two date in seconds
     * 
     * @author Ramandeep Singh
     * @param d1
     * @param d2
     * @return
     */
    @SuppressWarnings("deprecation")
    public static Long diffBwDatesInSeconds(Date d1, Date d2) {

	Calendar cal1 = Calendar.getInstance();
	Calendar cal2 = Calendar.getInstance();

	// Set the date for both of the calendar instance
	cal1.set(d1.getYear(), d1.getMonth(), d1.getDate(), d1.getHours(),
		d1.getMinutes(), d1.getSeconds());
	cal2.set(d2.getYear(), d2.getMonth(), d2.getDate(), d2.getHours(),
		d2.getMinutes(), d2.getSeconds());

	// Get the represented date in milliseconds
	long milis1 = cal1.getTimeInMillis();
	long milis2 = cal2.getTimeInMillis();

	// Calculate difference in milliseconds
	long diff = milis2 - milis1;

	// Calculate difference in seconds
	long diffSeconds = diff / 1000;

	return diffSeconds;
    }

    private static String saltGenerator(String string) {
		StringBuilder builder = new StringBuilder(string);
		int length = string.length();
		for (int i = 0; i < length; i++) {
		    int j = i;
		    i = i + i;
		    if (i < length) {
			builder.insert(i, builder.substring(j, i));
		    }
		}
		return builder.toString();
    }

    public static String strongStringEncryptor(String string, String password) {
		StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
		encryptor.setPassword(generateStrongStringPassword(password)); // we
									       // HAVE
									       // TO set
									       // a
									       // password
		encryptor.setAlgorithm("PBEWithMD5AndTripleDES"); // optionally set the
								  // algorithm
	
		String encryptedText = encryptor.encrypt(string);
	
		return encryptedText;
    }

    public static String strongStringDecryptor(String string, String password) {
		StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
		encryptor.setPassword(password); // we HAVE TO set a password
		encryptor.setAlgorithm("PBEWithMD5AndTripleDES"); // optionally set the
								  // algorithm
	
		String encryptedText = encryptor.decrypt(string);
		return encryptedText;
    }

    public static String generateStrongStringPassword(String string) {
		String key = encodeStringBase64(string);
		String password = passwordEncryption(saltGenerator(key));
		return password;
    }

    public static String passwordEncryption(String string) {
		ConfigurablePasswordEncryptor passwordEncryptor = new ConfigurablePasswordEncryptor();
		passwordEncryptor.setAlgorithm("MD5");
		passwordEncryptor.setPlainDigest(true);
		String encryptedPassword = passwordEncryptor.encryptPassword(string);
	
		return encryptedPassword;
    }

    public static String generateRandomPassword() {
		SecureRandom random = new SecureRandom();
		String str = new BigInteger(40, random).toString(32);
		return str;
    }

    public static String numberToWord(double number) {
		NumberFormat formatter = new RuleBasedNumberFormat(
			RuleBasedNumberFormat.SPELLOUT);
		String result = formatter.format(number);
		return result;
    }
    
    public static BufferedImage resizeImage(BufferedImage image,int height,int width) throws IOException {    	
    	BufferedImage thumbnail = Thumbnails.of(image)
    	        .size(width, height)
    	        .asBufferedImage();    	        
    	return thumbnail;
    }
    
    public static String replaceSpecialCharacters(String str) {
    	if(!StringUtils.isEmpty(str)) {
//    		str = str.replaceAll("[-+.^:,']", "");
//    		str = str.replaceAll("[']", "''");
    	}
    	return str;
    }
    
    public static String searchValueCheck(String value) {
    	if(value == null){
    		value = "%";
		}else{
			value = value.trim() + "%";
		}
    	return value;
	}
    
}
