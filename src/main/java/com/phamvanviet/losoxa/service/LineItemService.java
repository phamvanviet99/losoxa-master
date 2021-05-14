package com.phamvanviet.losoxa.service;

import com.phamvanviet.losoxa.entity.LineItem;

import java.util.List;

public interface LineItemService {
    void saveLineItem(LineItem lineItem);
    void saveAllLineItem(List<LineItem> lineItems);
}
