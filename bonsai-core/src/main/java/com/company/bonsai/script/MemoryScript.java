package com.company.bonsai.script;

import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Arrays;

public class MemoryScript implements Script {

    private final String name;
    private final byte[] scriptBodyBytes;

    public MemoryScript(String name, byte[] scriptBodyBytes) {
        this.name = name;
        this.scriptBodyBytes = scriptBodyBytes;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Reader getScriptBody() {
        return new InputStreamReader(new ByteArrayInputStream(scriptBodyBytes));
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MemoryScript)) return false;

        MemoryScript that = (MemoryScript) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return Arrays.equals(scriptBodyBytes, that.scriptBodyBytes);

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (scriptBodyBytes != null ? Arrays.hashCode(scriptBodyBytes) : 0);
        return result;
    }

}
