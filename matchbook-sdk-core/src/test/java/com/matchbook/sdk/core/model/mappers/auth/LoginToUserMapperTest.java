package com.matchbook.sdk.core.model.mappers.auth;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.Test;

import com.matchbook.sdk.core.clients.rest.dtos.prices.Currency;
import com.matchbook.sdk.core.clients.rest.dtos.user.Account;
import com.matchbook.sdk.core.clients.rest.dtos.user.Login;
import com.matchbook.sdk.core.model.dataobjects.auth.User;

public class LoginToUserMapperTest {

    private static final String SESSION_TOKEN = "736dhhfdy";
    private static final Long USER_ID = 12345L;
    private static final Long ACCOUNT_ID = 6372L;
    private static final String ACCOUNT_USERNAME = "myusername";
    private static final BigDecimal ACCOUNT_BALANCE = new BigDecimal("1500");
    private static final BigDecimal ACCOUNT_EXPOSURE = new BigDecimal("100");
    private static final BigDecimal ACCOUNT_FREE_FUNDS = new BigDecimal("1400");
    private static final BigDecimal ACCOUNT_COMMISSION_RESERVE = new BigDecimal("0");

    private final LoginToUserMapper loginToUserMapper = new LoginToUserMapper();

    @Test
    public void mapToModel() {
        Login login = mock(Login.class);
        when(login.getSessionToken()).thenReturn(SESSION_TOKEN);
        when(login.getUserId()).thenReturn(USER_ID);

        Account account = mock(Account.class);
        when(account.getId()).thenReturn(ACCOUNT_ID);
        when(account.getUsername()).thenReturn(ACCOUNT_USERNAME);
        when(account.getBalance()).thenReturn(ACCOUNT_BALANCE);
        when(account.getExposure()).thenReturn(ACCOUNT_EXPOSURE);
        when(account.getFreeFunds()).thenReturn(ACCOUNT_FREE_FUNDS);
        when(account.getCommissionReserve()).thenReturn(ACCOUNT_COMMISSION_RESERVE);
        when(account.getCurrency()).thenReturn(Currency.CAD);
        when(login.getAccount()).thenReturn(account);

        User user = loginToUserMapper.mapToModel(login);

        assertThat(user).isNotNull();
        assertThat(user.getSessionToken()).isEqualTo(SESSION_TOKEN);
        assertThat(user.getUserId()).isEqualTo(USER_ID);
        assertThat(user.getAccount()).isNotNull();
        com.matchbook.sdk.core.model.dataobjects.auth.Account accountModel = user.getAccount();
        assertThat(accountModel.getId()).isEqualTo(ACCOUNT_ID);
        assertThat(accountModel.getUsername()).isEqualTo(ACCOUNT_USERNAME);
        assertThat(accountModel.getBalance()).isEqualTo(ACCOUNT_BALANCE);
        assertThat(accountModel.getExposure()).isEqualTo(ACCOUNT_EXPOSURE);
        assertThat(accountModel.getFreeFunds()).isEqualTo(ACCOUNT_FREE_FUNDS);
        assertThat(accountModel.getCommissionReserve()).isEqualTo(ACCOUNT_COMMISSION_RESERVE);
        assertThat(accountModel.getCurrency().toString()).isEqualTo(Currency.CAD.toString());
    }

    @Test
    public void mapToModel_NullValues() {
        Login login = mock(Login.class);
        when(login.getUserId()).thenReturn(null);

        Account account = mock(Account.class);
        when(account.getId()).thenReturn(null);
        when(login.getAccount()).thenReturn(account);

        User user = loginToUserMapper.mapToModel(login);

        assertThat(user).isNotNull();
        assertThat(user.getSessionToken()).isNull();
        assertThat(user.getUserId()).isNull();
        assertThat(user.getAccount()).isNotNull();
        com.matchbook.sdk.core.model.dataobjects.auth.Account accountModel = user.getAccount();
        assertThat(accountModel.getId()).isNull();
        assertThat(accountModel.getUsername()).isNull();
        assertThat(accountModel.getBalance()).isNull();
        assertThat(accountModel.getExposure()).isNull();
        assertThat(accountModel.getFreeFunds()).isNull();
        assertThat(accountModel.getCommissionReserve()).isNull();
        assertThat(accountModel.getCurrency()).isNull();
    }
}