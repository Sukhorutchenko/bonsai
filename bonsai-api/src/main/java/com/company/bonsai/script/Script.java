package com.company.bonsai.script;

import java.io.Reader;
import java.io.Serializable;

public interface Script extends Serializable {

    String getName();

    Reader getScriptBody();

}
