package com.example.study.sample;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.Item;
import com.example.study.model.entity.OrderDetail;
import com.example.study.model.entity.OrderGroup;
import com.example.study.model.entity.Users;
import com.example.study.model.enums.OrderDetailStatus;
import com.example.study.model.enums.OrderGroupOrderType;
import com.example.study.model.enums.OrderGroupPaymentType;
import com.example.study.model.enums.OrderGroupStatus;
import com.example.study.repository.ItemRepositroy;
import com.example.study.repository.OrderDetailRepository;
import com.example.study.repository.OrderGroupRepository;
import com.example.study.repository.UsersRepository;


public class OrderDetailSample extends StudyApplicationTests {

    private Random random = new Random();

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private ItemRepositroy itemRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderGroupRepository orderGroupRepository;

    @Test
    public void createOrder(){

        List<Users> usersList = usersRepository.findAll();

        for(int j = 0; j < 1; j++){
            Users users = usersList.get(j);
            item(users);

        }


        usersList.forEach(users -> {

            int orderCount = random.nextInt(10) + 1;
            for (int i = 0; i < orderCount; i++) {
                item(users);
            }
        });


    }


    private void item(Users users){
        double totalAmount = 0;

        List<Item> items = new ArrayList<>();
        List<OrderDetail> orderHistoryDetails = new ArrayList<>();


        int itemCount = random.nextInt(10)+1;
        for(int i = 0 ; i < itemCount; i ++){
            // db??? ????????? ????????? ??? 500??? ( * ????????? ????????? ???????????? )
            int itemNumber = random.nextInt(405)+1;

            Item item = itemRepository.findById((long)itemNumber).get();
            totalAmount += item.getPrice().doubleValue();
            items.add(item);
        }


        int s = random.nextInt(3)+1;
        OrderGroupStatus status = OrderGroupStatus.ORDERING;
        OrderGroupPaymentType paymentType = OrderGroupPaymentType.BANK_TRANSFER;
        switch (s){
            case 1 :
                status = OrderGroupStatus.ORDERING;
                paymentType = OrderGroupPaymentType.BANK_TRANSFER;
                break;

            case 2 :
                status = OrderGroupStatus.COMPLETE;
                paymentType = OrderGroupPaymentType.CARD;
                break;

            case 3 :
                status =  OrderGroupStatus.CONFIRM;
                paymentType = OrderGroupPaymentType.CHECK_CARD;
                break;
        }

        int t = random.nextInt(2)+1;
        OrderGroupOrderType type = t==1? OrderGroupOrderType.ALL:OrderGroupOrderType.EACH;


        OrderGroup orderGroup = OrderGroup.builder()
                .users(users)
                .status(status)
                .orderType(type)
                .revAddress("????????? ????????? ????????????")
                .revName(users.getEmail())
                .paymentType(paymentType)
                .totalPrice(new BigDecimal(totalAmount))
                .orderAt(getRandomDate())
                .totalQuantity(itemCount)
                .arrivalDate(getRandomDate().plusDays(3))
                .orderDetailList(orderHistoryDetails)
                .build();

        orderGroupRepository.save(orderGroup);



        for(Item item : items){

        	OrderDetailStatus orderDetailStatus = OrderDetailStatus.ORDERING;
            switch (random.nextInt(3)+1){
                case 1 :
                    orderDetailStatus = OrderDetailStatus.ORDERING;
                    break;

                case 2 :
                    orderDetailStatus = OrderDetailStatus.COMPLETE;
                    break;

                case 3 :
                    orderDetailStatus = OrderDetailStatus.CONFIRM;
                    break;
            }


            OrderDetail orderDetail = OrderDetail.builder()
                    .orderGroup(orderGroup)
                    .item(item)
                    .arrivalDate(type.equals(OrderGroupOrderType.ALL) ? orderGroup.getArrivalDate() : getRandomDate())
                    .status(type.equals(OrderGroupOrderType.ALL) ? orderDetailStatus :orderDetailStatus)
                    .build();
            orderDetailRepository.save(orderDetail);
            orderHistoryDetails.add(orderDetail);
        }


    }


    private LocalDateTime getRandomDate(){
        return LocalDateTime.of(2019,getRandomNumber(),getRandomNumber(),getRandomNumber(),getRandomNumber(),getRandomNumber());
    }

    private int getRandomNumber(){
        return random.nextInt(11)+1;
    }
}
