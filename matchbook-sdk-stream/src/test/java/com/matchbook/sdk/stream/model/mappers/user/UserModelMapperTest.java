package com.matchbook.sdk.stream.model.mappers.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import com.matchbook.sdk.rest.dtos.prices.Currency;
import com.matchbook.sdk.rest.dtos.user.Account;
import com.matchbook.sdk.rest.dtos.user.Login;
import com.matchbook.sdk.stream.model.dataobjects.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserModelMapperTest {

    private static final String SESSION_TOKEN = "736dhhfdy";
    private static final Long USER_ID = 12345L;
    private static final Long ACCOUNT_ID = 6372L;
    private static final String ACCOUNT_USERNAME = "myusername";

    private static final BigDecimal ACCOUNT_BALANCE = new BigDecimal("1500");
    private static final BigDecimal ACCOUNT_EXPOSURE = new BigDecimal("100");
    private static final BigDecimal ACCOUNT_FREE_FUNDS = new BigDecimal("1400");
    private static final BigDecimal ACCOUNT_COMMISSION_RESERVE = new BigDecimal("0");

    private UserModelMapper unit;

    @BeforeEach
    void setUp() {
        this.unit = new UserModelMapper();
    }

    @Test
    void mapLoginTest() {
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

        User user = unit.mapLogin(login);

        assertThat(user).isNotNull();
        assertThat(user.getSessionToken()).isEqualTo(SESSION_TOKEN);
        assertThat(user.getUserId()).isEqualTo(USER_ID);
        assertThat(user.getAccount()).isNotNull();
        com.matchbook.sdk.stream.model.dataobjects.user.Account accountModel = user.getAccount();
        assertThat(accountModel.getId()).isEqualTo(ACCOUNT_ID);
        assertThat(accountModel.getUsername()).isEqualTo(ACCOUNT_USERNAME);

        assertThat(accountModel.getBalance().getBalance()).isEqualTo(ACCOUNT_BALANCE);
        assertThat(accountModel.getBalance().getExposure()).isEqualTo(ACCOUNT_EXPOSURE);
        assertThat(accountModel.getBalance().getFreeFunds()).isEqualTo(ACCOUNT_FREE_FUNDS);
        assertThat(accountModel.getBalance().getCommissionReserve()).isEqualTo(ACCOUNT_COMMISSION_RESERVE);

        assertThat(accountModel.getCurrency().toString()).isEqualTo(Currency.CAD.toString());
    }

    @Test
    void mapLoginTest_NullValues() {
        Login login = mock(Login.class);
        when(login.getUserId()).thenReturn(null);

        Account account = mock(Account.class);
        when(account.getId()).thenReturn(null);
        when(login.getAccount()).thenReturn(account);

        User user = unit.mapLogin(login);

        assertThat(user).isNotNull();
        assertThat(user.getSessionToken()).isNull();
        assertThat(user.getUserId()).isNull();
        assertThat(user.getAccount()).isNotNull();
        com.matchbook.sdk.stream.model.dataobjects.user.Account accountModel = user.getAccount();
        assertThat(accountModel.getId()).isNull();
        assertThat(accountModel.getUsername()).isNull();
        assertThat(accountModel.getBalance().getBalance()).isNull();
        assertThat(accountModel.getBalance().getExposure()).isNull();
        assertThat(accountModel.getBalance().getFreeFunds()).isNull();
        assertThat(accountModel.getBalance().getCommissionReserve()).isNull();
        assertThat(accountModel.getCurrency()).isNull();
    }

}
