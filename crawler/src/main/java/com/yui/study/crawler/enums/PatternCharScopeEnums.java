package com.yui.study.crawler.enums;

import java.util.Arrays;
import java.util.List;

public enum PatternCharScopeEnums {
    /**
     *
     */
    number(48,57,"\\d", "\\D"),
    nextPage(12, 12, "\\f","^\\f"),
    nextLine(10,10,"\\n", "^\\n"),
    ebter(13,13,"\\r","^\\r"),
    tab(9,9,"\\t","^\\t"),
    //space(32,32,"\\s","\\S"),
    lowercase(97,122,"a-z","^a-z"),
    uppercase(65,90,"A-Z","^A-Z"),
    ;
    public static void main(String[] args) {
        String a = "AZ" ;
        for (char c : a.toCharArray()) {
            System.out.println(Integer.valueOf(c));
        }
    }

    private int begin;
    private int end;
    private String pattern;
    private String withoutPattern;
    private boolean isScopeContinuity;
    private List<PatternCharScopeEnums> charScopeEnums;

    PatternCharScopeEnums(int begin, int end, String pattern, String withoutPattern){
        this.begin = begin;
        this.end = end;
        this.pattern = pattern;
        this.withoutPattern = withoutPattern;
        this.isScopeContinuity = true;
    }
    PatternCharScopeEnums(PatternCharScopeEnums...charScopeEnums){
        this.isScopeContinuity = false;
        this.charScopeEnums = Arrays.asList(charScopeEnums);
    }

    public boolean isInScope(char c){
        int number = (int) c;
        if (isScopeContinuity) {
            return number >= this.begin && number <= this.end;
        }else{

            return false;
        }
    }
    public String getPattern(){
        return pattern;
    }

    public String getWithoutPattern() {
        return withoutPattern;
    }
}
