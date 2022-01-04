package quest.ejercicioseccion6.models;

public class Fruit {
    public String name;
    public String description;
    public int stock;
    public int imgBackground;
    public int imgIcon;

    /* Valores accesibles estaticamente */
    public static final int LIMIT_QUANTITY = 10;
    public static final int RESET_VALUE_QUANTITY = 0;

    public Fruit(){

    }

    public Fruit(String name, String description, int stock, int imgBackground, int imgIcon) {
        this.name = name;
        this.description = description;
        this.stock = stock;
        this.imgBackground = imgBackground;
        this.imgIcon = imgIcon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getImgBackground() {
        return imgBackground;
    }

    public void setImgBackground(int imgBackground) {
        this.imgBackground = imgBackground;
    }

    public int getImgIcon() {
        return imgIcon;
    }

    public void setImgIcon(int imgIcon) {
        this.imgIcon = imgIcon;
    }


    public void addStock() {
        if(this.stock < LIMIT_QUANTITY)
            this.stock += 1;
    }

    public void resetStock() {
        this.stock = RESET_VALUE_QUANTITY;
    }
}
