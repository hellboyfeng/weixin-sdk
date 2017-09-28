package com.riversoft.weixin.mp.card;

import com.google.common.base.Strings;
import com.riversoft.weixin.common.WxClient;
import com.riversoft.weixin.common.exception.WxRuntimeException;
import com.riversoft.weixin.common.util.JsonMapper;
import com.riversoft.weixin.mp.MpWxClientFactory;
import com.riversoft.weixin.mp.base.AppSetting;
import com.riversoft.weixin.mp.base.WxEndpoint;
import com.riversoft.weixin.mp.card.bean.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 卡券接口
 * Created by exizhai on 11/28/2015.
 */
public class Cards {

    private static Logger logger = LoggerFactory.getLogger(Cards.class);

    private WxClient wxClient;

    public static Cards defaultCards() {
        return with(AppSetting.defaultSettings());
    }

    public static Cards with(AppSetting appSetting) {
        Cards cards = new Cards();
        cards.setWxClient(MpWxClientFactory.getInstance().with(appSetting));
        return cards;
    }

    public void setWxClient(WxClient wxClient) {
        this.wxClient = wxClient;
    }

    /**
     * 创建团购券
     *
     * @param groupon
     * @return
     */
    public String groupon(Groupon groupon) {
        Card card = new Card();
        card.setCardType("GROUPON");
        card.setGroupon(groupon);

        return createCard(card);
    }

    /**
     * 创建代金券
     *
     * @param cash
     * @return
     */
    public String cash(Cash cash) {
        Card card = new Card();
        card.setCardType("CASH");
        card.setCash(cash);

        return createCard(card);
    }

    /**
     * 创建礼品券
     *
     * @param gift
     * @return
     */
    public String gift(Gift gift) {
        Card card = new Card();
        card.setCardType("GIFT");
        card.setGift(gift);

        return createCard(card);
    }

    /**
     * 创建折扣券
     *
     * @param discount
     * @return
     */
    public String discount(Discount discount) {
        Card card = new Card();
        card.setCardType("DISCOUNT");
        card.setDiscount(discount);

        return createCard(card);
    }

    /**
     * 创建优惠券
     *
     * @param coupon
     * @return
     */
    public String coupon(Coupon coupon) {
        Card card = new Card();
        card.setCardType("GENERAL_COUPON");
        card.setCoupon(coupon);

        return createCard(card);
    }

    public String member(Member member) {
        Card card = new Card();
        card.setCardType("MEMBER_CARD");
        card.setMember(member);

        return createCard(card);
    }

    private String createCard(Card card) {
        String json = JsonMapper.defaultMapper().toJson(new CardWrapper(card));
        logger.debug("create card: {}", json);

        String url = WxEndpoint.get("url.card.create");
        String response = wxClient.post(url, json);

        Map<String, Object> result = JsonMapper.nonDefaultMapper().json2Map(response);

        if (result.containsKey("card_id")) {
            return result.get("card_id").toString();
        } else {
            throw new WxRuntimeException(999, "create card failed.");
        }
    }
    /**
     * 删除优惠券
     *
     * @param coupon
     * @return
     */
    public String deleteCard(String  cardId) {
        String json = "{\"card_id\":\"%s\"}";
        logger.info("delete card: {}", cardId);

        String url = WxEndpoint.get("url.card.delete");
        String response = wxClient.post(url,String.format(json,cardId));
        return response;
    }


    /**
     * 投放优惠券
     *
     * @param coupon
     * @return
     */
    public String deposit(String cardid,String codes) {
        String json = "{\"card_id\":\"%s\",\"code\":%s}";
        logger.info("deposit card: {} codes:{}", cardid,codes);
        String url = WxEndpoint.get("url.card.code.deposit");
        String response = wxClient.post(url,String.format(json,cardid,codes));
        return response;
    }

    /**
     * 核查优惠券
     *
     * @param coupon
     * @return
     */
    public String checkCode(String cardid,String codes) {
        String json = "{\"card_id\":\"%s\",\"code\":%s}";
        logger.info("check card: {} codes:{}", cardid,codes);
        String url = WxEndpoint.get("url.card.code.check");
        String response = wxClient.post(url,String.format(json,cardid,codes));
        return response;
    }

     /**
     * 修改优惠券库存
     *
     * @param coupon
     * @return
     */
    public String modifyStock(String cardid,int increase,int reduce) {
        String json = "{\"card_id\":\"%s\",\"increase_stock_value\":%s,\"reduce_stock_value\":%s}";
        logger.info("modifysotck card: {},increase:{},reduce:{}", cardid,increase,reduce);
        String url = WxEndpoint.get("url.card.modifystock");
        String response = wxClient.post(url,String.format(json,cardid,increase,reduce));
        return response;
    }


    /**
     * 生成卡卷二维码
     *
     * @param coupon
     * @return
     */
    public String createQrcode(String cardId,String outerStr) {
        String json = " {" +
                "\"action_name\": \"QR_CARD\", " +
                "\"action_info\": {" +
                "\"card\": {" +
                "\"card_id\": \"%s\"," +
                "\"outer_str\":\"%s\""+
                "  }" +
                " }" +
                "}";
        String data = String.format(json,cardId,outerStr);
        logger.info(data);
        String url = WxEndpoint.get("url.card.qrcode.create");
        String response = wxClient.post(url,data);
        return response;
    }



    /**
     * 生成卡卷二维码
     *
     * @param coupon
     * @return
     */
    public String createQrcode(String cardId,String code,String outerStr,String openid) {
        String json = " {" +
                "\"action_name\": \"QR_CARD\", " +
                "\"action_info\": {" +
                "\"card\": {" +
                "\"card_id\": \"%s\"," +
                "\"openid\": \"%s\","+
                "\"code\": \"%s\"," +
                "\"outer_str\":\"%s\","+
                "\"is_unique_code\":false"+
                "  }" +
                " }" +
                "}";
        String data = String.format(json,cardId,openid,code,outerStr);
        logger.info(data);
        String url = WxEndpoint.get("url.card.qrcode.create");
        String response = wxClient.post(url,data);
        return response;
    }


    /**
     * 获取卡片总数
     *
     * @param statusList “CARD_STATUS_NOT_VERIFY”,待审核；
     *                   “CARD_STATUS_VERIFY_FALL”,审核失败；
     *                   “CARD_STATUS_VERIFY_OK”，通过审核；
     *                   “CARD_STATUS_USER_DELETE”，卡券被用户删除；
     *                   “CARD_STATUS_USER_DISPATCH”，在公众平台投放过的卡券
     * @return
     */
    public int count(List<String> statusList) {
        Map<String, Object> request = new HashMap<>();
        request.put("offset", 0);
        request.put("count", 1);
        if (statusList != null && !statusList.isEmpty()) {
            request.put("status_list", statusList);
        }

        String url = WxEndpoint.get("url.card.list");
        String json = JsonMapper.defaultMapper().toJson(request);
        String response = wxClient.post(url, json);

        Map<String, Object> result = JsonMapper.defaultMapper().json2Map(response);
        if (result.containsKey("total_num")) {
            return (Integer) result.get("total_num");
        } else {
            throw new WxRuntimeException(999, "create card failed.");
        }
    }

    /**
     * 获取卡片列表
     *
     * @param offset
     * @param count
     * @param statusList “CARD_STATUS_NOT_VERIFY”,待审核；
     *                   “CARD_STATUS_VERIFY_FALL”,审核失败；
     *                   “CARD_STATUS_VERIFY_OK”，通过审核；
     *                   “CARD_STATUS_USER_DELETE”，卡券被用户删除；
     *                   “CARD_STATUS_USER_DISPATCH”，在公众平台投放过的卡券
     * @return
     */
    public List<String> list(int offset, int count, List<String> statusList) {
        Map<String, Object> request = new HashMap<>();
        request.put("offset", offset);
        request.put("count", count);
        if (statusList != null && !statusList.isEmpty()) {
            request.put("status_list", statusList);
        }

        String url = WxEndpoint.get("url.card.list");
        String json = JsonMapper.defaultMapper().toJson(request);
        String response = wxClient.post(url, json);

        Map<String, Object> result = JsonMapper.defaultMapper().json2Map(response);
        if (result.containsKey("card_id_list")) {
            return (List<String>) result.get("card_id_list");
        } else {
            throw new WxRuntimeException(999, "create card failed.");
        }
    }

    public Card get(String cardId) {
        String json = "{\"card_id\":\"%s\"}";
        logger.debug("get card: {}", cardId);

        String url = WxEndpoint.get("url.card.get");
        String response = wxClient.post(url, String.format(json, cardId));

        CardWrapper cardWrapper = JsonMapper.defaultMapper().fromJson(response, CardWrapper.class);
        return cardWrapper.getCard();
    }

    public String codeDecrypt(String encryptCode) {
        String json = "{\"encrypt_code\":\"%s\"}";
        logger.debug("decrypt code: {}", encryptCode);

        String url = WxEndpoint.get("url.card.code.decrypt");
        String response = wxClient.post(url, String.format(json, encryptCode));
        return response;
    }



    public String searchCode(String cardId,String code) {
        String json = "{" +
                "   \"card_id\" : \"%s\"," +
                "   \"code\" : \"%s\"," +
                "   \"check_consume\" : true" +
                "}";
        logger.debug("get card code: {}", cardId);

        String url = WxEndpoint.get("url.card.code.get");
        String response = wxClient.post(url, String.format(json, cardId,code));
        return response;
    }


    /**
     * 核查优惠券
     *
     * @param coupon
     * @return
     */
    public String updateCode(String cardid,String code,String newCode) {
        String json = "{\"card_id\":\"%s\",\"code\":\"%s\",\"new_code\":\"%s\"}";
        String url = WxEndpoint.get("url.card.code.update");
        String response = wxClient.post(url,String.format(json,cardid,code,newCode));
        return response;
    }

    /**
     * 核销卡券
     * @param cardId
     * @param code
     * @return
     */
    public String consume(String cardId,String code) {
        String json = "{" +
                "  \"code\": \"%s\"," +
                "  \"card_id\": \"%s\"" +
                "}";
        logger.debug("consume card code: {}", cardId);

        String url = WxEndpoint.get("url.card.code.consume");
        String response = wxClient.post(url, String.format(json, code,cardId));
        return response;
    }

    /**
     * 设置卡券失效
     * @param cardId
     * @param code
     * @param reason
     * @return
     */
    public String unavailable(String cardId,String code,String reason) {
        String json = "{" +
                "  \"code\": \"%s\"," +
                "  \"card_id\": \"%s\"," +
                "  \"reason\": \"%s\"" +
                "}";
        logger.debug("unavailable card code: {}", code);

        String url = WxEndpoint.get("url.card.code.unavailable");
        String response = wxClient.post(url, String.format(json, code,cardId,reason));
        return response;
    }


    /**
     * 激活会员卡
     * @param cardId
     * @param code
     * @param number
     * @param integral
     * @param balance
     * @return
     */
    public String memberActive(String cardId,String code,String number,String integral,String balance,String level) {
        String format = "";
        String json = "{" +
                "    \"init_bonus\": %s," +
                "    \"init_bonus_record\":\"积分同步\"," +
                "    \"init_balance\": %s," +
                "    \"membership_number\": \"%s\"," +
                "    \"code\": \"%s\"," +
                "    \"card_id\": \"%s\"" +
                "}";
        if(!Strings.isNullOrEmpty(level)){
            json = "{" +
                    "    \"init_bonus\": %s," +
                    "    \"init_bonus_record\":\"积分同步\"," +
                    "    \"init_balance\": %s," +
                    "    \"membership_number\": \"%s\"," +
                    "    \"code\": \"%s\"," +
                    "    \"card_id\": \"%s\"," +
                    "    \"init_custom_field_value1\": \"%s\"" +
                    "}";
        }
        format = String.format(json, integral,balance,number,code,cardId,level);
        if(Strings.isNullOrEmpty(integral)){
            json = "{" +
                    "    \"membership_number\": \"%s\"," +
                    "    \"code\": \"%s\"," +
                    "    \"card_id\": \"%s\"" +
                    "}";
            format = String.format(json,number,code,cardId);
        }
        logger.debug("consume card code: {}", cardId);
        String url = WxEndpoint.get("url.card.member.activate");
        String response = wxClient.post(url,format);
        return response;
    }

    public String activeGetUrl(String cardId) {
        String json = "{" +
                "  \"card_id\": \"%s\"," +
                "  \"outer_str\": \"123\"" +
                "}";
        String url = WxEndpoint.get("url.card.member.activate.url");
        String response = wxClient.post(url, String.format(json,cardId));
        return response;
    }

    public String getCardList(String cardId,String openId) {
        String json = "{" +
                "  \"card_id\": \"%s\"," +
                "  \"openid\": \"%s\"" +
                "}";
        String url = WxEndpoint.get("url.card.code.getcardlist");
        String response = wxClient.post(url, String.format(json,cardId,openId));
        return response;
    }


    /**
     * 会员卡货架
     * @param cardId
     * @return
     */
    public String landingpageCreate(String cardId) {
        String json = "{  " +
                "\"banner\":\"https://mmbiz.qlogo.cn/mmbiz_jpg/ON84cr4Rib6MbWzVicnWueAgYWrXua1RgQGuOkz2K5NroWibYSkAlQS2ancLQWVBQm31Hco4LvwPPYMahXkMVJyjw/0?wx_fmt=jpeg\"," +
                "   \"page_title\": \"西亚会员卡列表\"," +
                "   \"can_share\": true," +
                "   \"scene\": \"SCENE_NEAR_BY\"," +
                "   \"card_list\": [" +
                "       {" +
                "           \"card_id\": \"p2zKvjtfKqZSTw_jPY8O4bLUKpeM\"," +
                "           \"thumb_url\": \"http://mmbiz.qpic.cn/mmbiz_jpg/ON84cr4Rib6M7kPADl39MIwc4OOJmWHnvfSkTu4NtKKkCjd7AwgwS2G8nsLKibZzHLQESBJaeGt7tAMN3gYxYyrw/0?wx_fmt=jpeg\"" +
                "       }," +
                "       {" +
                "           \"card_id\": \"p2zKvju2GkX79HfLNxDLE7Had5-k\"," +
                "           \"thumb_url\": \"http://mmbiz.qpic.cn/mmbiz_jpg/ON84cr4Rib6M7kPADl39MIwc4OOJmWHnvfSkTu4NtKKkCjd7AwgwS2G8nsLKibZzHLQESBJaeGt7tAMN3gYxYyrw/0?wx_fmt=jpeg\"" +
                "       }" +
                "   ]" +
                "}";
        String url = WxEndpoint.get("url.card.landingpage.create");
        String response = wxClient.post(url, String.format(json,cardId));
        return response;
    }


    /**
     * 微信会员设置开卡字段
     * @param cardId
     * @return
     */
    public String activateUserform(String cardId) {
        String json = "{" +
                "    \"card_id\": \"%s\"," +
                "    \"bind_old_card\": {" +
                "        \"name\": \"老会员绑定\"," +
                "        \"url\": \"http://wx.xiya3333.com/xiya/weixin/mp/member/bindview\"" +
                "    }," +
                "    \"required_form\": {" +
                "        \"can_modify\":false," +
                "        \"common_field_id_list\":[" +
                "            \"USER_FORM_INFO_FLAG_MOBILE\"," +
                "            \"USER_FORM_INFO_FLAG_SEX\"," +
                "            \"USER_FORM_INFO_FLAG_NAME\"" +
                "        ]" +
                "    }," +
                " \"optional_form\": {" +
                "        \"can_modify\":false," +
                "        \"common_field_id_list\":[" +
                "            \"USER_FORM_INFO_FLAG_IDCARD\"," +
                "            \"USER_FORM_INFO_FLAG_BIRTHDAY\"" +
                "        ]"+
                "    }"+
                "}";
        String url = WxEndpoint.get("url.card.member.activate.form");
        String response = wxClient.post(url, String.format(json,cardId));
        return response;
    }


    public String activeInfoGet(String activateTicke) {
        String json = " {" +
                "\"activate_ticket\" : \"%s\"" +
                "}";
        String url = WxEndpoint.get("url.card.member.attivate.info.get");
        String response = wxClient.post(url, String.format(json,activateTicke));
        return response;
    }


    public String updateUser(String code,String cardId,String point) {
        String json = "{\n" +
                "    \"code\": \"%s\",\n" +
                "     \"card_id\": \"%s\",\n" +
                "    \"bonus\": %s,\n" +
                "    \"notify_optional\": {\n" +
                "        \"is_notify_bonus\": true,\n" +
                "        \"is_notify_balance\": true\n" +
                "    }\n" +
                "}";
        String url = WxEndpoint.get("url.card.member.update.user");
        String response = wxClient.post(url, String.format(json,code,cardId,point));
        return response;
    }


    public String update(String cardId,String title) {
        String json = "{\n" +
                "    \"card_id\": \"%s\",\n" +
                "    \"member_card\": {\n" +
                "        \"base_info\": {\n" +
                "            \"center_title\": \"%s\",\n" +
                "            \"center_url\": \"%s\",\n" +
                "            \"custom_url_sub_title\": \"%s\"\n" +
                "        }\n" +
                "    }\n" +
                "}";
        String url = WxEndpoint.get("url.card.update");
        String response = wxClient.post(url, String.format(json,cardId,"查询余额","http://wx.xiya3333.com/xiya/weixin/mp/member/money",title));
        return response;
    }


    public String updateUserImg(String code,String cardId,String bgimg) {
        String json = "{\n" +
                "    \"code\": \"%s\",\n" +
                "     \"card_id\": \"%s\",\n" +
                "    \"background_pic_url\": \"%s\",\n" +
                "    \"notify_optional\": {\n" +
                "        \"is_notify_bonus\": true\n" +
                "    }\n" +
                "}";
        String url = WxEndpoint.get("url.card.member.update.user");
        String response = wxClient.post(url, String.format(json,code,cardId,bgimg));
        return response;
    }


    /**
     * 设置测试使用的白名单
     *
     * @param openIds
     * @param userNames
     */
    public void setWhiteList(List<String> openIds, List<String> userNames) {
        Map<String, Object> request = new HashMap<>();
        if (openIds != null && !openIds.isEmpty()) {
            request.put("openid", openIds);
        }
        if (userNames != null && !userNames.isEmpty()) {
            request.put("username", userNames);
        }

        String url = WxEndpoint.get("url.card.test.whitelist");
        String json = JsonMapper.defaultMapper().toJson(request);
        logger.debug("set test white list: {}", json);
        wxClient.post(url, json);
    }

    /**
     * 根据card id获取图文消息里面的content字段
     *
     * @param cardId
     * @return
     */
    public  String getContentByCardId(String cardId) {
        Map<String, Object> request = new HashMap<>();
        request.put("card_id", cardId);

        String url = WxEndpoint.get("url.card.mpnews.gethtml");
        String json = JsonMapper.defaultMapper().toJson(request);
        logger.debug("get mpnews content by card id: {}", json);
        String response = wxClient.post(url, json);
        Map<String, Object> result = JsonMapper.defaultMapper().json2Map(response);
        if (result.containsKey("content")) {
            return result.get("content").toString();
        } else {
            throw new WxRuntimeException(999, "get html content by card id failed.");
        }
    }

    /**
     * 获取卡券背景颜色列表
     *
     * @return
     */
    public List<Color> listColors() {
        String url = WxEndpoint.get("url.card.colors.get");
        logger.debug("list colors.");
        String response = wxClient.get(url);
        ColorWrapper colorWrapper = JsonMapper.defaultMapper().fromJson(response, ColorWrapper.class);
        return colorWrapper.getColors();
    }

    public static class ColorWrapper {

        private List<Color> colors;

        public List<Color> getColors() {
            return colors;
        }

        public void setColors(List<Color> colors) {
            this.colors = colors;
        }
    }

    public static class CardWrapper {

        private Card card;

        public CardWrapper() {
        }

        public CardWrapper(Card card) {
            this.card = card;
        }

        public Card getCard() {
            return card;
        }

        public void setCard(Card card) {
            this.card = card;
        }

    }

}
