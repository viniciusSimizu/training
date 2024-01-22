package com.linkedrh.training.lib.helpers;

import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class QueryFinder {

    private final String modulesPath =
            System.getProperty("user.dir") + "/src/main/java/com/linkedrh/training/modules";
    private final Map<String, String> cache = new HashMap<>();

    public String findQuery(String module, String filename) throws IOException {
        String identifier = module + ":" + filename;

        if (!this.cache.containsKey(identifier)) {
            String path = this.modulesPath + "/" + module + "/queries/" + filename + ".sql";
            FileInputStream in = new FileInputStream(path);
            this.cache.put(identifier, new String(in.readAllBytes()));
            in.close();
        }
        ;

        return this.cache.get(identifier);
    }
}
