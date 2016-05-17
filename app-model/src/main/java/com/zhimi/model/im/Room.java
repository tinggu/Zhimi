package com.zhimi.model.im;

import io.realm.RealmObject;

/**
 * Created by lzw on 14-9-26.
 */
public class Room extends RealmObject {
    //@PrimaryKey
    /**
     * 会话id
     */
    private String conversationId;

    /**
     * 未读消息数量
     */
    private int unreadCount;


    /**
     * 最后更新时间
     */
    private long lastModifyTime;


    public String getConversationId() {
        return conversationId;
    }

    public void setConversationId(String conversationId) {
        this.conversationId = conversationId;
    }

    public int getUnreadCount() {
        return unreadCount;
    }

    public void setUnreadCount(int unreadCount) {
        this.unreadCount = unreadCount;
    }

    public void setLastModifyTime(long timestamp) {
        this.lastModifyTime = timestamp;
    }

    public long getLastModifyTime() {
//        AVIMConversation conversation = AVIMConversationCacheUtils.getCacheConversation(getConversationId());
//        if (null != conversation && null != conversation.getUpdatedAt()) {
//            return conversation.getUpdatedAt().getTime();
//        }
        return lastModifyTime;
    }

//    public AVIMConversation getConversation() {
//        return AVIMConversationCacheUtils.getCacheConversation(getConversationId());
//    }


//    public static abstract class MultiRoomsCallback {
//        public abstract void done(List<Room> roomList, AVException exception);
//    }
}
