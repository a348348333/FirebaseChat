package com.yzucse.android.firebasechat;

import com.google.android.gms.common.util.Strings;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User implements Serializable {
    private String username;
    private String userID;
    private Map<String, Boolean> stickers; // this structure might be changed
    private Map<String, String> friends;
    private Map<String, Boolean> blockade;
    private Map<String, Boolean> groups;
    private Map<String, String> chatrooms;
    private String sign;
    private Boolean online;
    private String photoUrl;
    private String email;
    private String canMessage;
    private boolean openCan;
    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String userID, String username, String email, String photoUrl) {
        this.userID = userID;
        this.username = username;
        this.email = email;
        this.photoUrl = photoUrl;
    }

    public User(String userID, String username) {
        this.userID = userID;
        this.username = username;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public Boolean getOnline() {
        return online;
    }

    public void setOnline(Boolean online) {
        this.online = online;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Map<String, Boolean> getStickers() {
        return stickers;
    }

    public String getCanMessage() {
        return this.canMessage;
    }

    public void setCanMessage(String canMessage) {
        this.canMessage = canMessage;
    }

    public boolean isOpenCan() {
        return this.openCan;
    }

    public void setOpenCan(boolean openCan) {
        this.openCan = openCan;
    }

    public void setStickers(Map<String, Boolean> stickers) {
        if (!StaticValue.isNullorEmptyMap(stickers)) {
            this.stickers = new HashMap<>();
            this.stickers.putAll(stickers);
        }
    }

    public void addSticker(String id) {
        if (Strings.isEmptyOrWhitespace(id)) return;
        if (this.stickers == null) this.stickers = new HashMap<>();
        this.stickers.put(id, true);
    }

    public void eraseSticker(String id) {
        if (Strings.isEmptyOrWhitespace(id) || StaticValue.isNullorEmptyMap(this.stickers)) return;
        if (this.stickers.containsKey(id))
            this.stickers.remove(id);
    }

    public Map<String, Boolean> getGroups() {
        return groups;
    }

    public void setGroups(Map<String, Boolean> groups) {
        if (!StaticValue.isNullorEmptyMap(groups)) {
            this.groups = new HashMap<>();
            this.groups.putAll(groups);
        }
    }

    public void addGroup(String id) {
        if (Strings.isEmptyOrWhitespace(id)) return;
        if (this.groups == null) this.groups = new HashMap<>();
        this.groups.put(id, true);
    }

    public void eraseGroup(String id) {
        if (Strings.isEmptyOrWhitespace(id) || StaticValue.isNullorEmptyMap(this.groups)) return;
        if (this.groups.containsKey(id))
            this.groups.remove(id);
    }

    public Map<String, String> getFriends() {
        return friends;
    }

    public void setFriends(Map<String, String> friends) {
        if (!StaticValue.isNullorEmptyMap(friends)) {
            this.friends = new HashMap<>();
            this.friends.putAll(friends);
        }
    }

    public void addFriend(String id, String name) {
        if (Strings.isEmptyOrWhitespace(id) || Strings.isEmptyOrWhitespace(name)) return;
        if (friends == null) friends = new HashMap<>();
        friends.put(id, name);
        addBlockade(id);
    }

    public void eraseFriend(String id) {
        if (Strings.isEmptyOrWhitespace(id) || StaticValue.isNullorEmptyMap(this.friends)) return;
        if (this.friends.containsKey(id))
            this.friends.remove(id);
    }

    public Map<String, Boolean> getBlockade() {
        return blockade;
    }

    public void setBlockade(Map<String, Boolean> blockade) {
        if (!StaticValue.isNullorEmptyMap(blockade)) {
            this.blockade = new HashMap<>();
            this.blockade.putAll(blockade);
        }
    }

    public void setFriendBlockade(String id) {
        if (Strings.isEmptyOrWhitespace(id) || StaticValue.isNullorEmptyMap(this.blockade)) return;
        if (this.blockade.containsKey(id))
            this.blockade.put(id, true);
    }

    public void addBlockade(String id) {
        if (Strings.isEmptyOrWhitespace(id)) return;
        if (this.blockade == null) this.blockade = new HashMap<>();
        this.blockade.put(id, false);
    }

    public void cancelBlockade(String id) {
        if (Strings.isEmptyOrWhitespace(id) || StaticValue.isNullorEmptyMap(this.blockade)) return;
        if (this.blockade.containsKey(id))
            this.blockade.put(id, false);
    }

    public Map<String, String> getChatrooms() {
        return chatrooms;
    }

    public void setChatrooms(Map<String, String> chatrooms) {
        if (!StaticValue.isNullorEmptyMap(chatrooms)) {
            this.chatrooms = new HashMap<>();
            this.chatrooms.putAll(chatrooms);
        }
    }

    public String getChatroomName(ChatRoom chatRoom) {
        if (chatRoom != null) {
            if (!Strings.isEmptyOrWhitespace(chatRoom.getChatroomID()) && !StaticValue.isNullorEmptyMap(chatrooms)) {
                String name = chatrooms.get(chatRoom.getChatroomID());
                if (!Strings.isEmptyOrWhitespace(name)) return name;
            }
        }
        return chatRoom.getChatroomName();
    }

    public boolean hasFriend(String friendID) {
        return !Strings.isEmptyOrWhitespace(getFriendsName(friendID, ""));
    }

    public List<String> getFriendsId(String friendName) {
        List<String> ids = new ArrayList<>();
        if (!Strings.isEmptyOrWhitespace(friendName)) {
            if (!StaticValue.isNullorEmptyMap(friends)) {
                if (friends.containsValue(friendName)) {
                    ids = new ArrayList<>(StaticValue.getKeysByValue(friends, friendName));
                }
            }
        }
        return ids;
    }

    public String getFriendsName(String friendID, String defaultName) {
        if (!Strings.isEmptyOrWhitespace(friendID)) {
            if (!StaticValue.isNullorEmptyMap(friends)) {
                if (friends.containsKey(friendID)) return friends.get(friendID);
            }
        }
        return defaultName;
    }

    public List<String> getAllFriendsNames() {
        List<String> names = new ArrayList<>();
        if (!StaticValue.isNullorEmptyMap(friends)) {
            names.addAll(friends.values());
        }
        return names;
    }

    public void addChatroom(String id, String name) {
        if (Strings.isEmptyOrWhitespace(id) || Strings.isEmptyOrWhitespace(name)) return;
        if (chatrooms == null) chatrooms = new HashMap<>();
        chatrooms.put(id, name);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String chatroomfindfriend(String chatroomid){
        return chatrooms.get(chatroomid);
    }
    @Override
    public String toString() {
        return " { " +
                "'online' : '" + online + "'" + ", " + "'name' : '" + username + "'"
                + ", " + "'id' : '" + userID + "'" + ", " + "'chatrooms' : " + StaticValue.Map2String(chatrooms)
                + ", " + "'friends' : " + StaticValue.Map2String(friends) + ", " + "'blockade' : " + StaticValue.Map2String(blockade)
                + ", " + "'stickers' : " + StaticValue.Map2String(stickers) + ", " + "'sign' : '" + sign + "'" + ", " + "'photoUrl' : '" + photoUrl + "' "
                + ", " + "'email' : '" + email + "'" + "," + "'groups' : " + StaticValue.Map2String(groups)
                + " }";
    }

}
