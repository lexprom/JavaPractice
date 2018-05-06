package main.webapp.com.pak.model;

public class Item
{
    private int count = 0;

    private int id;

    private String title;

    private int price;

    private String desc;

    public Item(String title, int price, String desc) {
        this.id = ++count;
        this.title = title;
        this.price = price;
        this.desc = desc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return id +":"+title+":"+price+":"+desc+"\n";
    }
}
