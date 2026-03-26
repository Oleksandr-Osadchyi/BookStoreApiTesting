//package api.users;
//
//import lombok.*;
//import utils.FakerUtil;
//
//
//@Getter
//@Setter
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
//public class User {
//    private int id;
//    private String userName;
//    private String password;
//
//    public User createUserModel(int id, String userName, String password) {
//        return User.builder()
//                .id(id)
//                .userName(userName)
//                .password(password)
//                .build();
//    };
//
//    public User createRandomUserModel() {
//        return User.builder()
//                .id(FakerUtil.randomNumber(1000, 100000))
//                .userName(FakerUtil.randomEmailAddress())
//                .password(FakerUtil.randomPassword())
//                .build();
//    }
//}
