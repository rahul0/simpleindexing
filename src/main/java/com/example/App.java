package com.example;

import com.example.data.Input;
import com.example.data.InputUtility;
import com.example.search.QuerySearch;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{


    private static final Logger app_logger = Logger.getLogger("app_logger");


    public static void main( String[] args )
    {
        //laod data
        List<Input> corpus= InputUtility.deserializeCorpus();
        QuerySearch qs= new QuerySearch();
        qs.populateTrie(corpus);
        boolean done =false;
        Scanner in = new Scanner(System.in);

        while(!done){
            System.out.println("Enter a query");
            String s = in.nextLine();
            if(s.trim().isEmpty()){
                app_logger.warn("Empty query entered: exiting!");
                break;
            }
            app_logger.debug(String.format("query entered: %s ",s));
            qs.getResult(s.trim(),10);
        }
    }

}
