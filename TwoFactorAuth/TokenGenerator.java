package TwoFactorAuth;

import java.util.Random;

public class TokenGenerator{

    String generateToken(){
        Random random = new Random();
        int[] token = new int[6];
        String tokenToString = "";
        for(int i = 0; i < token.length; i++){
            int partOfToken = random.nextInt(9);
               token[i] = partOfToken;
               tokenToString = tokenToString + token[i];
        }
        return tokenToString;
    }
}
