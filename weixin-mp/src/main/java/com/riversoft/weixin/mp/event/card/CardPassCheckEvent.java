package com.riversoft.weixin.mp.event.card;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.riversoft.weixin.common.event.EventRequest;


public class CardPassCheckEvent extends EventRequest {

    @JsonProperty("CardId")
    @JacksonXmlCData
    private String cardId;

    @JsonProperty("RefuseReason")
    private String refuseReason;



    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getRefuseReason() {
        return refuseReason;
    }

    public void setRefuseReason(String refuseReason) {
        this.refuseReason = refuseReason;
    }
}
