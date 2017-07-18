package com.riversoft.weixin.mp.event.card;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.riversoft.weixin.common.event.EventRequest;

/**
 * @borball on 3/17/2016.
 */
public class SubmitMemberCardUserInfoEvent extends EventRequest {

    @JsonProperty("CardId")
    @JacksonXmlCData
    private String cardId;

    @JsonProperty("UserCardCode")
    @JacksonXmlCData
    private String userCardCode;

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getUserCardCode() {
        return userCardCode;
    }

    public void setUserCardCode(String userCardCode) {
        this.userCardCode = userCardCode;
    }
}
