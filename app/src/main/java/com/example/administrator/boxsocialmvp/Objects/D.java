
package com.example.administrator.boxsocialmvp.Objects;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class D {

    @Expose
    private List<Result> results = new ArrayList<Result>();
    @SerializedName("__next")
    @Expose
    private String Next;

    /**
     * 
     * @return
     *     The results
     */
    public List<Result> getResults() {
        return results;
    }

    /**
     * 
     * @param results
     *     The results
     */
    public void setResults(List<Result> results) {
        this.results = results;
    }

    /**
     * 
     * @return
     *     The Next
     */
    public String getNext() {
        return Next;
    }

    /**
     * 
     * @param Next
     *     The __next
     */
    public void setNext(String Next) {
        this.Next = Next;
    }

}
