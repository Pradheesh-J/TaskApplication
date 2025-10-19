package org.example.model;

public enum Priority {
    LOW, MEDIUM, HIGH;
    public String toString(){
        return name().toLowerCase();
    }
}
