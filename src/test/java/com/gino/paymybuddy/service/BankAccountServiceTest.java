package com.gino.paymybuddy.service;

import com.gino.paymybuddy.model.Account;
import com.gino.paymybuddy.model.User;
import com.gino.paymybuddy.repository.BankAccountRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

/**
 * The type Bank account service test.
 */
@ExtendWith(MockitoExtension.class)
public class BankAccountServiceTest {

  @InjectMocks
  private BankAccountServiceImpl bankAccountService;

  @Mock
  private BankAccountRepository bankAccountRepository;
  @Mock
  private UserService userService;

  private Account account;
  private List<Account> accountList = new ArrayList<>();
  private User user;

  /**
   * Sets up.
   */
  @BeforeEach
  void setUp() {
    account = new Account(1, 12345, "test", "testtest", 1000);
    user = new User("userTest", "userPassword", "user@gmail.com", 100);
  }

  /**
   * Find by id test.
   */
  @Test
  public void findByIdTest() {
    when(bankAccountRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(account));

    Optional<Account> accountLocal = bankAccountService.findById(1);
    Account accountTest = new Account();
    if (accountLocal.isPresent()) {
      accountTest = accountLocal.get();
    }

    assertThat(accountTest.getAccountnumber()).isEqualTo("testtest");
    Mockito.verify(bankAccountRepository, Mockito.times(1)).findById(Mockito.anyInt());
  }

  /**
   * Insert test.
   */
  @Test
  public void insertTest() {
    when(bankAccountRepository.save(Mockito.any(Account.class))).thenReturn(account);
    Account accountTest = bankAccountService.insert(account);

    assertThat(accountTest.getIdAccount()).isNotNull();
    assertThat(accountTest.getAccountnumber()).isEqualTo("testtest");
  }

  /**
   * Delete test.
   */
  @Test
  public void deleteTest() {
    ArgumentCaptor<Account> argumentCaptor = ArgumentCaptor.forClass(Account.class);
    when(bankAccountRepository.findById(anyInt())).thenReturn(Optional.ofNullable(account));

      bankAccountService.delete(account.getIdAccount());
      Mockito.verify(bankAccountRepository, Mockito.times(1)).delete(argumentCaptor.capture());

  }

  /**
   * Update test.
   */
  @Test
  public void updateTest() {
    when(bankAccountRepository.findById(anyInt())).thenReturn(
        Optional.ofNullable(account));
    Account accountLocal = account;
      accountLocal.setAccountnumber("nouveauTest");
      bankAccountService.update(account.getIdAccount(), accountLocal);
      assertThat(account.getAccountnumber()).isEqualTo("nouveauTest");

  }

  /**
   * Update test when account does not exist.
   */
  @Test
  public void updateTest_whenAccountDoesNotExist() {
    when(bankAccountRepository.findById(anyInt())).thenReturn(
        Optional.empty());
    Account accountLocal = bankAccountService.update(account.getIdAccount(), account);
    assertThat(accountLocal).isNull();

  }

  /**
   * Find all by user id test.
   */
  @Test
  public void findAllByUserIdTest() {
    Account accountLocal = new Account(54321,"test2", "test2", 2000);
    accountList.add(accountLocal);
    accountList.add(account);
    when(bankAccountRepository.findAllByUser_IdUser(1)).thenReturn(accountList);

    List<Account> accountsLocal = bankAccountService.findAllByUserId(1);

    assertThat(accountsLocal.size()).isEqualTo(accountList.size());
    assertThat(accountsLocal.get(1).getAccountnumber()).isEqualTo(accountList.get(1).getAccountnumber());
  }

  /**
   * Transfer to user account test.
   *
   * @throws Exception the exception
   */
  @Test
  public void transferToUserAccountTest() throws Exception {
    int idUser = user.getIdUser();
    double amountToTransfer = 50;
    double userAmountExpected = user.getAccountBalance() + amountToTransfer;
    double accountAmountExpected = account.getAmount() - amountToTransfer;

    when(bankAccountRepository.findById(1)).thenReturn(Optional.of(account));
    when(userService.findById(idUser)).thenReturn(Optional.of(user));

    bankAccountService.transferToUserAccount(idUser,account.getIdAccount(), amountToTransfer);

    assertThat(user.getAccountBalance()).isEqualTo(userAmountExpected);
    assertThat(account.getAmount()).isEqualTo(accountAmountExpected);
  }


  /**
   * Transfer to bank account test.
   *
   * @throws Exception the exception
   */
  @Test
  public void transferToBankAccountTest() throws Exception {
    int idUser = user.getIdUser();
    double amountToTransfer = 50;
    double userAmountExpected = user.getAccountBalance() - amountToTransfer;
    double accountAmountExpected = account.getAmount() + amountToTransfer;

    when(bankAccountRepository.findById(1)).thenReturn(Optional.of(account));
    when(userService.findById(idUser)).thenReturn(Optional.of(user));

    bankAccountService.transferToBankAccount(idUser,account.getIdAccount(), amountToTransfer);
    assertThat(user.getAccountBalance()).isEqualTo(userAmountExpected);
    assertThat(account.getAmount()).isEqualTo(accountAmountExpected);
  }

  /**
   * Transfer to user account when sold is superior account amount test.
   */
  @Test
  public void transferToUserAccount_whenSoldIsSuperiorAccountAmountTest() {

    int idUser = user.getIdUser();
    double amountToTransfer = account.getAmount() + 50;

    when(bankAccountRepository.findById(1)).thenReturn(Optional.of(account));
    when(userService.findById(idUser)).thenReturn(Optional.of(user));


    assertThrows(Exception.class, () -> bankAccountService.transferToUserAccount(idUser, account.getIdAccount(), amountToTransfer));
  }

  /**
   * Transfer to bank account when sold is superior account amount test.
   */
  @Test
  public void transferToBankAccount_whenSoldIsSuperiorAccountAmountTest() {

    int idUser = user.getIdUser();
    double amountToTransfer = user.getAccountBalance() + 50;

    when(bankAccountRepository.findById(1)).thenReturn(Optional.of(account));
    when(userService.findById(idUser)).thenReturn(Optional.of(user));


    assertThrows(Exception.class, () -> bankAccountService.transferToBankAccount(idUser, account.getIdAccount(), amountToTransfer));
  }
}
