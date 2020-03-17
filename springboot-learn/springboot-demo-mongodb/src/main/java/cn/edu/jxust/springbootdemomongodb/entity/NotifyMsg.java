//package cn.edu.jxust.springbootdemomongodb.entity;
//
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.springframework.data.annotation.CreatedDate;
//import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.index.Indexed;
//import org.springframework.data.mongodb.core.mapping.Document;
//import org.springframework.data.mongodb.core.mapping.Field;
//
//import java.io.Serializable;
//import java.util.Date;
//
///**
// * @author: ddh
// * @data: 2019/12/11 17:10
// * @description
// **/
//@Document(collation = "notify_msg")
//@Data
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
//public class NotifyMsg implements Serializable {
//    private static final long serialVersionUID = -8985545025018238754L;
//
//    @Id
//    private String id;
//
//    /**
//     * 消息类型
//     */
//    @Indexed
//    private String notifyType;
//
//    /**
//     * 消息单号
//     */
//    @Indexed
//    private String notifyNo;
//
//    /**
//     * 消息通知日期
//     */
//    private String notifyDate;
//
////    @Field("notifyMsg")
//    private String notifyMsg;
//
//    @CreatedDate
//    private Date gmtCreate;
//}
