package store;

import java.util.Objects;

public class Category {
    private String categoryName;
    private String categoryDescription;
    private String categoryColor;

    public Category() {
    }

    public Category(String categoryName, String categoryDescription, String categoryColor) {
        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
        this.categoryColor = categoryColor;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    public String getCategoryColor() {
        return categoryColor;
    }

    public void setCategoryColor(String categoryColor) {
        this.categoryColor = categoryColor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(categoryName, category.categoryName) &&
                Objects.equals(categoryDescription, category.categoryDescription) &&
                Objects.equals(categoryColor, category.categoryColor);
    }

    @Override
    public int hashCode() {

        return Objects.hash(categoryName, categoryDescription, categoryColor);
    }

    @Override
    public String toString() {
        return "\ncategoryName='" + categoryName + '\'' +
                "\ncategoryDescription='" + categoryDescription + '\'' +
                "\ncategoryColor='" + categoryColor + '\'' +
                "\n";
    }
}
