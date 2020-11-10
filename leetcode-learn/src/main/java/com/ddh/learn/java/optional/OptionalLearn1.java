package com.ddh.learn.java.optional;

import com.ddh.learn.java.model.User;

import javax.swing.text.html.Option;
import java.util.Optional;

/**
 * @author: dengdh@dist.com.cn
 * @data: 2020/11/9 14:30
 */
public class OptionalLearn1 {
    public static void main(String[] args) {
        User user = new User();
        /*if (user.getPhone() != null){
            if (user.getPhone().getPrice() !=null) {
                if (user.getPhone().getPrice().getCost() != null) {
                    System.out.println(user.getPhone().getPrice().getCost());
                }
            }
        }*/

        // 替换上面的if判断，
        Double aDouble = Optional.ofNullable(user)
                .map(User::getPhone)
                .map(User.Phone::getPrice)
                .map(User.Price::getCost)
                .orElse(null);
        System.out.println(aDouble);

        User user1 = Optional.ofNullable(user)
                .orElseGet(User::new);

    }
}
