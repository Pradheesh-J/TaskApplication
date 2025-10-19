package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubTask {
    int taskNo;
    String content;

    @Override
    public String toString() {
        return "SubTask " + taskNo + ": " + content;
    }
}
