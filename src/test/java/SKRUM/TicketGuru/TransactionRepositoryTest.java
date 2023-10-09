package SKRUM.TicketGuru;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import SKRUM.TicketGuru.domain.*;

@DataJpaTest
public class TransactionRepositoryTest {

    @Autowired
    private TransactionRepository trRepo;

    // Luo uusi transaction ja tarkista, saako se ID:n
    @Test
    public void createNewTransaction() {
        Transaction transaction = new Transaction();
        Transaction savedTransaction = trRepo.save(transaction);

        assertThat(savedTransaction.getId()).isNotNull();
    }

    // Luo transaction, laske transactioneiden lukumäärä, poista transaction ja varmista lukumäärän muutos
    @Test
    @Transactional
    public void deleteTransaction() {
        Transaction transaction = trRepo.save(new Transaction());
        Long transactionId = transaction.getId();
        
        long initialTransactionCount = trRepo.count();

        trRepo.deleteById(transactionId);

        long newTransactionCount = trRepo.count();

        assertThat(initialTransactionCount).isEqualTo(newTransactionCount + 1);
    }

    // Luo transaction, etsi se ID:n perusteella ja tarkista löydetyn transactionin tiedot
    @Test
    public void createAndFindTransactionById() {
        Transaction transaction = trRepo.save(new Transaction());
        Long transactionId = transaction.getId();

        Transaction foundTransaction = trRepo.findById(transactionId).orElse(null);

        assertThat(foundTransaction).isNotNull();
        assertThat(foundTransaction.getId()).isEqualTo(transaction.getId());
    }
}