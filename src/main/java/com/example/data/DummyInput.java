package com.example.data;

/**
 * Created by rahulkumar on 6/27/18.
 */

/**
 * Just a dummy object for derived prefix string.
 */
public class DummyInput extends Input {


    private Input parent;

    DummyInput(String input, int value, Input parent){
        super(input,value);
        this.parent=parent;
    }

    @Override
    public String toString() {
        return  String.format("dummy text: {%s}, dummy_weight: {%d} , parent : {%s}",this.getText(),this.getValue(), parent.toString()) ;
    }

    public Input getParent() {
        return parent;
    }

    public void setParent(Input parent) {
        this.parent = parent;
    }
}
