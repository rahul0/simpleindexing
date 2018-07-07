package com.example.data;

import com.example.Util.Constants;
import com.example.Util.DataGenerationUtility;
import com.example.Util.TokenizerUtility;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;




/**
 * Created by rahulkumar on 6/26/18.
 */
public class InputUtility {

    private static final Logger app_logger = Logger.getLogger("InputUtility");

    /*
      Converts Input object into multiple Input objects based on the splitting criteria
     */
    public List<Input> constructInputList(Input input){
        String inputText=input.getText();
        int score= input.getValue();
        String[]  prefixes= TokenizerUtility.prefixSplitting(inputText,'_');
        List<Input> prefixList= new ArrayList<>();
        Input dummyParentNode=null;
        for(String prefix: prefixes){
            if(prefix.equals(inputText)){
                Input ip= new Input(prefix,score);
                dummyParentNode=ip;
                prefixList.add(ip);
            }else{
                if(dummyParentNode!=null){
                    prefixList.add(new DummyInput(prefix,score,dummyParentNode));
                }else{
                    app_logger.error(String.format("Some unexpected error occured while " +
                            "constructing prefix for {%s}, prefix= {%s} ", inputText,prefix));
                }
            }
        }
        return prefixList;
    }


    public static void serializeCorpus(List<Input> inputList){

        if(inputList==null || inputList.isEmpty()){
            app_logger.error(String.format("empty list, exiting "));
            return;
        }

        try{
            ObjectMapper objectMapper = new ObjectMapper();
            Input[] arr=inputList.toArray(new Input[inputList.size()]);
            objectMapper.writeValue(new FileOutputStream(Constants.DATA_LOCATION), arr);
            app_logger.info(String.format("serialized %d input to %s file ",inputList.size(),Constants.DATA_LOCATION));
        }catch (IOException ioe){
             ioe.printStackTrace();
        }

    }


    public static List<Input> deserializeCorpus(){

        ObjectMapper objectMapper = new ObjectMapper();
        List<Input> result=new ArrayList<>();

        try{
            String jsonCarArray= FileUtils.readFileToString(new File(Constants.DATA_LOCATION),"utf-8");
            result = objectMapper.readValue(jsonCarArray, new TypeReference<List<Input>>(){});
            app_logger.info(String.format("Loaded a list of %s tuples", result.size()));

        }catch (IOException ioe){
            app_logger.error("Error in deserializing", ioe );
        }
        return  result;
    }


    public static void main(String[] args) {
        DataGenerationUtility nm= new DataGenerationUtility();
        List<Input> inputList=nm.generateKInputData(1000000);
        serializeCorpus(inputList);

    }
}
