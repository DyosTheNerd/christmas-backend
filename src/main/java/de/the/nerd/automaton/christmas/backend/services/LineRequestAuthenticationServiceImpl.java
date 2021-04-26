package de.the.nerd.automaton.christmas.backend.services;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

@Service
public class LineRequestAuthenticationServiceImpl implements LineRequestAuthenticationService {

    @Value("${line.bot.api.secret}")
    public String botApiSecret;


    static Logger logger = LoggerFactory.getLogger(LineRequestAuthenticationServiceImpl.class);

    public boolean validateLineRequest(String requestBody, String authHeader){

        String channelSecret = botApiSecret; // Channel secret string
        String httpRequestBody = requestBody; // Request body string
        SecretKeySpec key = new SecretKeySpec(channelSecret.getBytes(), "HmacSHA256");
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(key);
            byte[] source = httpRequestBody.getBytes("UTF-8");
            String signature = Base64.encodeBase64String(mac.doFinal(source));


            if (signature.equals(authHeader)){
                return true;
            }

            logger.info("Failed to validate signature " + authHeader +
                    " got " + signature);

            logger.info("request body is::" + requestBody + "::");

            logger.info("secret is::"+ botApiSecret + "::");

            return false;



        } catch(NoSuchAlgorithmException | InvalidKeyException | UnsupportedEncodingException nsae){

            logger.info("Failed to validate signature due to " + nsae.toString());
            return false;
        }

    }

    public String getAuthHeaderContent(Map<String,String> headers) {

        String returnValue = "";

        for (String header: headers.keySet()){
            if (header.toLowerCase().equals("x-line-signature")){
                returnValue = headers.get(header);
            }
        }

        return returnValue;
    }

}
