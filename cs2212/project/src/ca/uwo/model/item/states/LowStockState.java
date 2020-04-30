package ca.uwo.model.item.states;

import ca.uwo.model.Item;
import ca.uwo.utils.ItemResult;
import ca.uwo.utils.ResponseCode;
import ca.uwo.viewer.Viewer;

import java.util.List;

public class LowStockState implements ItemState {

    @Override
    public ItemResult deplete(Item item, int quantity) {

        item.setState(ItemStateFactory.create(quantity));

        List<Viewer> viewers = item.getViewers();
        for (Viewer viewer : viewers){
            viewer.inform(item);
        }

        return new ItemResult("AVAILABLE", ResponseCode.Completed);

    }

    @Override
    public ItemResult replenish(Item item, int quantity) {

        item.setState(ItemStateFactory.create(quantity));
        return new ItemResult("RESTOCKED", ResponseCode.Completed);

    }

}
