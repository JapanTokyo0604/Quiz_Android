package com.elephant.examplequiza;

public class DtoStatment {
    String id;
    String statement ;
    String sec1 ;
    String sec2 ;
    String sec3 ;
    String sec4 ;

    String explanation ;
    String Answer;

    public DtoStatment(String id,
                String statement,
                String sec1,
                String sec2,
                String sec3,
                String sec4,
                String explanation,
                String Answer ){
        this.id = id;
        this.statement = statement;
        this.sec1 = sec1;
        this.sec2 = sec2;
        this.sec3 = sec3;
        this.sec4 = sec4;
        this.explanation = explanation;
        switch(Answer) {
            case "回答1":
                this.Answer = sec1;
            break;
            case "回答2":
                this.Answer = sec2;
                break;
            case "回答3":
                this.Answer = sec3;
                break;
            case "回答4":
                this.Answer = sec4;
                break;


        }
    }



}
