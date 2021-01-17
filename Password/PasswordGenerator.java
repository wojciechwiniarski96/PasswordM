package Password;

import java.util.Random;
import javax.crypto.*;

public class PasswordGenerator{

    private Random random = new Random();

    public String getRandomArrayOfSigns(){

        String smallLetters = "qwertzuiopasdfghjklyxcvbnm";
        String bigLetters = "QWERTZUIOPASDFGHJKLYXCVBM";
        String numbers = "1234567890";
        String specialCase = "!§$%&/()=?][{^*+';,:.-_";

        String [] allCases = {smallLetters, bigLetters, numbers, specialCase};

        int randomArrayOfCases = random.nextInt(4);

        return allCases[randomArrayOfCases];
    }

    public String getRandomSign(){
        String sign;

        String characterSet = getRandomArrayOfSigns();

        int lengthOfChosenArrayOfSigns = characterSet.length();
        int randomCase = random.nextInt(lengthOfChosenArrayOfSigns);

        sign = String.valueOf(characterSet.charAt(randomCase)) ;

        return sign;
    }

    public String generatePassword(int passwordLength){
        String[] password = new String[passwordLength];
        String pass = "";

        for(int i = 0; i < password.length; i++){

            password[i] = getRandomSign();

            pass = pass + password[i];
        }

        return pass;
    }
}
