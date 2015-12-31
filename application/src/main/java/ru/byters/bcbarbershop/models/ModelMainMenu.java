package ru.byters.bcbarbershop.models;

import ru.byters.bcbarbershop.dataclasses.ItemMenuColored;

public class ModelMainMenu {

    private ItemMenuColored[] data;

    public ModelMainMenu(ItemMenuColored[] data) {
        this.data = data;
    }

    public int getSize() {
        if (data != null)
            return data.length;
        return 0;
    }

    public ItemMenuColored getItem(int pos) {
        if ((pos < data.length) && (pos >= 0))
            return data[pos];
        return null;
    }
}
