package com.example.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Created by rahulkumar on 6/23/18.
 */


@JsonPropertyOrder({ "text", "value" })
public class Input {

    @JsonProperty("text")
    private String text="";

    @JsonProperty("value")
    private int value=Integer.MIN_VALUE;

    public Input () {

    }

    public Input(String text, int value){
        this.text= text;
        this.value=value;
    }

    public Input(String text, String value){
        this.text= text;
        try{
            this.value=Integer.valueOf(value);
        }catch ( NumberFormatException ne){
            ne.printStackTrace();
        }
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public int hashCode() {
        return text.hashCode() + 13* value;
    }

    @Override
    public boolean equals(Object object) {

        if (this == object) {
            return true;
        }
        else if (object == null || getClass() != object.getClass()) {
            return false;
        }

        final Input other = (Input) object;

        return this.value== other.value  && text.equals(other.getText());
    }

    @Override
    public String toString() {
        return "(" +text +", " + value  +" )";
    }

}


