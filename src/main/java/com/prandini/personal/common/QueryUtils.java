package com.prandini.personal.common;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class QueryUtils {

    public static void safeAddParams(Map<String, Object> params, String nome, Object valor, StringBuilder sb, String queryPart){
        if(valor != null){
            params.put(nome, valor);
            sb.append(queryPart);
        }
    }
}
