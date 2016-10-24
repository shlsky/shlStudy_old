package com.mars.jmapper;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by chengbinjiao on 16/10/22.
 */
@Data
public class Destination {
    @Getter
    @Setter
    private String destName;
    @Getter
    @Setter
    private List<Language> languages;
    @Getter
    @Setter
    private SystemD systemD;

    @Override
    public String toString() {
        return "Destination{" +
                "destName='" + destName + '\'' +
                ", languages=" + languages +
                ", systemD=" + systemD +
                '}';
    }
}
