
package com.example.administrator.boxsocialmvp.Objects;

import com.google.gson.annotations.Expose;

public class Metadata_ {

    @Expose
    private String type;

    /**
     * 
     * @return
     *     The type
     */
    public String getType() {
        return type;
    }

    /**
     * 
     * @param type
     *     The type
     */
    public void setType(String type) {
        this.type = type;
    }

}
