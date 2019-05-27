package com.bkhech.home.practice.entity;

/**
 * @author guowm[guowm@5fun.com]
 * @date 2018/11/29
 */
public class AccountState {

    private boolean isBan;

    private String banTime;

    private boolean isChatBan;

    private String chatBanTime;

    public boolean isBan() {
        return isBan;
    }

    public void setBan(boolean ban) {
        isBan = ban;
    }

    public String getBanTime() {
        return banTime;
    }

    public void setBanTime(String banTime) {
        this.banTime = banTime;
    }

    public boolean isChatBan() {
        return isChatBan;
    }

    public void setChatBan(boolean chatBan) {
        isChatBan = chatBan;
    }

    public String getChatBanTime() {
        return chatBanTime;
    }

    public void setChatBanTime(String chatBanTime) {
        this.chatBanTime = chatBanTime;
    }

    @Override
    public String toString() {
        return "AccountState{" +
                "isBan=" + isBan +
                ", banTime='" + banTime + '\'' +
                ", isChatBan=" + isChatBan +
                ", chatBanTime='" + chatBanTime + '\'' +
                '}';
    }
}
