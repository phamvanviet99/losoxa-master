package com.phamvanviet.losoxa.service.impl;

import com.phamvanviet.losoxa.entity.LineItem;
import com.phamvanviet.losoxa.repository.LineItemRepository;
import com.phamvanviet.losoxa.service.LineItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LineItemServiceImpl implements LineItemService {

    @Autowired
    private LineItemRepository lineItemRepository;

    @Override
    public void saveLineItem(LineItem lineItem) {
        lineItemRepository.save(lineItem);
    }

    @Override
    public void saveAllLineItem(List<LineItem> lineItems) {
        try {
            lineItemRepository.saveAll(lineItems);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
