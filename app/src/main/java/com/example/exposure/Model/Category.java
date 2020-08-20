package com.example.exposure.Model;

/**
 * Category POJO containing constructors, getters and setters
 */
public class Category {
    private int id;
    private String description;

    /**
     * Constructor
     * @param id category id
     * @param description category description
     */

    public Category(int id, String description) {
        this.id = id;
        this.description = description;
    }

    /**
     * Returns Id
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets Id
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets Description
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set Description
     * @param description description
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
