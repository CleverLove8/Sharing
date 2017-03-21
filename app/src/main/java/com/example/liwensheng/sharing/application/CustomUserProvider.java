package com.example.liwensheng.sharing.application;

import com.example.liwensheng.sharing.entity.UserEntity;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.leancloud.chatkit.LCChatKitUser;
import cn.leancloud.chatkit.LCChatProfileProvider;
import cn.leancloud.chatkit.LCChatProfilesCallBack;

/**
 * Created by liWensheng on 2017/3/21.
 */

public class CustomUserProvider implements LCChatProfileProvider {
    private static CustomUserProvider customUserProvider;

    public synchronized static CustomUserProvider getInstance() {
        if (null == customUserProvider) {
            customUserProvider = new CustomUserProvider();
        }
        return customUserProvider;
    }

    private CustomUserProvider() {
    }

    private static List<LCChatKitUser> partUsers = new ArrayList<LCChatKitUser>();

    // 此数据均为 fake，仅供参考
    static {
//        partUsers.add(new LCChatKitUser("Tom", "Tom", "http://www.avatarsdb.com/avatars/tom_and_jerry2.jpg"));
//        partUsers.add(new LCChatKitUser("Jerry", "Jerry", "http://www.avatarsdb.com/avatars/jerry.jpg"));
//        partUsers.add(new LCChatKitUser("Harry", "Harry", "http://www.avatarsdb.com/avatars/young_harry.jpg"));
//        partUsers.add(new LCChatKitUser("William", "William", "http://www.avatarsdb.com/avatars/william_shakespeare.jpg"));
//        partUsers.add(new LCChatKitUser("Bob", "Bob", "http://www.avatarsdb.com/avatars/bath_bob.jpg"));
//        partUsers.add(new LCChatKitUser("58ca674f2f301e007e388e46","jj" , "http://www.avatarsdb.com/avatars/bath_bob.jpg"));
        BmobQuery<UserEntity> query = new BmobQuery<>();
        query.include("User_icon");
        query.findObjects(new FindListener<UserEntity>() {
            @Override
            public void done(List<UserEntity> list, BmobException e) {
                for (UserEntity userEntity:list) {
                    String name = userEntity.getUser_name();
                    String url = "http://www.avatarsdb.com/avatars/tom_and_jerry2.jpg";
                    partUsers.add(new LCChatKitUser(name, name, url));
                }
            }
        });
    }

    @Override
    public void fetchProfiles(List<String> list, LCChatProfilesCallBack callBack) {
        List<LCChatKitUser> userList = new ArrayList<LCChatKitUser>();
        for (String userId : list) {
            for (LCChatKitUser user : partUsers) {
                if (user.getUserId().equals(userId)) {
                    userList.add(user);
                    break;
                }
            }
        }
        callBack.done(userList, null);
    }

    public List<LCChatKitUser> getAllUsers() {
        return partUsers;
    }
}
