package com.riversoft.weixin.mp.event.card;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.riversoft.weixin.common.event.EventRequest;


public class UserConsumeCardEvent extends EventRequest {

    @JsonProperty("CardId")
    @JacksonXmlCData
    private String cardId;

    @JsonProperty("UserCardCode")
    @JacksonXmlCData
    private String userCardCode;

    @JsonProperty("ConsumeSource")
    @JacksonXmlCData
    private String consumeSource;

    @JsonProperty("LocationName")
    @JacksonXmlCData
    private String locationName;

    @JsonProperty("StaffOpenId")
    @JacksonXmlCData
    private String staffOpenId;

    @JsonProperty("OuterStr")
    @JacksonXmlCData
    private String outerStr;

    @JsonProperty("VerifyCode")
    @JacksonXmlCData
    private String verifyCode;

    @JsonProperty("RemarkAmount")
    @JacksonXmlCData
    private String remarkAmount;


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

    public String getConsumeSource() {
        return consumeSource;
    }

    public void setConsumeSource(String consumeSource) {
        this.consumeSource = consumeSource;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getStaffOpenId() {
        return staffOpenId;
    }

    public void setStaffOpenId(String staffOpenId) {
        this.staffOpenId = staffOpenId;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public String getRemarkAmount() {
        return remarkAmount;
    }

    public void setRemarkAmount(String remarkAmount) {
        this.remarkAmount = remarkAmount;
    }
}
