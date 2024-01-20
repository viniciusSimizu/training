package com.linkedrh.training.lib.interfaces;

import java.util.List;

public interface Validated {

    public boolean isValid();

    public List<String> getErrors();
}
