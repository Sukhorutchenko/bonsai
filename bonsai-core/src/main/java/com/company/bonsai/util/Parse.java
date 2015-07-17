package com.company.bonsai.util;

public class Parse {

    public String parse(String source, String beginSubstring, String endSubstring) {
        try {
            if((source.indexOf(beginSubstring)) < 0) {
                return "";
            } else {
                if ((source.indexOf(endSubstring, source.indexOf(beginSubstring)
                        + beginSubstring.length())) < 0) {
                    return "";
                }
            }
            if(isEmpty(beginSubstring)) {
                if(isEmpty(endSubstring)) {
                    return "";
                }
                return source.substring(0, source.indexOf(endSubstring));
            }
            if (isEmpty(endSubstring)) {
                return source.substring(source.indexOf(beginSubstring) + beginSubstring.length());
            }
            return source.substring(source.indexOf(beginSubstring) + beginSubstring.length(),
                source.indexOf(endSubstring, source.indexOf(beginSubstring) + beginSubstring.length()));
        } catch(StringIndexOutOfBoundsException e) {
            return e.getClass().getSimpleName();
        }
    }

    public String parseLast(String source, String beginSubstring, String endSubstring) {
        try {
            if((source.lastIndexOf(beginSubstring)) < 0) {
                return "";
            } else {
                if (!isEmpty(beginSubstring)) {
                    if((source.indexOf(endSubstring, source.lastIndexOf(beginSubstring)
                            + beginSubstring.length())) < 0) {
                        return "";
                    }
                } else {
                    if (source.lastIndexOf(endSubstring) < 0) {
                        return "";
                    }
                }
            }
            if(isEmpty(beginSubstring)) {
                if(isEmpty(endSubstring)) {
                    return "";
                }
                return source.substring(0, source.lastIndexOf(endSubstring));
            }
            if (isEmpty(endSubstring)) {
                return source.substring(source.lastIndexOf(beginSubstring) + beginSubstring.length());
            }
            return source.substring(source.lastIndexOf(beginSubstring) + beginSubstring.length(),
                    source.indexOf(endSubstring, source.lastIndexOf(beginSubstring) + beginSubstring.length()));
        } catch(StringIndexOutOfBoundsException e) {
            return e.getClass().getSimpleName();
        }
    }

    public String parseIfEmpty(String variable, String source, String beginSubstring, String endSubstring) {
        if(isEmpty(variable)) {
            return parse(source, beginSubstring, endSubstring);
        } else {
            return variable;
        }
    }

    public String parseIfEmptyLast(String variable, String source, String beginSubstring, String endSubstring) {
        if(isEmpty(variable)) {
            return parseLast(source, beginSubstring, endSubstring);
        } else {
            return variable;
        }
    }

    public boolean isEmpty(String str) {
        return str.length() == 0;
    }

}