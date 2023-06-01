package dataprovider;

import org.apache.commons.lang3.RandomStringUtils;
import pojo.CreateCourierRequest;
import pojo.CreateOrderRequest;

public class OrderProvider {
    public static CreateOrderRequest getRandomCreateOrderRequest() {
        CreateOrderRequest createOrderRequest = new CreateOrderRequest();
        createOrderRequest.setFirstName(RandomStringUtils.randomAlphabetic(8));
        createOrderRequest.setLastName(RandomStringUtils.randomAlphabetic(8));
        createOrderRequest.setAddress(RandomStringUtils.randomAlphabetic(8));
        createOrderRequest.setMetroStation(RandomStringUtils.randomAlphabetic(8));
        createOrderRequest.setPhone(RandomStringUtils.randomAlphabetic(11));
        createOrderRequest.setRentTime(1);
        createOrderRequest.setDeliveryDate("2020-03-03");
        createOrderRequest.setComment(RandomStringUtils.randomAlphabetic(20));
        createOrderRequest.setColor(new String[]{"BLACK"});

        return createOrderRequest;
    }
}
