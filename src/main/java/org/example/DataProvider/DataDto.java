package org.example.DataProvider;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class DataDto implements IDto {
    private final String name;
    private final String url;
    private final boolean check;

    @Override
    public String toString() {
        return name;
    }
}