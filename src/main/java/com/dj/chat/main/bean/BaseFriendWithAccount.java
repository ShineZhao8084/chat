package com.dj.chat.main.bean;

import java.io.Serializable;
import java.util.Objects;

/**
 * base_friend
 * @author 
 */
public class BaseFriendWithAccount extends BaseFriend implements Serializable {

    private static final long serialVersionUID = 1L;

    private BaseAccount baseAccount;

    public BaseAccount getBaseAccount() {
        return baseAccount;
    }

    public void setBaseAccount(BaseAccount baseAccount) {
        this.baseAccount = baseAccount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaseFriendWithAccount)) return false;
        if (!super.equals(o)) return false;
        BaseFriendWithAccount that = (BaseFriendWithAccount) o;
        return Objects.equals(baseAccount, that.baseAccount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), baseAccount);
    }
}
