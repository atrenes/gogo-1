package com.example.gogo.mapping;

import com.example.gogo.dto.ItemDto;
import com.example.gogo.entity.Item;
import lombok.Generated;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
@Generated
public interface ItemMapper {

    ItemDto mapItem(Item item);

    default List<ItemDto> mapItemList(List<Item> items) {
        List<ItemDto> itemDtos = new ArrayList<>();
        for (Item item: items) {
            itemDtos.add(mapItem(item));
        }
        return itemDtos;
    }
}
