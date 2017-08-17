package com.fobid.retrica.models;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by android01 on 2017. 8. 17..
 */

public class GitHubUser extends RealmObject {

    @SerializedName("login")
    public String login;
    @PrimaryKey
    @SerializedName("id")
    public int id;
    @SerializedName("avatar_url")
    public String avatar_url;
    @SerializedName("gravatar_id")
    public String gravatar_id;
    @SerializedName("url")
    public String url;
    @SerializedName("html_url")
    public String html_url;
    @SerializedName("followers_url")
    public String followers_url;
    @SerializedName("following_url")
    public String following_url;
    @SerializedName("gists_url")
    public String gists_url;
    @SerializedName("starred_url")
    public String starred_url;
    @SerializedName("subscriptions_url")
    public String subscriptions_url;
    @SerializedName("organizations_url")
    public String organizations_url;
    @SerializedName("repos_url")
    public String repos_url;
    @SerializedName("events_url")
    public String events_url;
    @SerializedName("received_events_url")
    public String received_events_url;
    @SerializedName("type")
    public String type;
    @SerializedName("site_admin")
    public boolean site_admin;
}
