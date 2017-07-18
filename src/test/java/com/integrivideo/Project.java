package com.integrivideo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus on 7/18/2017.
 */
public class Project {
    private String name;
    private String description;
    private List<String> domains;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getDomains() {
        return domains;
    }

    public void setDomains(List<String> domains) {
        this.domains = domains;
    }

    /**
     * @param name
     * @param description
     * @param domains TODO separated string to list
     */
    public Project(String name, String description, List<String> domains){
        this.name = name;
        this.description = description;
        this.domains = domains;
    }

    public Project(){}

    public Project(String name, String description, String domains){
        this.name = name;
        this.description = description;
        this.domains = new ArrayList<>();
        this.domains.add(domains);
    }
}
