package JUnitTest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

//import static org.junit.jupiter.api.Assertions.*;

public class UserTest {
    private User user;
    private User user1;
    private User user2;

    private User userNotAdd;
    private User userNotAdd1;

    @Before
    public void setUp() throws Exception {
        user = new User("Евгений", 35, User.Sex.MALE);
        user1 = new User("Марина", 34, User.Sex.FEMALE);
        user2 = new User("Алина", 7, User.Sex.FEMALE);

        userNotAdd = new User("", 0, null);
        userNotAdd1 = new User(null, 0, null);
    }

    @Test
    public void newUser_EMPTY_NAME() {
        for (User user : User.getAllUsers()){
            if (user.getName() != null && user.getName().isEmpty()) {
                Assert.fail("Попытка создания пользователя с пустым именем");
            }
        }
    }

    @Test
    public void newUser_AGE_ZERO() {
        for (User user : User.getAllUsers()) {
            if (user.getAge() <= 0) {
                Assert.fail("Попытка создания пользователя c не допустимым возрастом");
            }
        }
    }

    @Test
    public void newUser_SEX_NO_NULL() {
        for (User user : User.getAllUsers()) {
            if (user.getSex() == null) {
                Assert.fail("Попытка создания пользователя с указанием пола = null");
            }
        }
    }

    @Test
    public void getAllUsers() {
        User user = new User("Евгений", 35, User.Sex.MALE);
        User user1 = new User("Марина", 34, User.Sex.FEMALE);
        User user2 = new User("Алина", 7, User.Sex.FEMALE);

        List<User> expected = User.getAllUsers();

        List<User> actual = new ArrayList<>();
        actual.add(user);
        actual.add(user1);
        actual.add(user2);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getAllUsers_NO_NULL() {
        List<User> expected = User.getAllUsers();
        Assert.assertNotNull(expected);
    }

    @Test
    public void getAllUsers_MALE() {
        User user = new User("Евгений", 35, User.Sex.MALE);
        User user1 = new User("Марина", 34, User.Sex.FEMALE);
        User user2 = new User("Алина", 7, User.Sex.FEMALE);

        List<User> expected = User.getAllUsers(User.Sex.MALE);

        List<User> actual = new ArrayList<>();
        actual.add(user);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getAllUsers_MALE_NO_NULL() {
        List<User> expected = User.getAllUsers(User.Sex.MALE);
        Assert.assertNotNull(expected);
    }
    @Test
    public void getAllUsers_FEMALE() {
        User user = new User("Евгений", 35, User.Sex.MALE);
        User user1 = new User("Марина", 34, User.Sex.FEMALE);
        User user2 = new User("Алина", 7, User.Sex.FEMALE);

        List<User> expected = User.getAllUsers(User.Sex.FEMALE);

        List<User> actual = new ArrayList<>();
        actual.add(user1);
        actual.add(user2);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getAllUsers_FEMALE_NO_NULL() {
        //добавим проверку на null
        List<User> expected = User.getAllUsers(User.Sex.FEMALE);
        Assert.assertNotNull(expected);
    }

    @Test
    public void getHowManyUsers() {
        User user = new User("Евгений", 35, User.Sex.MALE);
        User user1 = new User("Марина", 34, User.Sex.FEMALE);
        User user2 = new User("Алина", 7, User.Sex.FEMALE);

        int expected = User.getHowManyUsers();

        int actual = 3;

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getHowManyUsers_MALE() {
        User user = new User("Евгений", 35, User.Sex.MALE);
        User user1 = new User("Марина", 34, User.Sex.FEMALE);
        User user2 = new User("Алина", 7, User.Sex.FEMALE);

        int expected = User.getHowManyUsers(User.Sex.MALE);

        int actual = 1;

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getHowManyUsers_FEMALE() {
        User user = new User("Евгений", 35, User.Sex.MALE);
        User user1 = new User("Марина", 34, User.Sex.FEMALE);
        User user2 = new User("Алина", 7, User.Sex.FEMALE);

        int expected = User.getHowManyUsers(User.Sex.FEMALE);

        int actual = 2;

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getAllAgeUsers() {
        User user = new User("Евгений", 35, User.Sex.MALE);
        User user1 = new User("Марина", 34, User.Sex.FEMALE);
        User user2 = new User("Алина", 7, User.Sex.FEMALE);

        int expected = User.getAllAgeUsers();

        int actual = 35 + 34 + 7;

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getAllAgeUsers_MALE() {
        User user = new User("Евгений", 35, User.Sex.MALE);
        User user1 = new User("Марина", 34, User.Sex.FEMALE);
        User user2 = new User("Алина", 7, User.Sex.FEMALE);

        int expected = User.getAllAgeUsers(User.Sex.MALE);

        int actual = 35;

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getAllAgeUsers_FEMALE() {
        User user = new User("Евгений", 35, User.Sex.MALE);
        User user1 = new User("Марина", 34, User.Sex.FEMALE);
        User user2 = new User("Алина", 7, User.Sex.FEMALE);

        int expected = User.getAllAgeUsers(User.Sex.FEMALE);

        int actual = 34 + 7;

        Assert.assertEquals(expected, actual);
    }
}