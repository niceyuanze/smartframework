package org.smart4j.framework.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by niceyuanze on 17-5-21.
 */
public class View {

    /**
     * 试图路径
     */

    private String path;

    /**
     * 模型路径
     */

    private Map<String, Object> model;

    public View(String path) {
        this.path = path;
        model = new HashMap<>();
    }

    public View addModel(String key, Object value){
        model.put(key, value);
        return this;
        //这段好好思考一下
    }

    public String getPath(){
        return path;
    }

    public Map<String, Object> getModel() {
        return model;
    }
}
