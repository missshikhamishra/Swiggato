package com.example.Swiggato.service;

import com.example.Swiggato.dto.request.DeliveryPartnerRequest;
import com.example.Swiggato.model.DeliveryPartner;
import com.example.Swiggato.repository.DeliveryPartnerRepo;
import com.example.Swiggato.transformer.PartnerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeliveryPartnerService {

    final DeliveryPartnerRepo deliveryPartnerRepo;

    @Autowired
    public DeliveryPartnerService(DeliveryPartnerRepo deliveryPartnerRepo) {
        this.deliveryPartnerRepo = deliveryPartnerRepo;
    }

    public String addPartner(DeliveryPartnerRequest deliveryPartnerRequest) {

        //dto -> entity
        DeliveryPartner deliveryPartner = PartnerTransformer.PartnerRequestToDeliveryPartner(deliveryPartnerRequest);

        // save
        DeliveryPartner savedPartner = deliveryPartnerRepo.save(deliveryPartner);

        return "You have been successfully regsitered!!!";

    }
     public DeliveryPartnerResponse DeliveryPartnerWithMostNoOfOrders() {
        List<DeliveryPartner> list = deliveryPartnerRepository.findAll();
        int maxSize = Integer.MIN_VALUE;
        DeliveryPartner deliveryPartner = null;
        for(DeliveryPartner p : list){
            int size = p.getOrders().size();
           if(size>maxSize){
               maxSize = Math.max(maxSize,size);
               deliveryPartner = p;
           }
        }
        DeliveryPartnerResponse deliveryPartnerResponse = DeliveryPartnerTransformer.DeliveryPartnerToDeliveryPartnerResponse(deliveryPartner);
        deliveryPartnerResponse.setTotalNoOfOrders(maxSize);
        return deliveryPartnerResponse;
}
