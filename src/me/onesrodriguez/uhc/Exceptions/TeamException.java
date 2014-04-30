package me.onesrodriguez.uhc.Exceptions;

public class TeamException extends Exception{

    private String message;
    
    public TeamException(String message){
        this.message = message;
    }
    
    @Override
    public String getMessage(){
        return message;
    }
}
