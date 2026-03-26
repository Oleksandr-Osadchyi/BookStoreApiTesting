//package api.users;
//
//import api.engine.BookStoreObject;
//import api.engine.BookStoreObjectApiClient;
//import api.engine.ResponseSpecifications;
//
//import java.util.List;
//
//public class UsersClient {
//
//    private static final BookStoreObjectApiClient bookStoreObjectApiService = new BookStoreObjectApiClient(BookStoreObject.USERS);
//
//    public User createUser(User user) {
//        return bookStoreObjectApiService
//                .postObject(user)
//                .validate(ResponseSpecifications.responseCode200Spec())
//                .getRecord(User.class);
//    }
//
//    public List<User> getUsers() {
//        return bookStoreObjectApiService
//                .getObjects()
//                .validate(ResponseSpecifications.responseCode200Spec())
//                .getRecords(User.class);
//    }
//
//    public User getUser(int userId) {
//        return bookStoreObjectApiService
//                .getObject(userId)
//                .validate(ResponseSpecifications.responseCode200Spec())
//                .getRecord(User.class);
//    }
//
//    public void updateUser(int userId, User user) {
//        bookStoreObjectApiService
//                .updateObject(userId, user)
//                .validate(ResponseSpecifications.responseCode200Spec());
//    }
//
//    public void deleteUser(int userId) {
//        bookStoreObjectApiService
//                .deleteObject(userId)
//                .validate(ResponseSpecifications.responseCode200Spec());
//    }
//}
