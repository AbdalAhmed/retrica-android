package com.fobid.retrica.ui.viewholders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.fobid.retrica.R;
import com.fobid.retrica.models.GitHubUser;
import com.github.fobid.linkabletext.view.OnLinkClickListener;
import com.github.fobid.linkabletext.widget.LinkableTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by android01 on 2017. 8. 17..
 */

public class GitHubViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.login)
    TextView loginTextView;
    @BindView(R.id.avatar)
    CircleImageView avatarImageView;
    @BindView(R.id.github_url)
    LinkableTextView githubUrlTextView;

    public Delegate delegate;

    public interface Delegate {
        void gitHugViewHolderUrlClick(@NonNull GitHubViewHolder viewHolder, @NonNull String url);
    }

    public GitHubViewHolder(final @NonNull View itemView, final @NonNull Delegate delegate) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.delegate = delegate;
    }

    public void bindData(final @NonNull GitHubUser item) {
        loginTextView.setText(item.login);
        githubUrlTextView.setText(item.html_url);

        githubUrlTextView.setOnLinkClickListener(new OnLinkClickListener() {
            @Override
            public void onHashtagClick(String hashtag) {

            }

            @Override
            public void onMentionClick(String mention) {

            }

            @Override
            public void onEmailAddressClick(String email) {

            }

            @Override
            public void onWebUrlClick(String url) {
                delegate.gitHugViewHolderUrlClick(GitHubViewHolder.this, url);
            }

            @Override
            public void onPhoneClick(String phone) {

            }
        });

    }
}
