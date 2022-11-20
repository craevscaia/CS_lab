package Hash;

import java.util.ArrayList;

public class UserStorage {
    public ArrayList<User> usersList = new ArrayList<>();

    public void getUsersList() {
        for (int i = 0; i < usersList.size(); i++) {
            System.out.println(usersList.get(i).name);
        }
    }
}
