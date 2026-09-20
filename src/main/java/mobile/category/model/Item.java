package mobile.category.model;


public final class Item {
    public final String raw;
    public final String name;
    public final int left;
    public final int top;
    public final int bottom;

    public Item(String raw, String name, int left, int top, int bottom) {
        this.raw = raw;
        this.name = name;
        this.left = left;
        this.top = top;
        this.bottom = bottom;
    }

    public int centerY() {
        return (top + bottom) / 2;
    }

    @Override
    public String toString() {
        return raw;
    }
}
