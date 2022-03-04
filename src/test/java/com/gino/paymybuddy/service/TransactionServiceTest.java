package com.gino.paymybuddy.service;

import com.gino.paymybuddy.dto.TransactionDTO;
import com.gino.paymybuddy.model.Account;
import com.gino.paymybuddy.model.Commission;
import com.gino.paymybuddy.model.Enterprise;
import com.gino.paymybuddy.model.Transaction;
import com.gino.paymybuddy.model.User;
import com.gino.paymybuddy.repository.TransactionRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {

  @InjectMocks
  private TransactionServiceImpl transactionService;

  @Mock
  private TransactionRepository transactionRepository;
  @Mock
  private UserService userService;
  @Mock
  private EnterpriseService enterpriseService;
  @Mock
  private CommissionService commissionService;
  @Mock
  private BankAccountService bankAccountService;

  private static Transaction transaction1Emit;
  private static Transaction transaction2Emit;
  private static Transaction transaction1Receiver;
  private static Transaction transaction2Receiver;
  private static List<Transaction> transactionListEmitter = new ArrayList<>();
  private static List<Transaction> transactionListReceiver = new ArrayList<>();
  private static List<Transaction> transactionList = new ArrayList<>();
  private static TransactionDTO transactionDTOEmit1;
  private static TransactionDTO transactionDTOEmit2;
  private static User user1;
  private static User user2;
  private static Enterprise enterprise;
  private static Commission commission;
  private static Account account;

  @BeforeEach
  void setUp() {
    user1 = new User(1,"username1", "password", "username1@gmail.com", 50);
    user2 = new User(2,"username2", "password", "username2@gmail.com", 150);
    transaction1Emit = new Transaction("descriptionEmit1", 1, user1, user2);
    transaction2Emit = new Transaction("descriptionEmit2", 1, user1, user2);


    transactionDTOEmit1 = new TransactionDTO(user2.getUsername(), transaction1Emit.getDescription(), transaction1Emit.getAmount());
    transactionDTOEmit2 = new TransactionDTO(user2.getUsername(), transaction2Emit.getDescription(), transaction2Emit.getAmount());

    transaction1Receiver = new Transaction("descriptionReceiver1", 1, user2, user1);
    transaction2Receiver = new Transaction("descriptionReceiver2", 1, user2, user1);
    commission = new Commission(0.5, 0.5);
    enterprise = new Enterprise("name", "siret");
    account = new Account(1, 12345, "test", "testtest", 1000);
    transactionListEmitter.add(transaction1Emit);
    transactionListEmitter.add(transaction2Emit);
    transactionListReceiver.add(transaction1Receiver);
    transactionListReceiver.add(transaction2Receiver);
    transactionList.addAll(transactionListReceiver);
    transactionList.addAll(transactionListEmitter);
  }

  @Test
  public void findByIdTest() {
    when(transactionRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(transaction1Emit));

    Optional<Transaction> transactionLocal = transactionService.findById(1);
    Transaction transactionLocal1 = new Transaction();
    if (transactionLocal.isPresent()) {
      transactionLocal1 = transactionLocal.get();
    }

    assertThat(transactionLocal1.getDescription()).isEqualTo("descriptionEmit1");
    Mockito.verify(transactionRepository, Mockito.times(1)).findById(Mockito.anyInt());
  }

  @Test
  public void insertTest() {
    when(transactionRepository.save(Mockito.any(Transaction.class))).thenReturn(transaction1Emit);
    Transaction transactionLocal = transactionService.insert(transaction1Emit);

    assertThat(transactionLocal.getIdTransaction()).isNotNull();
    assertThat(transactionLocal.getDescription()).isEqualTo("descriptionEmit1");
  }

  @Test
  public void findAllByEmitterIdTest() {
    Pageable paging = PageRequest.of(0, 2);
    Page<Transaction> pro = new PageImpl<Transaction>(transactionListEmitter);
    when(transactionRepository.findAllByEmitter_IdUser(Mockito.anyInt(), Mockito.any())).thenReturn(pro);
    Page<Transaction> transactionPageLocal = transactionService.findAllByEmitterId(1, paging);

    assertThat(transactionPageLocal.getTotalElements()).isEqualTo(2);
    assertThat(transactionPageLocal.get().findFirst().get().getDescription()).isEqualTo("descriptionEmit1");
  }

  @Test
  public void findAllByReceiverIdTest() {
    Pageable paging = PageRequest.of(0, 2);
    Page<Transaction> pro = new PageImpl<Transaction>(transactionListReceiver);
    when(transactionRepository.findAllByReceiver_IdUser(Mockito.anyInt(), Mockito.any())).thenReturn(pro);
    Page<Transaction> transactionPageLocal = transactionService.findAllByReceiverId(2, paging);

    assertThat(transactionPageLocal.getTotalElements()).isEqualTo(2);
    assertThat(transactionPageLocal.get().findFirst().get().getDescription()).isEqualTo("descriptionReceiver1");
  }

  @Test
  public void findAllTest() {
    when(transactionRepository.findAll()).thenReturn(transactionList);

    List<Transaction> transactionListLocal = transactionService.findAll();

    assertThat(transactionListLocal.size()).isEqualTo(transactionList.size());
  }

  @Test
  public void createTransactionTest() {

  }
}
