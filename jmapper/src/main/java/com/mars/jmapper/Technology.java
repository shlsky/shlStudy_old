package com.mars.jmapper;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by chengbinjiao on 16/10/22.
 */
@Data
public class Technology {
    @Getter
    @Setter
    private String techName;
    @Getter
    @Setter
    private List<Language> languageList;
    @Getter
    @Setter
    private SystemS systemS;

    @Override
    public String toString() {
        return "Technology{" +
                "languageList=" + languageList +
                '}';
    }
}
