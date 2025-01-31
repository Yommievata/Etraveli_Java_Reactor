package model;

public enum MovieType {
    REGULAR("regular"),
    NEW_RELEASE("new"),
    CHILDRENS("childrens");

    private final String code;

    MovieType(String code){
        this.code = code;
    }

    public String getCode(){
        return code;
    }

    public static MovieType fromCodeCode(String code){
        for (MovieType movieType : values()) {
            if (movieType.code.equals(code)) {
                return movieType;
            }
        }
        throw new IllegalArgumentException("Invalid MovieType code: " + code);
    }
}
