package com.example.Util;

import com.example.data.Input;
import org.apache.log4j.Logger;

import java.util.*;

/**
 * Created by rahulkumar on 6/27/18.
 */
public class DataGenerationUtility {

    //lexicon is small so that I could get more _ in
    private final String lexicon = "01234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ_______";
//    private final String lexicon = "ABC___";

    final java.util.Random rand = new java.util.Random();
    private static final Logger app_logger = Logger.getLogger("DataGenerationUtility");
    private final Set<String> identifiers = new HashSet<>();

    /**
     * Utility function to generate a tuple of string and a number
     * @return
     */
    private String[] randomInput() {
        StringBuilder builder = new StringBuilder();
        while(builder.toString().length() == 0) {
            int length = rand.nextInt(10)+5;
            for(int i = 0; i < length; i++) {
                builder.append(lexicon.charAt(rand.nextInt(lexicon.length())));
            }
            if(identifiers.contains(builder.toString())) {
                builder = new StringBuilder();
            }
        }
        String[] input= new String[2];
        input[0]=builder.toString();
        input[1]=Integer.toString(rand.nextInt(10000));
        return  input;
    }


    public List<Input>  generateKInputData(int count){

        if(count <=0){
            app_logger.error(String.format("Request to generate {%d} data points",count));
            throw new IllegalArgumentException("invalid input");
        }
        List<Input >  result= new ArrayList<>();
        for(int i=0 ; i<count ; i++  ){
            String[] input_arr=  randomInput();
            result.add(new Input(input_arr[0], input_arr[1]));
        }
        return result;
    }


    public static void main(String[] args) {

        DataGenerationUtility nm= new DataGenerationUtility();
        List<Input> result= nm.generateKInputData(100);

        for(Input ip : result){
            System.out.println(ip);
        }

    }
}
