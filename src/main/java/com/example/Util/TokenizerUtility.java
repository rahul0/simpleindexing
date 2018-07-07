package com.example.Util;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rahulkumar on 6/26/18.
 */
public class TokenizerUtility {

    /**
     *
     * @param input
     * @return
     */
    private static final Logger app_logger = Logger.getLogger("TokenizerUtility");

    /*
        Extract prefix of a string
        (a_b_c, -) -> {a_b_c, b_c , c}
     */
    public static String[] prefixSplitting(String input, char delimiter){

        app_logger.debug( String.format("Processing input :{%s}, with delimiter: {%c} ",input,delimiter));
        if (input==null || input.isEmpty()){
            return Constants.EMPTY_STRING_ARRAY;
        }


        boolean lastIndexWasDelimiter= false;
        List<String> prefixeList= new ArrayList<>();
        //string is a prefix of itself
        prefixeList.add(input);
        for(int i=0; i< input.length();i++){

            if(lastIndexWasDelimiter ){
                //trigger prefix construction
                String prefix=input.substring(i);
                if(!prefix.isEmpty()){
                    app_logger.debug(prefix);
                    prefixeList.add(input.substring(i));
                }

            }
            lastIndexWasDelimiter= input.charAt(i) == delimiter;
        }

        String[] result= new String[prefixeList.size()];
        int counter=0;

        for(String prefix : prefixeList){
            result[counter++]=prefix;
        }

        return result;

    }

    public static void main(String[] args) {
        String input= "abc_def_zef";
//        String input= "__def_zef";
//        String input= "___";
        String [] result= prefixSplitting(input,'_');
        for(String prefix: result){
            System.out.println(prefix + ":"  + prefix.length());
        }
    }
}
