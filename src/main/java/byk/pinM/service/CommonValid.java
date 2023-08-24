package byk.pinM.service;

public class CommonValid {
    //입력받은 문자열이 공백인가를 검증.
    public boolean isBlankString(String str) {
        return str != null && str.trim().length() < 1;
    }
}
