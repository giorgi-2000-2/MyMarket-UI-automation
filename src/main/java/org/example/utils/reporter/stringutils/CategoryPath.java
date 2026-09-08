package org.example.utils.reporter.stringutils;
import lombok.Getter;

@Getter
public class CategoryPath {
        private final String mainCategory;
        private final String itemName;
        private final String[] subCategories;

        public CategoryPath(String mainCategory, String itemName, String[] subCategories) {
            this.mainCategory = mainCategory;
            this.itemName = itemName;
            this.subCategories = subCategories;
        }

}

