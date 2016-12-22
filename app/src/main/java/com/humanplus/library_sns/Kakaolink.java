package com.humanplus.library_sns;


import android.content.Context;
import android.widget.Toast;

import com.kakao.kakaolink.AppActionBuilder;
import com.kakao.kakaolink.AppActionInfoBuilder;
import com.kakao.kakaolink.KakaoLink;
import com.kakao.kakaolink.KakaoTalkLinkMessageBuilder;
import com.kakao.util.KakaoParameterException;

/**
 * You have to register the permission at your own manifests.
 * android.permissions.INTERNET
 *
 * Add codes below in intent-filter
 * <data android:scheme="@string/kakao_scheme"
 *      android:host="@string/kakaolink_host"/>
 *
 * And add meta-data
 * <meta-data
 *      android:name="com.kakao.sdk.Appkey"
 *      android:value="@string/kakao_app_key"/>
 *
 * It is easier if you register these keys to strings.xml.
 * For details, visit developers.kakao.com
 */

public class Kakaolink {
    private Context context;
    private KakaoLink kakaoLink;
    private KakaoTalkLinkMessageBuilder kakaoTalkLinkMessageBuilder;

    // Default constructor.
    // The only param is 'Context'.
    Kakaolink(Context context) {
        this.context = context;

        try {
            kakaoLink = KakaoLink.getKakaoLink(this.context);
        } catch (KakaoParameterException e) {
            Toast.makeText(context, e.getMessage().toString(), Toast.LENGTH_SHORT);
        }
        kakaoTalkLinkMessageBuilder = kakaoLink.createKakaoTalkLinkMessageBuilder();
    }

    // Adding Image.
    public void addImage(String Imageurl, int width, int height) {
        try {
            kakaoTalkLinkMessageBuilder.addImage(Imageurl, width, height);
        } catch (KakaoParameterException e) {
            Toast.makeText(context, e.getMessage().toString(), Toast.LENGTH_SHORT);
        }
    }


    // Adding Text.
    public void addText(String text) {
        try {
            kakaoTalkLinkMessageBuilder.addText(text);
        } catch (KakaoParameterException e) {
            Toast.makeText(context, e.getMessage().toString(), Toast.LENGTH_SHORT);
        }
    }


    // Adding AppButton.
    // For Paramkey, you can just put numbers. e.g) 1111, 2222
    public void addAppButton(String ActionBtnText, String LinkUrl, String Paramkey) {
        String ParamKey = "execparamkey2=" + Paramkey;
        String MarketParam = "referrer=kakaotalklink";
        try {
            kakaoTalkLinkMessageBuilder.addAppButton(ActionBtnText, new AppActionBuilder()
                    .addActionInfo(AppActionInfoBuilder.
                            createAndroidActionInfoBuilder().setExecuteParam(ParamKey).setMarketParam(MarketParam).build())
                    .addActionInfo(AppActionInfoBuilder.
                            createiOSActionInfoBuilder(AppActionBuilder.DEVICE_TYPE.PHONE).setExecuteParam(MarketParam).setExecuteParam(ParamKey).build())
                    .setUrl(LinkUrl).build());
        } catch (KakaoParameterException e) {
            Toast.makeText(context, e.getMessage().toString(), Toast.LENGTH_SHORT);
        }
    }

    // Send a message.
    // To send message, you do not need to add all properties
    // Nor can add multiple same properties.
    public void sendMessage() {
        kakaoTalkLinkMessageBuilder.setForwardable(true);
        try {
            kakaoLink.sendMessage(kakaoTalkLinkMessageBuilder, this.context);
        } catch (KakaoParameterException e) {
            Toast.makeText(context, e.getMessage().toString(), Toast.LENGTH_SHORT);
        }
    }
}
