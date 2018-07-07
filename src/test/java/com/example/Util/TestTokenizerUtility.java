package com.example.Util;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;


/**
 * Created by rahulkumar on 6/27/18.
 */
public class TestTokenizerUtility {

    @Test

    public void testSimplePrefixConstruction(){
        String input="ABC_DEF_GEH";
        String[]  prefixTokens=   {"ABC_DEF_GEH","DEF_GEH","GEH"};
        String[] output= TokenizerUtility.prefixSplitting(input,'_');

        assertArrayEquals(prefixTokens,output);
    }

    @Test
    public void testPrefixConstructionWithoutDash(){
        String input="ABCDEFGEH";
        String[]  prefixTokens=   {"ABCDEFGEH"};
        String[] output= TokenizerUtility.prefixSplitting(input,'_');

        assertArrayEquals(prefixTokens,output);
    }

    @Test
    public void testPrefixConstructionWithOnlyDash(){
        String input="______";
        String[]  prefixTokens=   {"______","_____","____","___","__","_"};
        String[] output= TokenizerUtility.prefixSplitting(input,'_');

        assertArrayEquals(prefixTokens,output);
    }
}
