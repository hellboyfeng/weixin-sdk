package com.riversoft.weixin.mp.event.card;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.riversoft.weixin.common.event.EventRequest;


public class UserViewCardEvent extends EventRequest {

    @JsonProperty("CardId")
    @JacksonXmlCData
    private String cardId;

    @JsonProperty("UserCardCode")
    @JacksonXmlCData
    private String userCardCode;

    @JsonProperty("OuterStr")
    @JacksonXmlCData
    private String outerStr;

    @JsonProperty("Encrypt")
    @JacksonXmlCData
    private String encrypt;



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

    public String getOuterStr() {
        return outerStr;
    }

    public void setOuterStr(String outerStr) {
        this.outerStr = outerStr;
    }

    public String getEncrypt() {
        return encrypt;
    }

    public void setEncrypt(String encrypt) {
        this.encrypt = encrypt;
    }
}
