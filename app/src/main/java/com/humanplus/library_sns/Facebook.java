package com.humanplus.library_sns;

import android.app.Activity;
import android.net.Uri;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;

/**
 * You have to register the permission at your own manifests.
 * android.permissions.INTERNET
 *
 * And register
 * <provider android:authorities="YOUR FACEBOOK KEY"
 * android:name="com.facebook.FacebookContentProvider"
 * android:exported="true" />
 *
 * Also for meta data.
 * <meta-data
 *          android:name="com.facebook.sdk.ApplicationId"
 *          android:value="YOUR KEY" />
 *
 * <meta-data
 *          android:name="com.facebook.sdk.ApplicationName"
 *          android:value="your application name" />
 *
 * It is easier if you register these keys to strings.xml.
 * For details, visit developers.facebook.com
 */

public class Facebook {
    private ShareLinkContent shareLinkContent;
    private ShareDialog shareDialog;
    private Activity activity;

    public Facebook() {

    }

    // You should call this method at MainActivity's onCreate
    // @1st param: MainActivity's context
    public void initialize(Activity activity) {
        FacebookSdk.sdkInitialize(activity);
        AppEventsLogger.activateApp(activity);
        this.activity = activity;
    }

    // And then, set the content of links.
    // @1st param: Url of your application.
    // @2nd param: Title of share of Facebook
    // @3rd param: The url of an image that will be shown at user's timeline
    // @4th param: The text which will be shown at user's timeline
    public ShareLinkContent createLinkContent(String Url, String title, String ImageUrl
            , String Description) {
        shareLinkContent = new ShareLinkContent.Builder()
                .setContentUrl(Uri.parse(Url))
                .setContentTitle(title)
                .setImageUrl(Uri.parse(ImageUrl))
                .setContentDescription(Description)
                .build();

        return shareLinkContent;
    }

    // Last, pop up sharing activity with default android web view
    // Typically, you can put 'this' as parameter
    // Had better to put this method on Listener
    public void PopShare() {
        shareDialog = new ShareDialog(this.activity);
        shareDialog.show(shareLinkContent, ShareDialog.Mode.AUTOMATIC);
    }

}
