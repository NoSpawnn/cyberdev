package com.nospawnn.ui.listeners;

import com.nospawnn.operations.Operation;

import java.util.LinkedHashMap;
import java.util.Map;

public interface RecipeListener {
    void stepAdded(LinkedHashMap<Operation, Map<String, Object>> steps);

    void stepRemoved(LinkedHashMap<Operation, Map<String, Object>> steps);
}
