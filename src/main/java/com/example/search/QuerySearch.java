package com.example.search;

import com.example.data.DummyInput;
import com.example.data.Input;
import com.example.data.InputUtility;
import org.apache.commons.collections4.Trie;
import org.apache.commons.collections4.trie.PatriciaTrie;
import org.apache.log4j.Logger;

import java.util.*;

/**
 * Created by rahulkumar on 6/27/18.
 */
public class QuerySearch {

    private final Logger app_logger = Logger.getLogger("QuerySearch");
    private Trie<String, Input> trie = new PatriciaTrie<>();

    public  QuerySearch(){

    }

    public void populateTrie(List<Input> inputList){

        if(inputList==null){
            app_logger.error("Empty input, exiting", new IllegalArgumentException());

        }
        long startTime = System.currentTimeMillis();
        InputUtility ipu = new InputUtility();
        for(Input input : inputList){
            List<Input> inputPrefixList = ipu.constructInputList(input);
            populateTrieFromInputList(inputPrefixList);
        }
        long end = System.currentTimeMillis();
        app_logger.info(String.format("Time taken to construct patricia tree: %d milliseconds",end-startTime));

    }


    public void populateTrieFromInputList(List<Input>  inputPrefixList){
        for(Input prefixInput : inputPrefixList){
            trie.put(prefixInput.getText(),prefixInput);
        }
    }

    public void getResult(String query, int k){

        long startTime = System.currentTimeMillis();
        app_logger.info(String.format("query received: %s" ,query));

        if(query.isEmpty()){
            app_logger.warn("Empty query, exiting!");
            return;
        }
        Map<String,Input> searchresult= trie.prefixMap(query);
        List<Input> result=findTopK(searchresult,k);

        long stop = System.currentTimeMillis();

        app_logger.info(String.format("Time taken: %d milliseconds, Search result: %s",(stop-startTime),Arrays.toString(result.toArray())));
    }

    private List<Input> findTopK(Map<String,Input> full_result , int k ){

        Set<String> keys=full_result.keySet();
        Set<Input> matches= new HashSet<>();

        for (String key:  keys){
            Input val = full_result.get(key);
            if(val.getClass() == DummyInput.class){
                app_logger.debug(String.format("discarding dummy trie nodes: %s",val.toString()));
                DummyInput di=(DummyInput)val;
                Input parent=di.getParent();
                matches.add(parent);
                app_logger.debug(String.format("adding parent trie node: %s",parent.toString()));
            }else{
                app_logger.debug(String.format("match: %s",val.toString()));
                matches.add(val);
            }
        }

        List<Input> result=new ArrayList<>(matches);
        app_logger.debug(String.format("match input before sorting : %s",Arrays.toString(result.toArray())));

        //scoring scheme , in reverse order of weight
        Collections.sort(result,(item1, item2) -> -(item1.getValue() -item2.getValue()));
        app_logger.debug(String.format("all match after sorting : %s",Arrays.toString(result.toArray())));

        if(result.size()>k){
            return result.subList(0,k);
        }

        return  result;

    }

    public Trie<String, Input> getTrie() {
        return trie;
    }

    public void setTrie(Trie<String, Input> trie) {
        this.trie = trie;
    }
}
