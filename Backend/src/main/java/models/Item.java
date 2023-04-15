package models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Document(collection = "items")
public class Item implements Comparable<Item> {

    @Id
    private ObjectId id;
    private String unit;
    private double price;
    private double amount;
    private String name;
    private String brand;
    private String store;
    private String category;
    private String subCategory;
    private double pricePerBaseAmount;


    public Item(String unit, double price, double amount) {
        this.unit = unit;
        this.price = price;
        this.amount = amount;
        pricePerBaseAmount = calculatePricePerBaseAmount();
    }

    public Item(String unit, double price, double amount, String name, String brand, String store, String category, String subCategory) {
        this.unit = unit;
        this.price = price;
        this.amount = amount;
        this.name = name;
        this.brand = brand;
        this.store = store;
        this.category = category;
        this.subCategory = subCategory;
        pricePerBaseAmount = calculatePricePerBaseAmount();
    }

    private double calculatePricePerBaseAmount() {
        // amount that converted to the base unit
        double result = -1;
        double baseAmount = UnitConverter.getStandardAmount(unit) * amount;
        if (baseAmount != 0) {
            result = price / baseAmount;
        }
        return result;
    }

    @Override
    public int compareTo(Item other) {
        double currentItemPricePerAmount = pricePerBaseAmount;
        double otherItemPricePerAmount = other.getPricePerBaseAmount();

//        compareTo method calculates each of the itemPricePerAmount and return
//        - positive number. if i2 is cheaper than i1
//        - 0. if i1 and i2 has same value
//        - negative number. if i1 is cheaper than i2
        return Double.compare(currentItemPricePerAmount, otherItemPricePerAmount);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        Item other = (Item) obj;

        return other.id.equals(id) && other.unit.equals(unit) && other.name.equals(name) && other.price == price && other.amount == amount && other.brand.equals(brand) && other.store.equals(store) && other.category.equals(category) && other.subCategory.equals(subCategory);
    }

    @Override
    public int hashCode(){
        // creating hashcode() with custom : starting point 1, prime multiplier 31
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((unit == null) ? 0 : unit.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((brand == null) ? 0 : brand.hashCode());
        result = prime * result + ((store == null) ? 0 : store.hashCode());
        result = prime * result + ((category == null) ? 0 : category.hashCode());
        result = prime * result + ((subCategory == null) ? 0 : subCategory.hashCode());
        result = prime * result + (int) price;
        result = prime * result + (int) amount;
        result = prime * result + (int) pricePerBaseAmount;

        return result;
    }
}