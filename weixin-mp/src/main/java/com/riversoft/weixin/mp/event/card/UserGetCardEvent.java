package com.riversoft.weixin.mp.event.card;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.riversoft.weixin.common.event.EventRequest;

/**
 * @borball on 3/17/2016.
 */
public class UserGetCardEvent extends EventRequest {

    @JsonProperty("CardId")
    @JacksonXmlCData
    private String cardId;

    @JsonProperty("IsGiveByFriend")
    private int isGiveByFriend;

    @JsonProperty("UserCardCode")
    @JacksonXmlCData
    private String userCardCode;

    @JsonProperty("FriendUserName")
    @JacksonXmlCData
    private String friendUserName;

    @JsonProperty("OuterId")
    @JacksonXmlCData
    private String outerId;

    @JsonProperty("OldUserCardCode")
    @JacksonXmlCData
    private String oldUserCardCode;

    @JsonProperty("OuterStr")
    @JacksonXmlCData
    private String outerStr;

    @JsonProperty("IsRestoreMemberCard")
    @JacksonXmlCData
    private String isRestoreMemberCard;

    @JsonProperty("IsRecommendByFriend")
    @JacksonXmlCData
    private String isRecommendByFriend;


    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public int getIsGiveByFriend() {
        return isGiveByFriend;
    }

    public void setIsGiveByFriend(int isGiveByFriend) {
        this.isGiveByFriend = isGiveByFriend;
    }

    public String getUserCardCode() {
        return userCardCode;
    }

    public void setUserCardCode(String userCardCode) {
        this.userCardCode = userCardCode;
    }

    public String getFriendUserName() {
        return friendUserName;
    }

    public void setFriendUserName(String friendUserName) {
        this.friendUserName = friendUserName;
    }

    public String getOuterId() {
        return outerId;
    }

    public void setOuterId(String outerId) {
        this.outerId = outerId;
    }

    public String getOldUserCardCode() {
        return oldUserCardCode;
    }

    public void setOldUserCardCode(String oldUserCardCode) {
        this.oldUserCardCode = oldUserCardCode;
    }

    public String getOuterStr() {
        return outerStr;
    }

    public void setOuterStr(String outerStr) {
        this.outerStr = outerStr;
    }

    public String getIsRestoreMemberCard() {
        return isRestoreMemberCard;
    }

    public void setIsRestoreMemberCard(String isRestoreMemberCard) {
        this.isRestoreMemberCard = isRestoreMemberCard;
    }

    public String getIsRecommendByFriend() {
        return isRecommendByFriend;
    }

    public void setIsRecommendByFriend(String isRecommendByFriend) {
        this.isRecommendByFriend = isRecommendByFriend;
    }
}
