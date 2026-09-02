package org.example.DataProvider;

import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class DataDto2 implements IDto {
    private String name;
    private String url;

    @Override
    public String toString() {
        return name;
    }
}
